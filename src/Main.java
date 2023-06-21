import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Main {

    static StringBuilder stringBuilder = new StringBuilder();

    public static void main(String[] args) {

        List<String> listDirGame = Arrays.asList("src", "res", "savegames", "temp");
        for (String nameDir : listDirGame) {
            directoryCreationMethod("C://Games", nameDir);
        }

        fileCreationMethod("C://Games/temp", "temp.txt");

        List<String> listDirSrc = Arrays.asList("main", "test");
        for (String nameDir : listDirSrc) {
            directoryCreationMethod("C://Games/src", nameDir);
        }

        List<String> listDirRes = Arrays.asList("drawables", "vectors", "icons");
        for (String nameDir : listDirRes) {
            directoryCreationMethod("C://Games/res", nameDir);
        }


        List<String> listFileDirMain = Arrays.asList("Main.java", "Utils.java");
        for (String nameFile : listFileDirMain) {
            fileCreationMethod("C://Games/src/main", nameFile);
        }

        // проверка на ошибку создания файла, пишется в лог (ошибка пути)
        fileCreationMethod("C://Games/temp/1", "temp1.txt");

        logWrite(stringBuilder, "C://Games/temp/temp.txt");

    }

    public static void directoryCreationMethod(String path, String nameDir) {

        Date date = new Date();
        File dir = new File(path, nameDir);
        if (dir.exists()) {
            stringBuilder.append(date.toString() + " - Попытка создания директории " + nameDir + ", директория "
                    + nameDir + " уже существует!");
            stringBuilder.append("\r\n");
        } else {
            if (dir.mkdir()) {
                stringBuilder.append(date.toString() + " - Директория " + nameDir + " создана.");
                stringBuilder.append("\r\n");
            } else {
                stringBuilder.append(date.toString() + " - Директория " + nameDir + " не создана!");
                stringBuilder.append("\r\n");
            }
        }
    }

    public static void fileCreationMethod(String path, String nameFile) {

        Date date = new Date();
        File file = new File(path, nameFile);
        if (file.exists()) {
            stringBuilder.append(date.toString() + " - Попытка создания файла " + nameFile +
                    ", файл " + nameFile + " уже существует!");
            stringBuilder.append("\r\n");
        } else {
            try {
                if (file.createNewFile()) {
                    stringBuilder.append(date.toString() + " - Файл " + nameFile + " создан.");
                    stringBuilder.append("\r\n");
                }
            } catch (IOException e) {
                stringBuilder.append(date.toString() + " - Файл " + nameFile + " не создан! " + e.getMessage() + ".");
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


