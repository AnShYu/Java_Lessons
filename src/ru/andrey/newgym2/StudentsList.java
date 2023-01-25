package ru.andrey.newgym2;

import ru.andrey.gym.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StudentsList {

    private List<Student> listOfStudents = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);

    public void displayStudentsList() {
        for (Student student: listOfStudents) {
            System.out.println("Ученик:");
            System.out.println(student);
        }
    }

    public void displayStudentInfo() {
        System.out.println("Введите имя ученика");
        String studentName = scanner.next();
        System.out.println("Введите фамилию ученика");
        String studentSurname = scanner.next();

        for (Student student: listOfStudents) {
            if (student.returnName().equals(studentName) && student.returnSurname().equals(studentSurname)) {
                System.out.println(student);
            }
        }
    }

    public void addStudent() {

        System.out.println("Введите фамилию ученика:");
        String surname = scanner.next();
        System.out.println("Введите имя ученика:");
        String name = scanner.next();
        System.out.println("Введите возраст ученика:");
        int age = scanner.nextInt();

        Student student = new Student (name, surname, age);
        listOfStudents.add(student);
    }

    public void removeStudent() {
        System.out.println("Введите имя удаляемого ученика:");
        String studentName = scanner.next();
        System.out.println("Введите фамилию удаляемого ученика:");
        String studentSurname = scanner.next();

        for (Student student: listOfStudents) {
            if (student.returnName().equals(studentName) && student.returnSurname().equals(studentSurname)) {
                listOfStudents.remove(student);
            }
        }
    }

    public Student[] convertToArray () {
        Student[] array = new Student[listOfStudents.size()];
        for (int i = 0; i<listOfStudents.size(); i++) {
            array[i] = listOfStudents.get(i);
        }
        return array;
    }
}
