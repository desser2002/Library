package features.java;

import features.book.Book;
import org.junit.jupiter.api.Test;

import java.time.Year;

import static org.junit.jupiter.api.Assertions.*;

class BookTest {

    @Test
    void borrowBook_DecreaseAvailableValue() {
        Book book = new Book("Title", "Author", 2024, 2);
        book.setAvailableCopies(2);

        boolean result = book.borrowBook();

        assertTrue(result);
        assertEquals(1, book.getAvailableCopies());
    }

    @Test
    void borrowBook_whenNoCopiesAvailable() {
        Book book = new Book("Title", "Author", 2024, 1);
        book.setAvailableCopies(1);
        book.borrowBook();

        boolean result = book.borrowBook();

        assertFalse(result);
        assertEquals(0, book.getAvailableCopies());
    }

    @Test
    void returnBook_shouldIncreaseAvailable() {
        Book book = new Book("Title", "Author", 2024, 2);
        book.setAvailableCopies(1);

        boolean result = book.returnBook();

        assertTrue(result);
        assertEquals(2, book.getAvailableCopies());
    }

    @Test
    void returnBook_whenAllCopiesAvailable() {
        Book book = new Book("Title", "Author", 2024, 2);
        book.setAvailableCopies(2);

        boolean result = book.returnBook();

        assertFalse(result);
        assertEquals(2, book.getAvailableCopies());
    }

    @Test
    void bookConstructor_shouldThrowException_whenYearInvalidLow() {
        assertThrows(IllegalArgumentException.class, () ->
                new Book("Title", "Author", -3000, 2));
    }

    @Test
    void bookConstructor_shouldThrowException_whenYearFuture() {
        int futureYear = Year.now().getValue() + 1;
        assertThrows(IllegalArgumentException.class, () ->
                new Book("Title", "Author", futureYear, 2));
    }

    @Test
    void setYear_shouldUpdate_whenValid() {
        Book book = new Book("Title", "Author", 2022, 1);
        int currentYear = Year.now().getValue();

        book.setYear(currentYear);

        assertEquals(currentYear, book.getYear());
    }

    @Test
    void setYear_shouldThrow_whenInvalid() {
        Book book = new Book("Title", "Author", 2022, 1);

        assertThrows(IllegalArgumentException.class, () -> book.setYear(0));
        assertThrows(IllegalArgumentException.class, () -> book.setYear(Year.now().getValue() + 10));
    }

    @Test
    void bookConstructor_shouldIgnoreInvalidAvailableCopiesInternally() {
        Book book = new Book("Title", "Author", 2023, 2);
        book.setAvailableCopies(-5);

        assertEquals(0, book.getAvailableCopies());
    }
}