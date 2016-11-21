package controllers;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import database.DBConnector;
import model.Author;
import model.Book;
import model.BookStore;
import model.Publisher;

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


    public boolean addBook(String authors, String bookStores, String data) throws Exception {
        DBConnector db = new DBConnector();

        ArrayList<Author> listOfAuthors = new Gson().fromJson(authors, new TypeToken<ArrayList<Author>>() {
        }.getType());

        ArrayList<BookStore> listOfBookstores = new Gson().fromJson(bookStores, new TypeToken<ArrayList<BookStore>>() {
        }.getType());

        Book book = new Gson().fromJson(data,Book.class);

        return db.addBook(listOfAuthors, listOfBookstores, book);
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

}
