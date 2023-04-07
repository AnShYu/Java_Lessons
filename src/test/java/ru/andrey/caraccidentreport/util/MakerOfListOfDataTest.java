package ru.andrey.caraccidentreport.util;

import org.junit.Test;
import ru.andrey.caraccidentreport.accidentcircumstances.Accident;
import ru.andrey.caraccidentreport.accidentcircumstances.Driver;
import ru.andrey.caraccidentreport.accidentcircumstances.GeneralCircumstances;

import java.util.List;

import static org.junit.Assert.*;

public class MakerOfListOfDataTest {

    @Test
    public void prepareData() {
        Driver driver1 = new Driver();
        Driver driver2 = new Driver();
        GeneralCircumstances gc = new GeneralCircumstances();

        driver1.setName("Куликов Кирилл Викторович");
        driver1.setPassportNo("5123 123123");
        driver1.setCarBrandAndModel("Kia Ceed");
        driver1.setCarPlateNumber("A320TK99");
        driver1.setoSAGONumber("ООО 1224566230");

        driver2.setName("Петров Петр Петрович");
        driver2.setPassportNo("4515 274299");
        driver2.setCarBrandAndModel("Нисан Альмера");
        driver2.setCarPlateNumber("А355МТ150");
        driver2.setoSAGONumber("ОСАГО нет");

        gc.setAccidentAddress("г.Москва, ул. Плеханова, дом 9");
        gc.setAccidentDate("23.03.20");
        gc.setAccidentTime("21 час. 45 мин.");
        gc.setGuiltOfTheReportingDriver("признаю");

        Accident accident = new Accident(driver1, driver2, gc);

        List<String> dataForWriting = MakerOfListOfData.prepareData(accident);

        String result = "1";
        for (String string: dataForWriting) {
            result = result + string;
        }

        String expectedString = "1Данные водителя, составившего пояснение к ДТП:"+"\n"+"\n"+"ФИО: "+"Куликов Кирилл Викторович"+"\n"+
                                "Номер паспорта: "+"5123 123123"+"\n"+"Марка и модель авто: "+"Kia Ceed"+"\n"+"Номер авто: "+
                                "A320TK99"+"\n"+"Номер полиса ОСАГО: "+"ООО 1224566230"+"\n"+"\n"+"Данные второго участника ДТП:"+"\n"+"\n"+
                                "ФИО: "+"Петров Петр Петрович"+"\n"+"Номер паспорта: "+"4515 274299"+"\n"+"Марка и модель авто: "+
                                "Нисан Альмера"+"\n"+"Номер авто: "+"А355МТ150"+"\n"+"Номер полиса ОСАГО: "+"ОСАГО нет"+"\n"+"\n"+
                                "Обстоятельства ДТП:"+"\n"+"\n"+"Адрес ДТП: "+"г.Москва, ул. Плеханова, дом 9"+"\n"+"Дата ДТП: "+
                                "23.03.20"+"\n"+"Время ДТП: "+"21 час. 45 мин."+"\n"+"Составитель пояснения признал вину в ДТП: "+
                                "признаю"+"\n";

        assertEquals(result, expectedString);
    }
}