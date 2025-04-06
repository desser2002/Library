package features.book;

import java.util.UUID;

public class Book {
    private String id;
    private String title;
    private String author;
    private int year;
    private int availableCopies;
    private int totalCopies;


    public Book(String title, String author, int year,int totalCopies) {
        this.title = title;
        this.author = author;
        this.year = year;
        this.id = UUID.randomUUID().toString();
        this.totalCopies = totalCopies;
        this.availableCopies = totalCopies;
    }

    public Book(){}

    public boolean borrowBook(){
        if (availableCopies > 0){ availableCopies--; return true;} return false;

    }

    public boolean returnBook(){ if (availableCopies<totalCopies) {availableCopies++; return true;}return false;}

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getTotalCopies() {
        return totalCopies;
    }

    public void setTotalCopies(int totalCopies) {
        this.totalCopies = totalCopies;
    }

    public int getAvailableCopies() {
        return availableCopies;
    }

    public void setAvailableCopies(int availableCopies) {
        this.availableCopies = availableCopies;
    }
}