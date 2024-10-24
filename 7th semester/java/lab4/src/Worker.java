package src;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

import java.util.concurrent.locks.ReentrantLock;

public class Worker implements Runnable {
    private int id;
    private Map<String, ArrayList<String>> map;
    private File file;
    private CountDownLatch countDownLatch;
    private final ReentrantLock locker = new ReentrantLock();

    public Worker(int id, Map<String, ArrayList<String>> map, File file, CountDownLatch countDownLatch) {
        this.id = id;
        this.map = map;
        this.file = file;
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {

        List<Pair> pairs = GetChildAndParentsClassesInFile.Get(file);

        for (var pair : pairs) {
            for (var parent : pair.parents) {

                locker.lock();
                try {
                    ArrayList<String> list = map.getOrDefault(parent, new ArrayList<String>());
                    list.add(pair.child);
                    map.put(parent, list);
                } catch (Exception exception) {
                    System.out.println(exception.getMessage());
                } finally {
                    locker.unlock();
                }
            }
        }

        countDownLatch.countDown();
    }

}
