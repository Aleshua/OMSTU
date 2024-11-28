package Actions;

import Constants.ElevatorStates;
import Interfaces.IAction;
import Interfaces.IElevator;

public class GoUpAction implements IAction {

    public void Execute(IElevator elevator) {
        elevator.Next(ElevatorStates.GoUp);
        elevator.GoUp();
    }
}
