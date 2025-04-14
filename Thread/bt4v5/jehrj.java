package Thread.bt4v5;

import java.util.concurrent.Semaphore;

class jehrj {
    private static final int NUM_PHILOSOPHERS = 5;
    private static final Semaphore[] chopsticks = new Semaphore[NUM_PHILOSOPHERS];
    private static final Semaphore maxDiners = new Semaphore(NUM_PHILOSOPHERS - 1); // Đảm bảo ít nhất 1 người không lấy đũa

    static {
        for (int i = 0; i < NUM_PHILOSOPHERS; i++) {
            chopsticks[i] = new Semaphore(1); // Mỗi đũa chỉ có 1 người giữ
        }
    }

    public static void main(String[] args) {
        Thread[] philosophers = new Thread[NUM_PHILOSOPHERS];

        for (int i = 0; i < NUM_PHILOSOPHERS; i++) {
            final int id = i;
            philosophers[i] = new Thread(() -> dine(id));
            philosophers[i].start();
        }

        for (Thread philosopher : philosophers) {
            try {
                philosopher.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static void dine(int id) {
        try {
            maxDiners.acquire(); // Giới hạn số người có thể lấy đũa cùng lúc
            chopsticks[id].acquire(); // Lấy đũa bên phải
            chopsticks[(id + 1) % NUM_PHILOSOPHERS].acquire(); // Lấy đũa bên trái

            System.out.println("Philosopher " + id + " has both chopsticks and is eating.");

            // Giả lập ăn trong 1 khoảng thời gian
            Thread.sleep((long) (Math.random() * 1000));

            chopsticks[id].release();
            chopsticks[(id + 1) % NUM_PHILOSOPHERS].release();
            maxDiners.release();

            System.out.println("Philosopher " + id + " has finished eating.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
