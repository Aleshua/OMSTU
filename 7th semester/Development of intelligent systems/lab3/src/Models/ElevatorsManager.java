package Models;

import java.util.List;
import java.util.Queue;

import Constants.ElevatorCommands;
import Constants.ElevatorStates;
import Interfaces.IAction;
import Interfaces.IElevator;
import Maps.CommonMap;
import Maps.ElevatorMap;

public class ElevatorsManager {

    private final IElevator[] elevators;
    private final int floorsNum;

    public ElevatorsManager(int floorsNum, IElevator elevatorFirst, IElevator elevatorSecond) {
        elevators = new IElevator[] { elevatorFirst, elevatorSecond };
        this.floorsNum = floorsNum;
    }

    public void Run(Queue<Integer> queueCalls) {
        var elevatorAutomaton = ElevatorMap.Create(floorsNum);

        var commonAutomaton = CommonMap.Create(floorsNum);

        do {
            List<ElevatorStates> states = List.of(
                    elevators[0].CurrentState(),
                    elevators[1].CurrentState());

            ElevatorCommands[] commands = commonAutomaton.get(states).apply(elevators, queueCalls);

            IAction actionFirstElevators = elevatorAutomaton.get(elevators[0].GetCurrentFloor()).get(commands[0]);
            IAction actionSecondElevators = elevatorAutomaton.get(elevators[1].GetCurrentFloor()).get(commands[1]);

            actionFirstElevators.Execute(elevators[0]);
            actionSecondElevators.Execute(elevators[1]);

        } while (elevators[0].CurrentState() != ElevatorStates.Idle ||
                elevators[1].CurrentState() != ElevatorStates.Idle);
    }
}
