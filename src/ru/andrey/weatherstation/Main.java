package ru.andrey.weatherstation;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private Scanner scanner = new Scanner(System.in);
    private WeatherStation weatherStation = new WeatherStation();

    private boolean stopped = false;

    public static void main(String[] args) {
        Main main = new Main();
        main.readAllSavedRadars("Files_for_weatherstation/Radars.txt");
        main.readAllSavedRadarReadings("Files_for_weatherstation/RadarReadings");
        while(main.stopped) main.showMainMenu();
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
                processFinishTheProgram("Files_for_weatherstation/Radars.txt");
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
        } catch (WrongRadarUIDException e) {
            e.printStackTrace();
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
        } catch (WrongRadarUIDException e) {
            e.printStackTrace();
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
        System.out.println(weatherStation.getForecastOnCertainDate(date));
    }

    public void processMarkMalfunction() {
        System.out.println("Введите uid радара");
        String uid = scanner.next();
        try {
            weatherStation.markRadarMalfunction(uid);
        } catch (WrongRadarUIDException e) {
            e.printStackTrace();
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
            processFixRadar();
        }
    }

    private void processGetAllMalfunctioningRadars() {
        List<Radar> listOfMalfunctioningRadars = weatherStation.getListOfAllMalfunctioningRadars();
        for (Radar radar: listOfMalfunctioningRadars) {
            System.out.println(radar);
        }
    }

    private void processFinishTheProgram(String fileName) {
        RadarsFileManager.writeRadarsToTheFile(weatherStation.getListOfAllRadars(), fileName);
        stopped = true;
    }

    private void readAllSavedRadars (String fileName) {
        List<Radar> listOfRadars = RadarsFileManager.readListOfRadarsFromFile(fileName);
        for (Radar radar: listOfRadars) {
            weatherStation.addRadar(radar);
        }
    }

    private void readAllSavedRadarReadings (String fileName) {
        List<RadarReading> list = RadarReadingsFileManager.makeListOfRadarReadings(fileName);
        weatherStation.addRadarReading(list);
    }
}
