package package1021;

import package1021.book.BookList;
import package1021.user.Admin;
import package1021.user.NormalUser;
import package1021.user.User;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        BookList bookList = new BookList();

        User user = login();
        while(true){
            int choice = user.meun();
            user.doOperation(choice,bookList);
        }
    }
    public static User login(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入您的姓名");
        String name = scanner.next();
        System.out.println("请输入您的身份（1.普通用户  2.管理员）");
        int role = scanner.nextInt();
        if(role == 1){
            return new NormalUser(name);
        }
        return new Admin(name);
    }
}
