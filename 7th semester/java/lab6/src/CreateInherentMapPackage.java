package src;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

public class CreateInherentMapPackage {
    public static Map<String, ArrayList<String>> Create(String packagePath, int threadsNum)
            throws InterruptedException, ExecutionException {

        CountDownLatch latch = new CountDownLatch(threadsNum);
        ExecutorService executorService = Executors.newFixedThreadPool(threadsNum);

        BlockingQueue<File> fileQueue = new LinkedBlockingQueue<File>();
        BlockingQueue<Pair> pairQueue = new LinkedBlockingQueue<Pair>();

        for (int i = 0; i < threadsNum; i++) {
            executorService.submit(new Worker(fileQueue, pairQueue, latch));
        }

        FindJavaFilesInPackage.Find(packagePath, fileQueue);

        for (int i = 0; i < threadsNum; i++) {
            fileQueue.put(new PoisonPillFile());
        }

        Map<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();

        while (!pairQueue.isEmpty() || latch.getCount() > 0) {
            Pair pair = pairQueue.take();
            for (var parent : pair.parents) {
                ArrayList<String> list = map.getOrDefault(parent, new ArrayList<String>());
                list.add(pair.child);
                map.put(parent, list);
            }
        }

        executorService.shutdownNow();

        return map;
    }
}
