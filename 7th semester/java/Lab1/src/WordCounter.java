package src;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class WordCounter {

    public static Map<String, Integer> main(String filePath) {

        String text = ReadFile(filePath).toLowerCase();

        Pattern pattern = Pattern.compile("\\s*(\\s|\\p{Punct}+)\\s*");
        String[] words = pattern.split(text);

        for (String word : words) {
            System.out.println(word);
        }

        Map<String, Integer> dictionary = new HashMap<String, Integer>();

        for (String word : words) {

            if (dictionary.get(word) == null) {
                dictionary.put(word, 1);
            } else {
                dictionary.put(word, dictionary.get(word) + 1);
            }

        }

        return dictionary;

    }

    private static String ReadFile(String filePath) {
        File file = new File(filePath);
        String text = "";
        try (FileInputStream inputStream = new FileInputStream(file)) {
            int i;
            while ((i = inputStream.read()) != -1) {
                text += (char) i;
            }
            inputStream.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return text;
    }
}
