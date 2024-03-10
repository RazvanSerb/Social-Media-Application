package TemaTest;
import java.util.ArrayList;
import java.util.Date;

public class Post implements Likeable {
    protected int postID;
    protected User owner;
    protected String content;
    protected Date date;
    protected ArrayList<User> likes = new ArrayList<>();
    protected ArrayList<Comment> comments = new ArrayList<>();
    public Post() {}

    protected void setPostID(int postID) {
        this.postID = postID;
    }
    protected int getPostID() {
        return postID;
    }
    protected void setOwner(User owner) {
        this.owner = owner;
    }
    protected User getOwner() {
        return owner;
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
