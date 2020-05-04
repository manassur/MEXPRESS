package com.app.mexpress.model;

public class FeaturedModel {
    private String image;
    private String title,category;

    public FeaturedModel(String image, String title, String category) {
        this.image = image;
        this.title = title;
        this.category = category;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
