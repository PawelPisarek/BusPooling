package BusPooling.rest.domain;

import BusPooling.rest.infrastructure.entity.DelayedTransportEntity;
import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

@ApiModel(value = "transport-offer")
public class TransportOffer {
    private String id;
    private String uuid;
    private String price;
    private String transportName;
    private String seats;
    private String isJoined;
    private DelayedTransportEntity delayedTransportEntity;


    public TransportOffer() {
    }

    public TransportOffer(String id, String uuid, String price, String transportName, String seats, String isJoined, DelayedTransportEntity delayedTransportEntity) {
        this.id = id;
        this.uuid = uuid;
        this.price = price;
        this.transportName = transportName;
        this.seats = seats;
        this.isJoined = isJoined;
        this.delayedTransportEntity = delayedTransportEntity;
    }

    @ApiModelProperty(value = "Transport Offer id", required = true)
    public String getId() {
        return id;
    }

    @ApiModelProperty(value = "Transport Offer id", required = true)
    public String getUuid() {
        return uuid;
    }

    @ApiModelProperty(value = "Transport Offer price", required = true)
    public String getPrice() {
        return price;
    }

    @ApiModelProperty(value = "Transport Offer transportName", required = true)
    public String getTransportName() {
        return transportName;
    }

    @ApiModelProperty(value = "Transport Offer seats", required = true)
    public String getSeats() {
        return seats;
    }

    @ApiModelProperty(value = "Transport Offer isJoined", required = true)
    public String getIsJoined() {
        return isJoined;
    }

    @ApiModelProperty(value = "Transport Offer DelayedTransportEntity", required = true)
    public DelayedTransportEntity getDelayedTransportEntity() {
        return delayedTransportEntity;
    }


}
