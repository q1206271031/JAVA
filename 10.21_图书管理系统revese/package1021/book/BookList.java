package package1021.book;

public class BookList {
    private Book[] books = new Book[100];
    private  int size;
//BookList方法的size不可遗漏
    public BookList(){
        books[0] = new Book("水浒传","003","施耐庵",200,"经典名著",false);
        books[1] = new Book("西游记","006","吴承恩",150,"经典名著",false);
        books[2] = new Book("教父","009","马里奥·普佐",400,"黑道小说",false);
        size = 3;
    }

    public void setBooks(int index, Book book) {
        books[index] = book;
    }

    public Book getBooks(int index) {
        return books[index];
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
