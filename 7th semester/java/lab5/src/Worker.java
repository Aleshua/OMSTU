package src;

import java.io.File;
import java.util.List;
import java.util.concurrent.Callable;

public class Worker implements Callable<List<Pair>> {
    private File file;

    public Worker(File file) {
        this.file = file;
    }

    @Override
    public List<Pair> call() {

        List<Pair> pairs = GetChildAndParentsClassesInFile.Get(file);

        return pairs;
    }

}
