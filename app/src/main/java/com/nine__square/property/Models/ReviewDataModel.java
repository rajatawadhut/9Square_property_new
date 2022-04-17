package com.nine__square.property.Models;

public class ReviewDataModel {

    String fname;
    String lname;
    String rating;
    String review;
    String valueid;
    String userreviewid;
    String userrating;
    String userreview;


    public ReviewDataModel(String fname, String lname, String rating, String review, String valueid, String userreviewid, String userreview , String userrating) {
        this.fname = fname;
        this.lname = lname;
        this.rating = rating;
        this.review = review;
        this.userreviewid = userreviewid;
        this.valueid = valueid;
        this.userreview = userreview;
        this.userrating = userrating;

    }

    public String getUserreviewid() {
        return userreviewid;
    }

    public void setUserreviewid(String userreviewid) {
        this.userreviewid = userreviewid;
    }

    public String getUserrating() {
        return userrating;
    }

    public void setUserrating(String userrating) {
        this.userrating = userrating;
    }

    public String getUserreview() {
        return userreview;
    }

    public void setUserreview(String userreview) {
        this.userreview = userreview;
    }

    public String getValueid() {
        return valueid;
    }

    public void setValueid(String valueid) {
        this.valueid = valueid;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }
}