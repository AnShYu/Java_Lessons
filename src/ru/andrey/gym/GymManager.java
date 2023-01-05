package ru.andrey.gym;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class GymManager {

    String clubName;
    List<Coach> coaches = new ArrayList<Coach>();
    List<Group> groups = new ArrayList<>();
    List<Student> students = new ArrayList<>();

    Scanner scanner = new Scanner(System.in); // есть ли разница, если поставить сканнер внутри main?

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
        System.out.println("1.1 Просмотреть список тренеров");
        System.out.println("1.2. Просмотреть информацию о тренере");
        System.out.println("1.3. Добавить нового тренера");
        System.out.println("1.4. Уволить тренера");

        int coachAction = scanner.nextInt();
        processCoachAction(coachAction);
    }

    private void processGroups(){
        System.out.println("Выберите действие:");
        System.out.println("2.1. Посмотреть список групп");
        System.out.println("2.2. Посмотреть информацию о группе");
        System.out.println("2.3. Создать новую группу");
        System.out.println("2.4. Удалить группу");
        System.out.println("2.5. Добавить ученика в группу");
        System.out.println("2.6. Удалить ученика из группы");
        System.out.println("2.7. Показать расписание группы");
        System.out.println("2.8. Добавить тренировочный день");
        System.out.println("2.9. Удалить тренировочный день");

        int groupAction = scanner.nextInt();
        processGroupAction(groupAction);
    }

    private void processStudents() {
        System.out.println("Выберите действие:");
        System.out.println("3.1. Просмотреть список учеников");
        System.out.println("3.2. Просмотреть информацию об ученике");
        System.out.println("3.3. Добавить нового ученика");
        System.out.println("3.4. Удалить ученика");

        int studentAction = scanner.nextInt();
        processStudentAction(studentAction);

    }

    private void processCoachAction(int coachAction) {
        switch (coachAction) {
            case 1:
                displayCoachesList();
                break;
            case 2:
                displayCoachInfo();
                break;
            case 3:
                processAddCoach();
                break;
            case 4:
                processRemoveCoach();
                break;
        }
    }

    private void processGroupAction(int coachAction) {
        switch (coachAction) {
            case 1:
                displayGroupsList();
                break;
            case 2:
                displayGroupInfo();
                break;
            case 3:
                processAddGroup();
                break;
            case 4:
                processRemoveGroup();
                break;
            case 5:
                processAddStudentToTheGroup();
                break;
            case 6:
                processRemoveStudentFromTheGroup();
                break;
            case 7:
                processShowSchedule();
                break;
            case 8:
                processAddTrainingDay();
                break;
            case 9:
                processRemoveTrainingDay();
                break;
        }
    }

    private void processStudentAction(int coachAction) {
        switch (coachAction) {
            case 1:
                displayStudentsList();
                break;
            case 2:
                displayStudentInfo();
                break;
            case 3:
                processAddStudent();
                break;
            case 4:
                processRemoveStudent();
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

    private void displayCoachInfo() {
        System.out.println("Введите имя тренера");
        String coachName = scanner.next();
        System.out.println("Введите фамилию тренера");
        String coachSurname = scanner.next();

        for (Coach coach: coaches) {
            if (coach.returnName().equals(coachName) && coach.returnSurname().equals(coachSurname)) {
                System.out.println(coach);
            }
        }
    }

    private void processAddCoach() {
        System.out.println("Создаем нового тренера...");
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
        coaches.add(coach);
    }

    private void processRemoveCoach() {
        System.out.println("Введите имя удаляемого тренера:");
        String coachName = scanner.next();
        System.out.println("Введите фамилию удаляемого тренера:");
        String coachSurname = scanner.next();

        for (Coach coach: coaches) {
            if (coach.returnName().equals(coachName) && coach.returnSurname().equals(coachSurname)) {
                coaches.remove(coach);
            }
        }
    }

    private void displayGroupsList(){
        System.out.println("Список групп клуба " + clubName);
        for (Group group: groups) {
            System.out.println("Группа: ");
            System.out.println(group);
        }
    }

    private void displayGroupInfo(){
        System.out.println("Введите название группы:");
        String groupName = scanner.next();
        for (Group group: groups) {
            if (group.returnGroupName().equals(groupName)) {
                System.out.println(group); // покажет ли это расписание?
            }
        }
    }

    private void processAddGroup() {
        System.out.println("Введите название группы (в формате: Группа Мальчики 7-10 лет):");
        String groupName = scanner.next();

        System.out.println("Введите имя тренера группы: ");
        String groupCoachName = scanner.next();
        System.out.println("Введите фамилию тренера группы:");
        String groupCoachSurname = scanner.next();
        for (Coach coach: coaches)
            if (coach.returnName().equals(groupCoachName) && coach.returnSurname().equals(groupCoachSurname)) {
                Coach groupCoach = coach;
                Group group = new Group (groupName, groupCoach);
                groups.add(group);
            }
        else {
                System.out.println("Тренер не найден");
            }
        //System.out.println("Введите дату начала тренировок:");
        //Date groupStartingDate = scanner.nextDate; // ОШИБКА. Тип Date


    }

    private void processRemoveGroup() {
        System.out.println("Введите название группы:");
        String groupName = scanner.next();
        for (Group group: groups) {
            if (group.returnGroupName().equals(groupName)) {
                groups.remove(group);
            }
        }
    }

    private void processAddStudentToTheGroup() {
        System.out.println("Введите название группы:");
        String groupName = scanner.next();

        System.out.println("Введите имя ученика:");
        String name = scanner.next();

        System.out.println("Введите фамилию ученика:");
        String surname = scanner.next();

        Student newStudent = new Student();

        for (Student student: students) {
            if (student.returnStudentName().equals(name) && student.returnStudentSurname().equals(surname)) {
                newStudent = student;
            }
        }

        for (Group group: groups) {
            if (group.returnGroupName().equals(groupName)) {
                group.addNewStudent(newStudent);
            }
        }
    }

    private void processRemoveStudentFromTheGroup() {
        System.out.println("Введите название группы:");
        String groupName = scanner.next();

        System.out.println("Введите имя ученика:");
        String name = scanner.next();

        System.out.println("Введите фамилию ученика:");
        String surname = scanner.next();

        Student studentToRemove = new Student();

        for (Student student: students) {
            if (student.returnStudentName().equals(name) && student.returnStudentSurname().equals(surname)) {
                studentToRemove = student;
            }
        }

        for (Group group: groups) {
            if (group.returnGroupName().equals(groupName)) {
                group.excludeStudent(studentToRemove);
            }
        }
    }

    private void processShowSchedule() {
        System.out.println("Введите название группы:");
        String groupName = scanner.next();

        for (Group group: groups) {
            if (group.returnGroupName().equals(groupName)) {
                group.showGroupSchedule();
            }
        }
    }

    private void processAddTrainingDay() {
        System.out.println("Введите название группы:");
        String groupName = scanner.next();

        System.out.println("Введите день тренировки (в формате: 1 или 2 или 3 - до 7; где 1 - Пн.");
        int trainingDayOfWeek = scanner.nextInt();

        System.out.println("Введите час начала тренировки");
        int trainingHour = scanner.nextInt();

        System.out.println("Введите минуты начала тренировки");
        int trainingMinutes = scanner.nextInt();

        for (Group group : groups) {
            if (group.returnGroupName().equals(groupName)) {
                group.addTrainingDay(trainingDayOfWeek, trainingHour, trainingMinutes);
            }
            else {
                System.out.println("Группа не найдена");
            }
        }
    }

    private void processRemoveTrainingDay() {
        System.out.println("Введите название группы:");
        String groupName = scanner.next();

        System.out.println("Введите день тренировки (в формате: 1 или 2 или 3 - до 7; где 1 - Пн.");
        int trainingDayOfWeek = scanner.nextInt();

        System.out.println("Введите час начала тренировки");
        int trainingHour = scanner.nextInt();

        System.out.println("Введите минуты начала тренировки");
        int trainingMinutes = scanner.nextInt();

        TrainingDay trainingDayToDelete = new TrainingDay(trainingDayOfWeek, trainingHour, trainingMinutes);

        for (Group group : groups) {
            if (group.returnGroupName().equals(groupName)) {
                group.deleteTrainingDay(trainingDayToDelete);
            }
        }
    }

    private void displayStudentsList() {
        System.out.println("Список учеников клуба " + clubName);
        for (Student student: students) {
            System.out.println("Ученик:");
            System.out.println(student);
        }
    }
    private void displayStudentInfo() {
        System.out.println("Введите имя ученика");
        String studentName = scanner.next();
        System.out.println("Введите фамилию ученика");
        String studentSurname = scanner.next();

        for (Student student: students) {
            if (student.returnName().equals(studentName) && student.returnSurname().equals(studentSurname)) {
                System.out.println(student);
            }
        }
    }

    private void processAddStudent() {
        System.out.println("Создаем нового ученика...");
        System.out.println("Введите фамилию ученика:");
        String surname = scanner.next();
        System.out.println("Введите имя ученика:");
        String name = scanner.next();
        System.out.println("Введите возраст ученика:");
        int age = scanner.nextInt();

        Student student = new Student (name, surname, age);
        students.add(student);
    }

    private void processRemoveStudent() {
        System.out.println("Введите имя удаляемого ученика:");
        String studentName = scanner.next();
        System.out.println("Введите фамилию удаляемого ученика:");
        String studentSurname = scanner.next();

        for (Student student: students) {
            if (student.returnName().equals(studentName) && student.returnSurname().equals(studentSurname)) {
                students.remove(student); // как я понимаю, он удалится из массива. А сам объект останется, пока его не удалит габэдж коллектор. Предлагает заменить на Collection.removelf
            }
        }
    }

}
