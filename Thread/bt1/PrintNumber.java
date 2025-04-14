package Thread.bt1;

public class PrintNumber {
    private int n = 0;
    private int max;

    public PrintNumber(int max) {
        this.max = max;
    }
    public synchronized void printOdd(){
        while(n < max) {
            while (n % 2==0) {
                try {
                    wait();
                } catch (InterruptedException e) { e.printStackTrace(); }

            }
            System.out.println(Thread.currentThread().getName() + " - " + n);
            n++;
            notify();
        }
    }

    public synchronized void printEven() {
        while(n < max) {
            while(n % 2 != 0) {
                try {
                    wait();
                } catch (InterruptedException e) { e.printStackTrace();}
            }
            System.out.println(Thread.currentThread().getName() + " - " + n);
            n++;
            notify();
        }
    }
}
