package package1021.operation;

import package1021.book.Book;
import package1021.book.BookList;

import java.util.Scanner;

public class ReturnOperation implements IOperation {
    @Override
    public void work(BookList bookList) {
        System.out.println("归还书籍");
        Scanner scanner =new Scanner(System.in);
        System.out.println("请输入归还的序号");
        String id = scanner.next();
        for (int i = 0; i < bookList.getSize(); i++) {
            Book book = bookList.getBooks(i);
            if(!book.getId().equals(id)){
                continue;
            }
            if(!book.isborrowed()){
                System.out.println("此书已经归还了");
            }
            book.setIsborrowed(false);
        }
    }
}
