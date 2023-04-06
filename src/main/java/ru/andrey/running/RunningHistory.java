package ru.andrey.running;

import java.util.ArrayList;

public class RunningHistory {
    private ArrayList<Kilometer> listOfRunningDays = new ArrayList<Kilometer>();

    // Method for adding distance
    // User must add distance every day, even if it is 0. If user skips any day, the respective day will not be in the array at all and further methods will give wrong results
    public Kilometer addRunningDistanceOfTheDay (float completedDistance) {
        Kilometer newDistance = new Kilometer (completedDistance);
        listOfRunningDays.add(newDistance);
        return newDistance;
    }

    // Method to receive distance of the given day
    // Keep in mind, that numeration of the days starts with 0 as in array.
    public Kilometer receiveDistanceOfGivenDay (int dayNumber) {
        return listOfRunningDays.get(dayNumber);
    }

    // Method to calculate average distance between two given days
    public Kilometer calculateAverageDistance (int startDay, int finishDay) {
        float totalDistance = 0;
        for (int i = startDay; i <= finishDay; i++){
            float distanceOfDay = listOfRunningDays.get(i).convertToFloat();
            totalDistance = totalDistance + distanceOfDay;
        }
        int numberOfDays = finishDay - startDay + 1;
        float averageDistance = totalDistance / numberOfDays;
        Kilometer distance = new Kilometer(averageDistance);
        return distance;
    }

    // Method to calculate average distance for the whole period
    public Kilometer calculateAverageDistance () {
        Kilometer distance = calculateAverageDistance(0, listOfRunningDays.size() - 1); // здесь не указано, к какому объекту применяется метод, это ок?
        return distance;
    }

    // Method to get the maximum distance between two given days
    public Kilometer receiveMaximumDistance (int startDay, int finishDay) {
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

    // Method to get the maximum distance for the whole period
    public Kilometer receiveMaximumDistance () {
        Kilometer distance = receiveMaximumDistance(0, listOfRunningDays.size() - 1);
        return distance;
    }

    // Method to get number of recorded days
    public int receiveNumberOfRecordedDays() {
        return listOfRunningDays.size();
    }

    // Method to get number of days when the user did not run
    public int receiveNumberOfNoRunningDays () {
        int numberOfNoRunningDays = 0;
        for (int i = 0; i <= listOfRunningDays.size(); i++) {
            if (listOfRunningDays.get(i).convertToFloat() == 0) {
                numberOfNoRunningDays ++;
            }
        }
        return numberOfNoRunningDays;
    }

}
