package com.LMS;

import java.util.Scanner;
import java.util.ArrayList;

public class Admin {

    Scanner in  = new Scanner(System.in);

    private String admin_name;

//    public String book_name_to_type(ArrayList<Book> books, String book_name) {
//        for(Book book : books) {
//            if(book.getBook_name().equals(book_name))
//            {
//                return book.getClassification();
//            }
//        }
//        return null;
//    }

    public Book book_name_to_book(ArrayList<Book> books, String book_name) {
        for(Book book : books) {
            if(book.getBook_name().equals(book_name))
            {
                return book;
            }
        }
        return null;
    }

    public void alter_book(Book book) {
        int option;
        String book_name = new String();
        int page;
        String new_and_old = new String();
        String classification = new String();
        String author = new String();
        String blog_link = new String();
        String language_type = new String();
        String synopsis = new String();
        String male_and_female_principal = new String();
        if(book.getClassification().equals("cartoon")) {
            Cartoon_book book_to_set = (Cartoon_book) book;
            System.out.println("请输入数字对应操作，1：修改书名，2：修改页数，3：修改新旧程度，4：修改分类，5：修改作者，6：修改剧情简介");
            option = in.nextInt();
            in.nextLine();
            switch (option) {
                case 1:
                    System.out.println("请输入新的书名");
                    book_name = in.nextLine();
                    book_to_set.setBook_name(book_name);
                    break;
                case 2:
                    System.out.println("请输入新的页数");
                    page = in.nextInt();
                    in.nextLine();
                    book_to_set.setNumber_of_page(page);
                    break;
                case 3:
                    System.out.println("请输入新的新旧程度");
                    new_and_old = in.nextLine();
                    book_to_set.setOld_and_new_degree(new_and_old);
                    break;
                case 4:
                    System.out.println("请输入新的分类");
                    classification = in.nextLine();
                    book_to_set.setClassification(classification);
                    break;
                case 5:
                    System.out.println("请输入新的作者");
                    author = in.nextLine();
                    book_to_set.setAuthor(author);
                    break;
                case 6:
                    System.out.println("请输入新的剧情简介");
                    synopsis = in.nextLine();
                    book_to_set.setSynopsis(synopsis);
                    break;
            }
        }
        else if(book.getClassification().equals("coding")) {
            Coding_book book_to_set = (Coding_book) book;
            System.out.println("请输入数字对应操作，1：修改书名，2：修改页数，3：修改新旧程度，4：修改分类，5：修改语言类型，6：修改博客链接");
            option = in.nextInt();
            in.nextLine();
            switch (option) {
                case 1:
                    System.out.println("请输入新的书名");
                    book_name = in.nextLine();
                    book_to_set.setBook_name(book_name);
                    break;
                case 2:
                    System.out.println("请输入新的页数");
                    page = in.nextInt();
                    in.nextLine();
                    book_to_set.setNumber_of_page(page);
                    break;
                case 3:
                    System.out.println("请输入新的新旧程度");
                    new_and_old = in.nextLine();
                    book_to_set.setOld_and_new_degree(new_and_old);
                    break;
                case 4:
                    System.out.println("请输入新的分类");
                    classification = in.nextLine();
                    book_to_set.setClassification(classification);
                    break;
                case 5:
                    System.out.println("请输入新的语言类型");
                    language_type = in.nextLine();
                    book_to_set.setLanguage_type(language_type);
                    break;
                case 6:
                    System.out.println("请输入新的博客链接");
                    blog_link = in.nextLine();
                    book_to_set.setBlog_link(blog_link);
                    break;
            }
        }
        else if(book.getClassification().equals("novel")) {
            Novel_book book_to_set = (Novel_book) book;
            System.out.println("请输入数字对应操作，1：修改书名，2：修改页数，3：修改新旧程度，4：修改分类，5：作者，6：男女主，7：作品简介");
            option = in.nextInt();
            in.nextLine();
            switch (option) {
                case 1:
                    System.out.println("请输入新的书名");
                    book_name = in.nextLine();
                    book_to_set.setBook_name(book_name);
                    break;
                case 2:
                    System.out.println("请输入新的页数");
                    page = in.nextInt();
                    in.nextLine();
                    book_to_set.setNumber_of_page(page);
                    break;
                case 3:
                    System.out.println("请输入新的新旧程度");
                    new_and_old = in.nextLine();
                    book_to_set.setOld_and_new_degree(new_and_old);
                    break;
                case 4:
                    System.out.println("请输入新的分类");
                    classification = in.nextLine();
                    book_to_set.setClassification(classification);
                    break;
                case 5:
                    System.out.println("请输入新的作者");
                    author = in.nextLine();
                    book_to_set.setAuthor(author);
                    break;
                case 6:
                    System.out.println("请输入新的男女主");
                    male_and_female_principal = in.nextLine();
                    book_to_set.setMale_and_female_principal(male_and_female_principal);
                    break;
            }
        }
    }


    public void delete_book(ArrayList<Book> books,String book_name) {
        for(Book book :books) {
            if(book.getBook_name().equals(book_name)) {
                books.remove(book);
                break;
            }
        }

    }

    public void create_book(ArrayList<Book> books,ArrayList<Book> beifenbooks, int shell_order) {
        System.out.println("请输入要创建的书的类型：");
        String type = in.nextLine();
        System.out.println("请输入书的名称：");
        String book_name = in.nextLine();

        if(type.equals("cartoon")) {
            Cartoon_book book =  new Cartoon_book();
            book.setLocation_of_library(shell_order);
            book.setBook_name(book_name);
            books.add(book); //这里相当于也是多态的体现
            beifenbooks.add(book);
        }
        else if(type.equals("coding")) {
            Coding_book book = new Coding_book();
            book.setLocation_of_library(shell_order);
            book.setBook_name(book_name);
            books.add(book);
            beifenbooks.add(book);
        }
        else if(type.equals("novel")) {
            Novel_book book = new Novel_book();
            book.setLocation_of_library(shell_order);
            book.setBook_name(book_name);
            books.add(book);
            beifenbooks.add(book);
        }

    }

    public void down_shell(ArrayList<Book> books, String book_name) {
        for(Book book :books) {
            if(book.getBook_name().equals(book_name)) {
                book.setDown_shell(1); //为1即下架
                break;
            }
        }
    }

    public void up_shell(ArrayList<Book> books, String book_name){
        for(Book book :books) {
            if(book.getBook_name().equals(book_name)) {
                book.setDown_shell(0); //为0即上架
                break;
            }
        }
    }

    public void ban_user(ArrayList<Book> books, User user) {
        user.all_return(books);
        user.setForbid(1);
    }

    public String getAdmin_name() {
        return admin_name;
    }

    public void setAdmin_name(String admin_name) {
        this.admin_name = admin_name;
    }

}

