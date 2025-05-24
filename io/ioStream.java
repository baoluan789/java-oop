package com.example.io;

import java.io.*;
public class ioStream {
    public static void main(String[] args) {
        String fileInput = "C:\\Users\\ADMIN\\IdeaProjects\\L\\src\\btjava/IO/input.txt";
        String fileOutput = "C:\\Users\\ADMIN\\IdeaProjects\\L\\src\\btjava/IO/output.txt";

        try {
            FileInputStream fis = new FileInputStream(fileInput);
            FileOutputStream fos = new FileOutputStream(fileOutput);

            byte[] buffer = new byte[1024];
            int byteRead;

            while ((byteRead = fis.read(buffer)) != -1) {
                fos.write(buffer,0,byteRead);
            }

            fis.close();
            fos.close();
        } catch (FileNotFoundException e) {
            System.out.println(e);
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}