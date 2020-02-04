package com.stpprojects.einscriptionslms.bean;

import java.io.Serializable;

public class AllCourseBean implements Serializable {

    String id;
    String image;
    String title;
    String rating;
    String students;
    String description;
    String price;
    String status;

    public AllCourseBean(String id, String image, String title, String rating, String students, String description, String price, String status) {
        this.id = id;
        this.image = image;
        this.title = title;
        this.rating = rating;
        this.students = students;
        this.description = description;
        this.price = price;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
