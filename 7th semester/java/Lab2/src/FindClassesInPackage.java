import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class FindClassesInPackage {
    @SuppressWarnings("rawtypes")
    public static Class[] Find(String packagePath) {
        InputStream stream = ClassLoader.getSystemClassLoader().getResourceAsStream(packagePath);
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));

        return reader.lines().filter(file -> file.endsWith(".class")).map(file -> GetClass(packagePath + file))
                .toArray(Class[]::new);
    }

    @SuppressWarnings("rawtypes")
    private static Class GetClass(String className) {
        try {
            return Class.forName(className.replace('/', '.').substring(0, className.lastIndexOf('.')));
        } catch (Exception e) {
            return null;
        }
    }
}
