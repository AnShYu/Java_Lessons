package ru.andrey.lesson2;

import java.util.Scanner;

public class Lesson2 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        float result = 0;
        String c = scanner.next();

        switch (c) {
            case ("+"):
                result = a+b;
                break;
            case ("-"):
                result = a-b;
                break;
            case ("*"):
                result = a*b;
                break;
            case ("/"):
                result = (float)a/b;
                break;
            default:
                System.out.println("Invalid Signe");
                break;


        }
        System.out.println(result + "Hello, world");


    }
}
