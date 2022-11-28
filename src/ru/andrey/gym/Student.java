package ru.andrey.gym;

import java.util.Date;

public class Student {

    private String name;
    private String surname;
    private int age;
    private Date lastVisitDate;
    private int numberOfVisits;

    public Student (String name, String surname, int age) {
        this.name = name;
        this.surname = surname;
        this.age = age;
    }

    public void visit () {
        lastVisitDate = new Date();
        numberOfVisits++;
    }

}
