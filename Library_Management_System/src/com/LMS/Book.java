package com.LMS;

import java.util.ArrayList;

public  class Book {
    private String book_name; //书名

    private int number_of_page; //页数

    private String old_and_new_degree; //新旧程度

    private String classification; //分类

    private int status; //status为1时，表示在书架上。status为0时，表示不在书架上，借出去了。

    private int location_of_library;


    private int down_shell; //默认为0，表示在架上，为1时表示下架

    public String getBook_name() {
        return book_name;
    }

    public void setBook_name(String book_name) {
        this.book_name = book_name;
    }

    public int getNumber_of_page() {
        return number_of_page;
    }

    public void setNumber_of_page(int number_of_page) {
        this.number_of_page = number_of_page;
    }

    public String getOld_and_new_degree() {
        return old_and_new_degree;
    }

    public void setOld_and_new_degree(String old_and_new_degree) {
        this.old_and_new_degree = old_and_new_degree;
    }

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getLocation_of_library() {
        return location_of_library;
    }

    public void setLocation_of_library(int location_of_library) {
        this.location_of_library = location_of_library;
    }


    public int getDown_shell() {
        return down_shell;
    }

    public void setDown_shell(int down_shell) {
        this.down_shell = down_shell;
    }


}
