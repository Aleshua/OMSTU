package src;

import java.io.File;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;

public class Worker implements Runnable {
    BlockingQueue<File> fileQueue;
    BlockingQueue<Pair> pairQueue;
    CountDownLatch latch;

    public Worker(BlockingQueue<File> fileQueue, BlockingQueue<Pair> pairQueue, CountDownLatch latch) {
        this.fileQueue = fileQueue;
        this.pairQueue = pairQueue;
        this.latch = latch;
    }

    @Override
    public void run() {
        try {
            while (true) {
                File file = fileQueue.take();
                if (file instanceof PoisonPillFile) {
                    latch.countDown();
                    return;
                }

                List<Pair> pairs = GetChildAndParentsClassesInFile.Get(file);

                for (var pair : pairs) {
                    pairQueue.put(pair);
                }
            }
        } catch (InterruptedException e) {
            latch.countDown();
            Thread.currentThread().interrupt();
        }

    }

}
