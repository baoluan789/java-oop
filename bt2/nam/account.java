package bt2.nam;

import bt1.sau.Account;

public class account {
    private int id;
    private Customer customer;
    private double balance = 0.0;

    public int getId() {
        return id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public double getBalance() {
        return balance;
    }

    public account(Customer customer, int id) {
        this.customer = customer;
        this.id = id;
    }

    public account(Customer customer, int id, double balance) {
        this.customer = customer;
        this.id = id;
        this.balance = balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
    public String toString() {
        return customer.toString() +   "balance =$ " + balance;
    }
    public String getCustomerName() {
        return customer.getName();
    }
    public account deposit(double amount) {
        balance += amount;
        return this;
    }
    public account withdraw(double amount) {
        if(amount > 0 && amount < this.balance) {
            balance -= amount;
        } else {
            System.out.println("amount withdraw exceeds the current balance!");
        }
        return this;
    }
}
