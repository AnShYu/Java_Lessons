package ru.andrey.caraccidentreport.parsing;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class PatternsKeeper {
    Map<String, Pattern> patterns = new HashMap<>();
    public static final String NAME = "name";
    public static final String PASSPORTNUMBER = "passportNo";
    public static final String CARBRANDANDMODEL = "carBrandAndModel";
    public static final String CARPLATENUMBER = "carPlateNumber";
    public static final String OSAGONUMBER = "oSAGONumber";
    public static final String ADDRESS = "address";
    public static final String DATE = "date";
    public static final String TIME = "time";
    public static final String GUILT = "guilt";

    private String name = "[А-Я][а-я]*?\\s[А-Я][а-я]*?\\s[А-Я][а-я]*";
    private String passportNo = "[0-9]{4}(\\s|\\s№\\s|\\sномер\\s)[0-9]{6}";
    private String carBrandAndModel = "(марк([аиеу]|ой)|автомобил([ьяюе]|ем))\\s(?<carBrandAndModel>([А-Я][а-я]*|[A-Z][a-z]*)\\s([А-Я][а-я]*|[A-Z][a-z]*))";
    private String carPlateNumber = "[A-Za-zА-Яа-я]\\d{3}[A-Za-zА-Яа-я]{2}\\d{2,3}";
    private String oSAGONumber = "([A-ZА-Я]{3}(\\s|\\s№\\s|\\sномер\\s)\\d{10})|ОСАГО\\sнет";
    private String address = "(г\\.|гор\\.|город)(\\s|[А-Я])[А-Яа-я]*,\\s(ул\\.|улица|просп\\.|проспект|шос\\.|шоссе)(\\s|[А-Я0-9])[А-Яа-я\\d]*,\\s(д.|дом?)(\\s\\d{1,3}|\\d{1,3})";
    private String date = "\\d{2}\\.\\d{2}\\.(\\d{2}|\\d{4})";
    private String time = "(\\d{1,2}:\\d{1,2}|\\d{1,2}\\sчас\\.\\s\\d{1,2}\\sмин\\.)";
    private String guilt = "([^е]\\sпризнаю|не\\sпризнаю)";

    public PatternsKeeper() {

        this.patterns.put(NAME, Pattern.compile(name));
        this.patterns.put(PASSPORTNUMBER, Pattern.compile(passportNo));
        this.patterns.put(CARBRANDANDMODEL, Pattern.compile(carBrandAndModel));
        this.patterns.put(CARPLATENUMBER, Pattern.compile(carPlateNumber));
        this.patterns.put(OSAGONUMBER, Pattern.compile(oSAGONumber));
        this.patterns.put(ADDRESS, Pattern.compile(address));
        this.patterns.put(DATE, Pattern.compile(date));
        this.patterns.put(TIME, Pattern.compile(time));
        this.patterns.put(GUILT, Pattern.compile(guilt));
    }

    public Pattern getPattern (String patternName) { // тут же не нужно добавлять обработку какой-то ошибки?
        Pattern pattern = patterns.get(patternName);
        return pattern;
    }
}
