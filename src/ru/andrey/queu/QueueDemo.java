package ru.andrey.queu;

import java.util.Scanner;

public class QueueDemo {

    Queue<Task> queueOfTasks = new Queue();
    Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        QueueDemo queueDemo = new QueueDemo();
        while(true) {
            queueDemo.showMainMenu();
        }
    }

    private void showMainMenu() {
        System.out.println("Для добавления задачи введите 1");
        System.out.println("Для получения следующей задачи введите 2");

        int direction = scanner.nextInt();
        processAction(direction); // переименовать в процессэкшн
    }

    private void processAction(int direction) {
        switch(direction) {
            case 1:
                processEnqueue();
                break;
            case 2:
                Task nextTask = queueOfTasks.dequeue();
                if (nextTask == null) {
                    System.out.println("Больше задач нет. Отдохни");
                }
                else {
                    System.out.println(nextTask);
                }
                break;
        }
    }

    private void processEnqueue() {
        System.out.println("Введите новую задачу");
        Task newTask =  new Task(scanner.next());
        queueOfTasks.enqueue(newTask);
    }


}
