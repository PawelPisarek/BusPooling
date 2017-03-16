package BusPooling.rest.domain;

import BusPooling.rest.infrastructure.entity.PersonEntity;
import BusPooling.rest.infrastructure.entity.TransportOfferEntity;
import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

/**
 * Created by pawe on 3/16/17.
 */
@ApiModel(value = "accepted-offer")
public class AcceptedOffer {
    private String id;
    private PersonEntity personEntity;
    private TransportOfferEntity transportOffer;

    public AcceptedOffer(String id, PersonEntity personEntity, TransportOfferEntity transportOffer) {
        this.id = id;
        this.personEntity = personEntity;
        this.transportOffer = transportOffer;
    }

    public AcceptedOffer() {
    }

    @ApiModelProperty(value = "AcceptedOffer id", required = true)
    public String getId() {
        return id;
    }

    @ApiModelProperty(value = "AcceptedOffer personEntity", required = true)
    public PersonEntity getPersonEntity() {
        return personEntity;
    }

    @ApiModelProperty(value = "AcceptedOffer transportOffer", required = true)
    public TransportOfferEntity getTransportOffer() {
        return transportOffer;
    }

}
