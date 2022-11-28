package ru.andrey.gym;

public class SeniorCoach extends Coach {

    // allows to chane the main coach in the group
    public void replaceMainCoach (Coach newCoach) { // но замена тренера уже предусмотрена в Group. Не знаю, как это прописать в самой Group и нужно ли
        Coach coach = newCoach; // как это будет работать с переменной Coach coach в Group?
    }

    // defines on which day shall be exam in the group
    public TrainingDay makeGroupExamination (TrainingDay dayOfExam) {
        TrainingDay groupExam = dayOfExam;
        return groupExam;
    }

}
