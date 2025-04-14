package Thread.test;

public class Thread1 extends Thread {
    public void run() {
        System.out.println("t1 run");
        for(int i = 1;i <10;i++) {
            System.out.println("thread 4 " + i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }

}
