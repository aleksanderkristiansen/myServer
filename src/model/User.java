package model;

/**
 * Variabler til User oprettes samt deres getters/setters.
 */

public class User {
    int userID;
    String firstName, lastName, email, password, token;
    Boolean userType;

    public User(){

    }

    public User(int userID, String token){
        this.userID = userID;
        this.token = token;
    }


   public User(String email, String password){
       this.email = email;
       this.password = password;
   }



    public User(String firstName, String lastName, String email, String password, Boolean userType) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.userType = userType;
    }
    public User(int userID, String firstName, String lastName, String email, String password, Boolean userType) {
        this.userID = userID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.userType = userType;
    }

    public User(int userID, String firstName, String lastName, String email, Boolean userType, String token){
        this.userID = userID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.userType = userType;
        this.token = token;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String password) {
        this.token = token;
    }

    public Boolean getUserType() {
        return userType;
    }

    public void setUserType(Boolean admin) {
        userType = admin;
    }
}


