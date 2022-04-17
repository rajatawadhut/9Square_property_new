package com.nine__square.property.Models;

public class SearchProjectDataModel {

        private String id;
        private String project;

    public SearchProjectDataModel(String id, String project) {
        this.id = id;
        this.project = project;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }
}
