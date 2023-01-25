package ru.andrey.newgym2;

import java.util.Scanner;

public class GymManager {

    String clubName;
    CoachesList coaches = new CoachesList();
    GroupsList groups = new GroupsList();
    StudentsList students = new StudentsList();

    Scanner scanner = new Scanner(System.in);

    public GymManager(String clubName) {
        this.clubName = clubName;
    }

    public static void main(String[] args) {
        GymManager gymManager = new GymManager("Рассвет");
        while(true) {
            gymManager.showMainMenu();
        }
    }

    private void showMainMenu() {
        System.out.println("Выберите, с чем вы хотите работать:");
        System.out.println("1. Тренеры");
        System.out.println("2. Группы");
        System.out.println("3. Студенты");

        int direction = scanner.nextInt();
        processDirection(direction);
    }

    private void processDirection(int direction) {
        switch (direction) {
            case 1:
                processCoaches();
                break;
            case 2:
                processGroups();
                break;
            case 3:
                processStudents();
                break;
        }
    }

    private void processCoaches() {
        System.out.println("Выберите действие:");
        System.out.println("1. Просмотреть список тренеров");
        System.out.println("2. Просмотреть информацию о тренере");
        System.out.println("3. Добавить нового тренера");
        System.out.println("4. Уволить тренера");

        int coachAction = scanner.nextInt();
        processCoachAction(coachAction);
    }

    private void processGroups(){
        System.out.println("Выберите действие:");
        System.out.println("1. Посмотреть список групп");
        System.out.println("2. Посмотреть информацию о группе");
        System.out.println("3. Создать новую группу");
        System.out.println("4. Удалить группу");
        System.out.println("5. Добавить ученика в группу");
        System.out.println("6. Удалить ученика из группы");
        System.out.println("7. Показать расписание группы");
        System.out.println("8. Добавить тренировочный день");
        System.out.println("9. Удалить тренировочный день");

        int groupAction = scanner.nextInt();
        processGroupAction(groupAction);
    }

    private void processStudents() {
        System.out.println("Выберите действие:");
        System.out.println("1. Просмотреть список учеников");
        System.out.println("2. Просмотреть информацию об ученике");
        System.out.println("3. Добавить нового ученика");
        System.out.println("4. Удалить ученика");

        int studentAction = scanner.nextInt();
        processStudentAction(studentAction);

    }

    private void processCoachAction(int coachAction) {
        switch (coachAction) {
            case 1:
                System.out.println("Список тренеров клуба " + clubName);
                coaches.displayCoachesList();
                break;
            case 2:
                System.out.println("Информация о тренере клуба " + clubName);
                coaches.displayCoachInfo();
                break;
            case 3:
                System.out.println("Создаем нового тренера клуба "  + clubName);
                coaches.addCoach();
                break;
            case 4:
                System.out.println("Удаляем тренера клуба " + clubName);
                coaches.removeCoach();
                break;
        }
    }

    private void processGroupAction(int coachAction) {
        switch (coachAction) {
            case 1:
                System.out.println("Список групп клуба " + clubName);
                groups.displayGroupsList();
                break;
            case 2:
                System.out.println("Информация о группе клуба " + clubName);
                groups.displayGroupInfo();
                break;
            case 3:
                System.out.println("Создаем новую группу клуба "  + clubName);
                groups.addGroup(coaches);
                break;
            case 4:
                System.out.println("Удаляем группу клуба "  + clubName);
                groups.removeGroup();
                break;
            case 5:
                System.out.println("Добавляем студента в группу клуба "  + clubName);
                groups.addStudentToTheGroup(students);
                break;
            case 6:
                System.out.println("Удаляем студента из группы клуба "  + clubName);
                groups.removeStudentFromTheGroup(students);
                break;
            case 7:
                System.out.println("Показать расписание группы клуба "  + clubName);
                groups.showSchedule();
                break;
            case 8:
                System.out.println("Добавить тренировочный день в расписание группы клуба "  + clubName);
                groups.addTrainingDay();
                break;
            case 9:
                System.out.println("Удалить тренировочный день из расписания группы клуба "  + clubName);
                groups.removeTrainingDay();
                break;
        }
    }

    private void processStudentAction(int coachAction) {
        switch (coachAction) {
            case 1:
                System.out.println("Список учеников клуба " + clubName);
                students.displayStudentsList();
                break;
            case 2:
                System.out.println("Информация об ученике клуба " + clubName);
                students.displayStudentInfo();
                break;
            case 3:
                System.out.println("Создаем нового ученика клуба " + clubName);
                students.addStudent();
                break;
            case 4:
                System.out.println("Удаляем ученика клуба " + clubName);
                students.removeStudent();
                break;
        }
    }
}
