import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

import Constants.ElevatorStates;
import Interfaces.IElevator;
import Models.Elevator;
import Models.ElevatorsManager;

public class Main {
        public static void main(String[] args) {

                int floorsNum = 9;

                List<int[]> calls = Arrays.asList(
                                new int[] { 1, 3 },
                                new int[] { 2, 4 },
                                new int[] { 2, 10 });

                Queue<Integer> queueCalls = calls.stream()
                                .flatMapToInt(Arrays::stream)
                                .boxed()
                                .collect(Collectors.toCollection(LinkedList::new));

                IElevator elevatorFirst = new Elevator(1, ElevatorStates.Idle, 1);
                IElevator elevatorSecond = new Elevator(2, ElevatorStates.Idle, 3);

                ElevatorsManager manager = new ElevatorsManager(floorsNum, elevatorFirst, elevatorSecond);

                manager.Run(queueCalls);
        }
}
