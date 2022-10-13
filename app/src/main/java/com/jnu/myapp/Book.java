package com.jnu.myapp;

import java.io.Serializable;

public class Book implements Serializable {
    private int imageID;
    private String name;

    public Book(String name,int imageID) {
        this.name=name;
        this.imageID=imageID;
    }

    public int getImageID() {
        return imageID;
    }

    public String getName() {
        return name;
    }

    public void setImageID(int imageID) {
        this.imageID = imageID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getID() {return 1;
    }
}
