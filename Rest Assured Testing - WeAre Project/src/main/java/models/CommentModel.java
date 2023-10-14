package models;

public class CommentModel {
    public int commentId;
    public String content;
    public boolean deletedConfirmed;
    public int postId;
    public int userId;

    public String date;
    public String likes[];
    public boolean liked;
}
