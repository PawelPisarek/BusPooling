package BusPooling.rest.aplication.query.Comment;

/**
 * Created by pawe on 3/13/17.
 */
public class CommentView {

    private String id;
    private String uuid;
    private String root;
    private String text;

    public CommentView(String id, String uuid, String root, String text) {
        this.id = id;
        this.uuid = uuid;
        this.root = root;
        this.text = text;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getRoot() {
        return root;
    }

    public void setRoot(String root) {
        this.root = root;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
