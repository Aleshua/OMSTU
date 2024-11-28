package Actions;

import Constants.ElevatorStates;
import Interfaces.IAction;
import Interfaces.IElevator;

public class OpenDoorAction implements IAction {

    public void Execute(IElevator elevator) {
        elevator.Next(ElevatorStates.OpenDoor);
        elevator.OpenDoor();
    }

}
