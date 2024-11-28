package Actions;

import Constants.ElevatorStates;
import Interfaces.IAction;
import Interfaces.IElevator;

public class CloseDoorAction implements IAction {

    public void Execute(IElevator elevator) {
        elevator.Next(ElevatorStates.CloseDoor);
        elevator.CloseDoor();
    }

}
