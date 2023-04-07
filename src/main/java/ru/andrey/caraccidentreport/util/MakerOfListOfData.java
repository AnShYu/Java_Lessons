package ru.andrey.caraccidentreport.util;

import ru.andrey.caraccidentreport.accidentcircumstances.Accident;
import ru.andrey.caraccidentreport.accidentcircumstances.Driver;
import ru.andrey.caraccidentreport.accidentcircumstances.GeneralCircumstances;

import java.util.ArrayList;
import java.util.List;

public class MakerOfListOfData {

    public static List<String> prepareData(Accident accident) {
        Driver reportingDriver = accident.getReportingDriver();
        Driver secondDriver = accident.getSecondDriver();
        GeneralCircumstances circumstances = accident.getCircumstances();
        List<String> stringsForWritingtoFile = new ArrayList<>();

        stringsForWritingtoFile.add("Данные водителя, составившего пояснение к ДТП:"+ "\n");
        stringsForWritingtoFile.add("\n");
        stringsForWritingtoFile.add("ФИО: " + reportingDriver.getName() + "\n");
        stringsForWritingtoFile.add("Номер паспорта: " + reportingDriver.getPassportNo() + "\n");
        stringsForWritingtoFile.add("Марка и модель авто: " + reportingDriver.getCarBrandAndModel() + "\n");
        stringsForWritingtoFile.add("Номер авто: " + reportingDriver.getCarPlateNumber() + "\n");
        stringsForWritingtoFile.add("Номер полиса ОСАГО: " + reportingDriver.getOSAGONumber() + "\n");
        stringsForWritingtoFile.add("\n");
        stringsForWritingtoFile.add("Данные второго участника ДТП:" + "\n");
        stringsForWritingtoFile.add("\n");
        stringsForWritingtoFile.add("ФИО: " + secondDriver.getName() + "\n");
        stringsForWritingtoFile.add("Номер паспорта: " + secondDriver.getPassportNo() + "\n");
        stringsForWritingtoFile.add("Марка и модель авто: " + secondDriver.getCarBrandAndModel() + "\n");
        stringsForWritingtoFile.add("Номер авто: " + secondDriver.getCarPlateNumber() + "\n");
        stringsForWritingtoFile.add("Номер полиса ОСАГО: " + secondDriver.getOSAGONumber() + "\n");
        stringsForWritingtoFile.add("\n");
        stringsForWritingtoFile.add("Обстоятельства ДТП:" + "\n");
        stringsForWritingtoFile.add("\n");
        stringsForWritingtoFile.add("Адрес ДТП: " + circumstances.getAddress() + "\n");
        stringsForWritingtoFile.add("Дата ДТП: " + circumstances.getDate() + "\n");
        stringsForWritingtoFile.add("Время ДТП: " + circumstances.getTime() + "\n");
        stringsForWritingtoFile.add("Составитель пояснения признал вину в ДТП: " + circumstances.getGuiltOfTheReportingDriver() + "\n");

        return stringsForWritingtoFile;
    }
}
