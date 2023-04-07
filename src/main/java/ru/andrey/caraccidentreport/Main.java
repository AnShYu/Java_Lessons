package ru.andrey.caraccidentreport;

import ru.andrey.caraccidentreport.accidentcircumstances.Accident;
import ru.andrey.caraccidentreport.exceptions.DataAccessException;
import ru.andrey.caraccidentreport.parsing.PatternsKeeper;
import ru.andrey.caraccidentreport.parsing.ReportParser;
import ru.andrey.caraccidentreport.util.FileReaderUtil;
import ru.andrey.caraccidentreport.util.FileWriterUtil;
import ru.andrey.caraccidentreport.util.MakerOfListOfData;

import java.io.File;
import java.util.List;
import java.util.Map;

public class Main {
    //Пререквизиты:
    // 1. В пояснении к ДТП встречаются только данные двух участников ДТП, данных никаких других лиц нет
    // 2. Данные участников ДТП идут всегда последовательно - сначала данные составителя отчета, потом данные второго участника ДТП
    // 3. Формат даты: дд.мм.гггг или дд.мм.гг
    // 4. Формат адреса: город, улица, дом
    public static void main(String[] args) {
        File initialReport = new File("Files_for_Car_Accident_Report/Initial_Report.txt");
        File accidentParsedData = new File("Files_for_Car_Accident_Report/Parsed_Data_From_Report.txt");
        PatternsKeeper patternsKeeper = new PatternsKeeper();

        try {
            String text = FileReaderUtil.readFile(initialReport);
            Accident accident = ReportParser.makeParsing(text, patternsKeeper);
            List<String> dataForWriting = MakerOfListOfData.prepareData(accident);
            FileWriterUtil.writeListOfStringsToTheFile(dataForWriting, accidentParsedData);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }
}
