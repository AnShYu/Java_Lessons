package ru.andrey.caraccidentreport.parsing;

import ru.andrey.caraccidentreport.accidentcircumstances.Accident;
import ru.andrey.caraccidentreport.accidentcircumstances.Driver;
import ru.andrey.caraccidentreport.accidentcircumstances.GeneralCircumstances;

public class ReportParser {

    public static Accident makeParsing(String text, PatternsKeeper patternsKeeper) {
        Driver reportingDriver = new Driver();
        reportingDriver.setName(TextParserUtil.parseText(text, patternsKeeper.getPattern(PatternsKeeper.NAME)).get(0));
        reportingDriver.setPassportNo(TextParserUtil.parseText(text, patternsKeeper.getPattern(PatternsKeeper.PASSPORTNUMBER)).get(0));
        reportingDriver.setCarBrandAndModel(TextParserUtil.parseText(text, patternsKeeper.getPattern(PatternsKeeper.CARBRANDANDMODEL), "carBrandAndModel").get(0));
        reportingDriver.setCarPlateNumber(TextParserUtil.parseText(text, patternsKeeper.getPattern(PatternsKeeper.CARPLATENUMBER)).get(0));
        String oSAGONumber1 = TextParserUtil.parseText(text, patternsKeeper.getPattern(PatternsKeeper.OSAGONUMBER)).get(0);
        if (oSAGONumber1.contains("№")) {
            String[] splitedOSAGONumber = oSAGONumber1.split("№");
            oSAGONumber1 = splitedOSAGONumber[0].trim() + " " + splitedOSAGONumber[1].trim();
        }
        reportingDriver.setoSAGONumber(oSAGONumber1);

        Driver secondDriver = new Driver();
        secondDriver.setName(TextParserUtil.parseText(text, patternsKeeper.getPattern(PatternsKeeper.NAME)).get(1));
        secondDriver.setPassportNo(TextParserUtil.parseText(text, patternsKeeper.getPattern(PatternsKeeper.PASSPORTNUMBER)).get(1));
        secondDriver.setCarBrandAndModel(TextParserUtil.parseText(text, patternsKeeper.getPattern(PatternsKeeper.CARBRANDANDMODEL), "carBrandAndModel").get(1));
        secondDriver.setCarPlateNumber(TextParserUtil.parseText(text, patternsKeeper.getPattern(PatternsKeeper.CARPLATENUMBER)).get(1));
        String oSAGONumber2 = TextParserUtil.parseText(text, patternsKeeper.getPattern(PatternsKeeper.OSAGONUMBER)).get(1);
        if (oSAGONumber2.contains("№")) {
            String[] splitedOSAGONumber = oSAGONumber2.split("№");
            oSAGONumber2 = splitedOSAGONumber[0].trim() + " " + splitedOSAGONumber[1].trim();
        }
        secondDriver.setoSAGONumber(oSAGONumber2);

        GeneralCircumstances circumstances = new GeneralCircumstances();
        circumstances.setAccidentAddress(TextParserUtil.parseText(text, patternsKeeper.getPattern(PatternsKeeper.ADDRESS)).get(0));
        circumstances.setAccidentDate(TextParserUtil.parseText(text, patternsKeeper.getPattern(PatternsKeeper.DATE)).get(0));
        circumstances.setAccidentTime(TextParserUtil.parseText(text, patternsKeeper.getPattern(PatternsKeeper.TIME)).get(0));
        String guilt = TextParserUtil.parseText(text, patternsKeeper.getPattern(PatternsKeeper.GUILT)).get(0);
        if (!guilt.contains("не признаю")) {
            guilt = "признаю";
        }
        circumstances.setGuiltOfTheReportingDriver(guilt);

        Accident accident = new Accident(reportingDriver, secondDriver, circumstances);
        return accident;
    }

}
