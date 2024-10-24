package src;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

public class CreateInherentMapPackage {
    public static Map<String, ArrayList<String>> Create(String packagePath) throws InterruptedException {
        List<File> files = FindJavaFilesInPackage.Find(packagePath);

        Map<String, ArrayList<String>> map = Collections.synchronizedMap(new HashMap<String, ArrayList<String>>());

        CountDownLatch countDownLatch = new CountDownLatch(files.size());
        List<Thread> workers = new ArrayList<>();

        int ind = 0;
        for (var file : files) {
            workers.add(new Thread(new Worker(ind, map, file, countDownLatch)));
            ind += 1;
        }

        workers.forEach(Thread::start);
        countDownLatch.await();

        return map;
    }
}
