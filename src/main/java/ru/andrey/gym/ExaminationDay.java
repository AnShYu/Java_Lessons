package ru.andrey.gym;

public class ExaminationDay extends TrainingDay {

    private SeniorCoach examinator; // ошибка
    private int monthOfExam;

    public ExaminationDay(int monthOfExam, int weekDayNumber, int dayTimeHour, int dayTimeMinute, SeniorCoach examinator) {
        super(weekDayNumber, dayTimeHour, dayTimeMinute);
        this.monthOfExam = monthOfExam;
        this.examinator = examinator;
    }
    public void setExaminator(SeniorCoach examinator) {
        this.examinator = examinator;
    }

    public void setMonthOfExam(int monthOfExam) {
        this.monthOfExam = monthOfExam;
    }
}
