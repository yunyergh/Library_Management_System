package com.LMS;
import java.util.ArrayList;

public class User {

    private int forbid; //forbid默认为0，表示有权限，为1的时候表示没有权限

    private String user_name = new String();

    private ArrayList<Book> mybooks = new ArrayList<>(); //相当于斯人书架
    //在书这里相当于就体现了多态。

    public Book book_name_search_book(String book_name, ArrayList<Book> books) {
        for(Book book : books) {
            if(book.getBook_name().equals(book_name))
                return book;
        }
        return null;
    }

    public void all_return(ArrayList<Book> books) {
        for(Book book : mybooks) {
            if(book!=null){
                books.add(book);
                mybooks.remove(book);
            }
        }
    }

    public void find_shell_book(ArrayList<Book> books, int shell_order ,String book_name) {
        for (Book book : books) {
            if(book!=null) {
                if (book.getLocation_of_library() == shell_order) {
                    if(book.getBook_name().equals(book_name))
                    {
                        System.out.println("Find it successfully!");
                        return;
                    }
                }
            }

        }
        System.out.println("Don't find it");
    }

    public void find_all_book(ArrayList<Book> books, String book_name) { //这里是否为被借的书架是没关系的，只是一个拷贝的问题
        for (Book book : books) {
            if(book!=null) {
                if(book.getBook_name().equals(book_name))
                {
                    System.out.println("Find it successfully!");
                    return;
                }
            }
        }
        System.out.println("Don't find it");
    }

    public void find_special_type_book(ArrayList<Book> books, String type, String book_name) {
        for (Book book : books) {
            if (book.getClassification().equals(type)) {
                if(book.getBook_name().equals(book_name))
                {
                    System.out.println("Find it successfully!");
                    return;
                }
            }
        }
        System.out.println("Don't find it");
    }

    public void type_iteration() {
        //还得有开始和结束的提示。
        Cartoon_book cartoonBook;
        Coding_book codingBook;
        Novel_book novelBook;
        System.out.print("cartoon_book: ");
        for (Book book : mybooks) {
            if (book.getClassification().equals("cartoon")) {
                cartoonBook = (Cartoon_book) book;
                cartoonBook.show();
            }
        }
        System.out.print("coding_book: ");
        for (Book book : mybooks) {
            if (book.getClassification().equals("coding")) {
                codingBook = (Coding_book) book;
                codingBook.show();
            }
        }

        System.out.print("novel_book: ");
        for (Book book : mybooks) {
            if (book.getClassification().equals("novel")) {
                novelBook = (Novel_book) book;
                novelBook.show();
            }
        }

    }    
    
    public void iteration() {
        Cartoon_book cartoonBook;
        Coding_book codingBook;
        Novel_book novelBook;
        for (Book book : mybooks) {
            if(book.getClassification().equals("cartoon")) {
                cartoonBook = (Cartoon_book) book;
                cartoonBook.show();
            }
            else if(book.getClassification().equals("coding")) {
                codingBook = (Coding_book) book;
                codingBook.show();
            }
            else if(book.getClassification().equals("novel")) {
                novelBook = (Novel_book) book;
                novelBook.show();
            }
        }

    }

    public void return_book(ArrayList<Book> books,Book book_to_return) {
        books.add(book_to_return);
        mybooks.remove(book_to_return);
    }

    public void borrow_book(ArrayList<Book> books,Book book_to_borrow) {
        if(this.forbid!=1) {
            books.remove(book_to_borrow);
            mybooks.add(book_to_borrow);
        }
    }

    public int getForbid() {
        return forbid;
    }

    public void setForbid(int forbid) {
        this.forbid = forbid;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

}

/*
1. 能借书、还书，通过什么借书，就通过序号，或者通过书名（需要查找）
2. 能查看自己借的书：要求:（1）全部书的遍历（2）按书的分类查看
3. 能按多种方式查找书：要求（1）全部书的遍历（2）指定分类，查看该分类的所有书（3）指定书架，查看该书架的所有书
 */