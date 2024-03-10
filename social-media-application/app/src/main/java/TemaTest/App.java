/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package TemaTest;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;

public class App {
    protected static ArrayList<User> users = new ArrayList<>();
    protected static int counterPostID = 0;
    protected static ArrayList<Post> posts = new ArrayList<>();
    protected static int counterCommentID = 0;
    protected static ArrayList<Comment> comments = new ArrayList<>();
    public App() {}

    private String createUser(java.lang.String[] args) {
        if (args.length == 1)
            return "{ 'status' : 'error', 'message' : 'Please provide username'}";
        if (args.length == 2)
            return "{ 'status' : 'error', 'message' : 'Please provide password'}";
        String username = args[1].substring("-u ".length() + 1, args[1].length() - 1);
        String password = args[2].substring("-p ".length() + 1, args[2].length() - 1);
        for (User user : users)
            if (user.getUsername().equals(username))
                return "{ 'status' : 'error', 'message' : 'User already exists'}";
        User newUser = new User();
        newUser.setUsername(username);
        newUser.setPassword(password);
        users.add(newUser);
        return "{ 'status' : 'ok', 'message' : 'User created successfully'}";
    }
    private String followUser(java.lang.String[] args) {
        if (args.length == 1 || args.length == 2)
            return "{ 'status' : 'error', 'message' : 'You need to be authenticated'}";
        String username = args[1].substring("-u ".length() + 1, args[1].length() - 1);
        String password = args[2].substring("-p ".length() + 1, args[2].length() - 1);
        for (User user : users)
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                if (args.length == 3)
                    return "{ 'status' : 'error', 'message' : 'No username to follow was provided'}";
                String usernameToFollow = args[3].substring("-username ".length() + 1, args[3].length() - 1);
                for (User userToFollow : users)
                    if (userToFollow.getUsername().equals(usernameToFollow)) {
                        if (user.following.contains(userToFollow))
                            return "{ 'status' : 'error', 'message' : 'The username to follow was not valid'}";
                        user.following.add(userToFollow);
                        userToFollow.followers.add(user);
                        return "{ 'status' : 'ok', 'message' : 'Operation executed successfully'}";
                    }
                return "{ 'status' : 'error', 'message' : 'The username to follow was not valid'}";
            }
        return "{ 'status' : 'error', 'message' : 'Login failed'}";
    }
    private String unfollowUser(java.lang.String[] args) {
        if (args.length == 1 || args.length == 2)
            return "{ 'status' : 'error', 'message' : 'You need to be authenticated'}";
        String username = args[1].substring("-u ".length() + 1, args[1].length() - 1);
        String password = args[2].substring("-p ".length() + 1, args[2].length() - 1);
        for (User user : users)
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                if (args.length == 3)
                    return "{ 'status' : 'error', 'message' : 'No username to unfollow was provided'}";
                String usernameToUnfollow = args[3].substring("-username ".length() + 1, args[3].length() - 1);
                for (User userToUnfollow : users)
                    if (userToUnfollow.getUsername().equals(usernameToUnfollow)) {
                        if (user.following.contains(userToUnfollow)) {
                            user.following.remove(userToUnfollow);
                            userToUnfollow.followers.remove(user);
                            return "{ 'status' : 'ok', 'message' : 'Operation executed successfully'}";
                        }
                        return "{ 'status' : 'error', 'message' : 'The username to unfollow was not valid'}";
                    }
                return "{ 'status' : 'error', 'message' : 'The username to unfollow was not valid'}";
            }
        return "{ 'status' : 'error', 'message' : 'Login failed'}";
    }
    private String getFollowing(java.lang.String[] args) {
        if (args.length == 1 || args.length == 2)
            return "{ 'status' : 'error', 'message' : 'You need to be authenticated'}";
        String username = args[1].substring("-u ".length() + 1, args[1].length() - 1);
        String password = args[2].substring("-p ".length() + 1, args[2].length() - 1);
        for (User user : users)
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                StringBuilder result = new StringBuilder();
                result.append("{ 'status' : 'ok', 'message' : [");
                for (User elem : user.following)
                    result.append("'").append(elem.getUsername()).append("',");
                result.deleteCharAt(result.length() - 1).append("]}");
                return result.toString();
            }
        return "{ 'status' : 'error', 'message' : 'Login failed'}";
    }
    private String getFollowers(java.lang.String[] args) {
        if (args.length == 1 || args.length == 2)
            return "{ 'status' : 'error', 'message' : 'You need to be authenticated'}";
        String username = args[1].substring("-u ".length() + 1, args[1].length() - 1);
        String password = args[2].substring("-p ".length() + 1, args[2].length() - 1);
        for (User user : users)
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                if (args.length == 3)
                    return "{ 'status' : 'error', 'message' : 'No username to list followers was provided'}";
                String usernameToListFollowers = args[3].substring("-username ".length() + 1, args[3].length() - 1);
                for (User userToListFollowers : users)
                    if (userToListFollowers.getUsername().equals(usernameToListFollowers)) {
                        StringBuilder result = new StringBuilder();
                        result.append("{ 'status' : 'ok', 'message' : [");
                        for (User elem : userToListFollowers.followers)
                            result.append("'").append(elem.getUsername()).append("',");
                        result.deleteCharAt(result.length() - 1).append("]}");
                        return result.toString();
                    }
                return "{ 'status' : 'error', 'message' : 'The username to list followers was not valid'}";
            }
        return "{ 'status' : 'error', 'message' : 'Login failed'}";
    }
    private String createPost(java.lang.String[] args) {
        if (args.length == 1 || args.length == 2)
            return "{ 'status' : 'error', 'message' : 'You need to be authenticated'}";
        String username = args[1].substring("-u ".length() + 1, args[1].length() - 1);
        String password = args[2].substring("-p ".length() + 1, args[2].length() - 1);
        for (User user : users)
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                if (args.length == 3)
                    return "{ 'status' : 'error', 'message' : 'No text provided'}";
                if (args[3].length() > 300)
                    return "{ 'status' : 'error', 'message' : 'Post text length exceeded'}";
                Post newPost = new Post();
                newPost.setPostID(++counterPostID);
                newPost.setOwner(user);
                user.postsOwned.add(newPost);
                String content = args[3].substring("-text ".length() + 1, args[3].length() - 1);
                newPost.setContent(content);
                newPost.date = new Date();
                posts.add(newPost);
                return "{ 'status' : 'ok', 'message' : 'Post added successfully'}";
            }
        return "{ 'status' : 'error', 'message' : 'Login failed'}";
    }
    private String deletePost(java.lang.String[] args) {
        if (args.length == 1 || args.length == 2)
            return "{ 'status' : 'error', 'message' : 'You need to be authenticated'}";
        String username = args[1].substring("-u ".length() + 1, args[1].length() - 1);
        String password = args[2].substring("-p ".length() + 1, args[2].length() - 1);
        for (User user : users)
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                if (args.length == 3)
                    return "{ 'status' : 'error', 'message' : 'No identifier was provided'}";
                int postID = Integer.parseInt(args[3].substring("-id ".length() + 1, args[3].length() - 1));
                for (Post post : posts)
                    if (post.getPostID() == postID) {
                        if (post.getOwner().equals(user)) {
                            user.postsOwned.remove(post);
                            posts.remove(post);
                            return "{ 'status' : 'ok', 'message' : 'Post deleted successfully'}";
                        }
                        return "{ 'status' : 'error', 'message' : 'The identifier was not valid'}";
                    }
                return "{ 'status' : 'error', 'message' : 'The identifier was not valid'}";
            }
        return "{ 'status' : 'error', 'message' : 'Login failed'}";
    }
    private String getFollowingsPosts(java.lang.String[] args) {
        if (args.length == 1 || args.length == 2)
            return "{ 'status' : 'error', 'message' : 'You need to be authenticated'}";
        String username = args[1].substring("-u ".length() + 1, args[1].length() - 1);
        String password = args[2].substring("-p ".length() + 1, args[2].length() - 1);
        for (User user : users)
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                StringBuilder result = new StringBuilder();
                result.append("{ 'status' : 'ok', 'message' : [");
                ArrayList<Post> postsCopy = new ArrayList<>(posts);
                postsCopy.sort(new Comparator<>() {
                    public int compare(Post a, Post b) {
                        return Integer.compare(b.getPostID(), a.getPostID());
                    }
                });
                for (Post elem : postsCopy)
                    if (user.following.contains(elem.getOwner())) {
                        result.append("{'post_id' : '").append(elem.getPostID()).append("',");
                        result.append("'post_text' : '").append(elem.getContent()).append("',");
                        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                        String dateAsString = dateFormat.format(elem.date);
                        result.append("'post_date' : '").append(dateAsString).append("',");
                        result.append("'username' : '").append(elem.getOwner().getUsername()).append("'},");
                    }
                result.deleteCharAt(result.length() - 1).append("]}");
                return result.toString();
            }
        return "{ 'status' : 'error', 'message' : 'Login failed'}";
    }
    private String getUserPosts(java.lang.String[] args) {
        if (args.length == 1 || args.length == 2)
            return "{ 'status' : 'error', 'message' : 'You need to be authenticated'}";
        String username = args[1].substring("-u ".length() + 1, args[1].length() - 1);
        String password = args[2].substring("-p ".length() + 1, args[2].length() - 1);
        for (User user : users)
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                if (args.length == 3)
                    return "{ 'status' : 'error', 'message' : 'No username to list posts was provided'}";
                String usernameToListPosts = args[3].substring("-username ".length() + 1, args[3].length() - 1);
                for (User userToListPosts : users)
                    if (userToListPosts.getUsername().equals(usernameToListPosts)) {
                        if (user.following.contains(userToListPosts)) {
                            StringBuilder result = new StringBuilder();
                            result.append("{ 'status' : 'ok', 'message' : [");
                            ArrayList<Post> postsOwnedCopy = new ArrayList<>(userToListPosts.postsOwned);
                            postsOwnedCopy.sort(new Comparator<>() {
                                public int compare(Post a, Post b) {
                                    return Integer.compare(b.getPostID(), a.getPostID());
                                }
                            });
                            for (Post elem : postsOwnedCopy) {
                                result.append("{'post_id' : '").append(elem.getPostID()).append("',");
                                result.append("'post_text' : '").append(elem.getContent()).append("',");
                                DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                                String dateAsString = dateFormat.format(elem.date);
                                result.append("'post_date' : '").append(dateAsString).append("'},");
                            }
                            result.deleteCharAt(result.length() - 1).append("]}");
                            return result.toString();
                        }
                        return "{ 'status' : 'error', 'message' : 'The username to list posts was not valid'}";
                    }
                return "{ 'status' : 'error', 'message' : 'The username to list posts was not valid'}";
            }
        return "{ 'status' : 'error', 'message' : 'Login failed'}";
    }
    private String likePost(java.lang.String[] args) {
        if (args.length == 1 || args.length == 2)
            return "{ 'status' : 'error', 'message' : 'You need to be authenticated'}";
        String username = args[1].substring("-u ".length() + 1, args[1].length() - 1);
        String password = args[2].substring("-p ".length() + 1, args[2].length() - 1);
        for (User user : users)
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                if (args.length == 3)
                    return "{ 'status' : 'error', 'message' : 'No post identifier to like was provided'}";
                int postID = Integer.parseInt(args[3].substring("-post-id ".length() + 1, args[3].length() - 1));
                for (Post post : posts)
                    if (post.getPostID() == postID) {
                        if (post.getOwner().equals(user))
                            return "{ 'status' : 'error', 'message' : 'The post identifier to like was not valid'}";
                        if (user.postsLiked.contains(post))
                            return "{ 'status' : 'error', 'message' : 'The post identifier to like was not valid'}";
                        user.postsLiked.add(post);
                        post.likedBy(user);
                        return "{ 'status' : 'ok', 'message' : 'Operation executed successfully'}";
                    }
                return "{ 'status' : 'error', 'message' : 'The post identifier to like was not valid'}";
            }
        return "{ 'status' : 'error', 'message' : 'Login failed'}";
    }
    private String unlikePost(java.lang.String[] args) {
        if (args.length == 1 || args.length == 2)
            return "{ 'status' : 'error', 'message' : 'You need to be authenticated'}";
        String username = args[1].substring("-u ".length() + 1, args[1].length() - 1);
        String password = args[2].substring("-p ".length() + 1, args[2].length() - 1);
        for (User user : users)
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                if (args.length == 3)
                    return "{ 'status' : 'error', 'message' : 'No post identifier to unlike was provided'}";
                int postID = Integer.parseInt(args[3].substring("-post-id ".length() + 1, args[3].length() - 1));
                for (Post post : posts)
                    if (post.getPostID() == postID) {
                        if (user.postsLiked.contains(post)) {
                            user.postsLiked.remove(post);
                            post.unlikedBy(user);
                            return "{ 'status' : 'ok', 'message' : 'Operation executed successfully'}";
                        }
                        return "{ 'status' : 'error', 'message' : 'The post identifier to unlike was not valid'}";
                    }
                return "{ 'status' : 'error', 'message' : 'The post identifier to unlike was not valid'}";
            }
        return "{ 'status' : 'error', 'message' : 'Login failed'}";
    }
    private String addComment(java.lang.String[] args) {
        if (args.length == 1 || args.length == 2)
            return "{ 'status' : 'error', 'message' : 'You need to be authenticated'}";
        String username = args[1].substring("-u ".length() + 1, args[1].length() - 1);
        String password = args[2].substring("-p ".length() + 1, args[2].length() - 1);
        for (User user : users)
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                if (args.length == 3)
                    return "{ 'status' : 'error', 'message' : 'No text provided'}";
                int postID = Integer.parseInt(args[3].substring("-post-id ".length() + 1, args[3].length() - 1));
                if (args.length == 4)
                    return "{ 'status' : 'error', 'message' : 'No text provided'}";
                if (args[4].length() > 300)
                    return "{ 'status' : 'error', 'message' : 'Comment text length exceeded'}";
                for (Post post : posts)
                    if (post.getPostID() == postID) {
                        Comment newComment = new Comment();
                        newComment.setCommentID(++counterCommentID);
                        newComment.setOwner(user);
                        user.commentsAdded.add(newComment);
                        newComment.setPostOrigin(post);
                        post.comments.add(newComment);
                        String content = args[4].substring("-text ".length() + 1, args[4].length() - 1);
                        newComment.setContent(content);
                        newComment.date = new Date();
                        comments.add(newComment);
                        return "{ 'status' : 'ok', 'message' : 'Comment added successfully'}";
                    }
                return "{ 'status' : 'error', 'message' : 'The post identifier to add comment was not valid'}";
            }
        return "{ 'status' : 'error', 'message' : 'Login failed'}";
    }
    private String deleteComment(java.lang.String[] args) {
        if (args.length == 1 || args.length == 2)
            return "{ 'status' : 'error', 'message' : 'You need to be authenticated'}";
        String username = args[1].substring("-u ".length() + 1, args[1].length() - 1);
        String password = args[2].substring("-p ".length() + 1, args[2].length() - 1);
        for (User user : users)
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                if (args.length == 3)
                    return "{ 'status' : 'error', 'message' : 'No identifier was provided'}";
                int commentID = Integer.parseInt(args[3].substring("-id ".length() + 1, args[3].length() - 1));
                for (Comment comment : comments)
                    if (comment.getCommentID() == commentID) {
                        if (user.commentsAdded.contains(comment)) {
                            user.commentsAdded.remove(comment);
                            comment.getPostOrigin().comments.remove(comment);
                            comments.remove(comment);
                            return "{ 'status' : 'ok', 'message' : 'Operation executed successfully'}";
                        }
                        return "{ 'status' : 'error', 'message' : 'The identifier was not valid'}";
                    }
                return "{ 'status' : 'error', 'message' : 'The identifier was not valid'}";
            }
        return "{ 'status' : 'error', 'message' : 'Login failed'}";
    }
    private String likeComment(java.lang.String[] args) {
        if (args.length == 1 || args.length == 2)
            return "{ 'status' : 'error', 'message' : 'You need to be authenticated'}";
        String username = args[1].substring("-u ".length() + 1, args[1].length() - 1);
        String password = args[2].substring("-p ".length() + 1, args[2].length() - 1);
        for (User user : users)
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                if (args.length == 3)
                    return "{ 'status' : 'error', 'message' : 'No comment identifier to like was provided'}";
                int commentID = Integer.parseInt(args[3].substring("-comment-id ".length() + 1, args[3].length() - 1));
                for (Comment comment : comments)
                    if (comment.getCommentID() == commentID) {
                        if (comment.getOwner().equals(user))
                            return "{ 'status' : 'error', 'message' : 'The comment identifier to like was not valid'}";
                        if (user.commentsLiked.contains(comment))
                            return "{ 'status' : 'error', 'message' : 'The comment identifier to like was not valid'}";
                        user.commentsLiked.add(comment);
                        comment.likedBy(user);
                        return "{ 'status' : 'ok', 'message' : 'Operation executed successfully'}";
                    }
                return "{ 'status' : 'error', 'message' : 'The comment identifier to like was not valid'}";
            }
        return "{ 'status' : 'error', 'message' : 'Login failed'}";
    }
    private String unlikeComment(java.lang.String[] args) {
        if (args.length == 1 || args.length == 2)
            return "{ 'status' : 'error', 'message' : 'You need to be authenticated'}";
        String username = args[1].substring("-u ".length() + 1, args[1].length() - 1);
        String password = args[2].substring("-p ".length() + 1, args[2].length() - 1);
        for (User user : users)
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                if (args.length == 3)
                    return "{ 'status' : 'error', 'message' : 'No comment identifier to unlike was provided'}";
                int commentID = Integer.parseInt(args[3].substring("-comment-id ".length() + 1, args[3].length() - 1));
                for (Comment comment : comments)
                    if (comment.getCommentID() == commentID) {
                        if (user.commentsLiked.contains(comment)) {
                            user.commentsLiked.remove(comment);
                            comment.unlikedBy(user);
                            return "{ 'status' : 'ok', 'message' : 'Operation executed successfully'}";
                        }
                        return "{ 'status' : 'error', 'message' : 'The comment identifier to unlike was not valid'}";
                    }
                return "{ 'status' : 'error', 'message' : 'The comment identifier to unlike was not valid'}";
            }
        return "{ 'status' : 'error', 'message' : 'Login failed'}";
    }
    private String getPostDetails(java.lang.String[] args) {
        if (args.length == 1 || args.length == 2)
            return "{ 'status' : 'error', 'message' : 'You need to be authenticated'}";
        String username = args[1].substring("-u ".length() + 1, args[1].length() - 1);
        String password = args[2].substring("-p ".length() + 1, args[2].length() - 1);
        for (User user : users)
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                if (args.length == 3)
                    return "{ 'status' : 'error', 'message' : 'No post identifier was provided'}";
                int postID = Integer.parseInt(args[3].substring("-post-id ".length() + 1, args[3].length() - 1));
                for (Post post : posts)
                    if (post.getPostID() == postID) {
                        StringBuilder result = new StringBuilder();
                        result.append("{ 'status' : 'ok', 'message' : [");
                        result.append("{'post_text' : '").append(post.getContent()).append("',");
                        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                        String dateAsString = dateFormat.format(post.date);
                        result.append("'post_date' : '").append(dateAsString).append("',");
                        result.append("'username' : '").append(post.getOwner().getUsername()).append("',");
                        result.append("'number_of_likes' : '").append(post.getNrLikes()).append("',");
                        result.append("'comments' : [");
                        ArrayList<Comment> postCommentsCopy = new ArrayList<>(post.comments);
                        postCommentsCopy.sort(new Comparator<>() {
                            public int compare(Comment a, Comment b) {
                                return Integer.compare(b.getCommentID(), a.getCommentID());
                            }
                        });
                        for (Comment elem : postCommentsCopy) {
                            result.append("{'comment_id' : '").append(elem.getCommentID()).append("',");
                            result.append("'comment_text' : '").append(elem.getContent()).append("',");
                            dateAsString = dateFormat.format(elem.date);
                            result.append("'comment_date' : '").append(dateAsString).append("',");
                            result.append("'username' : '").append(elem.getOwner().getUsername()).append("',");
                            result.append("'number_of_likes' : '").append(elem.getNrLikes()).append("'},");
                        }
                        result.deleteCharAt(result.length() - 1).append("] }] }");
                        return result.toString();
                    }
                return "{ 'status' : 'error', 'message' : 'The post identifier was not valid'}";
            }
        return "{ 'status' : 'error', 'message' : 'Login failed'}";
    }
    private String mostFollowedUsers(java.lang.String[] args) {
        if (args.length == 1 || args.length == 2)
            return "{ 'status' : 'error', 'message' : 'You need to be authenticated'}";
        String username = args[1].substring("-u ".length() + 1, args[1].length() - 1);
        String password = args[2].substring("-p ".length() + 1, args[2].length() - 1);
        for (User user : users)
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                StringBuilder result = new StringBuilder();
                result.append("{ 'status' : 'ok', 'message' : [");
                int i = 0;
                ArrayList<User> usersCopy = new ArrayList<>(users);
                usersCopy.sort(new Comparator<>() {
                    public int compare(User a, User b) {
                        return Integer.compare(b.followers.size(), a.followers.size());
                    }
                });
                for (User elem : usersCopy) {
                    i++;
                    result.append("{'username' : '").append(elem.getUsername()).append("',");
                    result.append("'number_of_followers' : '").append(elem.followers.size()).append("'},");
                    if (i == 5)
                        break;
                }
                result.deleteCharAt(result.length() - 1).append(" ]}");
                return result.toString();
            }
        return "{ 'status' : 'error', 'message' : 'Login failed'}";
    }
    private String mostLikedPosts(java.lang.String[] args) {
        if (args.length == 1 || args.length == 2)
            return "{ 'status' : 'error', 'message' : 'You need to be authenticated'}";
        String username = args[1].substring("-u ".length() + 1, args[1].length() - 1);
        String password = args[2].substring("-p ".length() + 1, args[2].length() - 1);
        for (User user : users)
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                StringBuilder result = new StringBuilder();
                result.append("{ 'status' : 'ok', 'message' : [");
                int i = 0;
                ArrayList<Post> postsCopy = new ArrayList<>(posts);
                postsCopy.sort(new Comparator<>() {
                    public int compare(Post a, Post b) {
                        return Integer.compare(b.getNrLikes(), a.getNrLikes());
                    }
                });
                for (Post elem : postsCopy) {
                    i++;
                    result.append("{'post_id' : '").append(elem.getPostID()).append("',");
                    result.append("'post_text' : '").append(elem.getContent()).append("',");
                    DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                    String dateAsString = dateFormat.format(elem.date);
                    result.append("'post_date' : '").append(dateAsString).append("',");
                    result.append("'username' : '").append(elem.getOwner().getUsername()).append("',");
                    result.append("'number_of_likes' : '").append(elem.getNrLikes()).append("'},");
                    if (i == 5)
                        break;
                }
                result.deleteCharAt(result.length() - 1).append(" ]}");
                return result.toString();
            }
        return "{ 'status' : 'error', 'message' : 'Login failed'}";
    }
    private String mostCommentedPosts(java.lang.String[] args) {
        if (args.length == 1 || args.length == 2)
            return "{ 'status' : 'error', 'message' : 'You need to be authenticated'}";
        String username = args[1].substring("-u ".length() + 1, args[1].length() - 1);
        String password = args[2].substring("-p ".length() + 1, args[2].length() - 1);
        for (User user : users)
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                StringBuilder result = new StringBuilder();
                result.append("{ 'status' : 'ok', 'message' : [");
                int i = 0;
                ArrayList<Post> postsCopy = new ArrayList<>(posts);
                postsCopy.sort(new Comparator<>() {
                    public int compare(Post a, Post b) {
                        return Integer.compare(b.comments.size(), a.comments.size());
                    }
                });
                for (Post elem : postsCopy) {
                    i++;
                    result.append("{'post_id' : '").append(elem.getPostID()).append("',");
                    result.append("'post_text' : '").append(elem.getContent()).append("',");
                    DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                    String dateAsString = dateFormat.format(elem.date);
                    result.append("'post_date' : '").append(dateAsString).append("',");
                    result.append("'username' : '").append(elem.getOwner().getUsername()).append("',");
                    result.append("'number_of_comments' : '").append(elem.comments.size()).append("'},");
                    if (i == 5)
                        break;
                }
                result.deleteCharAt(result.length() - 1).append("]}");
                return result.toString();
            }
        return "{ 'status' : 'error', 'message' : 'Login failed'}";
    }
    private String mostLikedUsers(java.lang.String[] args) {
        if (args.length == 1 || args.length == 2)
            return "{ 'status' : 'error', 'message' : 'You need to be authenticated'}";
        String username = args[1].substring("-u ".length() + 1, args[1].length() - 1);
        String password = args[2].substring("-p ".length() + 1, args[2].length() - 1);
        for (User user : users)
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                StringBuilder result = new StringBuilder();
                result.append("{ 'status' : 'ok', 'message' : [");
                int i = 0;
                ArrayList<User> usersCopy = new ArrayList<>(users);
                usersCopy.sort(new Comparator<>() {
                    public int compare(User a, User b) {
                        return Integer.compare(b.getNrLikes(), a.getNrLikes());
                    }
                });
                for (User elem : usersCopy) {
                    i++;
                    result.append("{'username' : '").append(elem.getUsername()).append("',");
                    result.append("'number_of_likes' : '").append(elem.getNrLikes()).append("'},");
                    if (i == 5)
                        break;
                }
                result.deleteCharAt(result.length() - 1).append("]}");
                return result.toString();
            }
        return "{ 'status' : 'error', 'message' : 'Login failed'}";
    }
    private String cleanup() {
        users.clear();
        counterPostID = 0;
        posts.clear();
        counterCommentID = 0;
        comments.clear();
        return "{ 'status' : 'ok', 'message' : 'Cleanup finished successfully'}";
    }
    public static void main(java.lang.String[] strings) {
        App application = new App();
        if (strings == null)
            System.out.println("Hello world!");
        else switch (strings[0]) {
            case "-create-user":
                System.out.println(application.createUser(strings));
                break;
            case "-follow-user-by-username":
                System.out.println(application.followUser(strings));
                break;
            case "-unfollow-user-by-username":
                System.out.println(application.unfollowUser(strings));
                break;
            case "-get-following":
                System.out.println(application.getFollowing(strings));
                break;
            case "-get-followers":
                System.out.println(application.getFollowers(strings));
                break;
            case "-create-post":
                System.out.println(application.createPost(strings));
                break;
            case "-delete-post-by-id":
                System.out.println(application.deletePost(strings));
                break;
            case "-get-followings-posts":
                System.out.println(application.getFollowingsPosts(strings));
                break;
            case "-get-user-posts":
                System.out.println(application.getUserPosts(strings));
                break;
            case "-like-post":
                System.out.println(application.likePost(strings));
                break;
            case "-unlike-post":
                System.out.println(application.unlikePost(strings));
                break;
            case "-comment-post":
                System.out.println(application.addComment(strings));
                break;
            case "-delete-comment-by-id":
                System.out.println(application.deleteComment(strings));
                break;
            case "-like-comment":
                System.out.println(application.likeComment(strings));
                break;
            case "-unlike-comment":
                System.out.println(application.unlikeComment(strings));
                break;
            case "-get-post-details":
                System.out.println(application.getPostDetails(strings));
                break;
            case "-get-most-followed-users":
                System.out.println(application.mostFollowedUsers(strings));
                break;
            case "-get-most-liked-posts":
                System.out.println(application.mostLikedPosts(strings));
                break;
            case "-get-most-commented-posts":
                System.out.println(application.mostCommentedPosts(strings));
                break;
            case "-get-most-liked-users":
                System.out.println(application.mostLikedUsers(strings));
                break;
            case "-cleanup-all":
                System.out.println(application.cleanup());
                break;
            default:
                System.out.println("Command not found!");
        }
    }
}