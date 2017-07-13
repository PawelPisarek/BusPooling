package BusPooling.rest.aplication.query.MyOfferView;

import BusPooling.rest.aplication.query.Person.PersonView;

/**
 * Created by pawe on 3/12/17.
 */
public class MyOfferView {
    private String id;
    private String price;
    private String timeToLeft;
    private PersonView author;

    public MyOfferView(String id, String price, String timeToLeft, PersonView author) {
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

    public PersonView getAuthor() {
        return author;
    }

    public void setAuthor(PersonView author) {
        this.author = author;
    }
}
