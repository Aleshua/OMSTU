package src;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

public class Worker implements Runnable {
    private Map<String, ArrayList<String>> map;
    private File file;
    private CountDownLatch countDownLatch;

    public Worker(Map<String, ArrayList<String>> map, File file, CountDownLatch countDownLatch) {
        this.map = map;
        this.file = file;
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {

        List<Pair> pairs = GetChildAndParentsClassesInFile.Get(file);

        for (var pair : pairs) {
            for (var parent : pair.parents) {
                ArrayList<String> list = map.getOrDefault(parent, new ArrayList<String>());
                list.add(pair.child);
                map.put(parent, list);
            }
        }

        countDownLatch.countDown();
    }

}
