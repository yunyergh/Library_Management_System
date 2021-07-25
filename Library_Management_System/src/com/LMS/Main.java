package com.LMS;
import com.sun.org.apache.bcel.internal.generic.GOTO;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) {

        ArrayList<Book> all_book = new ArrayList<>(); //这里的all是包括书架上和书架下的意思

        ArrayList<Book> beifen_book = new ArrayList<>();

        Scanner in  = new Scanner(System.in);

        Loging_System system = new Loging_System();

        system.initiate_books(all_book); //这里引入了七本书

        system.initiate_books(beifen_book);

        User user = new User();

        Admin admin = new Admin();

        int op;

        int flag = 0 ;//对应登录界面的循环，flag1 = 1时用户登录成功，flag1 = 2时管理员登录成功

        while (true) {
            System.out.println("请输入数字对应操作，1：管理员注册 , 2：用户注册 , 3：管理员登录 , 4：用户登录 , 5：帮助");
            op = in.nextInt();
            String book_name = new String();
            String book_type = new String();
            in.nextLine(); //把前面的换行解决了。
            switch (op) {
                case 1:
                    system.admin_sign_up();
                    break;

                case 2:
                    system.user_sign_up();
                    break;

                case 3:
                    admin = system.admin_sign_in();
                    if(admin !=null) {
                        flag = 0;
                        while(flag == 0){

                            System.out.println("请输入数字对应操作，1：图书上架，2：图书下架，3：创建书，4：删除书，5：设置用户黑名单， 6:修改书本信息，7：退出");
                            op = in.nextInt();
                            in.nextLine(); //把前面的换行解决了。

                            switch (op) {
                                case 1:
                                    System.out.println("请输入要上架的书名：");
                                    book_name = in.nextLine();
                                    admin.up_shell(all_book,book_name);
                                    break;
                                case 2:
                                    System.out.println("请输入要下架的书名：");
                                    book_name = in.nextLine();
                                    admin.down_shell(all_book,book_name);
                                    break;
                                case 3:
                                    System.out.println("请输入书架号：");
                                    int shell_order = in.nextInt();
                                    in.nextLine();

                                    if(shell_order > 5 || shell_order < 1) {
                                        System.out.println("超出范围！");
                                    }
                                    else {
                                        admin.create_book(all_book,beifen_book,shell_order);
                                    }
                                    break;
                                case 4:
                                    System.out.println("请输入要删除的书名：");
                                    book_name = in.nextLine();
                                    admin.delete_book(all_book, book_name);
                                    break;
                                case 5:
                                    System.out.println("请输入要删除的用户号：");
                                    String user_name = in.nextLine();
                                    admin.ban_user(all_book, system.name_search_user(user_name));
                                    break;
                                case 6:
                                    //修改书本信息比较简单。
                                    System.out.println("请输入要修改的书名：");
                                    book_name = in.nextLine();
                                    admin.alter_book(admin.book_name_to_book(all_book,book_name));
                                    break;
                                case 7:
                                    flag = 1;
                                    break;
                            }
                        }
                    }
                    break;

                case 4:
                    user = system.user_sign_in();
                    if(user != null) {
                        int shell_order;
                        System.out.println("");
                        flag = 0;
                        while(flag == 0) {
                            System.out.println("请输入数字对应操作，1：借书 ，2：还书 ，3：查看自己借的书（全部书的遍历），4：查看自己借的书（按书的分类查看），5：查找书（全部书的遍历），6：查找书（指定分类），7：查找书（指定书架），8：退出");
                            op = in.nextInt();
                            in.nextLine();
                            switch (op) {
                                case 1:
                                    System.out.println("请输入要借的书名：");
                                    book_name = in.nextLine();
                                    user.borrow_book(all_book,user.book_name_search_book(book_name,beifen_book));
                                    break;
                                case 2:
                                    System.out.println("请输入要还的书名：");
                                    book_name = in.nextLine();
                                    user.return_book(all_book, user.book_name_search_book(book_name,beifen_book));
                                    break;
                                case 3:
                                    user.iteration();
                                    break;
                                case 4:
                                    user.type_iteration();
                                    break;
                                case 5:
                                    System.out.println("请输入要查找的书名（查找范围是所有图书馆中的书，不包含借出的）：");
                                    book_name = in.nextLine();
                                    user.find_all_book(all_book,book_name);
                                    break;
                                case 6:
                                    System.out.println("请输入要查找的书名");
                                    book_name = in.nextLine();
                                    System.out.println("请输入要查找的类型范围");
                                    book_type = in.nextLine();
                                    user.find_special_type_book(all_book, book_type, book_name);
                                    break;
                                case 7:
                                    System.out.println("请输入要查找的书名");
                                    book_name = in.nextLine();
                                    System.out.println("请输入要查找的书架号以限定范围");
                                    shell_order = in.nextInt();
                                    in.nextLine();
                                    user.find_shell_book(all_book,shell_order,book_name);
                                    break;
                                case 8:
                                    flag = 1;
                                    break;
                            }
                        }
                    }
                    break;

                case 5:
                    system.help();
                    break;
            }
        }

    }
}
