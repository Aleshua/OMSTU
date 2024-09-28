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

        Pattern pattern = Pattern.compile("\\s*(\\s|[^a-zA-Z0-9])+\\s*");
        String[] words = pattern.split(text);

        words = CheckLastEmptyWord(words);
        words = CheckFirstEmptyWord(words);

        Map<String, Integer> dictionary = new HashMap<String, Integer>();

        for (String word : words) {
            dictionary.put(word, dictionary.getOrDefault(word, 0) + 1);
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

    private static String[] CheckLastEmptyWord(String[] words) {
        if (words.length == 0) {
            return words;
        }

        if (words[0] == "") {
            String[] dest = new String[words.length - 1];

            System.arraycopy(words, 1, dest, 0, dest.length);

            return dest;
        }

        return words;
    }

    private static String[] CheckFirstEmptyWord(String[] words) {
        if (words.length == 0) {
            return words;
        }

        if (words[words.length - 1] == "") {
            String[] dest = new String[words.length - 1];

            System.arraycopy(words, 0, dest, 0, dest.length);

            return dest;
        }

        return words;
    }
}
