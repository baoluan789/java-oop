package Thread.bt1;

public class Threadbt1 {
    public static void main(String[]args) {
        int max = 10;
        PrintNumber print = new PrintNumber(max);

        Thread t2 = new Thread(() -> print.printOdd(), "Thread2");
        Thread t1 = new Thread(() -> print.printEven(), "Thread1");

        t1.start();
        t2.start();
    }
}
