package projects.SDWSN.log;


import projects.SDWSN.read.UtilJson;

import javax.json.JsonArray;
import javax.json.JsonObject;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.util.Arrays;

/**
 * Created by matheus on 10/03/17.
 */
public class Log {

    private static String name() {
        return LocalDate.now().toString();
    }

    public static void write(String log) {
        write(log + "\n", "log/" + name() + ".log", StandardOpenOption.APPEND);
    }

    public static void write(String log, String name) {
        write(log + "\n", name, StandardOpenOption.APPEND);
    }

    public static void add(JsonObject object) {
        add(object, "data.json");
    }

    public static void add(JsonObject object, String name) {
        File f = new File(name);
        if (!f.exists()) {
            write("[]", name, StandardOpenOption.WRITE);
        }
        JsonArray array = UtilJson.getJsonArrayFromFile(name);
        String str = array.toString();
        str = str.substring(0, str.length() - 1);
//        System.out.println(array.size());
        if (!array.isEmpty()) {
            str += ",";
        }
        str += object.toString() + "]";
//        System.out.println(str);
        write(str, name, StandardOpenOption.WRITE);
    }

    private static void write(String log, String target, StandardOpenOption tag) {
        File f = new File(target);
        if (!f.exists()) {
            try {
                f.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            Files.write(Paths.get(target), log.getBytes(), tag);
        } catch (IOException e) {
            // exception handling left as an exercise for the reader
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
    }

}