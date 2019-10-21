package package1021.operation;

import package1021.book.Book;
import package1021.book.BookList;

import java.util.Scanner;

public class DelOperaton implements IOperation {
    @Override
    public void work(BookList bookList) {
        System.out.println("删除书籍");
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入要删除的序号");
        String id = scanner.next();
        int i = 0;
        for (; i < bookList.getSize(); i++) {
            Book book = bookList.getBooks(i);
            if(book.getId().equals(id)){
                break;
            }
        }
        if(i >= bookList.getSize()){
            System.out.println("没找到");
            return;
        }
        Book lastBook = bookList.getBooks(bookList.getSize() - 1);
        bookList.setBooks(i,lastBook);
        bookList.setSize(bookList.getSize() - 1);
        System.out.println("删除成功");
    }
}
