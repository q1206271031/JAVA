package package1019.operation;

import package1019.book.Book;
import package1019.book.BookList;

import java.util.Scanner;

public class DelOperation implements IOperation {
    @Override
    public void work(BookList bookList) {
        System.out.println("删除书籍");
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入要删除的序号");
        String id = scanner.next();
        //先查找
        int i = 0;
        for(;i < bookList.getSize();i++){
            Book book = bookList.getBook(i);
            if(book.getId().equals(id)){
                //找到了
                break;
            }
        }
        if(i >= bookList.getSize()){
            //没找到
            return;
        }
        //处理找到了的情况
        //直接用最后一个元素覆盖当前要删除的元素
        Book lastBook = bookList.getBook(bookList.getSize() - 1);
        bookList.setBook(i,lastBook);
        bookList.setSize(bookList.getSize() - 1);
        System.out.println("删除成功");
    }
}
