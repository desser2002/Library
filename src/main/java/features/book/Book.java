package features.book;

import java.time.Year;
import java.util.UUID;

public class Book {
    private String id;
    private String title;
    private String author;
    private int year;
    private int availableCopies;
    private int totalCopies;


    public Book(String title, String author, int year,int totalCopies) {
        int currentYear = Year.now().getValue();
        if (year > 0 && year <= currentYear)
            this.year = year;
        else throw new IllegalArgumentException("Year out of range");
        if (totalCopies>availableCopies)
        {
            {
                if (totalCopies>=0)
                    this.totalCopies = totalCopies;
                else totalCopies = 0;
            }
            if (availableCopies>=0)
                this.availableCopies = availableCopies;
            else availableCopies = 0;

        }
        this.title = title;
        this.author = author;
        this.id = UUID.randomUUID().toString();

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
        int currentYear = Year.now().getValue();
       if (year > 0 && year <= currentYear)
           this.year = year;
       else throw new IllegalArgumentException("Year out of range");
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
        if (totalCopies > 0) this.totalCopies = totalCopies;
    }

    public int getAvailableCopies() {
        return availableCopies;
    }

    public void setAvailableCopies(int availableCopies) {
        if (availableCopies > 0) this.availableCopies = availableCopies;

    }
}