package ru.andrey.gym;

import java.util.ArrayList;
import java.util.Date;

public class  Group {
    private String groupName;
    private ArrayList<TrainingDay> groupSchedule;
    private Coach coach;
    private ArrayList<Student> listOfStudents;
    private final Date startDate;
    private int totalIncomeFromGroup;
    private int incomeFromGroupInThisMonth;
    private ExaminationDay dayOfExam;

    // number of students at the opening of the group
    private int initialNumberOfStudents;


    public Group(String groupName, ArrayList<TrainingDay> weekDayAndTimeOfGroup, Coach coach, Date startDate) {
        this.groupName = groupName;
        this.groupSchedule = weekDayAndTimeOfGroup;
        this.coach = coach;
        this.startDate = startDate;
    }

    public void setGroupNameGroupName (String newGroupName) {
        groupName = newGroupName;
    }

    public void addTrainingDay (int newDay, int newHour, int newMinute) {
        TrainingDay newTrainingDay = new TrainingDay(newDay, newHour, newMinute);
        groupSchedule.add(newTrainingDay);
    }

    public void deleteTrainingDay (int dayToDelete) {
        for (int i = 0; i < groupSchedule.size(); i++) {
            int dayInTheSchedule = groupSchedule.get(i).getDayOfTheWeekOnly();
            if (dayInTheSchedule == dayToDelete) {
                groupSchedule.remove(dayInTheSchedule);
            }
        }
    }

    public void deleteTrainingDay (TrainingDay dayToDelete) {
        groupSchedule.remove(dayToDelete); // как будет собираться dayToDelete?
    }

    public void appointDateOfExam (int monthOfExam, SeniorCoach examinator, int day, int hour, int minutes) {
        dayOfExam = new ExaminationDay(monthOfExam, day, hour, minutes, examinator);
    }

    public void setInitialNumberOfStudents(int initialNumberOfStudents) {
        this.initialNumberOfStudents = initialNumberOfStudents;
    }

    public void addNewStudent (Student newStudent) {
        listOfStudents.add(newStudent);
    }

    public void excludeStudent (Student excludedStudent) {
        listOfStudents.remove(excludedStudent);
    }

    // show whether the group has changed and how (grows (if + number) or decreases (if - number))
    public int calculateGroupChange () {
        int difference = listOfStudents.size() - initialNumberOfStudents;
        return difference;
    }

    public void setCoach (Coach newCoach) {
        coach = newCoach;
    }

    public void addNewPayment (int receivedSum) {
        totalIncomeFromGroup = totalIncomeFromGroup + receivedSum;
        incomeFromGroupInThisMonth = incomeFromGroupInThisMonth + receivedSum; // как обнулять при смене месяца?
    }


}