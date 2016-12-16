package database;

import com.google.gson.Gson;
import config.Config;
import model.*;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by mortenlaursen on 17/10/2016.
 */
public class DBConnector {
    /**
     * Constructor for establishing connection.
     *
     * @throws Exception
     */
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://" + Config.getDbUrl() + ":" + Config.getDbPort() + "/" + Config.getDbName();

    //  Database credentials
    static final String USER = Config.getDbUserName();
    static final String PASS = Config.getDbPassword();

    //String sql; Not needed anymore after introducing prepared statements.
    Connection conn = null;
    Statement stmt = null;

    public DBConnector() {

        try {
            //STEP 2: Register JDBC driver
            Class.forName(JDBC_DRIVER).newInstance();

            //STEP 3: Open a connection
            this.conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //STEP 4: Execute a query
            this.stmt = conn.createStatement();

            //STEP 6: Clean-up environment
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /*5 user methods*/

    //Henter alle aktive brugere
    public ArrayList getUsers() throws IllegalArgumentException {
        ArrayList results = new ArrayList();
        ResultSet resultSet = null;

        try {
            PreparedStatement getUsers = conn.prepareStatement("SELECT * FROM user WHERE deleted = 0");
            resultSet = getUsers.executeQuery();

            while (resultSet.next()) {
                try {

                    User users = new User(
                            resultSet.getInt("id"),
                            resultSet.getString("first_name"),
                            resultSet.getString("last_name"),
                            resultSet.getString("email"),
                            resultSet.getString("password"),
                            resultSet.getBoolean("user_type")
                    );

                    results.add(users);

                } catch (Exception e) {

                }
            }
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        }
        return results;

    }

    //Henter specifik aktiv brugere
    public User getUser(int id) throws IllegalArgumentException {
        User user = null;
        ResultSet resultSet = null;

        try {
            PreparedStatement getUser = conn.prepareStatement("SELECT * FROM user WHERE id=? and deleted = 0");
            getUser.setInt(1, id);
            resultSet = getUser.executeQuery();


            while (resultSet.next()) {
                try {

                    user = new User(
                            resultSet.getInt("id"),
                            resultSet.getString("first_name"),
                            resultSet.getString("last_name"),
                            resultSet.getString("email"),
                            resultSet.getString("password"),
                            resultSet.getBoolean("user_type")
                    );

                } catch (Exception e) {

                }
            }


        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        }
        return user;
    }

    //Laver ændringer i en bruger
    public boolean editUser(int id, User u) throws SQLException {
        PreparedStatement editUserStatement = conn
                .prepareStatement("Update user set first_name = COALESCE(NULLIF( ?,NULL),first_name), last_name = COALESCE(NULLIF( ?,NULL),last_name), email = COALESCE(NULLIF( ?,NULL), email), password = COALESCE(NULLIF( ?,NULL),password) where id = ?");

        try {
            editUserStatement.setString(1, u.getFirstName());
            editUserStatement.setString(2, u.getLastName());
            editUserStatement.setString(3, u.getEmail());
            editUserStatement.setString(4, u.getPassword());
            editUserStatement.setInt(5, id);

            editUserStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    //Tilføj bruger
    public boolean addUser(User u) throws Exception {
        PreparedStatement addUserStatement =
                conn.prepareStatement("INSERT INTO user (first_name, last_name, email, password, user_type) VALUES (?, ?, ?, ?, ?)");

        try {
            addUserStatement.setString(1, u.getFirstName());
            addUserStatement.setString(2, u.getLastName());
            addUserStatement.setString(3, u.getEmail());
            addUserStatement.setString(4, u.getPassword());
            addUserStatement.setBoolean(5, u.getUserType());

            addUserStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    //Slet bruger
    public boolean deleteUser(int id) throws SQLException {
        PreparedStatement deleteUserStatement = conn.prepareStatement("UPDATE user SET deleted = 1 WHERE id=?");
        try {
            deleteUserStatement.setInt(1, id);
            deleteUserStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    /*Curriculum methods*/
    //Alle pensumlister med universiteter og uddannelser
    public ArrayList getCurriculums() throws IllegalArgumentException {
        ArrayList<Curriculum> results = new ArrayList<>();
        ResultSet resultSet = null;

        try {
            PreparedStatement getCurriculums = conn.prepareStatement("SELECT curriculum.id, school.school_name, education.education_name, curriculum.semester FROM education INNER JOIN curriculum on curriculum.education_id = education.id INNER JOIN school ON education.school_id = school.id where curriculum.deleted = 0");
            resultSet = getCurriculums.executeQuery();

            while (resultSet.next()) {
                try {

                    Curriculum cul = new Curriculum(
                            resultSet.getInt("id"),
                            resultSet.getString("school_name"),
                            resultSet.getString("education_name"),
                            resultSet.getInt("semester")
                    );

                    results.add(cul);
                } catch (Exception e) {
                }
            }
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        }
        return results;

    }


    //Specifik pensumliste med universiteter og uddannelser
    public Curriculum getCurriculum(int curriculumID) throws IllegalArgumentException {
        ResultSet resultSet = null;
        Curriculum curriculum = null;

        try {
            PreparedStatement getCurriculum = conn.prepareStatement("SELECT curriculum.id, school.school_name, education.education_name, curriculum.semester FROM education INNER JOIN curriculum on curriculum.education_id = education.id INNER JOIN school ON education.school_id = school.id where curriculum.id = ? and curriculum.deleted = 0");
            getCurriculum.setInt(1, curriculumID);
            resultSet = getCurriculum.executeQuery();

            while (resultSet.next()) {
                try {

                    curriculum = new Curriculum(
                            resultSet.getInt("id"),
                            resultSet.getString("school_name"),
                            resultSet.getString("education_name"),
                            resultSet.getInt("semester")
                    );

                } catch (Exception e) {

                }
            }
        } catch (SQLException sqlException) {

            System.out.println(sqlException.getMessage());
        }
        return curriculum;

    }

    //Ændre en pensumliste
    public boolean editCurriculum(int id, String data) throws SQLException {
        PreparedStatement editCurriculumStatement = conn.prepareStatement("UPDATE curriculum SET education_id = ?, semester = ? WHERE id = ?");

        Curriculum c = new Gson().fromJson(data, Curriculum.class);

        try {
            editCurriculumStatement.setInt(1, c.getEducationID());
            editCurriculumStatement.setInt(2, c.getSemester());
            editCurriculumStatement.setInt(3, id);

            editCurriculumStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    //Tilføj pensumliste
    public boolean addCurriculum(Curriculum c) throws SQLException {
        PreparedStatement addCurriculumStatement = conn.prepareStatement("INSERT INTO curriculum (education_id, semester) VALUES (?, ?)");

        try {

            addCurriculumStatement.setInt(1, c.getEducationID());
            addCurriculumStatement.setInt(2, c.getSemester());

            addCurriculumStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    //Slet pensumliste
    public boolean deleteCurriculum(int id) throws SQLException {
        PreparedStatement deleteUserStatement = conn.prepareStatement("UPDATE curriculum SET deleted = 1 WHERE id= ?");

        try {
            deleteUserStatement.setInt(1, id);
            deleteUserStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    //Alle bøger fra specifik pensumliste
    public ArrayList<Book> getCurriculumBooks(int curriculumID) {
        ArrayList results = new ArrayList();
        ResultSet resultSet = null;


        try {
            PreparedStatement getCurriculumBooks = conn.prepareStatement("select book.id, publisher.publisher_name, book.title, author.author_name, book.version, book.isbn, bookstore.bookstore_name, book_price.price from book inner join book_curriculum on book.id = book_curriculum.book_id inner join publisher on book.publisher_id = publisher.id inner join author_book on book.id = author_book.book_id inner join author on author_book.author_id = author.id inner join book_price on book.id = book_price.book_id inner join bookstore on book_price.bookstore_id = bookstore.id where book_curriculum.curriculum_id = ? AND book.deleted = 0");
            getCurriculumBooks.setInt(1, curriculumID);
            resultSet = getCurriculumBooks.executeQuery();

            while ( resultSet.next() ) {
                try {

                    Book books = new Book(
                            resultSet.getInt("id"),
                            resultSet.getString("publisher_name"),
                            resultSet.getString("title"),
                            resultSet.getString("author_name"),
                            resultSet.getInt("version"),
                            resultSet.getDouble("isbn"),
                            resultSet.getString("bookstore_name"),
                            resultSet.getDouble("price")
                    );

                    results.add(books);

                }catch(Exception e){

                }
            }
        }
        catch ( SQLException sqlException ){
            System.out.println(sqlException.getMessage());
        }
        return results;
    }

    /*books methods*/

    //Alle bøger
    public ArrayList getBooks() throws IllegalArgumentException {
        ArrayList results = new ArrayList();
        ResultSet resultSet = null;

        try {
            PreparedStatement getBooks = conn.prepareStatement("select book.id, publisher.publisher_name, book.title, author.author_name, book.version, book.isbn, bookstore.bookstore_name, book_price.price from book inner join book_curriculum on book.id = book_curriculum.book_id inner join publisher on book.publisher_id = publisher.id inner join author_book on book.id = author_book.book_id inner join author on author_book.author_id = author.id inner join book_price on book.id = book_price.book_id inner join bookstore on book_price.bookstore_id = bookstore.id where book.deleted = 0");
            resultSet = getBooks.executeQuery();

            while (resultSet.next()) {
                try {

                    Book books = new Book(
                            resultSet.getInt("id"),
                            resultSet.getString("publisher_name"),
                            resultSet.getString("title"),
                            resultSet.getString("author_name"),
                            resultSet.getInt("version"),
                            resultSet.getDouble("isbn"),
                            resultSet.getString("bookstore_name"),
                            resultSet.getDouble("price")
                    );

                    results.add(books);

                } catch (Exception e) {

                }
            }
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        }
        return results;

    }

    //Alle forfattere
    public ArrayList getAuthors() throws IllegalArgumentException {
        ArrayList results = new ArrayList();
        ResultSet resultSet = null;

        try {
            PreparedStatement getAuthors = conn.prepareStatement("select * from author");
            resultSet = getAuthors.executeQuery();

            while (resultSet.next()) {
                try {

                    Author author = new Author(
                            resultSet.getInt("id"),
                            resultSet.getString("author_name")
                    );

                    results.add(author);

                } catch (Exception e) {

                }
            }
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        }
        return results;

    }

    //Alle forfattere af specifik bog
    public ArrayList getAuthorsOfBook(int id) throws IllegalArgumentException {
        ArrayList results = new ArrayList();
        ResultSet resultSet = null;

        try {
            PreparedStatement getAuthorsOfBook = conn.prepareStatement("select author.id, author.author_name from author inner join author_book on author_book.author_id = author.id where author_book.book_id = ?");
            getAuthorsOfBook.setInt(1, id);
            resultSet = getAuthorsOfBook.executeQuery();

            while (resultSet.next()) {
                try {

                    Author author = new Author(
                            resultSet.getInt("id"),
                            resultSet.getString("author_name")
                    );

                    results.add(author);

                } catch (Exception e) {

                }
            }
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        }
        return results;

    }

    //Alle forlag
    public ArrayList getPublishers() throws IllegalArgumentException {
        ArrayList results = new ArrayList();
        ResultSet resultSet = null;

        try {
            PreparedStatement getPublishers = conn.prepareStatement("select * from publisher");
            resultSet = getPublishers.executeQuery();

            while (resultSet.next()) {
                try {

                    Publisher publisher = new Publisher(
                            resultSet.getInt("id"),
                            resultSet.getString("publisher_name")
                    );

                    results.add(publisher);

                } catch (Exception e) {

                }
            }
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        }
        return results;
    }

    //Alle boghandlere
    public ArrayList getBookStores() throws IllegalArgumentException {
        ArrayList results = new ArrayList();
        ResultSet resultSet = null;

        try {
            PreparedStatement getPublishers = conn.prepareStatement("select * from bookstore");
            resultSet = getPublishers.executeQuery();

            while (resultSet.next()) {
                try {

                    BookStore bookStore = new BookStore(
                            resultSet.getInt("id"),
                            resultSet.getString("bookstore_name")
                    );

                    results.add(bookStore);

                } catch (Exception e) {

                }
            }
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        }
        return results;

    }

    //Alle boghandlere af specifik bog
    public ArrayList getBookStoresOfBook(int id) throws IllegalArgumentException {
        ArrayList results = new ArrayList();
        ResultSet resultSet = null;

        try {
            PreparedStatement getBookStoresOfBook = conn.prepareStatement("select bookstore.id, bookstore.bookstore_name, book_price.price from bookstore inner join book_price on book_price.bookstore_id = bookstore.id where book_price.book_id = ?");
            getBookStoresOfBook.setInt(1, id);
            resultSet = getBookStoresOfBook.executeQuery();

            while (resultSet.next()) {
                try {

                    BookStore bookStore = new BookStore(
                            resultSet.getInt("id"),
                            resultSet.getString("bookstore_name"),
                            resultSet.getDouble("price")
                    );

                    results.add(bookStore);

                } catch (Exception e) {

                }
            }
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        }
        return results;

    }

    //Hent specifik bog
    public Book getBook(int id) throws IllegalArgumentException {
        Book book = null;
        ResultSet resultSet = null;

        try {
            PreparedStatement getBook = conn.prepareStatement("select book.id, book.title, book.version, book.isbn, publisher.publisher_name from book inner join publisher on book.publisher_id = publisher.id where book.id = ?");
            getBook.setInt(1, id);
            resultSet = getBook.executeQuery();
            resultSet.next();
            book = new Book(
                    resultSet.getInt("id"),
                    resultSet.getString("title"),
                    resultSet.getInt("version"),
                    resultSet.getDouble("isbn"),
                    resultSet.getString("publisher_name")
            );
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        }
        return book;

    }

    //Tilføj bog
    public  boolean addBook(Book book) throws SQLException{

        PreparedStatement addBookStatement = conn.prepareStatement("INSERT INTO book (title, version, isbn, publisher_id) VALUES (?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);


        int risultato = 0;

        try{
            addBookStatement.setString(1, book.getTitle());
            addBookStatement.setInt(2, book.getVersion());
            addBookStatement.setDouble(3, book.getISBN());
            addBookStatement.setInt(4, book.getPublisherID());

            addBookStatement.executeUpdate();

            ResultSet rs = addBookStatement.getGeneratedKeys();
            if (rs.next()){
                risultato=rs.getInt(1);
            }

        }catch (SQLException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < book.getLstAuthors().size(); i++){

            PreparedStatement addAuthorToBook = conn.prepareStatement("INSERT INTO author_book (book_id, author_id) VALUES (?, ?)");

            try{
                addAuthorToBook.setInt(1, risultato);
                addAuthorToBook.setInt(2, book.getLstAuthors().get(i).getId());

                addAuthorToBook.executeUpdate();
            }catch (SQLException e){
                e.printStackTrace();
            }

        }

        for (int i = 0; i < book.getLstBookStores().size(); i++){

            PreparedStatement addBookStoreToBook = conn.prepareStatement("INSERT INTO book_price (book_id, bookstore_id, price) VALUES (?, ?, ?)");

            try{
                addBookStoreToBook.setInt(1, risultato);
                addBookStoreToBook.setInt(2, book.getLstBookStores().get(i).getId());
                addBookStoreToBook.setDouble(3, book.getLstBookStores().get(i).getPriceOfBook());

                addBookStoreToBook.executeUpdate();
            }catch (SQLException e){
                e.printStackTrace();
            }

        }
        return true;
    }

    //Slet bog
    public boolean deleteBook(int id) throws SQLException {
        PreparedStatement deleteUserStatement = conn.prepareStatement("UPDATE book SET deleted = 1 WHERE id = ?");
        PreparedStatement deleteBooksCurriculumRows = conn.prepareStatement("UPDATE book_curriculum SET deleted = 1 WHERE book_id = ?");

        try {
            deleteUserStatement.setInt(1, id);
            deleteUserStatement.executeUpdate();

            deleteBooksCurriculumRows.setInt(1, id);
            deleteBooksCurriculumRows.execute();


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    //Se om brugeren findes i db
    public User authenticate(String email, String password) {

        ResultSet resultSet = null;
        User userFound = null;

        try {
            PreparedStatement authenticate = conn.prepareStatement("select * from user where email = ? AND password = ? AND deleted = 0");
            authenticate.setString(1, email);
            authenticate.setString(2, password);


            resultSet = authenticate.executeQuery();

            while (resultSet.next()) {
                try {
                    userFound = new User();
                    userFound.setUserID(resultSet.getInt("id"));
                    userFound.setFirstName(resultSet.getString("first_name"));
                    userFound.setLastName(resultSet.getString("last_name"));
                    userFound.setEmail(resultSet.getString("email"));
                    userFound.setUserType(resultSet.getBoolean("user_type"));

                } catch (SQLException e) {

                }


            }
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        }

        return userFound;

    }

    //Find bruger udfra token
    public User getUserFromToken(String token) throws SQLException {
        ResultSet resultSet = null;
        User userFromToken = null;
        try {

            PreparedStatement getUserFromToken = conn
                    .prepareStatement("select token.user_id, user.user_type from token inner join user on token.user_id = user.id where token.token = ? and token.deleted = 0 ");
            getUserFromToken.setString(1, token);
            resultSet = getUserFromToken.executeQuery();

            while (resultSet.next()) {

                userFromToken = new User();

                userFromToken.setUserID(resultSet.getInt("user_id"));
                userFromToken.setUserType(resultSet.getBoolean("user_type"));

            }
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        }
        return userFromToken;

    }

    //TIlføj token
    public void addToken(String token, int userId) {

        PreparedStatement addTokenStatement;
        try {
            addTokenStatement = conn.prepareStatement("INSERT INTO token (token, user_id) VALUES (?,?)");
            addTokenStatement.setString(1, token);
            addTokenStatement.setInt(2, userId);
            addTokenStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Fjern token ved logud
    public boolean deleteToken(String token) throws SQLException {
        PreparedStatement deleteTokenStatement = conn.prepareStatement("UPDATE token SET deleted = 1 WHERE token = ?");

        try {
            deleteTokenStatement.setString(1, token);
            deleteTokenStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public void close(){
        try {
            this.conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}