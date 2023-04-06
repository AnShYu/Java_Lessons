package ru.andrey.gym;

public class TrainingDay {

    int weekDayNumber;
    private int dayTimeHour;
    private int dayTimeMinutes;

    public TrainingDay (int weekDayNumber, int dayTimeHour, int dayTimeMinutes) {
        this.weekDayNumber = weekDayNumber;
        this.dayTimeHour = dayTimeHour;
        this.dayTimeMinutes = dayTimeMinutes;
    }

    public int getDayOfTheWeekOnly() {
        return weekDayNumber;
    }

    @Override
    public String toString() {
        return "День: " + weekDayNumber + "; Время: " + dayTimeHour + ":" + dayTimeMinutes;
    }
}
