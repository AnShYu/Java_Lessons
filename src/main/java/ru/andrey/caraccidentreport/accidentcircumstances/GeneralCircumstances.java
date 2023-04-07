package ru.andrey.caraccidentreport.accidentcircumstances;

import java.util.Objects;

public class GeneralCircumstances {
    private String accidentAddress;
    private String accidentDate;
    private String accidentTime;
    private String guiltOfTheReportingDriver;


    public void setAccidentAddress(String accidentAddress) {
        this.accidentAddress = accidentAddress;
    }

    public void setAccidentDate(String accidentDate) {
        this.accidentDate = accidentDate;
    }

    public void setAccidentTime(String accidentTime) {
        this.accidentTime = accidentTime;
    }

    public void setGuiltOfTheReportingDriver(String guiltOfTheReportingDriver) {
        this.guiltOfTheReportingDriver = guiltOfTheReportingDriver;
    }

    public String getAddress() { return accidentAddress; }

    public String getDate() {
        return accidentDate;
    }

    public String getTime() {
        return accidentTime;
    }

    public String getGuiltOfTheReportingDriver() {
        return guiltOfTheReportingDriver;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GeneralCircumstances that = (GeneralCircumstances) o;
        return Objects.equals(accidentAddress, that.accidentAddress) && Objects.equals(accidentDate, that.accidentDate) && Objects.equals(accidentTime, that.accidentTime) && Objects.equals(guiltOfTheReportingDriver, that.guiltOfTheReportingDriver);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accidentAddress, accidentDate, accidentTime, guiltOfTheReportingDriver);
    }
}
