package ru.andrey.caraccidentreport;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class AccidentDataWriter {

    public static void writeDataToFile (File parsedData, AccidentReportParser arp) {

        List<String> partyOneData = arp.getDataForAParty(0);
        String title1 = "Данные водителя, составившего пояснение к ДТП:"+ "\n";
        String space1 = "-----------\n";
        String name1 = "ФИО: " + partyOneData.get(0) + "\n";
        String passportNo1 = "Номер паспорта: " + partyOneData.get(1) + "\n";
        String carBrandAndModel1 = "Марка и модель авто: " + partyOneData.get(2) + "\n";
        String carPlateNumber1 = "Номер авто: " + partyOneData.get(3) + "\n";
        String oSAGONumber1 = "Номер полиса ОСАГО: " + partyOneData.get(4) + "\n";
        String space2 = "-----------\n";

        List<String> partyTwoData = arp.getDataForAParty(1);
        String title2 = "Данные второго участника ДТП:" + "\n";
        String space3 = "-----------\n";
        String name2 = "ФИО: " + partyTwoData.get(0) + "\n";
        String passportNo2 = "Номер паспорта: " + partyTwoData.get(1) + "\n";
        String carBrandAndModel2 = "Марка и модель авто: " + partyTwoData.get(2) + "\n";
        String carPlateNumber2 = "Номер авто: " + partyTwoData.get(3) + "\n";
        String oSAGONumber2 = "Номер полиса ОСАГО: " + partyTwoData.get(4) + "\n";
        String space4 = "-----------\n";

        List<String> commonData = arp.getCommonData();
        String title3 = "Обстоятельства ДТП:" + "\n";
        String space5 = "-----------\n";
        String address = "Адрес ДТП: " + commonData.get(0) + "\n";
        String date = "Дата ДТП: " + commonData.get(1) + "\n";
        String time = "Время ДТП: " + commonData.get(2) + "\n";
        String guilt = "Составитель пояснения признал вину в ДТП: " + commonData.get(3) + "\n";

        List<String> stringsForWritingtoFile = new ArrayList<>();
        stringsForWritingtoFile.add(title1);
        stringsForWritingtoFile.add(space1);
        stringsForWritingtoFile.add(name1);
        stringsForWritingtoFile.add(passportNo1);
        stringsForWritingtoFile.add(carBrandAndModel1);
        stringsForWritingtoFile.add(carPlateNumber1);
        stringsForWritingtoFile.add(oSAGONumber1);
        stringsForWritingtoFile.add(space2);
        stringsForWritingtoFile.add(title2);
        stringsForWritingtoFile.add(space3);
        stringsForWritingtoFile.add(name2);
        stringsForWritingtoFile.add(passportNo2);
        stringsForWritingtoFile.add(carBrandAndModel2);
        stringsForWritingtoFile.add(carPlateNumber2);
        stringsForWritingtoFile.add(oSAGONumber2);
        stringsForWritingtoFile.add(space4);
        stringsForWritingtoFile.add(title3);
        stringsForWritingtoFile.add(space5);
        stringsForWritingtoFile.add(address);
        stringsForWritingtoFile.add(date);
        stringsForWritingtoFile.add(time);
        stringsForWritingtoFile.add(guilt);

        FileWriterUtil.writeListOfStringsToTheFile(stringsForWritingtoFile, parsedData);
    }
}
