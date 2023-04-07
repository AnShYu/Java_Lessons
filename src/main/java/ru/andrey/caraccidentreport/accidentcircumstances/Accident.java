package ru.andrey.caraccidentreport.accidentcircumstances;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Accident accident = (Accident) o;
        return Objects.equals(reportingDriver, accident.reportingDriver) && Objects.equals(secondDriver, accident.secondDriver) && Objects.equals(circumstances, accident.circumstances);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reportingDriver, secondDriver, circumstances);
    }
}
