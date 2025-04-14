package Thread.test;

public class test1 {
    public static void main(String[]args) {
        System.out.println("start");
        Thread1 t1 = new Thread1();


        Thread t2 = new Thread(new Thread2(t1));

        t1.start();

        t2.start();
        try {
            t2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }



        System.out.println("end");
    }
}
