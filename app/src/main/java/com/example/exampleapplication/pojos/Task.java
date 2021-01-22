package com.example.exampleapplication.pojos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import io.reactivex.annotations.NonNull;

public class Task {

    @Expose
    @SerializedName("id")
    private Integer id;
    @Expose
    @SerializedName("text")
    private String text;
    @Expose
    @SerializedName("children")
    private ArrayList<Task> children;


    public Task(@NonNull final Integer id, @NonNull final String text, ArrayList<Task> children) {
        this.id = id;
        this.children = children;
        this.text = text;
    }

    public ArrayList<Task> getChildren() {
        return children;
    }

    public void setChildren(ArrayList<Task> children) {
        this.children = children;
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
            this.text = text;
        }
}
