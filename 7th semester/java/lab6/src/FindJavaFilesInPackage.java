package src;

import java.io.File;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class FindJavaFilesInPackage {
    public static void Find(String packagePath, BlockingQueue<File> fileQueue, AtomicInteger context)
            throws InterruptedException {
        File folder = new File(packagePath);

        LinkedList<File> listOfFiles = new LinkedList<File>(Arrays.asList(folder.listFiles()));

        while (listOfFiles.size() > 0) {

            File file = listOfFiles.removeLast();

            if (file.isDirectory()) {
                listOfFiles.addAll(Arrays.asList(file.listFiles()));
                continue;
            }

            if (file.getName().endsWith(".java")) {
                fileQueue.put(file);
                context.incrementAndGet();
            }
        }
    }
}
