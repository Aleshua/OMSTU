package src;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class CreateInherentMapPackage {
    public static Map<String, ArrayList<String>> Create(String packagePath, int threadsNum)
            throws InterruptedException, ExecutionException {

        AtomicInteger context = new AtomicInteger(0);

        ExecutorService executorService = Executors.newFixedThreadPool(threadsNum);

        BlockingQueue<File> fileQueue = new LinkedBlockingQueue<File>();
        BlockingQueue<List<Pair>> pairQueue = new LinkedBlockingQueue<List<Pair>>();

        for (int i = 0; i < threadsNum; i++) {
            executorService.submit(new Worker(fileQueue, pairQueue));
        }

        FindJavaFilesInPackage.Find(packagePath, fileQueue, context);

        for (int i = 0; i < threadsNum; i++) {
            fileQueue.put(new PoisonPillFile());
        }

        Map<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();
        while (context.getAndDecrement() > 0) {
            List<Pair> pairs = pairQueue.take();
            for (var pair : pairs) {
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
