package BusPooling.rest.domain;

import BusPooling.rest.infrastructure.entity.DelayedTransportEntity;
import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

@ApiModel(value = "comment")
public class Comment {
    private String id;
    private String uuid;
    private String root;
    private String text;
    private DelayedTransportEntity delayedTransportEntity;


    public Comment() {
    }

    public Comment(String id, String uuid, String root, String text, DelayedTransportEntity delayedTransportEntity) {
        this.id = id;
        this.uuid = uuid;
        this.root = root;
        this.text = text;
        this.delayedTransportEntity = delayedTransportEntity;
    }

    @ApiModelProperty(value = "comment id", required = true)
    public String getId() {
        return id;
    }

    @ApiModelProperty(value = "comment uuid", required = true)
    public String getUuid() {
        return uuid;
    }

    @ApiModelProperty(value = "comment root", required = true)
    public String getRoot() {
        return root;
    }

    @ApiModelProperty(value = "comment text", required = true)
    public String getText() {
        return text;
    }

    @ApiModelProperty(value = "comment delayedTransportEntity", required = true)
    public DelayedTransportEntity getDelayedTransportEntity() {
        return delayedTransportEntity;
    }
}
