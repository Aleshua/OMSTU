package Actions;

import Constants.ElevatorStates;
import Interfaces.IAction;
import Interfaces.IElevator;

public class ErrorAction implements IAction {
    public void Execute(IElevator elevator) {

        elevator.Next(ElevatorStates.Error);

        throw new IllegalArgumentException("Error");
    }
}
