package ru.andrey.newgym;

public class Coach {
    private String name;
    private String surname;
    private int age;
    private String specialisation;
    private boolean active = true;
    private int salary;
    private int lastMonthIncome;
    private int lastMonthNumberOfStudents;

    public Coach(String name, String surname, int age, String specialisation, int salary) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.specialisation = specialisation;
        this.salary = salary;
    }

    public Coach() {
    }

    // Change status "employed" to "not employed"
    public void terminateEmployment () {
        active = false;
    }

    public void setLastMonthIncome(int lastMonthIncome) {
        this.lastMonthIncome = lastMonthIncome;
    }

    public void setLastMonthNumberOfStudents(int lastMonthNumberOfStudents) {
        this.lastMonthNumberOfStudents = lastMonthNumberOfStudents;
    }

    // Calculate how many students the coach needs next month that the gym breaks even with coach's salary
    public int calculateMinimalNewStudents () {
        int minimalNewStudents = salary / (lastMonthIncome / lastMonthNumberOfStudents);
        return minimalNewStudents;
    }

    public String returnName () {
        return name;
    }

    public String returnSurname () {
        return surname;
    }

    @Override
    public String toString() {
        return "Имя, Фамилия: " + name + surname + "; Возраст: " + age + "; Специализация: " + specialisation + "; Зарплата: " + salary;
    }
}
