package models;

import java.util.ArrayList;

public class UserPersonal {

    public int id;
    public String username;
    public ArrayList<String> authorities;
    public String email;
    public Object firstName;
    public Object lastNAme;
    public Object gender;
    public Object city = "";
    public Object birthYear;
    public Object personalReview = "";
    public String expertise = "Doctor";
    public ArrayList<Skill> skills;
}
