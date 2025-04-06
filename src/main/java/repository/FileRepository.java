package repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import features.book.Book;
import features.book.BookCopy;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileRepository implements BookRepository {
    private final File booksFile;
    private final File copiesFile;
    private final ObjectMapper mapper = new ObjectMapper();

    public FileRepository(String booksFileName, String copiesFileName) {
        this.booksFile = new File(booksFileName);
        this.copiesFile = new File(copiesFileName);
    }

    @Override
    public List<Book> loadBookList() {
        if (!booksFile.exists()) return new ArrayList<>();
        try {
            CollectionType listType = mapper.getTypeFactory()
                    .constructCollectionType(List.class, Book.class);
            return mapper.readValue(booksFile, listType);
        } catch (IOException e) {
            throw new RuntimeException("Error in loding books list", e);
        }
    }

    @Override
    public List<BookCopy> loadBookCopiesList() {
        if (!copiesFile.exists()) return new ArrayList<>();
        try {
            CollectionType listType = mapper.getTypeFactory()
                    .constructCollectionType(List.class, BookCopy.class);
            return mapper.readValue(copiesFile, listType);
        } catch (IOException e) {
            throw new RuntimeException("Error in loding books COPIES list", e);
        }
    }

    @Override
    public void saveBooksList(List<Book> books) {
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(booksFile, books);
        } catch (IOException e) {
            throw new RuntimeException("Error in saving books list", e);
        }
    }

    @Override
    public void saveCopiesList(List<BookCopy> loadBookCopies) {
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(copiesFile, loadBookCopies);
        } catch (IOException e) {
            throw new RuntimeException("Error in saving books COPIES list", e);
        }
    }
}

