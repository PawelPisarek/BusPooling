package BusPooling.rest.domain;

import BusPooling.rest.infrastructure.entity.DelayedTransportEntity;
import BusPooling.rest.infrastructure.entity.MyOfferEntity;
import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

@ApiModel(value = "my-offer")
public class MyOffer {
    private String id;
    private String price;
    private String timeToLeft;
    private String author;
    private DelayedTransportEntity delayedTransportEntity;


    public MyOffer() {
    }

    public MyOffer(String id, String price, String timeToLeft, String author) {

        this.id = id;
        this.price = price;
        this.timeToLeft = timeToLeft;
        this.author = author;
    }

    @ApiModelProperty(value = "My offer id", required = true)
    public String getId() {
        return id;
    }

    @ApiModelProperty(value = "My offer price", required = true)
    public String getPrice() {
        return price;
    }
    @ApiModelProperty(value = "My offer id timeToLeft", required = true)
    public String getTimeToLeft() {
        return timeToLeft;
    }
    @ApiModelProperty(value = "My offer author", required = true)
    public String getAuthor() {
        return author;
    }

    public DelayedTransportEntity getDelayedTransportEntity() {
        return delayedTransportEntity;
    }

    public void setDelayedTransportEntity(DelayedTransportEntity delayedTransportEntity) {
        this.delayedTransportEntity = delayedTransportEntity;
    }
}
