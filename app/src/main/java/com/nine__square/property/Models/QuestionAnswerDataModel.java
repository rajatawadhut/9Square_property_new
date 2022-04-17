package com.nine__square.property.Models;

public class QuestionAnswerDataModel {

    String queid;
    String question;
    String mobile;
    String email;
    String name;
    String status;
    String answer;
    String propertyid;
    String datanull;


    public QuestionAnswerDataModel(String queid, String question, String mobile, String email, String name, String status, String answer, String propertyid, String datanull){
        this.queid = queid;
        this.question = question;
        this.mobile = mobile;
        this.email = email;
        this.name = name;
        this.status = status;
        this.answer = answer;
        this.propertyid = propertyid;
        this.datanull = datanull;
    }

    public String getDatanull() {
        return datanull;
    }

    public void setDatanull(String datanull) {
        this.datanull = datanull;
    }

    public String getPropertyid() {
        return propertyid;
    }

    public void setPropertyid(String propertyid) {
        this.propertyid = propertyid;
    }

    public String getQueid() {
        return queid;
    }

    public void setQueid(String queid) {
        this.queid = queid;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }



    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
