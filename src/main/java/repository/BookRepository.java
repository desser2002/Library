package repository;

import features.book.Book;
import features.book.BookCopy;

import java.util.List;


    public interface BookRepository {
        List<Book> loadBookList();
        List<BookCopy> loadBookCopiesList();

        void saveBooksList(List<Book> books);
        void saveCopiesList(List<BookCopy> loadBookCopies);
    }

