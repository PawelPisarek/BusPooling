package BusPooling.rest.domain;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

@ApiModel(value = "delayed-transport")
public class DelayedTransport {
    private String id;
    private String nameTrain;
    private String from;
    private String alternative;
    private String lat;
    private String lng;
    public DelayedTransport() {
    }

    public DelayedTransport(String id, String nameTrain, String from, String alternative, String lat, String lng) {
        this.id = id;
        this.nameTrain = nameTrain;
        this.from = from;
        this.alternative = alternative;
        this.lat = lat;
        this.lng = lng;
    }

    @ApiModelProperty(value = "DelayedTransport id", required = true)
    public String getId() {
        return id;
    }

    @ApiModelProperty(value = "DelayedTransport getNameTrain", required = true)
    public String getNameTrain() {
        return nameTrain;
    }
    @ApiModelProperty(value = "DelayedTransport from", required = true)
    public String getFrom() {
        return from;
    }
    @ApiModelProperty(value = "DelayedTransport alternative", required = true)
    public String getAlternative() {
        return alternative;
    }
    @ApiModelProperty(value = "DelayedTransport lat", required = true)
    public String getLat() {
        return lat;
    }
    @ApiModelProperty(value = "DelayedTransport lng", required = true)
    public String getLng() {
        return lng;
    }
}
