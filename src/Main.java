import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Main {

    static StringBuilder stringBuilder = new StringBuilder();

    public static void main(String[] args) {

        List<String> listDir
                = Arrays.asList("src", "res", "savegames", "temp", "main", "test", "drawables", "vectors", "icons");
        for (String nameDir : listDir) {
            if (nameDir.equals("src") || nameDir.equals("res") || nameDir.equals("savegames") || nameDir.equals("temp")) {
                directoryCreationMethod("C://Games/" + nameDir);
            }
            if (nameDir.equals("main") || nameDir.equals("test")) {
                directoryCreationMethod("C://Games/src/" + nameDir);
            }
            if (nameDir.equals("drawables") || nameDir.equals("vectors") || nameDir.equals("icons")) {
                directoryCreationMethod("C://Games/res/" + nameDir);
            }
        }

        fileCreationMethod("C://Games/temp", "temp.txt");

        List<String> listFileDirMain = Arrays.asList("Main.java", "Utils.java");
        for (String nameFile : listFileDirMain) {
            fileCreationMethod("C://Games/src/main", nameFile);
        }

        logWrite(stringBuilder, "C://Games/temp/temp.txt");
    }

    public static void directoryCreationMethod(String path) {

        Date date = new Date();
        File dir = new File(path);
        if (dir.exists()) {
            stringBuilder.append(date.toString() + " - Попытка создания директории по пути " + path + ", директория уже существует!");
            stringBuilder.append("\r\n");
        } else {
            if (dir.mkdir()) {
                stringBuilder.append(date.toString() + " - Директория по абсолютному пути " + path + ": создана.");
                stringBuilder.append("\r\n");
            } else {
                stringBuilder.append(date.toString() + " - Директория по абсолютному пути " + path + ": не создана!");
                stringBuilder.append("\r\n");
            }
        }
    }

    public static void fileCreationMethod(String path, String nameFile) {

        Date date = new Date();
        File file = new File(path, nameFile);
        if (file.exists()) {
            stringBuilder.append(date.toString() + " - Попытка создания файла " + nameFile +
                    ", файл уже существует!");
            stringBuilder.append("\r\n");
        } else {
            try {
                if (file.createNewFile()) {
                    stringBuilder.append(date.toString() + " - Файл " + nameFile + ": создан.");
                    stringBuilder.append("\r\n");
                }
            } catch (IOException e) {
                stringBuilder.append(date.toString() + " - Файл " + nameFile + ": не создан! " + e.getMessage() + ".");
                stringBuilder.append("\r\n");
            }
        }
    }

    public static void logWrite(StringBuilder strBuilderLog, String path) {

        try (FileWriter writer = new FileWriter(path, true)) {
            writer.write(strBuilderLog.toString());
            writer.flush();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}


