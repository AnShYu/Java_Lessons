package ru.andrey.caraccidentreport.accidentcircumstances;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Driver driver = (Driver) o;
        return Objects.equals(name, driver.name) && Objects.equals(passportNo, driver.passportNo) && Objects.equals(carBrandAndModel, driver.carBrandAndModel) && Objects.equals(carPlateNumber, driver.carPlateNumber) && Objects.equals(oSAGONumber, driver.oSAGONumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, passportNo, carBrandAndModel, carPlateNumber, oSAGONumber);
    }
}
