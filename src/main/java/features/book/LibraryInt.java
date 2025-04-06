package features.book;

import java.util.List;
import java.util.Optional;

public interface LibraryInt {

    void addBook(Book book, int numberOfCopies);
    List<Book> getAllBooks();
    boolean borrowBookCopy(BookCopy bookCopy);
    boolean returnBookCopie(BookCopy bookCopy);
    Optional<BookCopy> getBookCopyById(String copyId);
    List<BookCopy> getCopiesByBookId(String bookId);

}
