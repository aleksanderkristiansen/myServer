package model;

/**
 * Variabler til Book oprettes samt deres getters/setters.
 */

public class Book {

    private int bookID;
    private double ISBN;
    private String publisher;
    private String title;
    private String author;
    private String bookstoreName;
    private double price;
    private int version;

    public Book(int bookID, String publisher, String title, String author, int version, double ISBN, String bookstoreName, double price) {
        this.bookID = bookID;
        this.publisher = publisher;
        this.title = title;
        this.author = author;
        this.version = version;
        this.ISBN = ISBN;
        this.bookstoreName = bookstoreName;
        this.price = price;
    }

    public Book(String publisher, String title, String author, int version, double ISBN, String bookstoreName, double price) {
        this.publisher = publisher;
        this.title = title;
        this.author = author;
        this.version = version;
        this.ISBN = ISBN;
        this.bookstoreName = bookstoreName;
        this.price = price;
    }

    public int getBookID() {
        return bookID;
    }

    public void setBookID(int bookID) {
        this.bookID = bookID;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public double getISBN() {
        return ISBN;
    }

    public void setISBN(double ISBN) {
        this.ISBN = ISBN;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getBookstoreName() { return bookstoreName; }

    public void setBookstoreName(String bookstoreName) {
        this.bookstoreName = bookstoreName;
    }

    //    @Override
//    public String toString() {
//        return "model.Book{" +
//                "name='" + name + '\'' +
//                ", publisher='" + publisher + '\'' +
//                ", ISBN='" + ISBN + '\'' +
//                '}';
//    }
}
