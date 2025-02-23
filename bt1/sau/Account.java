package bt1.sau;

public class Account {
    private String id;
    private String name;
    private int blance = 0;

    public Account(String id,String name) {
        this.id = id;
        this.name = name;
    }
    public Account(String id,String name,int blance) {
        this.id = id;
        this.name = name;
        this.blance = blance;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getBlance() {
        return blance;
    }
    public int credit(int amount) {
        return blance += amount;
    }

    public int debit(int amount) {
        if(amount <= blance) {
             blance = blance - amount;
        } else {
            System.out.println("Amount exceeded balance");
        }
        return blance;
    }
    public int transferTo(Account another,int amount) {
        if(amount <= blance && amount > 0) {
            blance -= amount;
            another.debit(amount);
        } else {
            System.out.println("Amount exceeded balance");
        }
        return blance;
    }
    public String toString() {
        return "Account[id = " + id + " name = " + name + " blance = " + blance + "]";
    }
}
