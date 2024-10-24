package src;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CreateInherentMapPackage {
    public static Map<String, ArrayList<String>> Create(String packagePath)
            throws InterruptedException, ExecutionException {

        List<File> files = FindJavaFilesInPackage.Find(packagePath);
        Map<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();

        ExecutorService executorService = Executors.newFixedThreadPool(2);
        List<Callable<List<Pair>>> callableTasks = new ArrayList<>();

        for (var file : files) {
            callableTasks.add(new Worker(file));
        }

        List<Future<List<Pair>>> futures = executorService.invokeAll(callableTasks);

        for (var future : futures) {
            List<Pair> result = future.get();
            for (var pair : result) {
                for (var parent : pair.parents) {
                    ArrayList<String> list = map.getOrDefault(parent, new ArrayList<String>());
                    list.add(pair.child);
                    map.put(parent, list);
                }
            }
        }

        executorService.shutdownNow();

        return map;
    }
}
