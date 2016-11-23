package model;

/**
 * Created by aleksanderkristiansen on 20/11/2016.
 */
public class Author {

    private int id;
    private String name;

    public Author(int id, String name){
        this.id = id;
        this.name = name;
    }

    public Author(int id){
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
