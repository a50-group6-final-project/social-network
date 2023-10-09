package models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.ArrayList;

public class UserUpdatePersonal{

    public String username;
    public String email;
    public String firstName;
    public String lastName;
    public String gender = "MALE";
    public String birthYear;
    public Object personalReview;
    public String expertise = "Doctor";
    public ArrayList<Skill> skills;
    @JsonProperty("location")
    public Location location = new Location();
    public class Location{
        public City city;
    }

}