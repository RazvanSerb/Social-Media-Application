package TemaTest;
import java.util.ArrayList;

public class User {
    protected String username;
    private String password;
    protected ArrayList<User> following = new ArrayList<>();
    protected ArrayList<User> followers = new ArrayList<>();
    protected ArrayList<Post> postsOwned = new ArrayList<>();
    protected ArrayList<Post> postsLiked = new ArrayList<>();
    protected ArrayList<Comment> commentsAdded = new ArrayList<>();
    protected ArrayList<Comment> commentsLiked = new ArrayList<>();
    public User() {}

    protected void setUsername(String username) {
        this.username = username;
    }
    protected String getUsername() {
        return username;
    }
    protected void setPassword(String password) {
        this.password = password;
    }
    protected String getPassword() {
        return password;
    }
    public int getNrLikes() {
        int nrLikes = 0;
        for (Post post : postsOwned)
            nrLikes += post.getNrLikes();
        for (Comment comment : commentsAdded)
            nrLikes += comment.getNrLikes();
        return nrLikes;
    }
}
