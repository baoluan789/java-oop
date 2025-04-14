package Thread.test;

public class test {
    public static void main(String[]args) {
        System.out.println("start");
        //c2
        Thread1 t1 = new Thread1();
        t1.start();

        //c3
        Thread2 t2 = new Thread2();
        //new Thread(t2).start();

        //boolean isAlive() kt luong song hay chet
        //void join() noi luong, luong 1 chay xong toi luong 2
        //static int activeCount() xac dinh bao nhieu luong trong chuong trinh
        //static Thread currentThread() xac dinh luong nao dang chay
        Thread t = new Thread(t2);
        t.start();
        try {
            t1.join();
            t.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("end");
     /* try {
          Thread.sleep(5000);
            t1.stop();
            t.stop();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }*/
    }
}
