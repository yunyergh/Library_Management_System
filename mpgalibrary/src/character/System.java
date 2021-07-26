package character;

import com.sun.deploy.trace.Trace;
import entity.Book;
import entity.ComicBook;
import entity.NovelBook;
import entity.ProgrammingBook;
import library.BookSelf;
import library.Library;

import java.util.ArrayList;
import java.util.List;

//图书馆的系统
//职责有管理用户、管理图书、提供交互服务等
//系统全局只有一个，采用单例模式实现
//单例通俗点讲就是整个程序只能有一个实例对象，这很符合系统System的特点，故System设计为单例
//
//整个项目不存在管理员类，为什么？
//因为在这个场景下管理员不需要单独成类，管理员可以理解为系统的一种状态——即系统此时处于管理模式
//为什么说管理员是系统的一种状态？
//因为需求中的管理员并没有体现出任何差异性
//  （什么叫差异性？比如用户，每个用户有不同的属性，
//  这就是差异性，我们创建了一个个用户对象就体现了这种差异）
//但是需求中的管理员并不存在这样的特性，他只会干一件事，就是管理整个图书馆系统
//那在这样的场景下，创建多个管理员角色实例没有任何意义，不如让管理员和系统合为一体
//管理员的登场意味着可以管理用户、管理书籍，换言之，就是系统进入了一种“管理”状态
//因此，管理员的登录即系统的currentState切换为STATE_ADMIN
public class System {
    //标识当前系统的状态，是谁在操作系统
    public static final int STATE_ADMIN = 0;
    public static final int STATE_USER = 1;
    public static final int STATE_IDLE = 2;
    private static Trace out;
    public int currentState;
    public User currentUser;//标识当前用户，当且仅当currentState是STATE_USER时有效

    //系统管理着所有用户
    private final List<UserWrapper> users = new ArrayList<>();

    //系统也管理着图书馆
    private final Library library = new Library();

    //所有已创建但未上具体书架的图书以及从书架下架的图书放在这里
    private final List<Book> availableBooks = new ArrayList<>();

    //保证全程序唯一实例，所以构造函数设成私有，唯一实例通过get()获取
    private System() { }

    //返回System单例
    public static System get() {
        return SystemInner.INSTANCE;
    }

    //检查系统当前状态
    public boolean checkState(int state) {
        return state == currentState;
    }

    //第index个书架的图书上架，上架的书有两种可能的来源，用户还来的书以及availableBooks
    public void addBookToBookSelf(int index, Book book) {
        //只有管理员能操作
        if (!checkState(STATE_ADMIN)) {
            return;
        }

        if (index >= library.getBookSelvesNum()) {
            return;
        }

        library.getBookSelf(index).addBook(book);
    }

    //第index个书架的图书下架，下架的书可能是借给了用户也可能是被放入了availableBooks
    public void removeBookFromBookSelf(int bookId, int index) {
        if (!checkState(STATE_ADMIN)) {
            return;
        }

        if (index >= library.getBookSelvesNum()) {
            return;
        }

        library.getBookSelf(index).removeBook(bookId);
    }

    //创建书本，最后一个变长数组用于书本的额外信息
    public void createBook(String name, int pageNum, int type, String... others) {
        if (!checkState(STATE_ADMIN)) {
            return;
        }

        if (pageNum <= 0) {
            return;
        }

        Book book = null;
        if (type == Book.COMIC) {
            if (others.length != 2) {
                return;
            }
            book = new ComicBook(name, pageNum, Book.COMIC, others[0], others[1]);
        } else if (type == Book.PROGRAMMING) {
            if (others.length != 2) {
                return;
            }
            book = new ProgrammingBook(name, pageNum, Book.PROGRAMMING,  others[0], others[1]);
        } else if (type == Book.NOVEL) {
            if (others.length != 3) {
                return;
            }
            book = new NovelBook(name, pageNum, Book.NOVEL,  others[0], others[1], others[2]);
        }
        if (book == null) {
            return;
        }
        availableBooks.add(book);
    }

    //删除书本
    public void deleteBook(int bookId) {
        if (!checkState(STATE_ADMIN)) {
            return;
        }

        for (Book availableBook : availableBooks) {
            if (availableBook.getId() == bookId) {
                availableBooks.remove(availableBook);
                break;
            }
        }
    }

    //设置用户黑名单
    public void setUserActive(String username, boolean isActive) {
        if (!checkState(STATE_ADMIN)) {
            return;
        }

        for (UserWrapper user : users) {
            if (user.username.equals(username)) {
                user.isActive = isActive;
                if (!isActive) {
                    availableBooks.addAll(user.user.returnAllBooks());
                }
            }
        }
    }


