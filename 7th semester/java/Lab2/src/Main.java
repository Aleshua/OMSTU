package src;

import java.util.ArrayList;
import java.util.Map;

public class Main {

    public static void main(String[] args) {

        Map<String, ArrayList<String>> map = CreateInherentMapPackage.Create("src/MyPackage/");

        System.out.println(map);

    }
}
