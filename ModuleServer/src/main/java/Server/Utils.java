package Server;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public abstract class Utils {

    /**
     * Method verify string value, in case it contains any of punctuation marks, erases it
     * @return new string value without any punctuation marks
     */
    public static String verifyString(String value){
        ArrayList<String> punctMarks = new ArrayList<>(List.of("!@#$%^&*()<>?,.:;\'\"".split("")));

        if(punctMarks.contains(value))
            return null;

        int len = value.length();
        for(int i = 0; i < len; i++){
            if(punctMarks.contains(String.valueOf(value.charAt(i)))) {
                value = value.replaceAll(String.valueOf(value.charAt(i)), "");
            }
        }

        return value;
    }

    /**
     * @return ArrayList of all json files with languages from current directory
     */
    public static ArrayList<String> getListOfFiles() {
        ArrayList<String> files = null;
        File folder = new File("./");

        for (File file : folder.listFiles()) {
            if (file.isFile() && file.getName().contains(".json")) {
                if (files == null)
                    files = new ArrayList<>();
                if(file.getName().contains("export"))
                    continue;
                files.add(file.getName());
            }
        }

        return files;
    }
}