    //管理员登录
    public void adminLogin() {
        currentState = STATE_ADMIN;
        currentUser = null;
    }

    //用户包装类，增强User的属性和功能
    //ps:从面向对象设计的角度思考为什么要在System里写这样一个包装类？直接把下面几个属性写在User里不行吗？
    public static class UserWrapper {
        //持有一个User对象
        private User user;
        //是否黑名单
        private boolean isActive;
        //用户密码
        private String password;
        //用户名（为方便起见，用户名唯一）
        private String username;

        public UserWrapper(User user, String username, String password) {
            this.user = user;
            this.isActive = true;
            this.password = password;
            this.username = username;
        }
    }

    //这个内部类用于实现System全程序唯一实例，如果看不懂为什么这样能实现单例可以先暂时不用管
    private static final class SystemInner {
        private static System INSTANCE = new System();
    }

    public void all_books_iteration(Library library) { //把所有书架上的书遍历一遍
        int size_bookSelves = library.getBookSelvesNum();
        int option;
        ComicBook comicBook;
        NovelBook novelBook;
        ProgrammingBook programmingBook;
        for(int i = 0; i < size_bookSelves; i++) {
            for(Book book : library.getBookSelf(i).getBooks()) {  //library.getBookSelf(i).getBooks()表示第i个书架上的所有书
                if(book != null) {
                    option = book.getType();
                    switch (option) {
                        case 1:
                            comicBook = (ComicBook) book;
                            comic_show(comicBook);
                            break;
                        case 2:
                            programmingBook = (ProgrammingBook) book;
                            Programming_show(programmingBook);
                            break;
                        case 3:
                            novelBook = (NovelBook) book;
                            novel_show(novelBook);
                            break;
                    }
                }
            }
        }
    }

    public void special_shell_iteration(Library library, int shell) {
        if(shell < 1 || shell > library.getBookSelvesNum()) {
            return; //说明超出范围
        }
        int option;
        ComicBook comicBook;
        NovelBook novelBook;
        ProgrammingBook programmingBook;
        for(Book book : library.getBookSelf(shell).getBooks()) {
            if(book != null) {
                option = book.getType();
                switch (option) {
                    case 1:
                        comicBook = (ComicBook) book;
                        comic_show(comicBook);
                        break;
                    case 2:
                        programmingBook = (ProgrammingBook) book;
                        Programming_show(programmingBook);
                        break;
                    case 3:
                        novelBook = (NovelBook) book;
                        novel_show(novelBook);
                        break;
                }
            }
        }
    }

    public void special_type_iteration(Library library, int type) {
        int size_bookSelves = library.getBookSelvesNum();
        ComicBook comicBook;
        NovelBook novelBook;
        ProgrammingBook programmingBook;
        switch (type) {
            case 1:
                for(int i = 0; i < size_bookSelves; i++) {
                    for(Book book : library.getBookSelf(i).getBooks()) {
                        if(book != null && book.getType() == type) {
                            comicBook = (ComicBook) book;
                            comic_show(comicBook);
                        }
                    }
                }
                break;
            case 2:
                for(int i = 0; i < size_bookSelves; i++) {
                    for(Book book : library.getBookSelf(i).getBooks()) {
                        if(book != null && book.getType() == type) {
                            programmingBook = (ProgrammingBook) book;
                            Programming_show(programmingBook);
                        }
                    }
                }
                break;
            case 3:
                for(int i = 0; i < size_bookSelves; i++) {
                    for(Book book : library.getBookSelf(i).getBooks()) {
                        if(book != null && book.getType() == type) {
                            novelBook = (NovelBook) book;
                            novel_show(novelBook);
                        }
                    }
                }
                break;
        }
    }

    private void comic_show(ComicBook book) {
        System.out.print("书名：" + book.getName() + "、页数：" + book.getPageNum() + "、新旧程度：" + book.getQuality() + "、分类：ComicBook" );
        System.out.println("、作者：" + book.getAuthor() + "、作品简介：" + book.getIntroduction());
    }

    private void novel_show(NovelBook book) {
        System.out.print("书名：" + book.getName() + "、页数：" + book.getPageNum() + "、新旧程度：" + book.getQuality() + "、分类：NovelBook");
        System.out.println("、作者：" + book.getAuthor() + "、作品简介：" + book.getIntroduction() + "男女主：" + book.getLeadRole());
    }

    private void Programming_show(ProgrammingBook book) {
        System.out.print("书名：" + book.getName() + "、页数：" + book.getPageNum() + "、新旧程度：" + book.getQuality() + "、分类：ProgrammingBook");
        System.out.println("、语言类型：" + book.getLanguage() + "、博客链接：" + book.getBlogLink());
    }

}
