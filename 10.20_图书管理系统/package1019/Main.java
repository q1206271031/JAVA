package package1019;

import package1019.book.BookList;
import package1019.user.Admin;
import package1019.user.NormalUser;
import package1019.user.User;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //程序的入口
        //1.先准备书籍信息数据
        BookList bookList = new BookList();
        //2.创建用户(这里的多态)
        User user = login();
        //3.将进入主循环
        while(true){
            int choice = user.menu();
            user.doOperation(choice,bookList);
        }
    }
    public static User login(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("输入您的姓名");
        String name = scanner.next();
        System.out.println("请输入您的角色：（1 普通用户 2 管理员）");
        int role = scanner.nextInt();
        if(role == 1){
            return new NormalUser(name);
        }
        return new Admin(name);
    }
}
