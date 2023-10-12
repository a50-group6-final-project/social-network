package models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class PostModel {
    public int postId;
    public String content;
    public String picture;
    public String date;

    public ArrayList<Object> likes;
    public ArrayList<Object> comments;
    public int rank;
    @JsonProperty("public")
    public boolean mypublic;
    public Category category;
    public boolean liked;
}

