package ru.andrey.lambdafunctions.emailcreator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class FileBackup {

    public static void makeBackup (String text) {
        File file = new File("Files_for_EmailCreator/Emails_BackUp_File");
        try (Writer writer  = new FileWriter(file, true)) {
            writer.write("\n\n");
            writer.write(text);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
