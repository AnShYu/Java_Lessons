package ru.andrey.caraccidentreport.accidentcircumstances;

public class Accident {

    private Driver reportingDriver;
    private Driver secondDriver;
    private GeneralCircumstances circumstances;

    public Accident(Driver reportingDriver, Driver secondDriver, GeneralCircumstances circumstances) {
        this.reportingDriver = reportingDriver;
        this.secondDriver = secondDriver;
        this.circumstances = circumstances;
    }

    public Driver getReportingDriver() {
        return reportingDriver;
    }

    public Driver getSecondDriver() {
        return secondDriver;
    }

    public GeneralCircumstances getCircumstances() {
        return circumstances;
    }
}
