package models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public class ApproveRequest {
    public int id;
    public boolean approved;
    public boolean seen;
    public String timeStamp;
}
