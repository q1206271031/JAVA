package package1019.operation;

import package1019.book.BookList;

public class ExitOperation implements IOperation {
    @Override
    public void work(BookList bookList) {
        System.out.println("goodbye!");
        System.exit(0);
    }
}
