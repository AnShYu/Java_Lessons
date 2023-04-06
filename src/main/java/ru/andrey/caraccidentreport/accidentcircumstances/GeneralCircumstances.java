package ru.andrey.caraccidentreport.accidentcircumstances;

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
}
