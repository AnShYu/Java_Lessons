package ru.andrey.running;

import java.util.ArrayList;

public class RunningHistory {
    private ArrayList<Kilometer> listOfRunningDays;

    public RunningHistory (ArrayList listOfRunningDays) { // нужен ли здесь вообще конструктор? Конструктор массива такой же как для обычной переменной?
        this.listOfRunningDays = listOfRunningDays;
    }

    // Method for adding distance
    // User must add distance every day, even if it is 0. If user skips any day, the respective day will not be in the array at all and further methods will give wrong results
    public float addRunningDistanceOfTheDay (float completedDistance) {
        Kilometer newDistance = new Kilometer (completedDistance);
        listOfRunningDays.add(newDistance);
    }

    // Method to receive distance of the given day
    // Keep in mind, that numeration of the days starts with 0 as in array.
    public Kilometer receiveDistanceOfGivenDay (int dayNumber) {
        Kilometer distanceOftheDay = listOfRunningDays.get(dayNumber);
        return distanceOftheDay;
    }

    // Method to calculate average distance between two given days
    public Kilometer calculateAverageDistance (int startDay, int finishDay) {
        float totalDistance = 0;
        for (int i = startDay; i <= finishDay; i++){
            float distanceOfDay = listOfRunningDays.get(i).convertToFloat();
            totalDistance = totalDistance + distanceOfDay;
        }
        int numberOfDays = finishDay - startDay + 2;
        float averageDistance = totalDistance / numberOfDays;
        Kilometer distance = new Kilometer(averageDistance);
        return distance;
    }

    // Method to calculate average distance for the whole period
    public Kilometer calculateAverageDistance () {
        float totalDistance = 0;
        for (int i = 0; i <= listOfRunningDays.size(); i++){
            float distanceOfDay = listOfRunningDays.get(i).convertToFloat();
            totalDistance = totalDistance + distanceOfDay;
        }
        int numberOfDays = 0;
        for (int i = 0; i <= listOfRunningDays.size(); i++){
            numberOfDays++;
        }
        float averageDistance = totalDistance / numberOfDays;
        Kilometer distance = new Kilometer(averageDistance);
        return distance;
    }

    // Method to get the maximum distance between two given days
    public Kilometer receiveMaximumDistance (int startDay, int finishDay) {
        float maximumDistance = 0;
        for (int i = 0; i <= listOfRunningDays.size(); i++){
            float distanceOfDay = listOfRunningDays.get(i).convertToFloat();
            if (distanceOfDay > maximumDistance) {
            maximumDistance = distanceOfDay
            }
        }
        Kilometer distance = new Kilometer(maximumDistance);
        return distance;
    }

    // Method to get the maximum distance for the whole period
    public Kilometer receiveMaximumDistance () {
        float maximumDistance = 0;
        for (int i = 0; i <= listOfRunningDays.size(); i++){
            float distanceOfDay = listOfRunningDays.get(i).convertToFloat();
            if (distanceOfDay > maximumDistance) {
                maximumDistance = distanceOfDay;
            }
        }
        Kilometer distance = new Kilometer(maximumDistance);
        return distance;
    }

    // Method to get number of recorded days
    public int receiveNumberOfRecordedDays() {
        return listOfRunningDays.size();
    }

    // Method to get number of days when the user did not run
    public int receiveNumberOfNoRuningDays () {
        int numberOfNoRuningDays = 0;
        for (int i = 0; i <= listOfRunningDays.size(); i++) {
            if (listOfRunningDays.get(i).convertToFloat() == 0) {
                numberOfNoRuningDays ++;
            }
        }
        return numberOfNoRuningDays;
    }

}
