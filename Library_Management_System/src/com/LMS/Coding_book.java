package com.LMS;

public class Coding_book extends Book {

    private String language_type;

    private String blog_link;

    public String getLanguage_type() {
        return language_type;
    }

    public void setLanguage_type(String language_type) {
        this.language_type = language_type;
    }

    public String getBlog_link() {
        return blog_link;
    }

    public void setBlog_link(String blog_link) {
        this.blog_link = blog_link;
    }

    public void show() {
        System.out.print("book_name:" + this.getBook_name() + ' ');
        System.out.print("number_of_page:" + this.getNumber_of_page() + ' ');
        System.out.print("old_and_new_degree:" + this.getOld_and_new_degree() + ' ');
        System.out.print("classification:" + this.getClassification() + ' ');
        System.out.print("language_type:" + this.getLanguage_type() + ' ');
        System.out.print("blog_link:" + this.getBlog_link() + ' ');
        System.out.println("location:" + this.getLocation_of_library());
    }

}
