package BusPooling.rest.infrastructure.DAO;

/**
 * Created by pawe on 3/12/17.
 */
public class CommentDAO {
    private String uuid;
    private String root;
    private String text;

    public CommentDAO(String uuid, String root, String text) {
        this.uuid = uuid;
        this.root = root;
        this.text = text;
    }

    public CommentDAO(String text) {
        this.text = text;
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
