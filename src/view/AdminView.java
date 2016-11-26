package view;

import com.google.gson.Gson;
import config.Config;
import config.ConfigMap;
import controllers.BookController;
import controllers.UserController;
import model.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by aleksanderkristiansen on 24/11/2016.
 */
public class AdminView {

    Scanner sc = new Scanner(System.in);

    UserController userController;
    BookController bookController;

    public AdminView(){}

    public void run() throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(this.getClass().getClassLoader().getResourceAsStream("./config.json")));

        Gson gson = new Gson();

        ConfigMap config = gson.fromJson(br, ConfigMap.class);

        Config.setDbName(config.getDbName());
        Config.setDbPassword(config.getDbPassword());
        Config.setDbPort(config.getDbPort());
        Config.setDbUrl(config.getDbUrl());
        Config.setDbUserName(config.getDbUserName());

        this.userController = new UserController();
        this.bookController = new BookController();





        boolean stop = true;

        while (stop){
            System.out.print(" 1: Vis alle brugere"
                    +"\n 2: Opret bruger"
                    +"\n 3: Opret Bog"
                    +"\n 4: Slet bruger"
                    +"\n 5: Slet bog" );
            int choice = sc.nextInt();
            switch (choice){
                case 1: showAllUsers();
                    break;
                case 2: createUser();
                    break;
                case 3: createBook();
                    break;
                case 4: deleteUser();
                    break;
                case 5: deleteBook();
                    break;
            }
        }
    }

    private void deleteUser() throws SQLException {
        int userID = sc.nextInt();
        userController.deleteUser(userID);
        System.out.println("Bruger slettet");
    }

    private void deleteBook() throws Exception {
        int bookID = sc.nextInt();
        bookController.deleteBook(bookID);
        System.out.println("Bog slettet");
    }

    private void createBook() throws Exception {
        System.out.println("Title");
        String title = sc.next();
        System.out.println("Version");
        int version = sc.nextInt();
        System.out.println("ISBN");
        double isbn = sc.nextDouble();
        getPublishers();
        System.out.println("Skriv ID på publisher");
        int publisherID = sc.nextInt();

        for (Author author : bookController.getAuthors()){
            int index = bookController.getAuthors().indexOf(author);
            System.out.println(bookController.getAuthors().indexOf(author) + ": " + author.getName());
        }
        ArrayList<Author> lstAuthors = new ArrayList<>();
        System.out.println("Skriv ID på forfatter");
        int selectedAuthor = sc.nextInt();
        lstAuthors.add(bookController.getAuthors().get(selectedAuthor));
        int authorSelection = 1;
        while (authorSelection == 1){
            System.out.println("Tilføj forfatter mere? 0: Nej 1: Ja");
            authorSelection = sc.nextInt();
            if (authorSelection == 1){
                System.out.println("Skriv ID på forfatter");
                selectedAuthor = sc.nextInt();
                lstAuthors.add(bookController.getAuthors().get(selectedAuthor));
            }
        }
        getBookstores();
        ArrayList<BookStore> lstBookStores = new ArrayList<>();
        System.out.println("Skriv ID på Bookstore");
        int selectedBookstore = sc.nextInt();
        System.out.println("Hvad skal bogen koste");
        double price = sc.nextDouble();
        lstBookStores.add(bookController.getBookStores().get(selectedBookstore));
        int bookStoreSelection = 1;
        while (bookStoreSelection == 1){
            System.out.println("Tilføj forhandler mere? 0: Nej 1: Ja");
            bookStoreSelection = sc.nextInt();
            if (bookStoreSelection == 1){
                System.out.println("Skriv ID på Bookstore");
                selectedBookstore = sc.nextInt();
                System.out.println("Hvad skal bogen koste");
                price = sc.nextDouble();
                BookStore bookStore = new BookStore(bookController.getBookStores().get(selectedBookstore).getId(), bookController.getBookStores().get(selectedBookstore).getName(), price);
                lstBookStores.add(bookStore);
            }
        }
        Book book = new Book(title, version, isbn, publisherID, lstAuthors, lstBookStores );
        bookController.addBook(book);
    }

    private void createUser() throws Exception {
        System.out.println("Fornavn");
        String firstName = sc.next();
        System.out.println("Efternavn");
        String lastName = sc.next();
        System.out.println("E-mail");
        String email = sc.next();
        System.out.println("Adgangskode");
        String password = sc.next();
        System.out.println("0 for alm. bruger eller 1 for administrator");
        boolean userType = false;
        int userTypeChoice = sc.nextInt();
        if(userTypeChoice == 1){
            userType = true;
        }
        User user = new User(firstName, lastName, email, password, userType);
        userController.addUser(user);
        System.out.println("Bruger oprettet");
    }


    private void showAllUsers() {
        for (User user : userController.getUsers()){
            System.out.println(user.getEmail());
        }
    }

    private void getPublishers() throws Exception {
        for (Publisher publisher : bookController.getPublishers()){
            System.out.println(publisher.getId() + ": " + publisher.getName());
        }
    }

    private void getAuthors() throws Exception {
        for (Author author : bookController.getAuthors()){
            System.out.println(bookController.getAuthors().indexOf(author) + ": " + author.getName());
        }
    }

    private void getBookstores() throws Exception {
        for (BookStore bookStore : bookController.getBookStores()){
            System.out.println(bookController.getBookStores().indexOf(bookStore) + ": " + bookStore.getName());
        }
    }
}
