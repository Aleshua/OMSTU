import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Main {
    @SuppressWarnings("rawtypes")
    public static void main(String[] args) {

        Class[] classes = FindClassesInPackage.Find("");

        Map<Class, ArrayList<Class>> parentsMap = new HashMap<>();

        for (Class myClass : classes) {
            Class[] parents = FindParentsClasses.Find(myClass);

            for (Class parent : parents) {

                if (parent == null) {
                    continue;
                }

                if (parent == Object.class) {
                    continue;
                }

                ArrayList<Class> list = parentsMap.getOrDefault(parent, new ArrayList<Class>());
                list.add(myClass);
                parentsMap.put(parent, list);
            }
        }

        System.out.println(parentsMap);

    }
}
