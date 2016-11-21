package model;

/**
 * Created by aleksanderkristiansen on 20/11/2016.
 */
public class BookStore {

    private int id;
    private String name;
    private double priceOfBook;

    public BookStore(int id, String name, double priceOfBook){
        this.id = id;
        this.name = name;
        this.priceOfBook = priceOfBook;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPriceOfBook() {
        return priceOfBook;
    }

    public void setPriceOfBook(double priceOfBook) {
        this.priceOfBook = priceOfBook;
    }
}
