package Thread.test;

public class Thread2 implements Runnable{
    Thread1 t1;

    public Thread2(Thread1 t1) {
        this.t1 = t1;
    }
    public Thread2() {

    }

    public void run() {
        System.out.println("t2 run");
        try {
            System.out.println("t1 into t2");
            t1.join();
            System.out.println("t1 finish");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        for(int i = 1;i <10;i++) {
            System.out.println("thread 5 " + i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
