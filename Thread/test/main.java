package Thread.test;

public class main {
    public static void main(String[]args) {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = 1;i <10;i++) {
                    System.out.println("thread 1");
                }
            }
        });
        t1.start();

        new Thread(new Runnable() {
            public void run() {
                for(int i = 1;i <10;i++) {
                    System.out.println("thread 2");
                }
            }

        }).start();
        //lambda
        new Thread(() -> System.out.println("thread 3")).start();
    }
}
