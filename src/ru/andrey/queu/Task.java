package ru.andrey.queu;

public class Task {
    String taskDescription;

    public Task(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    @Override
    public String toString() {
        return taskDescription;
    }
}
