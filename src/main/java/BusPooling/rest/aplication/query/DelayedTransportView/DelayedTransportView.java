package BusPooling.rest.aplication.query.DelayedTransportView;

/**
 * Created by pawe on 3/3/17.
 */
public class DelayedTransportView {

    private String id;
    private String nameTrain;
    private String from;
    private String alternative;
    private String lat;
    private String lng;

    public DelayedTransportView(String id, String nameTrain, String from, String alternative, String lat, String lng) {
        this.id = id;
        this.nameTrain = nameTrain;
        this.from = from;
        this.alternative = alternative;
        this.lat = lat;
        this.lng = lng;
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
}
