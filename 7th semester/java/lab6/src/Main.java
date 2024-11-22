package src;

import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class Main {

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        long startTime = System.currentTimeMillis();

        Map<String, ArrayList<String>> map = CreateInherentMapPackage
                .Create("spring-framework/", 10);

        long endTime = System.currentTimeMillis();

        for (var key : map.keySet()) {
            System.out.printf("%s :\n\t", key);
            for (var child : map.get(key)) {
                System.out.printf("%s, ", child);
            }
            System.out.println("\n");
        }

        long duration = endTime - startTime;

        System.out.println("Время выполнения: " + duration + " миллисекунд.");
    }
}
