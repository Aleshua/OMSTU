package Maps;

import java.util.HashMap;
import java.util.Map;

import Constants.ElevatorCommands;

public class MoveMap {
    public static Map<Integer, Map<Integer, ElevatorCommands>> Create(int floorsNum) {
        Map<Integer, Map<Integer, ElevatorCommands>> automaton = new HashMap<>();

        for (int currentFloor = 1; currentFloor <= floorsNum; currentFloor++) {

            automaton.put(currentFloor, new HashMap<>());

            automaton.get(currentFloor).put(null, ElevatorCommands.GoIdle);

            for (int targetFloor = 1; targetFloor < currentFloor; targetFloor++) {
                automaton.get(currentFloor).put(targetFloor, ElevatorCommands.GoDown);
            }

            for (int targetFloor = currentFloor + 1; targetFloor <= floorsNum; targetFloor++) {
                automaton.get(currentFloor).put(targetFloor, ElevatorCommands.GoUp);
            }

            automaton.get(currentFloor).put(currentFloor, ElevatorCommands.OpenDoor);

        }

        // for (Integer currentFloor : automaton.keySet()) {
        // System.out.println("CurrentFloor: " + currentFloor);
        // for (Integer target : automaton.get(currentFloor).keySet()) {
        // System.out.println(" TargetFloor: " + target + " Command: " +
        // automaton.get(currentFloor).get(target));
        // }
        // System.out.println();
        // }

        return automaton;
    }
}
