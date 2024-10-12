package src;

import java.util.ArrayList;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();
        Map<String, ArrayList<String>> map = CreateInherentMapPackage
                .Create("/spring-framework/");
        long finish = System.currentTimeMillis();
        System.out.println(map.get("class RuntimeException"));
        // for (var key : map.keySet()) {
        // System.out.printf("%s :\n\t", key);
        // for (var child : map.get(key)) {
        // System.out.printf("%s, ", child);
        // }
        // System.out.println("\n");
        // }

        // long elapsed = finish - start;
        // System.out.println("Прошло времени, мс: " + elapsed);
    }
}
