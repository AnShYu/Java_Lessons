package ru.andrey.gym;

import java.util.List;
import java.util.Scanner;

public class GymManager {

    String clubName;
    List<Coach> coaches;

    public GymManager(String clubName) {
        this.clubName = clubName;
    }

    public static void main(String[] args) {
        GymManager gymManager = new GymManager("Рассвет");
        gymManager.showMainMenu();
    }

    private void showMainMenu() {
        System.out.println("Выберите, с чем вы хотите работать:");
        System.out.println("1. Тренеры");
        System.out.println("2. Группы");
        System.out.println("3. Студенты");

        Scanner scanner = new Scanner(System.in);
        int direction = scanner.nextInt();
        processDirection(direction);
    }

    private void processDirection(int direction) {
        switch (direction) {
            case 1:
                processCoaches();
                break;
        }
    }

    private void processCoaches() {
        System.out.println("Выберите действие:");
        System.out.println("1. Просмотреть список тренеров");
        System.out.println("2. Просмотреть информацию о тренере");
        System.out.println("3. Добавить нового тренера");
        System.out.println("4. Уволить тренера");

        Scanner scanner = new Scanner(System.in);
        int coachAction = scanner.nextInt();
    }

    private void processCoachAction(int coachAction) {
        switch (coachAction) {
            case 1:
                displayCoachesList();
                break;
            case 3:
                processAddCoach();
                break;
        }
    }

    private void displayCoachesList() {
        System.out.println("Список тренеров клуба " + clubName);
        for (Coach coach: coaches) {
            System.out.println("Тренер:");
            System.out.println(coach);
        }
    }

    private void processAddCoach() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Создаем нового тренера...");
        System.out.println("Введите фамилию тренера:");
        String surname = scanner.next();
        System.out.println("Введите имя тренера:");
        String name = scanner.next();

        Coach coach = new Coach(name, surname, 37, "гимнастика", 1200);
        coaches.add(coach);
    }


}
