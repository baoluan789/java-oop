package com.example.io;

import java.io.*;

public class bt5 {
    public static void main(String[] args) {
        String a = "C:\\Users\\ADMIN\\IdeaProjects\\L\\src\\btjava\\IO";

        File dire = new File(a);

        // Kiểm tra thư mục tồn tại
        if (!dire.exists()) {
            System.out.println("Thư mục không tồn tại: " + a);
            return;
        }

        // Kiểm tra xem có phải thư mục không
        if (!dire.isDirectory()) {
            System.out.println("Đường dẫn không phải thư mục: " + a);
            return;
        }

        // Lấy danh sách file và thư mục con
        File[] files = dire.listFiles();

        // Kiểm tra thư mục trống
        if (files == null || files.length == 0) {
            System.out.println("Thư mục trống!");
            return;
        }

        // Liệt kê danh sách file và thư mục
        System.out.println("Danh sách file trong: " + a);
        for (File file : files) {
            if (file.isDirectory()) {
                System.out.println("[Thư mục] " + file.getName());
            } else {
                System.out.println("[File] " + file.getName());
            }
        }
    }
}