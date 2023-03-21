package ru.andrey.caraccidentreport;

import java.io.File;

public class Main {
    //Пререквизиты: в пояснении к ДТП встречаются только данные двух участников ДТП, данных никаких других лиц нет
    public static void main(String[] args) {
        File initialReport = new File("Files_for_Car_Accident_Report/Initial_Report.txt");       // или лучше это вынести из мэйн?
        File parsedData = new File("Files_for_Car_Accident_Report/Parsed_Data_From_Report.txt");

        PatternsKeeper patternsKeeper = new PatternsKeeper();
        AccidentReportParser reportParser = new AccidentReportParser(initialReport, patternsKeeper);

        AccidentDataWriter.writeDataToFile(parsedData, reportParser);
    }
}
