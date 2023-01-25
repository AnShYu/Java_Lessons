package ru.andrey.newgym2;

import ru.andrey.gym.Coach;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CoachesList {

    private List<Coach> listOfCoaches = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);

    public void displayCoachesList() {
        for (Coach coach: listOfCoaches) {
            System.out.println("Тренер:");
            System.out.println(coach);
        }
    }

    public void displayCoachInfo() {
        System.out.println("Введите имя тренера");
        String coachName = scanner.next();
        System.out.println("Введите фамилию тренера");
        String coachSurname = scanner.next();

        for (Coach coach: listOfCoaches) {
            if (coach.returnName().equals(coachName) && coach.returnSurname().equals(coachSurname)) {
                System.out.println(coach);
            }
        }
    }

    public void addCoach() {
        System.out.println("Введите фамилию тренера:");
        String surname = scanner.next();
        System.out.println("Введите имя тренера:");
        String name = scanner.next();
        System.out.println("Введите возраст тренера:");
        int age = scanner.nextInt();
        System.out.println("Введите специализацию тренера:");
        String speciality = scanner.next();
        System.out.println("Введите зарплату тренера:");
        int salary = scanner.nextInt();

        Coach coach = new Coach(name, surname, age, speciality, salary);
        listOfCoaches.add(coach);
    }

    public void removeCoach() {
        System.out.println("Введите имя удаляемого тренера:");
        String coachName = scanner.next();
        System.out.println("Введите фамилию удаляемого тренера:");
        String coachSurname = scanner.next();

        for (Coach coach: listOfCoaches) {
            if (coach.returnName().equals(coachName) && coach.returnSurname().equals(coachSurname)) {
                listOfCoaches.remove(coach);
            }
        }
    }

    public Coach[] convertToArray () {
        Coach[] array = new Coach[listOfCoaches.size()];
        for (int i = 0; i<listOfCoaches.size(); i++) {
            array[i] = listOfCoaches.get(i);
        }
        return array;
    }

}
