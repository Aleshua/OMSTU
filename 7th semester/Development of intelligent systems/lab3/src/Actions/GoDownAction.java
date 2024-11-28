package Actions;

import Constants.ElevatorStates;
import Interfaces.IAction;
import Interfaces.IElevator;

public class GoDownAction implements IAction {

    public void Execute(IElevator elevator) {
        elevator.Next(ElevatorStates.GoDown);
        elevator.GoDown();
    }
}
