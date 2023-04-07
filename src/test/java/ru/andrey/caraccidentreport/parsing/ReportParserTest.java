package ru.andrey.caraccidentreport.parsing;

import org.junit.Test;
import ru.andrey.caraccidentreport.accidentcircumstances.Accident;
import ru.andrey.caraccidentreport.accidentcircumstances.Driver;
import ru.andrey.caraccidentreport.accidentcircumstances.GeneralCircumstances;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.*;

public class ReportParserTest {

    @Test
    public void testNameParsing() {
        PatternsKeeper patternsKeeper = new PatternsKeeper();
        String name = "Семенов Виктор Петрович";
        Pattern pattern = patternsKeeper.getPattern(PatternsKeeper.NAME);
        Matcher matcher = pattern.matcher(name);
        if (matcher.find()) {
            assertEquals(name, matcher.group());
        } else {
            fail("Не смачилось");
        }
    }

    @Test
    public void testPassportNumberParsing() {
        PatternsKeeper patternsKeeper = new PatternsKeeper();
        String passportNumber = "4511 678923";
        Pattern pattern = patternsKeeper.getPattern(PatternsKeeper.PASSPORTNUMBER);
        Matcher matcher = pattern.matcher(passportNumber);
        if (matcher.find()) {
            assertEquals(passportNumber, matcher.group());
        } else {
            fail("Не смачилось");
        }
    }

    @Test
    public void testCarBrandAndModelParsing() {
        PatternsKeeper patternsKeeper = new PatternsKeeper();
        String carBrandAndModel = "марки Бугати Веерон";
        Pattern pattern = patternsKeeper.getPattern(PatternsKeeper.CARBRANDANDMODEL);
        Matcher matcher = pattern.matcher(carBrandAndModel);
        if (matcher.find()) {
            assertEquals("Бугати Веерон", matcher.group("carBrandAndModel"));
        } else {
            fail("Не смачилось");
        }
    }

    @Test
    public void testCarPlateNumberParsing() {
        PatternsKeeper patternsKeeper = new PatternsKeeper();
        String carPlateNumber = "Н123АО77";
        Pattern pattern = patternsKeeper.getPattern(PatternsKeeper.CARPLATENUMBER);
        Matcher matcher = pattern.matcher(carPlateNumber);
        if (matcher.find()) {
            assertEquals(carPlateNumber, matcher.group());
        } else {
            fail("Не смачилось");
        }
    }

    @Test
    public void testOSAGOParsing() {
        PatternsKeeper patternsKeeper = new PatternsKeeper();
        String oSAGONumber = "ААА 1234567890";
        Pattern pattern = patternsKeeper.getPattern(PatternsKeeper.OSAGONUMBER);
        Matcher matcher = pattern.matcher(oSAGONumber);
        if (matcher.find()) {
            assertEquals(oSAGONumber, matcher.group());
        } else {
            fail("Не смачилось");
        }
    }

    @Test
    public void testAddressParsing() {
        PatternsKeeper patternsKeeper = new PatternsKeeper();
        String address = "г.Москва, шоссе Энтузиастов, дом 182";
        Pattern pattern = patternsKeeper.getPattern(PatternsKeeper.ADDRESS);
        Matcher matcher = pattern.matcher(address);
        if (matcher.find()) {
            assertEquals(address, matcher.group());
        } else {
            fail("Не смачилось");
        }
    }

    @Test
    public void testDateParsing() {
        PatternsKeeper patternsKeeper = new PatternsKeeper();
        String date = "12.03.02";
        Pattern pattern = patternsKeeper.getPattern(PatternsKeeper.DATE);
        Matcher matcher = pattern.matcher(date);
        if (matcher.find()) {
            assertEquals(date, matcher.group());
        } else {
            fail("Не смачилось");
        }
    }

    @Test
    public void testTimeParsing() {
        PatternsKeeper patternsKeeper = new PatternsKeeper();
        String time = "12:30";
        Pattern pattern = patternsKeeper.getPattern(PatternsKeeper.TIME);
        Matcher matcher = pattern.matcher(time);
        if (matcher.find()) {
            assertEquals(time, matcher.group());
        } else {
            fail("Не смачилось");
        }
    }

    @Test
    public void testGuiltParsing() {
        PatternsKeeper patternsKeeper = new PatternsKeeper();
        String guilt = "виновынм себя признаю";
        Pattern pattern = patternsKeeper.getPattern(PatternsKeeper.GUILT);
        Matcher matcher = pattern.matcher(guilt);
        if (matcher.find()) {
            assertEquals("я признаю", matcher.group());
        } else {
            fail("Не смачилось");
        }
    }


    @Test
    public void makeParsing() {
        String text = "Я, Куликов Кирилл Викторович, паспорт 5123 № 123123, выдан МВД по гор. Москве " +
                "в ВАО, ехал 23.03.20г. на автомобиле марки Kia Ceed (а/н A320TK99, ОСАГО ООО № 1224566230) в сторону области по шоссе" +
                " Энтузиастов и по адресу г.Москва, ул. Плеханова, дом 9 в 21 час. 45 мин. попал в ДТП с автомобилем Нисан Альмера (а/н А355МТ150, ОСАГО нет), " +
                "управляемом Петровым Петром Петровичем (гражданин Российской Федерации, паспорт 4515 274299). Виновным себя признаю.";
        PatternsKeeper patternsKeeper = new PatternsKeeper();
        Accident accident = ReportParser.makeParsing(text, patternsKeeper);

        Driver driver1 = new Driver();
        Driver driver2 = new Driver();
        GeneralCircumstances gc = new GeneralCircumstances();

        driver1.setName("Куликов Кирилл Викторович");
        driver1.setPassportNo("5123 № 123123");
        driver1.setCarBrandAndModel("Kia Ceed");
        driver1.setCarPlateNumber("A320TK99");
        driver1.setoSAGONumber("ООО 1224566230");

        driver2.setName("Петровым Петром Петровичем");
        driver2.setPassportNo("4515 274299");
        driver2.setCarBrandAndModel("Нисан Альмера");
        driver2.setCarPlateNumber("А355МТ150");
        driver2.setoSAGONumber("ОСАГО нет");

        gc.setAccidentAddress("г.Москва, ул. Плеханова, дом 9");
        gc.setAccidentDate("23.03.20");
        gc.setAccidentTime("21 час. 45 мин.");
        gc.setGuiltOfTheReportingDriver("признаю");

        Accident expectedAccident = new Accident(driver1, driver2, gc);

        assertEquals(expectedAccident, accident);
    }

}