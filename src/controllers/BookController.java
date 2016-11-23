package controllers;

import com.google.gson.Gson;
import database.DBConnector;
import model.*;
import java.util.ArrayList;
/**
 * Opretter en instans af DBConnector og kalder alle metoder til Book.
 * Klassen modtager datasættet fra DBConnector som videresendes til BookEndpoint.
 * Hver metode er forklaret med kommentarer i DBConnector.
 */

public class BookController {

    public ArrayList<Book> getBooks() throws Exception {
        DBConnector db = new DBConnector();
        ArrayList<Book> books = db.getBooks();
        db.close();
        return books;
    }

    public Book getBook(int id) throws Exception {
        DBConnector db = new DBConnector();
        Book book = db.getBook(id);
        book.setLstAuthors(db.getAuthorsOfBook(id));
        book.setLstBookStore(db.getBookStoresOfBook(id));
        db.close();
        return book;
    }

    public boolean editBook(int id, String data) throws Exception {
        DBConnector db = new DBConnector();
        boolean editBook = db.editBook(id, data);
        db.close();
        return editBook;
    }

    public boolean deleteBook(int id) throws Exception {
        DBConnector db = new DBConnector();
        boolean deleteBook = db.deleteBook(id);
        db.close();
        return deleteBook;
    }


    public boolean addBook(String bookData) throws Exception {
        DBConnector db = new DBConnector();

        Book book = new Gson().fromJson(bookData, Book.class);

        return db.addBook(book);
    }

    public ArrayList<Author> getAuthors() throws Exception {
        DBConnector db = new DBConnector();
        ArrayList<Author> authors = db.getAuthors();
        db.close();
        return authors;
    }

    public ArrayList<Publisher> getPublishers() throws Exception {
        DBConnector db = new DBConnector();
        ArrayList<Publisher> publishers = db.getPublishers();
        db.close();
        return publishers;
    }

    public ArrayList<BookStore> getBookStores() throws Exception {
        DBConnector db = new DBConnector();
        ArrayList<BookStore> bookStores = db.getBookStores();
        db.close();
        return bookStores;
    }

}
