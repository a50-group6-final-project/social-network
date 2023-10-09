package models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PostModel {
        public String content;
        public String picture;
        @JsonProperty("public")
        public boolean mypublic;
    }

