package src;

import java.util.ArrayList;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        Map<String, ArrayList<String>> map = CreateInherentMapPackage
                .Create("src/MyPackage/");

        for (var key : map.keySet()) {
            System.out.printf("%s :\n\t", key);
            for (var child : map.get(key)) {
                System.out.printf("%s, ", child);
            }
            System.out.println("\n");
        }
    }
}
