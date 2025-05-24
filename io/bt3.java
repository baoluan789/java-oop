package com.example.io;

import java.io.*;
public class bt3 {
    public static void main(String[] args) {
        String input = "C:\\Users\\ADMIN\\IdeaProjects\\L\\src\\btjava/input.txt";
        int line = 0;

        try {
            BufferedReader readline = new BufferedReader(new FileReader(input));

            while (readline.readLine() != null) {
                line++;
            }

            System.out.println(line);
        } catch (FileNotFoundException e){
            System.out.println(e);
        } catch (IOException e){
            System.out.println(e);
        }
    }
}
