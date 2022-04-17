package com.nine__square.property.Utils;

import java.io.Serializable;

public class SliderUtils2 implements Serializable {
    String image;
    String id;

    public SliderUtils2(String image, String id){

        this.image = image;
        this.id = id;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
