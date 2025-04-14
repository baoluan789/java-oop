package Thread.bt1;

class MyThread extends Thread {
    String name;
    MyThread(String name) { this.name = name; }

    public void run() {
        for (int i = 1; i <= 5; i++) {
            System.out.println(name + " đang chạy: " + i);
            try { Thread.sleep(1000); } catch (InterruptedException e) { e.printStackTrace(); }
        }
    }

    public static void main(String[] args) {
        MyThread t1 = new MyThread("Thread 1");
        MyThread t2 = new MyThread("Thread 2");

        t1.start();
        t2.start();
    }
}
