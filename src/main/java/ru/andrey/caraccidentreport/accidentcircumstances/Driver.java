package ru.andrey.caraccidentreport.accidentcircumstances;

public class Driver {
    private String name;
    private String passportNo;
    private String carBrandAndModel;
    private String carPlateNumber;
    private String oSAGONumber;

    public void setName(String name) {
        this.name = name;
    }

    public void setPassportNo(String passportNo) {
        this.passportNo = passportNo;
    }

    public void setCarBrandAndModel(String carBrandAndModel) {
        this.carBrandAndModel = carBrandAndModel;
    }

    public void setCarPlateNumber(String carPlateNumber) {
        this.carPlateNumber = carPlateNumber;
    }

    public void setoSAGONumber(String oSAGONumber) {
        this.oSAGONumber = oSAGONumber;
    }

    public String getName() { return name; }

    public String getPassportNo() {
        return passportNo;
    }

    public String getCarBrandAndModel() {
        return carBrandAndModel;
    }

    public String getCarPlateNumber() {
        return carPlateNumber;
    }

    public String getOSAGONumber() {
        return oSAGONumber;
    }
}
