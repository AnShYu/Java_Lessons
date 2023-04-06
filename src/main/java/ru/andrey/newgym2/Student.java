package ru.andrey.newgym2;

import java.util.Date;

public class Student {

    private String name;
    private String surname;
    private int age;
    private Date lastVisitDate;
    private int numberOfVisits;

    public Student(String name, String surname, int age) {
        this.name = name;
        this.surname = surname;
        this.age = age;
    }

    public Student() {
    }

    public String returnStudentName() {
        return name;
    }

    public String returnStudentSurname() {
        return surname;
    }

    public void visit () {
        lastVisitDate = new Date();
        numberOfVisits++;
    }

    public String returnName () {
        return name;
    }

    public String returnSurname () {
        return surname;
    }

    @Override
    public String toString() {
        return "Имя и фамилия ученика: " + name + " " + surname + "; Возраст ученика: " + age;
    }
}
