package src;

import java.io.File;
import java.util.List;
import java.util.concurrent.BlockingQueue;

public class Worker implements Runnable {
    BlockingQueue<File> fileQueue;
    BlockingQueue<List<Pair>> pairQueue;

    public Worker(BlockingQueue<File> fileQueue, BlockingQueue<List<Pair>> pairQueue) {
        this.fileQueue = fileQueue;
        this.pairQueue = pairQueue;
    }

    @Override
    public void run() {
        try {
            while (true) {
                File file = fileQueue.take();

                if (file instanceof PoisonPillFile) {
                    return;
                }

                List<Pair> pairs = GetChildAndParentsClassesInFile.Get(file);

                pairQueue.put(pairs);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

    }

}
