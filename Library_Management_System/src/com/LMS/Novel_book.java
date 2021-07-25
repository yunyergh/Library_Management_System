package com.LMS;

public class Novel_book extends Book {

    private String author;

    private String synopsis; //剧情简介

    private String male_and_female_principal; //男女主

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public String getMale_and_female_principal() {
        return male_and_female_principal;
    }

    public void setMale_and_female_principal(String male_and_female_principal) {
        this.male_and_female_principal = male_and_female_principal;
    }
    public void show() {
        System.out.print("book_name:" + this.getBook_name() + ' ');
        System.out.print("number_of_page:" + this.getNumber_of_page() + ' ');
        System.out.print("old_and_new_degree:" + this.getOld_and_new_degree() + ' ');
        System.out.print("classification:" + this.getClassification() + ' ');
        System.out.print("author:" + this.getAuthor() + ' ');
        System.out.print("synopsis:" + this.getSynopsis() + ' ');
        System.out.println("male_and_female_principal:" + this.getMale_and_female_principal() + ' ');
        System.out.println("location:" + this.getLocation_of_library());
    }
}
