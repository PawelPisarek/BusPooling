package BusPooling.rest.infrastructure.DAO;

/**
 * Created by pawe on 3/13/17.
 */
public class TransportOfferDAO {

    private String id;
    private String price;
    private String transportName;
    private String seats;
    private String isJoined;

    public TransportOfferDAO() {
    }

    public TransportOfferDAO(String id, String price, String transportName, String seats, String isJoined) {
        this.id = id;
        this.price = price;
        this.transportName = transportName;
        this.seats = seats;
        this.isJoined = isJoined;
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

    public String getTransportName() {
        return transportName;
    }

    public void setTransportName(String transportName) {
        this.transportName = transportName;
    }

    public String getSeats() {
        return seats;
    }

    public void setSeats(String seats) {
        this.seats = seats;
    }

    public String getIsJoined() {
        return isJoined;
    }

    public void setIsJoined(String isJoined) {
        this.isJoined = isJoined;
    }
}
