package com.example.io;

import java.io.*;
import java.util.Scanner;
public class bt2 {
    public static void main(String[]args) {
        String output = "C:\\Users\\ADMIN\\IdeaProjects\\L\\src\\btjava\\output.txt";

        Scanner sc = new Scanner(System.in);


        try {
            FileOutputStream fos = new FileOutputStream(output);
            System.out.println("nhap du lieu tu ban phi enter,nhan exit dung");

            while(true) {
                String input = sc.nextLine();
                if(input.equalsIgnoreCase("exit")) {
                    break;
                }

                byte[] data = (input + "\n").getBytes();
                fos.write(data);
            }
        } catch (FileNotFoundException e) {
            System.out.println(e);
        } catch (IOException e) {

        }
    }
}
