package package1021.user;

import package1021.book.BookList;
import package1021.operation.IOperation;

abstract public class User {
    protected String name;
    protected IOperation[] operations;

    public User(String name) {
        this.name = name;
    }

    abstract public int meun();

    public void doOperation(int choice, BookList bookList){
        //调用Ioperation接口
        operations[choice].work(bookList);
    }
}
