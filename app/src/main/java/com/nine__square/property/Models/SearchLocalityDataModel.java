package com.nine__square.property.Models;

public class SearchLocalityDataModel {

        private String id;
        private String state;
        private String city;
        private String locality;

    public SearchLocalityDataModel(String id, String state, String city, String locality) {
        this.id = id;
        this.state = state;
        this.city = city;
        this.locality = locality;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }
}
