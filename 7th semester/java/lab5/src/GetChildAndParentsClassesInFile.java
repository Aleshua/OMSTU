package src;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GetChildAndParentsClassesInFile {
    public static List<Pair> Get(File file) {

        List<String> classDefinitionStrList = FindClassesDefinitionStr(file);

        List<Pair> pairs = new ArrayList<>();

        for (var str : classDefinitionStrList) {
            if (str == null) {
                continue;
            }

            String child = GetChildByClassDefinitionStr(str);
            List<String> parents = GetParentsByClassDefinitionStr(str);

            pairs.add(new Pair(child, parents));
        }

        return pairs;
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

        pattern = Pattern.compile("(implements)(\\s|,|\\w)+");
        matcher = pattern.matcher(str);
        if (matcher.find()) {
            result = matcher.group();
            // result = str.substring(str.indexOf("implements") + "implements".length() +
            // 1);
            pattern = Pattern.compile("\\s*(,)\\s*");
            String[] interfaces = pattern.split(result.replaceAll("\\s*implements\\s*",
                    ""));
            for (String interfaceName : interfaces) {
                parents.add("interface" + " " + interfaceName);
            }
        }

        return parents;
    }

    private static List<String> FindClassesDefinitionStr(File file) {
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
                    .compile("(class|interface)\\s+[\\w]+\\s+(extends|implements)(\\s|,|\\w)+\\{");
            Matcher matcher = pattern.matcher(text);

            List<String> strList = new ArrayList<String>();
            String result = "";
            while (matcher.find()) {
                result = matcher.group();
                result = result.replaceAll("\\s+", " ");
                result = result.replaceAll("\\{", "");
                strList.add(result.strip());
            }
            return strList;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
