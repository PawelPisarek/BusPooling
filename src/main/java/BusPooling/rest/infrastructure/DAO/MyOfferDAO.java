package BusPooling.rest.infrastructure.DAO;

/**
 * Created by pawe on 3/12/17.
 */
public class MyOfferDAO {
    private String id;
    private String price;
    private String timeToLeft;
    private String author;

    public MyOfferDAO() {
    }

    public MyOfferDAO(String id, String price, String timeToLeft, String author) {
        this.id = id;
        this.price = price;
        this.timeToLeft = timeToLeft;
        this.author = author;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getTimeToLeft() {
        return timeToLeft;
    }

    public void setTimeToLeft(String timeToLeft) {
        this.timeToLeft = timeToLeft;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
