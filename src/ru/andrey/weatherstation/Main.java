package ru.andrey.weatherstation;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private Scanner scanner = new Scanner(System.in);
    private WeatherStation weatherStation = new WeatherStation();
    private File radarsFile = new File ("Files_for_weatherstation/Radars.txt");
    private File readingsFile = new File ("Files_for_weatherstation/RadarReadings");
    private File forecastsFile = new File ("Files_for_weatherstation/Forecasts.txt");

    private boolean stopped = false;

    public static void main(String[] args) {
        Main main = new Main();
        if (main.radarsFile.exists()) {
            main.readAllSavedRadars(main.radarsFile);
            System.out.println("Для информации: сохраненные радары прочитаны");
        }
        if (main.readingsFile.exists()) {
            main.readAllSavedRadarReadings(main.readingsFile);
            System.out.println("Для информации: сохраненные показания радаров прочитаны");
        }
        while(!main.stopped) main.showMainMenu();
    }

    private void showMainMenu() {
        System.out.println("Выберите действие");
        System.out.println("1. Добавить радар");
        System.out.println("2. Добавить показание радара");
        System.out.println("3. Получить все показания радара");
        System.out.println("4. Получить прогноз на определенную дату");
        System.out.println("5. Отметить радар как неисправный");
        System.out.println("6. Отметить радар как исправный");
        System.out.println("7. Получить список неисправных радаров");
        System.out.println("8. Завершить программу");


        int direction = scanner.nextInt();
        processDirection(direction);
    }

    public void processDirection(int direction) {
        switch (direction) {
            case 1:
                processAddRadar();
                break;
            case 2:
                processAddRadarReading();
                break;
            case 3:
                processGetAllReadingsOfTheRadar();
                break;
            case 4:
                processGetForecast();
                break;
            case 5:
                processMarkMalfunction();
                break;
            case 6:
                processFixRadar();
                break;
            case 7:
                processGetAllMalfunctioningRadars();
                break;
            case 8:
                processFinishTheProgram(radarsFile);
                break;
        }
    }

    public void processAddRadar() {
        System.out.println("Введите префикс радара");
        String uidPrefix = scanner.next();
        System.out.println("Введите название радара");
        String radarName = scanner.next();
        System.out.println("Введите широту радара");
        Float latitude = scanner.nextFloat();
        System.out.println("Введите долготу радара");
        Float longitude = scanner.nextFloat();
        System.out.println("Введите тип радара");
        String radarType = scanner.next();
        try {
            weatherStation.addRadar(uidPrefix, radarName, latitude, longitude, radarType);
        } catch (WrongRadarTypeException e) {
            e.printStackTrace();
            System.out.println("Произошла ошибка: " + e.getMessage());
            processAddRadar();
        }
    }

    public void processAddRadarReading() {
        System.out.println("Введите uid радара");
        String uid = scanner.next();
        System.out.println("Введите дату (формат: гггг-мм-дд)");
        String dateInString = scanner.next();
        LocalDate date = LocalDate.parse(dateInString);
        System.out.println("Введите показание радара");
        Float radarReading = scanner.nextFloat();
        try {
            weatherStation.addRadarReading(uid, date, radarReading);
        } catch (RadarMalfunctionException e) {
            e.printStackTrace();
            System.out.println("Произошла ошибка: " + e.getMessage());
            showMainMenu();
        } catch (WrongRadarUIDException e) {
            e.printStackTrace();
            System.out.println("Произошла ошибка: " + e.getMessage());
            processAddRadarReading();
        }
    }

    public void processGetAllReadingsOfTheRadar() {
        System.out.println("Введите uid радара");
        String uid = scanner.next();
        List<RadarReading> listOfRadarReadings = new ArrayList<>();
        try {
            listOfRadarReadings = weatherStation.getAllReadingsOfTheRadar(uid);
        } catch (RadarMalfunctionException e) {
            e.printStackTrace();
            System.out.println("Произошла ошибка: " + e.getMessage());
            showMainMenu();
        } catch (WrongRadarUIDException e) {
            e.printStackTrace();
            System.out.println("Произошла ошибка: " + e.getMessage());
            processGetAllReadingsOfTheRadar();
        }
        for (RadarReading reading: listOfRadarReadings) {
            System.out.println(reading);
        }
    }

    public void processGetForecast() {
        System.out.println("Введите дату (формат: гггг-мм-дд)");
        String dateInString = scanner.next();
        LocalDate date = LocalDate.parse(dateInString);
        Forecast forecast = weatherStation.getForecastOnCertainDate(date);
        ForecastsFileUtil.writeForecastToFile(forecast, forecastsFile);
        System.out.println(forecast);
    }

    public void processMarkMalfunction() {
        System.out.println("Введите uid радара");
        String uid = scanner.next();
        try {
            weatherStation.markRadarMalfunction(uid);
        } catch (WrongRadarUIDException e) {
            e.printStackTrace();
            System.out.println("Произошла ошибка: " + e.getMessage());
            processMarkMalfunction();
        }
    }

    public void processFixRadar() {
        System.out.println("Введите uid радара");
        String uid = scanner.next();
        try {
            weatherStation.fixRadar(uid);
        } catch (WrongRadarUIDException e) {
            e.printStackTrace();
            System.out.println("Произошла ошибка: " + e.getMessage());
            processFixRadar();
        }
    }

    private void processGetAllMalfunctioningRadars() {
        List<Radar> listOfMalfunctioningRadars = weatherStation.getListOfAllMalfunctioningRadars();
        for (Radar radar: listOfMalfunctioningRadars) {
            System.out.println(radar);
        }
    }

    private void processFinishTheProgram(File file) {
        RadarsFileUtil.writeRadarsToTheFile(weatherStation.getListOfAllRadars(), file);
        stopped = true;
    }

    private void readAllSavedRadars (File file) {
        List<Radar> listOfRadars = RadarsFileUtil.readListOfRadarsFromFile(file);
        for (Radar radar: listOfRadars) {
                weatherStation.addRadar(radar);
        }
    }

    private void readAllSavedRadarReadings (File file) {
        List<RadarReading> list = RadarReadingsFileUtil.makeListOfRadarReadings(file);
        weatherStation.addRadarReading(list);
    }
}
