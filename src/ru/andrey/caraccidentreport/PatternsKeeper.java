package ru.andrey.caraccidentreport;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class PatternsKeeper {
    Map<String, Pattern> patterns = new HashMap<>();

    String name = "[А-Я][а-я]*?\\s[А-Я][а-я]*?\\s[А-Я][а-я]*";
    String passportNo = "[0-9]{4}(\\s|\\s№\\s|\\sномер\\s)[0-9]{6}";
    String carBrandAndModel = "(марк([аиеу]|ой)|автомобил([ьяюе]|ем))\\s(?<carBrandAndModel>([А-Я][а-я]*|[A-Z][a-z]*)\\s([А-Я][а-я]*|[A-Z][a-z]*))";
    String carPlateNumber = "[A-Za-zА-Яа-я]\\d{3}[A-Za-zА-Яа-я]{2}\\d{2,3}";
    String oSAGONumber = "([A-ZА-Я]{3}(\\s|\\s№\\s|\\sномер\\s)\\d{10}|ОСАГО\\sнет)";
    String address = "Москва"; //"(г\\.|гор\\.|город)(\\s|[А-Я])[А-Яа-я]*?,\\s(ул\\.|улица|просп\\.|проспект|шос\\.|шоссе)(\\s|[А-Я0-9])[А-Яа-я\\d]*?,\\s(д.|дом)\\d{1,3}";
    String date = "\\d{2}\\.\\d{2}\\.(\\d{2}\\D|\\d{4}\\D)";
    String time = "(\\d{1,2}:\\d{1,2}|\\d{1,2}\\sчас\\.\\s\\d{1,2}\\sмин\\.)";
    String guilt = "(\s[^н][^е]\\sпризнаю|не\\sпризнаю)";

    public PatternsKeeper() {

        this.patterns.put("name", Pattern.compile(name));
        this.patterns.put("passportNo", Pattern.compile(passportNo));
        this.patterns.put("carBrandAndModel", Pattern.compile(carBrandAndModel));
        this.patterns.put("carPlateNumber", Pattern.compile(carPlateNumber));
        this.patterns.put("oSAGONumber", Pattern.compile(oSAGONumber));
        this.patterns.put("address", Pattern.compile(address));
        this.patterns.put("date", Pattern.compile(date));
        this.patterns.put("time", Pattern.compile(time));
        this.patterns.put("guilt", Pattern.compile(guilt));
    }

    public Pattern getPattern (String patternName) {
        Pattern pattern = patterns.get(patternName);
        return pattern;
    }
}
