package com.app.mexpress.model;

public class SavedModel {


    private int savedImage, savedIcon;
    private String techText, descriptionText, vergeText;


    public SavedModel(int savedImage, String techText, String descriptionText, String vergeText, int savedIcon) {
        this.savedImage = savedImage;
        this.savedIcon = savedIcon;
        this.techText = techText;
        this.descriptionText = descriptionText;
        this.vergeText = vergeText;
    }

    public int getSavedImage() {
        return savedImage;
    }

    public void setSavedImage(int savedImage) {
        this.savedImage = savedImage;
    }

    public int getSavedIcon() {
        return savedIcon;
    }

    public void setSavedIcon(int savedIcon) {
        this.savedIcon = savedIcon;
    }

    public String getTechText() {
        return techText;
    }

    public void setTechText(String techText) {
        this.techText = techText;
    }

    public String getDescriptionText() {
        return descriptionText;
    }

    public void setDescriptionText(String descriptionText) {
        this.descriptionText = descriptionText;
    }

    public String getVergeText() {
        return vergeText;
    }

    public void setVergeText(String vergeText) {
        this.vergeText = vergeText;
    }
}
