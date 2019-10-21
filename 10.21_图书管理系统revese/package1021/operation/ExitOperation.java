package package1021.operation;

import package1021.book.BookList;

public class ExitOperation implements IOperation {
    @Override
    public void work(BookList bookList) {
        System.out.println("goodbye！");
        System.exit(0);
    }
}
