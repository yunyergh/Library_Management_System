package com.LMS;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;

public class Loging_System{

    private Map<String, String> user_map = new HashMap<>();
    private Map<String, String> admin_map = new HashMap<>();
    private ArrayList<User> users = new ArrayList<>();
    private ArrayList<Admin> admins = new ArrayList<>();
    private String name = new String();
    private String password = new String();

    public void initiate_books(ArrayList<Book> books) {
        //七本书，5个架子，前两个架子两本书
        Cartoon_book book1 = new Cartoon_book();
        Coding_book book2 = new Coding_book();
        Novel_book book3 = new Novel_book();
        Cartoon_book book4 = new Cartoon_book();
        Coding_book book5 = new Coding_book();
        Novel_book book6 = new Novel_book();
        Cartoon_book book7 = new Cartoon_book();

        book1.setBook_name("book1");
        book1.setLocation_of_library(1);
        book1.setAuthor("zmx1");
        book1.setSynopsis("非常精彩");
        book1.setClassification("cartoon");
        book1.setNumber_of_page(100);
        book1.setOld_and_new_degree("very old");

        book2.setBook_name("book2");
        book2.setLocation_of_library(1);
        book2.setLanguage_type("chinese");
        book2.setBlog_link("www.baidu.com");
        book2.setClassification("coding");
        book2.setNumber_of_page(99);
        book2.setOld_and_new_degree("just new");

        book3.setBook_name("book3");
        book3.setLocation_of_library(2);
        book3.setMale_and_female_principal("Bob and Alice");
        book3.setSynopsis("很精彩就对了");
        book3.setAuthor("zmx3");
        book3.setClassification("novel");
        book3.setNumber_of_page(98);
        book3.setOld_and_new_degree("just new");

        book4.setBook_name("book4");
        book4.setLocation_of_library(2);
        book4.setAuthor("zmx4");
        book4.setSynopsis("还行吧");
        book4.setClassification("cartoon");
        book4.setNumber_of_page(97);
        book4.setOld_and_new_degree("a little old");

        book5.setBook_name("book5");
        book5.setLocation_of_library(4);
        book5.setLanguage_type("english");
        book5.setBlog_link("www.4399.com");
        book5.setClassification("coding");
        book5.setNumber_of_page(96);
        book5.setOld_and_new_degree("bad");

        book6.setBook_name("book6");
        book6.setLocation_of_library(4);
        book6.setMale_and_female_principal("Tom and Jack");
        book6.setSynopsis("太烂了");
        book6.setAuthor("zmx6");
        book6.setClassification("novel");
        book6.setNumber_of_page(95);
        book6.setOld_and_new_degree("so new");

        book7.setBook_name("book7");
        book7.setLocation_of_library(5);
        book7.setAuthor("zmx7");
        book7.setSynopsis("太差了退钱");
        book7.setClassification("cartoon");
        book7.setNumber_of_page(94);
        book7.setOld_and_new_degree("new");

        books.add(book1);
        books.add(book2);
        books.add(book3);
        books.add(book4);
        books.add(book5);
        books.add(book6);
        books.add(book7);
    }

    public Admin name_search_admin(String admin_name) {
        for(Admin admin : admins) {
            if(admin.getAdmin_name().equals(admin_name)) {
                return admin;
            }
        }
        return null;
    }

    public User name_search_user(String user_name) {
        for(User user: users) {
            if(user.getUser_name().equals(user_name)) {
                return user;
            }
        }
        return null;
    }

    private void cin(){
        Scanner in  = new Scanner(System.in);
        System.out.println("请输入账户:");
        name = in.nextLine();
        System.out.println("请输入密码:");
        password = in.nextLine();
    }

    public void user_sign_up() { //注册方法
        cin();
        user_map.put(name,password);
        User new_user = new User();
        new_user.setUser_name(name);
        users.add(new_user);
    }

    public void admin_sign_up() {
        cin();
        admin_map.put(name,password);
        Admin new_admin = new Admin();
        new_admin.setAdmin_name(name);
        admins.add(new_admin);
    }

    public User user_sign_in() {
        cin();
        if(user_map.get(name)!=null) {
            if(user_map.get(name).equals(password))
                return name_search_user(name);
            else
                return null;
        }
        else
            return null;
    }

    public Admin admin_sign_in() {
        cin();
        if(admin_map.get(name)!=null) {
            if(admin_map.get(name).equals(password))
                return name_search_admin(name);
            else
                return null;
        }
        return null;
    }

    public void help() {
        System.out.println("先注册账号再登录，当密码正确登录成功时，会进入对应控制界面；当密码错误登录失败时，回到登录界面。");
        System.out.println("书的类型一共有：cartoon、coding、novel 这三种。其中，cartoon 和 coding 各有7种属性，novel有8种属性。（属性包含了书架号）");
        System.out.println("书架范围为 1-5，超出范围会导致书的创建失败。");
    }


}
