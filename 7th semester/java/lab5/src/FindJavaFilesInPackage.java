package src;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class FindJavaFilesInPackage {
    public static List<File> Find(String packagePath) {
        File folder = new File(packagePath);

        LinkedList<File> listOfFiles = new LinkedList<File>(Arrays.asList(folder.listFiles()));

        List<File> result = new ArrayList<File>();

        while (listOfFiles.size() > 0) {

            File file = listOfFiles.removeLast();

            if (file.isDirectory()) {
                listOfFiles.addAll(Arrays.asList(file.listFiles()));
                continue;
            }

            if (file.getName().endsWith(".java")) {
                result.add(file);
            }
        }

        return result;
    }
}
