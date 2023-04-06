package ru.andrey.newgym2;

import ru.andrey.gym.Coach;
import ru.andrey.gym.Group;
import ru.andrey.gym.Student;
import ru.andrey.gym.TrainingDay;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GroupsList {

    private List<Group> listOfGroups = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);



    public void displayGroupsList() {
        for (Group group: listOfGroups) {
            System.out.println("Группа: ");
            System.out.println(group);
        }
    }

    public void displayGroupInfo(){
        System.out.println("Введите название группы:");
        String groupName = scanner.next();
        for (Group group: listOfGroups) {
            if (group.returnGroupName().equals(groupName)) {
                System.out.println(group);
            }
        }
    }

    public void addGroup(CoachesList coaches) {
        System.out.println("Введите название группы (в формате: Группа Мальчики 7-10 лет):");
        String groupName = scanner.next();

        System.out.println("Введите имя тренера группы: ");
        String groupCoachName = scanner.next();
        System.out.println("Введите фамилию тренера группы:");
        String groupCoachSurname = scanner.next();
        for (Coach coach: coaches.convertToArray()) {
            if (coach.returnName().equals(groupCoachName) && coach.returnSurname().equals(groupCoachSurname)) {
                Coach groupCoach = coach;
                Group group = new Group(groupName, groupCoach);
                listOfGroups.add(group);
            } else {
                System.out.println("Тренер не найден");
            }
        }
    }

    public void removeGroup() {
        System.out.println("Введите название группы:");
        String groupName = scanner.next();
        for (Group group: listOfGroups) {
            if (group.returnGroupName().equals(groupName)) {
                listOfGroups.remove(group);
            }
        }
    }

    public void addStudentToTheGroup(StudentsList students) {
        System.out.println("Введите название группы:");
        String groupName = scanner.next();

        System.out.println("Введите имя ученика:");
        String name = scanner.next();

        System.out.println("Введите фамилию ученика:");
        String surname = scanner.next();

        Student newStudent = new Student();

        for (Student student: students.convertToArray()) {
            if (student.returnStudentName().equals(name) && student.returnStudentSurname().equals(surname)) {
                newStudent = student;
            }
        }

        for (Group group: listOfGroups) {
            if (group.returnGroupName().equals(groupName)) {
                group.addNewStudent(newStudent);
            }
        }
    }

    public void removeStudentFromTheGroup(StudentsList students) {
        System.out.println("Введите название группы:");
        String groupName = scanner.next();

        System.out.println("Введите имя ученика:");
        String name = scanner.next();

        System.out.println("Введите фамилию ученика:");
        String surname = scanner.next();

        Student studentToRemove = new Student();

        for (Student student: students.convertToArray()) {
            if (student.returnStudentName().equals(name) && student.returnStudentSurname().equals(surname)) {
                studentToRemove = student;
            }
        }

        for (Group group: listOfGroups) {
            if (group.returnGroupName().equals(groupName)) {
                group.excludeStudent(studentToRemove);
            }
        }
    }

    public void showSchedule() {
        System.out.println("Введите название группы:");
        String groupName = scanner.next();

        for (Group group: listOfGroups) {
            if (group.returnGroupName().equals(groupName)) {
                group.showGroupSchedule();
            }
        }
    }

    public void addTrainingDay() {
        System.out.println("Введите название группы:");
        String groupName = scanner.next();

        System.out.println("Введите день тренировки (в формате: 1 или 2 или 3 - до 7; где 1 - Пн.");
        int trainingDayOfWeek = scanner.nextInt();

        System.out.println("Введите час начала тренировки");
        int trainingHour = scanner.nextInt();

        System.out.println("Введите минуты начала тренировки");
        int trainingMinutes = scanner.nextInt();

        for (Group group : listOfGroups) {
            if (group.returnGroupName().equals(groupName)) {
                group.addTrainingDay(trainingDayOfWeek, trainingHour, trainingMinutes);
            }
            else {
                System.out.println("Группа не найдена");
            }
        }
    }

    public void removeTrainingDay() {
        System.out.println("Введите название группы:");
        String groupName = scanner.next();

        System.out.println("Введите день тренировки (в формате: 1 или 2 или 3 - до 7; где 1 - Пн.");
        int trainingDayOfWeek = scanner.nextInt();

        System.out.println("Введите час начала тренировки");
        int trainingHour = scanner.nextInt();

        System.out.println("Введите минуты начала тренировки");
        int trainingMinutes = scanner.nextInt();

        TrainingDay trainingDayToDelete = new TrainingDay(trainingDayOfWeek, trainingHour, trainingMinutes);

        for (Group group : listOfGroups) {
            if (group.returnGroupName().equals(groupName)) {
                group.deleteTrainingDay(trainingDayToDelete);
            }
        }
    }
}
