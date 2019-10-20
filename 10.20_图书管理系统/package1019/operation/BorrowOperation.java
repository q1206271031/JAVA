package package1019.operation;

import package1019.book.Book;
import package1019.book.BookList;

import java.util.Scanner;

public class BorrowOperation implements IOperation{
    @Override
    public void work(BookList bookList) {
        System.out.println("借阅书籍");
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入要借阅书的编号");
        String id = scanner.next();
        for (int i = 0; i < bookList.getSize(); i++) {
            Book book = bookList.getBook(i);
            if(!book.getId().equals(id)){
                continue;
            }
            //执行具体借书操作
            if(book.isBorrowed()){
                System.out.println("这本书已经被借走了");
                break;
            }
            book.setBorrowed(true);
        }
    }
}
