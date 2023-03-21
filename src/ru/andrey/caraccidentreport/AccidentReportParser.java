package ru.andrey.caraccidentreport;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AccidentReportParser {

    Map<String, List> initiallyParsedData = new HashMap<>();
    public AccidentReportParser(File initialReport, PatternsKeeper patternsKeeper) {
        String text = FileReaderUtil.readFile(initialReport);
        // First five lists contain two values. Value[0] relates to the first party of the accident and value[1] - to the second.
        this.initiallyParsedData.put("names", TextParserUtil.parseText(text, patternsKeeper.getPattern("name")));
        this.initiallyParsedData.put("passportNos", TextParserUtil.parseText(text, patternsKeeper.getPattern("passportNo")));
        this.initiallyParsedData.put("carBrandAndModels", TextParserUtil.parseText(text, patternsKeeper.getPattern("carBrandAndModel"), "carBrandAndModel"));
        this.initiallyParsedData.put("carPlateNumbers", TextParserUtil.parseText(text, patternsKeeper.getPattern("carPlateNumber")));
        this.initiallyParsedData.put("oSAGONumbers", TextParserUtil.parseText(text, patternsKeeper.getPattern("oSAGONumber")));
        // Four Lists below will contain only one value (since address, date, time and guilt are the same in the report)
        this.initiallyParsedData.put("addresses", TextParserUtil.parseText(text, patternsKeeper.getPattern("address")));
        this.initiallyParsedData.put("dates", TextParserUtil.parseText(text, patternsKeeper.getPattern("date")));
        this.initiallyParsedData.put("times", TextParserUtil.parseText(text, patternsKeeper.getPattern("time")));
        this.initiallyParsedData.put("guilts", TextParserUtil.parseText(text, patternsKeeper.getPattern("guilt")));
    }

    public List<String> getDataForAParty (int index) {
        List<String> names = initiallyParsedData.get("names");
        String name = names.get(index);

        List<String> passportNos = initiallyParsedData.get("passportNos");
        String passportNo = passportNos.get(index);

        List<String> carBrandAndModels = initiallyParsedData.get("carBrandAndModels");
        String carBrandAndModel = carBrandAndModels.get(index);

        List<String> carPlateNumbers = initiallyParsedData.get("carPlateNumbers");
        String carPlateNumber = carPlateNumbers.get(index);

        List<String> oSAGONumbers = initiallyParsedData.get("oSAGONumbers");
        String oSAGONumber = oSAGONumbers.get(index);

        List<String> dataForAParty = new ArrayList<>();
        dataForAParty.add(name);
        dataForAParty.add(passportNo);
        dataForAParty.add(carBrandAndModel);
        dataForAParty.add(carPlateNumber);
        dataForAParty.add(oSAGONumber);

        return dataForAParty;
    }

    public List<String> getCommonData() {
        List<String> addresses = initiallyParsedData.get("addresses");
        String address = addresses.get(0);

        List<String> dates = initiallyParsedData.get("dates");
        String date = dates.get(0);

        List<String> times = initiallyParsedData.get("times");
        String time = times.get(0);

        List<String> guilts = initiallyParsedData.get("guilts");
        String guilt = guilts.get(0);
        String pleadedGuilt = "не признаю";
        if (guilt.equals(pleadedGuilt)) {
            guilt = "нет";
        } else {
            guilt = "да";
        }

        List<String> commonData = new ArrayList<>();
        commonData.add(address);
        commonData.add(date);
        commonData.add(time);
        // Since report is made by one part of the accident he can only plead himself guilty
        commonData.add(guilt);

        return commonData;
    }



}
