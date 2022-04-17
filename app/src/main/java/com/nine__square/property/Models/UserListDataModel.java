package com.nine__square.property.Models;

public class UserListDataModel {

    String fullname;
    String email;
    String mobile;
    String city;
    String userid;
    String subscriptionplan;
    String image;
    String usertype;

    public UserListDataModel(String fullname, String email, String mobile, String city, String userid, String subscriptionplan, String image, String usertype) {
        this.fullname = fullname;
        this.email = email;
        this.mobile = mobile;
        this.city = city;
        this.userid = userid;
        this.subscriptionplan = subscriptionplan;
        this.image = image;
        this.usertype = usertype;
    }

    public String getUsertype() {
        return usertype;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getSubscriptionplan() {
        return subscriptionplan;
    }

    public void setSubscriptionplan(String subscriptionplan) {
        this.subscriptionplan = subscriptionplan;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}