package repository;

import features.book.Book;
import features.book.BookCopy;

import java.util.ArrayList;
import java.util.List;


public class MemoryRepository implements BookRepository {
    private List<Book> booksList = new ArrayList<>();
    private List<BookCopy> copiesList = new ArrayList<>();

    @Override
    public List<Book> loadBookList() {
        return new ArrayList<>(booksList);
    }

    @Override
    public List<BookCopy> loadBookCopiesList() {
        return new ArrayList<>(copiesList);
    }

    @Override
    public void saveBooksList(List<Book> books) {
        this.booksList = new ArrayList<>(booksList);
    }

    @Override
    public void saveCopiesList(List<BookCopy> loadBookCopies) {
        this.copiesList = new ArrayList<>(copiesList);
    }
}
