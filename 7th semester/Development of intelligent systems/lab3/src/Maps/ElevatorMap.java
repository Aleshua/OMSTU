package Maps;

import java.util.HashMap;
import java.util.Map;

import Actions.CloseDoorAction;
import Actions.ErrorAction;
import Actions.GoDownAction;
import Actions.GoUpAction;
import Actions.IdleAction;
import Actions.OpenDoorAction;
import Constants.ElevatorCommands;
import Constants.ElevatorStates;
import Interfaces.IAction;

public class ElevatorMap {
        public static Map<Integer, Map<ElevatorCommands, IAction>> Create(int floorsNum) {

                Map<ElevatorStates, IAction> actionsMap = Map.of(
                                ElevatorStates.Idle, new IdleAction(),
                                ElevatorStates.CloseDoor, new CloseDoorAction(),
                                ElevatorStates.OpenDoor, new OpenDoorAction(),
                                ElevatorStates.GoDown, new GoDownAction(),
                                ElevatorStates.GoUp, new GoUpAction(),
                                ElevatorStates.Error, new ErrorAction());

                Map<Integer, Map<ElevatorCommands, IAction>> map = new HashMap<>();

                map.put(1, Map.of(
                                ElevatorCommands.GoIdle, actionsMap.get(ElevatorStates.Idle),
                                ElevatorCommands.CloseDoor, actionsMap.get(ElevatorStates.CloseDoor),
                                ElevatorCommands.OpenDoor, actionsMap.get(ElevatorStates.OpenDoor),
                                ElevatorCommands.GoDown, actionsMap.get(ElevatorStates.Error),
                                ElevatorCommands.GoUp, actionsMap.get(ElevatorStates.GoUp)));

                for (int i = 2; i <= floorsNum - 1; i++) {
                        map.put(i, Map.of(
                                        ElevatorCommands.GoIdle, actionsMap.get(ElevatorStates.Idle),
                                        ElevatorCommands.CloseDoor, actionsMap.get(ElevatorStates.CloseDoor),
                                        ElevatorCommands.OpenDoor, actionsMap.get(ElevatorStates.OpenDoor),
                                        ElevatorCommands.GoDown, actionsMap.get(ElevatorStates.GoDown),
                                        ElevatorCommands.GoUp, actionsMap.get(ElevatorStates.GoUp)));
                }

                map.put(floorsNum, Map.of(
                                ElevatorCommands.GoIdle, actionsMap.get(ElevatorStates.Idle),
                                ElevatorCommands.CloseDoor, actionsMap.get(ElevatorStates.CloseDoor),
                                ElevatorCommands.OpenDoor, actionsMap.get(ElevatorStates.OpenDoor),
                                ElevatorCommands.GoDown, actionsMap.get(ElevatorStates.GoDown),
                                ElevatorCommands.GoUp, actionsMap.get(ElevatorStates.Error)));

                return map;
        }
}
