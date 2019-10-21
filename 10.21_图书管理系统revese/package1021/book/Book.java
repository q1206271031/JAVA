package package1021.book;

public class Book {
    private String name;
    private String id;
    private String author;
    private int price;
    private String type;
    private boolean isborrowed;

    public Book(String name, String id, String author, int price, String type, boolean isborrowed) {
        this.name = name;
        this.id = id;
        this.author = author;
        this.price = price;
        this.type = type;
        this.isborrowed = isborrowed;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public boolean isborrowed() {
        return isborrowed;
    }

    public void setIsborrowed(boolean isborrowed) {
        this.isborrowed = isborrowed;
    }

    @Override
    public String toString() {
        return "Book{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", author='" + author + '\'' +
                ", price=" + price +
                ", type='" + type + '\'' +
                ", isborrowed=" + isborrowed +
                '}';
    }
}
