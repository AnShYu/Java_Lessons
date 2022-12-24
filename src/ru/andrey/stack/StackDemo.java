package ru.andrey.stack;

import java.util.Scanner;

public class StackDemo {

    Stack<Task> stackOfTasks = new Stack();
    Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        StackDemo queuDemo = new StackDemo();
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
                Task nextTask = stackOfTasks.pop();
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
        stackOfTasks.push(newTask);
    }


}
