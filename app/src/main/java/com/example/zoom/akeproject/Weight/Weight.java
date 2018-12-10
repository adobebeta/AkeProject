package com.example.zoom.akeproject.Weight;

import android.content.ContentValues;

/**
 * Created by adobebeta on 10/12/2018 AD.
 */

public class Weight {
    private String date;
    private String weight;



    //default Constructor
    public Weight() {
    }

    //Constructor
    public Weight(String date, String weight) {
        this.date = date;
        this.weight = weight;
    }

    //ContenValues
    ContentValues row = new ContentValues();
    public ContentValues getContent() {
        return row;
    }
    public void setContent(String date , String weight) {
        this.row.put("date", date);
        this.row.put("weight", weight);
    }


    //Getter and Setter
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }


}
