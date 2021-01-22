package com.example.exampleapplication.pojos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import io.reactivex.annotations.NonNull;

public class User {

    @Expose
    @SerializedName("id")
    private Integer id;
    @Expose
    @SerializedName("name")
    private String name;
    @Expose
    @SerializedName("notes")
    private ArrayList<User> notes;

    public User(Integer id, String name, ArrayList<User> notes) {
        this.id = id;
        this.name = name;
        this.notes = notes;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<User> getNotes() {
        return notes;
    }

    public void setNotes(ArrayList<User> notes) {
        this.notes = notes;
    }
}
