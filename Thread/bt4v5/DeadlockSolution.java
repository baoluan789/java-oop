package Thread.bt4v5;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class DeadlockSolution {
    private static final Lock lock1 = new ReentrantLock();
    private static final Lock lock2 = new ReentrantLock();

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            while (true) {
                if (lock1.tryLock()) {
                    try {
                        System.out.println("Thread 1 locked lock1");
                        Thread.sleep(50); // Giả lập xử lý

                        if (lock2.tryLock()) {
                            try {
                                System.out.println("Thread 1 locked lock2");
                                break; // Thoát khỏi vòng lặp khi lấy đủ lock
                            } finally {
                                lock2.unlock();
                            }
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        lock1.unlock();
                    }
                }
            }
        });

        Thread t2 = new Thread(() -> {
            while (true) {
                if (lock2.tryLock()) {
                    try {
                        System.out.println("Thread 2 locked lock2");
                        Thread.sleep(50);

                        if (lock1.tryLock()) {
                            try {
                                System.out.println("Thread 2 locked lock1");
                                break;
                            } finally {
                                lock1.unlock();
                            }
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        lock2.unlock();
                    }
                }
            }
        });

        t1.start();
        t2.start();
    }
}
