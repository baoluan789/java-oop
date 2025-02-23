package bt2.ba;

public class Book {
    private String isbn;
    private String name;
    private Author author;
    private double price = 0;
    private int qty;

    public Book(String isbn, String name, Author author, double price) {
        this.isbn = isbn;
        this.name = name;
        this.author = author;
        this.price = price;
    }

    public Book(String isbn, String name, Author author, double price, int qty) {
        this.isbn = isbn;
        this.name = name;
        this.author = author;
        this.price = price;
        this.qty = qty;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getName() {
        return name;
    }

    public Author getAuthor() {
        return author;
    }

    public double getPrice() {
        return price;
    }

    public int getQty() {
        return qty;
    }
    public String getAuthorname() {
        return author.getName();
    }
    public String toString() {
        return "book[isbn = " + isbn+ " name = " + name + author.toString() + " price = " + " qty = " +qty + "]";
    }
}
