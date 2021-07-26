import character.MySystem;
import entity.Book;
import library.BookSelf;

public class Main {
    public static void main(String[] args) {
        //调用System.get().xxx()来进行一切操作
        MySystem system = MySystem.get();

        //首先，管理员登录，然后才能创建书
        system.adminLogin();
        system.createBook("aaa",90,1,"aaa", "good"); //id从0到4

        system.createBook("bbb",80,3,"bbb", "bbb", "bbb");
        system.createBook("ccc",70,1,"ccc", "bad");
        system.createBook("ddd",60,2,"ddd", "csdn");
        system.createBook("eee",50,3,"eee", "eee", "eee");

        //创建三个书架
        BookSelf bookSelf1 = new BookSelf();
        BookSelf bookSelf2 = new BookSelf();
        BookSelf bookSelf3 = new BookSelf();
        system.getLibrary().addBookSelve(bookSelf1);
        system.getLibrary().addBookSelve(bookSelf2);
        system.getLibrary().addBookSelve(bookSelf3);

        //管理员将书上架
        system.addBookToBookSelf(0,system.bookIdForBook(0));
        system.addBookToBookSelf(0,system.bookIdForBook(1));
        system.addBookToBookSelf(1,system.bookIdForBook(2));
        system.addBookToBookSelf(2,system.bookIdForBook(3));
        system.addBookToBookSelf(2,system.bookIdForBook(4));

        //a用户注册，登录并借书
        system.userLogUp("a","a");
        system.userLogIn("a","a");
        system.getCurrentUser().returnUser().borrowBook(system.borrowBook(system.bookIdForBook(0)));
        system.getCurrentUser().returnUser().borrowBook(system.borrowBook(system.bookIdForBook(1)));
        system.getCurrentUser().returnUser().borrowBook(system.borrowBook(system.bookIdForBook(4)));

        //b用户注册，登录并借书
        system.userLogUp("b","b");
        system.userLogIn("b","b");

        system.getCurrentUser().returnUser().borrowBook(system.borrowBook(system.bookIdForBook(0)));
        system.getCurrentUser().returnUser().borrowBook(system.borrowBook(system.bookIdForBook(1)));
        system.getCurrentUser().returnUser().borrowBook(system.borrowBook(system.bookIdForBook(3)));

        //管理员登录，设置黑名单,设置新旧程度
        system.adminLogin();
        system.setUserActive("b",false);
        system.setQuality(50);

        //按三个书架的顺序输出书架上所有书
        System.out.println("按三个书架的顺序输出（从1计数）：");
        for(int i = 0; i < 3; i++) {
            System.out.println("第" + (i+1) + "个书架：");
            for(Book book : system.shellBookIteration(i)) {
                if(book != null)
                System.out.println(book.getName());
            }
            System.out.println();
        }
        System.out.println();

        //输出a的所有书
        System.out.println("输出a的所有书：");
        system.userLogIn("a","a");
        for(Book book : system.getCurrentUser().returnUser().getMyBorrowedBooks()) {
            if(book != null)
            System.out.println(book.getName());
        }
        System.out.println();

        //输出a的小说
        System.out.println("输出a的小说：");
        for(Book book : system.getCurrentUser().returnUser().getMyBorrowedBooks(3)) {
            if(book != null)
            System.out.println(book.getName());
        }
        System.out.println();

        //输出b的所有书
        System.out.println("输出b的所有书：");
        system.userLogIn("b","b");
        for(Book book : system.getCurrentUser().returnUser().getMyBorrowedBooks()) {
            if(book != null)
            System.out.println(book.getName());
        }
        System.out.println();
    }
}
