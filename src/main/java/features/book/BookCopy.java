package features.book;

import java.util.UUID;

public class BookCopy {
    private String bookCopieId;
    private String bookId;
    private boolean available;


    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public String getBookId() {
        return bookId;
    }

    public String getBookCopieId() {
        return bookCopieId;
    }

    public BookCopy(String bookId) {
        this.bookId = bookId;
        this.bookCopieId = UUID.randomUUID().toString();
        this.available = true;

    }

    public BookCopy(String bookId, String bookItemId) {
        this.bookId = bookId;
        this.bookCopieId = bookItemId;
        this.available = true;
    }

    public BookCopy(){};



}
