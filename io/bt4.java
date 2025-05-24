package com.example.io;

import java.io.*;
import java.util.Scanner;

public class bt4 {
    public static void main(String[] args) {
        String filePath = "C:\\Users\\ADMIN\\IdeaProjects\\L\\src\\btjava/output.txt";
        Scanner scanner = new Scanner(System.in);

        // Ghi số nguyên vào file
        try (FileOutputStream fos = new FileOutputStream(filePath);
             DataOutputStream dos = new DataOutputStream(fos)) {

            System.out.println("Nhập số lượng số nguyên cần lưu:");
            int n = scanner.nextInt();

            System.out.println("Nhập " + n + " số nguyên:");
            for (int i = 0; i < n; i++) {
                int number = scanner.nextInt();
                dos.writeInt(number); // Ghi số nguyên vào file
            }

            System.out.println("Đã lưu số nguyên vào file!");

        } catch (IOException e) {
            System.out.println("Lỗi khi ghi file: " + e.getMessage());
            return;
        }

        // Đọc số nguyên từ file
        try (FileInputStream fis = new FileInputStream(filePath);
             DataInputStream dis = new DataInputStream(fis)) {

            System.out.println("Danh sách số nguyên trong file:");
            while (dis.available() > 0) {
                int number = dis.readInt(); // Đọc số nguyên từ file
                System.out.print(number + " ");
            }

        } catch (FileNotFoundException e) {
            System.out.println("Lỗi: Không tìm thấy file - " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Lỗi khi đọc file: " + e.getMessage());
        }

        scanner.close();
    }

}
