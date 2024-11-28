package Maps;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.function.BiFunction;

import Constants.ElevatorCommands;
import Constants.ElevatorStates;
import Interfaces.IElevator;

public class CommonMap {
        public static Map<List<ElevatorStates>, BiFunction<IElevator[], Queue<Integer>, ElevatorCommands[]>> Create(
                        int floorsNum) {

                var moveMap = MoveMap.Create(floorsNum);

                Map<List<ElevatorStates>, BiFunction<IElevator[], Queue<Integer>, ElevatorCommands[]>> map = new HashMap<>();

                @SuppressWarnings("unused")
                Map<ElevatorStates, BiFunction<IElevator, Queue<Integer>, ElevatorCommands>> actionMap = Map.of(
                                ElevatorStates.CloseDoor, (elevator, queueCalls) -> {

                                        elevator.NextTargetFloor();

                                        ElevatorCommands elevatorCommand = moveMap
                                                        .get(elevator.GetCurrentFloor())
                                                        .get(elevator.GetTargetFloor());

                                        return elevatorCommand;
                                },
                                ElevatorStates.OpenDoor, (elevator, queueCalls) -> {
                                        return ElevatorCommands.CloseDoor;
                                },
                                ElevatorStates.GoUp, (elevator, queueCalls) -> {
                                        ElevatorCommands elevatorCommand = moveMap
                                                        .get(elevator.GetCurrentFloor())
                                                        .get(elevator.GetTargetFloor());

                                        return elevatorCommand;
                                },
                                ElevatorStates.GoDown, (elevator, queueCalls) -> {
                                        ElevatorCommands elevatorCommand = moveMap
                                                        .get(elevator.GetCurrentFloor())
                                                        .get(elevator.GetTargetFloor());

                                        return elevatorCommand;
                                },
                                ElevatorStates.Idle, (elevator, queueCalls) -> {
                                        elevator.AddFloorInQueue(queueCalls.poll());
                                        elevator.AddFloorInQueue(queueCalls.poll());

                                        elevator.NextTargetFloor();

                                        ElevatorCommands elevatorCommand = moveMap
                                                        .get(elevator.GetCurrentFloor())
                                                        .get(elevator.GetTargetFloor());
                                        return elevatorCommand;
                                });

                ElevatorStates[] elevatorStatesWithoutError = new ElevatorStates[] { ElevatorStates.CloseDoor,
                                ElevatorStates.OpenDoor, ElevatorStates.GoDown, ElevatorStates.GoUp,
                                ElevatorStates.Idle };

                for (ElevatorStates firstElevatorState : elevatorStatesWithoutError) {
                        for (ElevatorStates secondElevatorState : elevatorStatesWithoutError) {
                                map.put(List.of(firstElevatorState, secondElevatorState), (elevators,
                                                queueCalls) -> {

                                        ElevatorCommands firstElevatorCommand = actionMap.get(firstElevatorState)
                                                        .apply(elevators[0], queueCalls);

                                        ElevatorCommands secondElevatorCommand = actionMap.get(secondElevatorState)
                                                        .apply(elevators[1], queueCalls);

                                        return new ElevatorCommands[] { firstElevatorCommand, secondElevatorCommand
                                        };
                                });
                        }
                }

                map.put(List.of(ElevatorStates.Idle, ElevatorStates.Idle), (elevators,
                                queueCalls) -> {

                        ElevatorCommands firstElevatorCommand = actionMap.get(ElevatorStates.Idle)
                                        .apply(elevators[0], queueCalls);

                        ElevatorCommands secondElevatorCommand = ElevatorCommands.GoIdle;

                        return new ElevatorCommands[] { firstElevatorCommand, secondElevatorCommand
                        };
                });

                return map;
        }
}
