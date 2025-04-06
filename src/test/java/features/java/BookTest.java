package features.java;

import features.book.Book;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BookTest {

    @Test
    void borrowBook_DecreaseAvailableValue() {
        Book book = new Book("Title", "Author", 2024, 2);

        boolean result = book.borrowBook();

        assertTrue(result);
        assertEquals(1, book.getAvailableCopies());
    }

    @Test
    void borrowBook_whenNoCopiesAvailable() {
        Book book = new Book("Title", "Author", 2024, 1);
        book.borrowBook(); // now 0 left

        boolean result = book.borrowBook();

        assertFalse(result);
        assertEquals(0, book.getAvailableCopies());
    }

    @Test
    void returnBook_shouldIncreaseAvailable() {
        Book book = new Book("Title", "Author", 2024, 2);
        book.borrowBook(); // available = 1

        boolean result = book.returnBook();

        assertTrue(result);
        assertEquals(2, book.getAvailableCopies());
    }

    @Test
    void returnBook_whenAllCopiesAvailable() {
        Book book = new Book("Title", "Author", 2024, 2);

        boolean result = book.returnBook();

        assertFalse(result);
        assertEquals(2, book.getAvailableCopies());
    }
}