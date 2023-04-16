package ru.andrey.lambdafunctions.emailcreator;

import java.util.Scanner;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

public class EmailCreator {
    private Function<String, String> greeter;
    private Consumer<String> postProcessor;
    public void setGreeter (Function<String, String> greeter) {
        this.greeter = greeter;
    }
    public void setPostProcessor(Consumer<String> postProcessor) {
        this.postProcessor = postProcessor;
    }

    public String createEmail(String text, String addressee, String sender) {
        String greeting = this.greeter.apply(addressee);
        String email = greeting + text + "\nС уважением, " + sender;
        this.postProcessor.accept(email);
        return email;
    }

    public static void main(String[] args) {
        EmailCreator emailCreator = new EmailCreator();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Выберите вариант приветствия: \n1. Уважаемый [ИО], или \n2. [Имя], привет!");
        int userInput_1 = scanner.nextInt();

        switch (userInput_1) {
            case 1:
                emailCreator.setGreeter(name -> "Уважаемый " + name + ",\n");
                break;
            case 2:
                emailCreator.setGreeter(name -> name + ", привет!\n");
                break;
            default:
                System.out.println("Неверное значение");
                break;
        }

        System.out.println("Выберите вариант завершения процесса: \n1. Вывести на экран количество слов в имейле, или \n2. Сохранить имейл в файл");
        int userInput_2 = scanner.nextInt();
        switch (userInput_2) {
            case 1:
                emailCreator.setPostProcessor(text -> {
                    int numberOfWords = 0;
                    if (text.length() != 0) {
                        for (int i = 0; i<text.length(); i++) {
                            if (text.charAt(i) == ' ') {
                                numberOfWords++;
                            }
                        }
                        numberOfWords = numberOfWords + 3;
                    }
                    System.out.println("Количество слов в имейле: " + numberOfWords);
                });
                break;
            case 2:
                emailCreator.setPostProcessor(FileBackup::makeBackup);
                break;
            default:
                System.out.println("Неверное значение");
                break;
        }

        System.out.println("Введите имя адресата:");
        scanner.nextLine();
        String addressee = scanner.nextLine();


        System.out.println("Введите имя имя отправителя:");
        String sender = scanner.nextLine();

        System.out.println("Введите текст имейла:");
        String emailText = scanner.nextLine();

        String fullEmail = emailCreator.createEmail(emailText, addressee, sender);
        System.out.println(fullEmail);


    }
}
