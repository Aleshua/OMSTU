package src;

import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Map<String, Integer> dictionary = WordCounter
                .main("C:\\Users\\snleh\\OneDrive\\Рабочий стол\\OMSTU\\OMSTU\\7th semester\\java\\Lab1\\src\\text.txt");

        System.out.println(dictionary.toString());
    }
}
