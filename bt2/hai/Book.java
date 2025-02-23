package bt2.hai;
import java.util.Arrays;

public class Book {
    private String name;
    private Author[] author;
    private double price;
    private int qty = 0;


    public Book(String name, Author[] author, double price) {
        this.name = name;
        this.author = author;
        this.price = price;
    }

    public Book(String name, Author[] author, double price, int qty) {
        this.name = name;
        this.author = author;
        this.price = price;
        this.qty = qty;
    }

    public String getName() {
        return name;
    }

    public Author[] getAuthor() {
        return author;
    }

    public double getPrice() {
        return price;
    }

    public int getQty() {
        return qty;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }
    public String toString() {
        return  "Book[name=" + name + ",authors=" + Arrays.toString(author) +
                ",price=" + price + ",qty=" + qty + "]";
    }
    public String getAuthorNames() {
        StringBuilder names = new StringBuilder();
        for (int i = 0; i < author.length; i++) {
            names.append(author[i].getName());
            if (i < author.length - 1) {
                names.append(", ");
            }
        }
        return names.toString();
    }

}
