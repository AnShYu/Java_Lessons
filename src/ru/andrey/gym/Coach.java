package ru.andrey.gym;

public class Coach {
    private String name;
    private String surname;
    private int age;
    private String specialisation;
    private boolean active = true;
    private int salary;
    private int lastMonthIncome;
    private int lastMonthNumberOfStudents;

    public Coach (String name, String surname, int age, String specialisation, int salary, int lastMonthIncome, int lastMonthNumberOfStudents) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.specialisation = specialisation;
        this.salary = salary;
        this.lastMonthIncome = lastMonthIncome;
        this.lastMonthNumberOfStudents = lastMonthNumberOfStudents;
    }

    // Change status "employed" to "not employed"
    public void terminateEmployment () {
        active = false;
    }

    // Calculate how many students the coach needs next month that the gym breaks even with coach's salary
    public int calculateMinimalNewStudents () {
        int minimalNewStudents = salary / (lastMonthIncome / lastMonthNumberOfStudents);
        return minimalNewStudents;
    }




}
