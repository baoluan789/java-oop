package Thread.bt3;

public class BakerySimulation {
    public static void main(String[] args) {
        Bakery bakery = new Bakery();

        Producer producer = new Producer(bakery);
        Consumer customer1 = new Consumer(bakery);
        Consumer customer2 = new Consumer(bakery);
        Consumer customer3 = new Consumer(bakery);

        producer.start();
        customer1.start();
        customer2.start();
        customer3.start();
    }
}
