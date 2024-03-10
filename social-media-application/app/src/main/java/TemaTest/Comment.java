package TemaTest;
import java.util.ArrayList;
import java.util.Date;

public class Comment implements Likeable {
    protected int commentID;
    protected User owner;
    protected Post postOrigin;
    protected String content;
    protected Date date;
    protected ArrayList<User> likes = new ArrayList<>();
    public Comment() {}

    protected void setCommentID(int commentID) {
        this.commentID = commentID;
    }
    protected int getCommentID() {
        return commentID;
    }
    protected void setOwner(User owner) {
        this.owner = owner;
    }
    protected User getOwner() {
        return owner;
    }
    protected void setPostOrigin(Post postOrigin) {
        this.postOrigin = postOrigin;
    }
    protected Post getPostOrigin() {
        return postOrigin;
    }
    protected void setContent(String content) {
        this.content = content;
    }
    protected String getContent() {
        return content;
    }
    public void likedBy(Object o) {
        likes.add((User) o);
    }
    public void unlikedBy(Object o) {
        likes.remove((User) o);
    }
    public int getNrLikes() {
        return likes.size();
    }
}
