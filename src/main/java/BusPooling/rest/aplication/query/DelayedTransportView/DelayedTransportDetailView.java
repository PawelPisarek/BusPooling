package BusPooling.rest.aplication.query.DelayedTransportView;

import BusPooling.rest.aplication.query.Comment.CommentView;
import BusPooling.rest.aplication.query.MyOfferView.MyOfferView;
import BusPooling.rest.aplication.query.TransportView.TransportOfferView;

import java.util.List;

/**
 * Created by pawe on 3/3/17.
 */
public class DelayedTransportDetailView {

    private String id;
    private String nameTrain;
    private String from;
    private String alternative;
    private String lat;
    private String lng;
    private List<TransportOfferView> transportOffers;
    private List<MyOfferView> myOffers;
    private List<CommentView> comments;

    public DelayedTransportDetailView(String id, String nameTrain, String from, String alternative, String lat, String lng, List<TransportOfferView> transportOfferViews, List<MyOfferView> myOfferViews, List<CommentView> commentViews) {
        this.id = id;
        this.nameTrain = nameTrain;
        this.from = from;
        this.alternative = alternative;
        this.lat = lat;
        this.lng = lng;
        this.transportOffers = transportOfferViews;
        this.myOffers = myOfferViews;
        this.comments = commentViews;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNameTrain() {
        return nameTrain;
    }

    public void setNameTrain(String nameTrain) {
        this.nameTrain = nameTrain;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getAlternative() {
        return alternative;
    }

    public void setAlternative(String alternative) {
        this.alternative = alternative;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public List<TransportOfferView> getTransportOffers() {
        return transportOffers;
    }

    public void setTransportOffers(List<TransportOfferView> transportOffers) {
        this.transportOffers = transportOffers;
    }

    public List<MyOfferView> getMyOffers() {
        return myOffers;
    }

    public void setMyOffers(List<MyOfferView> myOffers) {
        this.myOffers = myOffers;
    }

    public List<CommentView> getComments() {
        return comments;
    }

    public void setComments(List<CommentView> comments) {
        this.comments = comments;
    }
}
