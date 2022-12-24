package ru.andrey.queu;

import java.util.Scanner;

public class QueuDemo {

    Queu<Task> queuOfTasks = new Queu();
    Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        QueuDemo queuDemo = new QueuDemo();
        while(true) {
            queuDemo.showMainMenue();
        }
    }

    private void showMainMenue() {
        System.out.println("Для добавления задачи введите 1");
        System.out.println("Для получения следующей задачи введите 2");

        int direction = scanner.nextInt();
        processDirection(direction);
    }

    private void processDirection(int direction) {
        switch(direction) {
            case 1:
                processEnque();
                break;
            case 2:
                Task nextTask = queuOfTasks.dequeu();
                if (nextTask == null) {
                    System.out.println("Больше задач нет. Отдохни");
                }
                if (nextTask != null) {
                    System.out.println(nextTask);
                }
                break;
        }
    }

    private void processEnque() {
        System.out.println("Введите новую задачу");
        Task newTask =  new Task(scanner.next());
        queuOfTasks.enqueu(newTask);
    }


}
