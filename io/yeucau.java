package com.example.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class yeucau {
    private static final String output1 = "C:\\Users\\ADMIN\\IdeaProjects\\L\\src\\btjava/IO/output1.txt";
    private static final Object lock = new Object();

    static class Fileread extends Thread {
        private final String input;

        public Fileread(String input) {
            this.input = input;
        }

        @Override
        public void run() {
            try (BufferedReader reader = new BufferedReader(new FileReader(input))) {
                String line;

                while ((line = reader.readLine()) != null) {
                    synchronized (lock) {
                        try (BufferedWriter writer = new BufferedWriter(new FileWriter(output1, true))) {
                            writer.write("[" + input + "] " + line); // Thêm khoảng cách
                            writer.newLine();
                        }
                    }
                }
                System.out.println("Đã đọc xong file: " + input);
            } catch (FileNotFoundException e) {
                System.out.println("Lỗi: Không tìm thấy file " + input + " - " + e.getMessage());
            } catch (IOException e) {
                System.out.println("Lỗi khi đọc/ghi file " + input + " - " + e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        String[] inputFiles = {
                "C:\\Users\\ADMIN\\IdeaProjects\\L\\src\\btjava/IO/input1.txt",
                "C:\\Users\\ADMIN\\IdeaProjects\\L\\src\\btjava/IO/input2.txt"
        };

        File output = new File(output1);
        if (output.exists()) {
            output.delete();
        }

        List<Thread> threads = new ArrayList<>();

        for (String inputFile : inputFiles) {
            Thread thread = new Fileread(inputFile);
            threads.add(thread);
            thread.start();
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                System.out.println("Lỗi khi chờ luồng: " + e.getMessage());
            }
        }

        System.out.println("Đã hoàn thành việc đọc và ghi vào file tổng hợp: " + output1);
    }
}
