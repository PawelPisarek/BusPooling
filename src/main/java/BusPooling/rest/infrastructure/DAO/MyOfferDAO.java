package BusPooling.rest.infrastructure.DAO;

/**
 * Created by pawe on 3/12/17.
 */
public class MyOfferDAO {
    private String id;
    private String price;
    private String timeToLeft;

    public MyOfferDAO() {
    }

    public MyOfferDAO(String id, String price, String timeToLeft) {
        this.id = id;
        this.price = price;
        this.timeToLeft = timeToLeft;
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

}
