package src;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CreateInherentMapPackage {
    public static Map<String, ArrayList<String>> Create(String packagePath) {
        List<File> files = FindJavaFilesInPackage.Find(packagePath);

        Map<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();

        while (files.size() > 0) {
            String classDefinitionStr = FindClassDefinitionStr(files.removeLast());

            if (classDefinitionStr == null) {
                continue;
            }

            String child = GetChildByClassDefinitionStr(classDefinitionStr);
            List<String> parents = GetParentsByClassDefinitionStr(classDefinitionStr);

            for (String parent : parents) {
                ArrayList<String> list = map.getOrDefault(parent, new ArrayList<String>());
                list.add(child);
                map.put(parent, list);
            }

        }

        return map;
    }

    private static String GetChildByClassDefinitionStr(String str) {

        Pattern pattern = Pattern.compile("(class|interface)\\s\\w+");
        Matcher matcher = pattern.matcher(str);

        String result = null;
        while (matcher.find()) {
            result = matcher.group();
        }

        return result;
    }

    private static List<String> GetParentsByClassDefinitionStr(String str) {
        List<String> parents = new ArrayList<String>();

        Pattern pattern = Pattern.compile("(extends)\\s\\w+");
        Matcher matcher = pattern.matcher(str);
        String result = null;
        if (matcher.find()) {
            result = matcher.group().replaceAll("extends", "class");
            parents.add(result);
        }

        // pattern = Pattern.compile("(implements)(\\s|,|\\w)+");
        // matcher = pattern.matcher(str);
        if (str.indexOf("implements") != -1) {
            // result = matcher.group();
            result = str.substring(str.indexOf("implements") + "implements".length() + 1);
            pattern = Pattern.compile("\\s*(,)\\s*");
            // String[] interfaces = pattern.split(result.replaceAll("\\s*implements\\s*",
            // ""));
            for (String interfaceName : pattern.split(result)) {
                parents.add("interface" + " " + interfaceName);
            }
        }

        return parents;
    }

    private static String FindClassDefinitionStr(File file) {
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line;
            String text = "";
            while ((line = bufferedReader.readLine()) != null) {
                text += line;
            }
            bufferedReader.close();
            fileReader.close();

            Pattern pattern = Pattern
                    .compile("(class|interface)\\s+[\\w]+\\s+(extends|implements)(\\s|,|\\w|\\{)+");
            Matcher matcher = pattern.matcher(text);

            String result = "";
            while (matcher.find()) {
                result = matcher.group();
                result = result.replaceAll("\\s+", " ");
                result = result.replaceAll("\\{", "");
                return result.strip();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
