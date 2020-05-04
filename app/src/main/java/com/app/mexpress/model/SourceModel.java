package com.app.mexpress.model;

public class SourceModel {


    private int sourceImage, sourceImagebg;

    public SourceModel(  int sourceImage, int sourceImagebg) {


        this.sourceImage = sourceImage;
        this.sourceImagebg = sourceImagebg;
    }


    public int getSourceImage() {
        return sourceImage;
    }

    public void setSourceImage(int sourceImage) {
        this.sourceImage = sourceImage;
    }

    public int getSourceImagebg() {
        return sourceImagebg;
    }

    public void setSourceImagebg(int sourceImagebg) {
        this.sourceImagebg = sourceImagebg;
    }
}
