package com.example.myappaccomo.entities;

import java.io.Serializable;

public class Ad implements Serializable {
    public static final String AD_KEY ="ad_key";

    private Long id;
    private String category;
    private String type;
    private String description;

    public Ad() {

    }

    public Ad(Long id, String category, String type, String description) {
        this.id = id;
        this.category = category;
        this.type = type;
        this.description = description;
    }



    public Long getId(){return id; }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Ad{" +
                "id=" + id +
                ", category='" + category + '\'' +
                ", type='" + type + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}

