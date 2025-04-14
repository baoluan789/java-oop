package Thread.bt3;

public class Producer extends Thread{
    private Bakery bakery;

    public Producer(Bakery bakery) {
        this.bakery = bakery;
    }

    public void run() {
        while (true) {
            bakery.produce();
            try { Thread.sleep(500); } catch (InterruptedException e) { e.printStackTrace(); }
        }
    }
}

class Consumer extends Thread {
    private Bakery bakery;

    public Consumer(Bakery bakery) {
        this.bakery = bakery;
    }

    public void run() {
        while (true) {
            bakery.consume();
            try { Thread.sleep(1000); } catch (InterruptedException e) { e.printStackTrace(); }
        }
    }
}
