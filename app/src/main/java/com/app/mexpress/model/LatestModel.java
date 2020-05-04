package com.app.mexpress.model;

public class LatestModel {


    private String username;
    private String profile_image;
    private String id;

    public LatestModel(String username, String profile_image, String id) {
        this.username = username;

        this.profile_image = profile_image;
        this.id = id;
    }




    public String getProfile_image() {
        return profile_image;
    }

    public void setProfile_image(String profile_image) {
        this.profile_image = profile_image;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
