package com.example.ultimatefitness;


public class Model {
    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    private String Title;
    private int image;
    private  String start;

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }
}
