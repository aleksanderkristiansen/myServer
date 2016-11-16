package controllers;

import Encrypters.Crypter;
import database.DBConnector;
import model.User;

import java.sql.SQLException;

/**
 * Opretter en instans af DBConnector og kalder alle metoder til AccesTokens.
 * Hver metode er forklaret med kommentarer i DBConnector.
 */

public class TokenController {

    DBConnector db = new DBConnector();

    public User authenticate(String email, String password) throws SQLException {
        // Authenticate the user using the credentials provided

        String token;

        User foundUser = db.authenticate(email, password);
        if (foundUser != null) {

            token = Crypter.buildToken("abcdefghijklmnopqrstuvxyz1234567890", 25);

            db.addToken(token, foundUser.getUserID());

            foundUser.setToken(token);

        } else {
            foundUser = null;
        }


        //Retunerer en access token til klienten.

        return foundUser;


    }

    public User getUserFromTokens(String token) throws SQLException {
        DBConnector db = new DBConnector();
        User user = db.getUserFromToken(token);
        db.close();
        return user;

    }

    public boolean deleteToken(String token) throws SQLException{
        DBConnector db = new DBConnector();
        boolean deleteToken = db.deleteToken(token);
        db.close();
        return deleteToken;

    }
}
