package package1019.user;

import package1019.book.BookList;
import package1019.operation.IOperation;

abstract public class User {
    protected String name;

    //持有当前用户支持的操作对象
     protected IOperation[] operations;

    public User(String name) {
        this.name = name;
    }

    //打印操作菜单
    //支持的操作不同，菜单不同
    abstract public int menu();

    //根据用户输入的选项（menu返回结果）
    //调用相应操作对象
    //注意多态在代码中的体现
    public void doOperation(int choice, BookList bookList){
        operations[choice].work(bookList);
    }
}
