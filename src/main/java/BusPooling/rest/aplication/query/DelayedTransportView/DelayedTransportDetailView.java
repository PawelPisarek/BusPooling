package BusPooling.rest.aplication.query.DelayedTransportView;

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
    private List<TransportOfferView> transportOfferViews;
    private List<MyOfferView> myOfferViews;

    public DelayedTransportDetailView(String id, String nameTrain, String from, String alternative, String lat, String lng, List<TransportOfferView> transportOfferViews, List<MyOfferView> myOfferViews) {
        this.id = id;
        this.nameTrain = nameTrain;
        this.from = from;
        this.alternative = alternative;
        this.lat = lat;
        this.lng = lng;
        this.transportOfferViews = transportOfferViews;
        this.myOfferViews = myOfferViews;
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

    public List<TransportOfferView> getTransportOfferViews() {
        return transportOfferViews;
    }

    public void setTransportOfferViews(List<TransportOfferView> transportOfferViews) {
        this.transportOfferViews = transportOfferViews;
    }

    public List<MyOfferView> getMyOfferViews() {
        return myOfferViews;
    }

    public void setMyOfferViews(List<MyOfferView> myOfferViews) {
        this.myOfferViews = myOfferViews;
    }
}
