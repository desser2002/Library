package app;

import features.book.Book;
import features.book.BookCopy;
import features.book.Library;
import features.book.LibraryInt;
import repository.BookRepository;
import repository.FileRepository;
import repository.MemoryRepository;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import static app.utils.InputUtils.*;

public class Main {
    public static void main(String[] args) {
                       Scanner scanner = new Scanner(System.in);

        boolean useFileStorage = true;
        BookRepository repository = useFileStorage
                ? new FileRepository("books.json", "copies.json")
                : new MemoryRepository();

        LibraryInt library = new Library(repository);


                while (true) {
                    System.out.println("\n===== LIBRARY MENU =====");
                    System.out.println("1. Show all books");
                    System.out.println("2. Add a new book");
                    System.out.println("3. Borrow a book copy (by copy ID)");
                    System.out.println("4. Return a book copy (by copy ID)");
                    System.out.println("5. List of copies by book ID");
                    System.out.println("0. Exit");
                    System.out.print("Choose an option: ");
                    String choice = scanner.nextLine();

                    switch (choice) {
                        case "1" -> {
                            for (Book book : library.getAllBooks()) {
                                System.out.printf("id:%s %s — %s (%d) | Available: %d of %d\n",
                                        book.getId(), book.getTitle(), book.getAuthor(),
                                        book.getYear(), book.getAvailableCopies(), book.getTotalCopies());
                            }
                        }
                        case "2" -> {
                            System.out.print("Title: ");
                            String title = capitalizeFirst(scanner.nextLine());

                            System.out.print("Author (e.g., John Smith): ");
                            String author = capitalizeEachWord(scanner.nextLine());

                            int year = readYear(scanner, "Year: ");
                            int copies = readInt(scanner, "Number of copies: ");

                            Book book = new Book(title, author, year, copies);
                            library.addBook(book, copies);
                            System.out.println("Book added successfully.");
                        }
                        case "3" -> {
                            System.out.print("Enter copy ID to borrow: ");
                            String copyId = scanner.nextLine();
                            Optional<BookCopy> copyOpt = library.getBookCopyById(copyId);

                            if (copyOpt.isPresent()) {
                                boolean success = library.borrowBookCopy(copyOpt.get());
                                System.out.println(success ? "Book copy successfully borrowed." : "This copy is already borrowed.");
                            } else {
                                System.out.println("Copy not found.");
                            }
                        }
                        case "4" -> {
                            System.out.print("Enter copy ID to return: ");
                            String copyId = scanner.nextLine();
                            Optional<BookCopy> copyOpt = library.getBookCopyById(copyId);

                            if (copyOpt.isPresent()) {
                                boolean success = library.returnBookCopie(copyOpt.get());
                                System.out.println(success ? "Book copy returned." : "This copy was already available.");
                            } else {
                                System.out.println("Copy not found.");
                            }
                        }

                        case "5" ->
                        {
                            System.out.print("Enter book ID to list its copies: ");
                            String bookId = scanner.nextLine();

                            List<BookCopy> bookCopies = library.getCopiesByBookId(bookId);
                            if (bookCopies.isEmpty()) {
                                System.out.println("No copies found for this book ID.");
                            } else {
                                for (BookCopy copy : bookCopies) {
                                    System.out.printf("Copy ID: %s | %s\n", copy.getBookCopieId(),
                                            copy.isAvailable() ? "Available" : "Borrowed");
                                }
                            }
                        }


                        case "0" -> {
                            return;
                        }
                        default -> System.out.println("Invalid option. Please try again.");
                    }
                }
    }
}

