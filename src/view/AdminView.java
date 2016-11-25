package view;

import com.google.gson.Gson;
import config.Config;
import config.ConfigMap;
import controllers.BookController;
import controllers.UserController;
import model.Book;
import model.User;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * Created by aleksanderkristiansen on 24/11/2016.
 */
public class AdminView {

    Scanner sc = new Scanner(System.in);

    UserController userController = new UserController();
    BookController bookController = new BookController();

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

        System.out.print(" 1: Vis alle brugere"
                +"\n 2: Opret bruger"
                +"\n 3: Opret Bog"
                +"\n 4: Slet bruger"
                +"\n 5: Slet bog" );

        int choice = sc.nextInt();

        boolean stop = true;

        while (stop){
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

    private void deleteUser(){

    }

    private void deleteBook(){

    }

    private void createBook() {
    }

    private void createUser() {
    }


    private void showAllUsers() {
        for (User user : userController.getUsers()){
            System.out.println(user.getEmail());
        }
    }
}
