package features.book;

import repository.BookRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

public class Library implements LibraryInt{
    private final BookRepository repository;
    private final List<Book> books;
    private final List<BookCopy> copies;

    public Library(BookRepository repository) {
        this.repository = repository;
        this.books = new ArrayList<>(repository.loadBookList());
        this.copies = new ArrayList<>(repository.loadBookCopiesList());
    }

    @Override
    public void addBook(Book book, int numberOfCopies) {
        Optional<Book> existingBookOpt = books.stream()
                .filter(b -> b.getId().equals(book.getId()))
                .findFirst();

        if (existingBookOpt.isPresent()) {
            Book existingBook = existingBookOpt.get();
            existingBook.setTotalCopies(existingBook.getTotalCopies() + numberOfCopies);
            existingBook.setAvailableCopies(existingBook.getAvailableCopies() + numberOfCopies);
        } else {
            book.setAvailableCopies(numberOfCopies);
            book.setTotalCopies(numberOfCopies);
            books.add(book);
        }

        for (int i = 0; i < numberOfCopies; i++) {
            String copyId = UUID.randomUUID().toString();
            BookCopy copy = new BookCopy(book.getId(), copyId);
            copies.add(copy);
        }

        save();
    }


    @Override
    public List<Book> getAllBooks() {
        return books.stream()
                .sorted((a, b) -> a.getTitle().compareToIgnoreCase(b.getTitle()))
                .collect(Collectors.toList());
    }


    @Override
    public boolean returnBookCopie(BookCopy copy) {
        if (!copy.isAvailable()) {
            copy.setAvailable(true);
            books.stream()
                    .filter(book -> book.getId().equals(copy.getBookId()))
                    .findFirst()
                    .ifPresent(Book::returnBook);
            save();
            return true;
        }
        return false;
    }

    public Optional<BookCopy> getBookCopyById(String copyId) {
        return copies.stream()
                .filter(copy -> copy.getBookCopieId().equals(copyId))
                .findFirst();
    }
    @Override
    public boolean borrowBookCopy(BookCopy bookCopy) {
        if (bookCopy.isAvailable()) {
            bookCopy.setAvailable(false);


            books.stream()
                    .filter(book -> book.getId().equals(bookCopy.getBookId()))
                    .findFirst()
                    .ifPresent(Book::borrowBook);

            save();
            return true;
        }
        return false;
    }

    @Override
    public List<BookCopy> getCopiesByBookId(String bookId) {
        return copies.stream()
                .filter(copy -> copy.getBookId().equals(bookId))
                .toList();
    }

    private void save () {
        repository.saveBooksList(books);
        repository.saveCopiesList(copies);
    }
}
