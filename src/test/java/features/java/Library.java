package features.java;

import features.book.Book;
import features.book.BookCopy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repository.MemoryRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class Library {

    private features.book.Library library;
    private Book testBook;

    @BeforeEach
    void setUp() {
        library = new features.book.Library(new MemoryRepository());
        testBook = new Book("Test Book", "Author", 2023, 2);
        library.addBook(testBook, 2);
    }

    @Test
    void borrowBookCopy_whenCopyIsAvailable() {
        BookCopy copy = library.getCopiesByBookId(testBook.getId()).get(0);

        boolean result = library.borrowBookCopy(copy);

        assertTrue(result);
        assertFalse(copy.isAvailable());
        assertEquals(1, testBook.getAvailableCopies());
    }

    @Test
    void borrowBookCopy_whenCopyIsNotAvailable() {
        BookCopy copy = library.getCopiesByBookId(testBook.getId()).get(0);
        library.borrowBookCopy(copy);

        boolean result = library.borrowBookCopy(copy);

        assertFalse(result);
    }

    @Test
    void returnBookCopie_whenCopyIsBorrowed() {
        BookCopy copy = library.getCopiesByBookId(testBook.getId()).get(0);
        library.borrowBookCopy(copy);

        boolean result = library.returnBookCopie(copy);

        assertTrue(result);
        assertTrue(copy.isAvailable());
        assertEquals(2, testBook.getAvailableCopies());
    }

    @Test
    void returnBookCopie_whenCopyIsAlreadyAvailable() {
        BookCopy copy = library.getCopiesByBookId(testBook.getId()).get(0);

        boolean result = library.returnBookCopie(copy);

        assertFalse(result);
    }

    @Test
    void getBookCopyById_shouldReturnCorrectCopy() {
        BookCopy copy = library.getCopiesByBookId(testBook.getId()).get(0);

        Optional<BookCopy> found = library.getBookCopyById(copy.getBookCopieId());

        assertTrue(found.isPresent());
        assertEquals(copy.getBookCopieId(), found.get().getBookCopieId());
    }

    @Test
    void getCopiesByBookId_shouldReturnCorrectList() {
        List<BookCopy> copies = library.getCopiesByBookId(testBook.getId());

        assertEquals(2, copies.size());
        for (BookCopy c : copies) {
            assertEquals(testBook.getId(), c.getBookId());
        }
    }
}
