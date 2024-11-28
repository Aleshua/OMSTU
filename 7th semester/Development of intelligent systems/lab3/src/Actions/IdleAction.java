package Actions;

import Constants.ElevatorStates;
import Interfaces.IAction;
import Interfaces.IElevator;

public class IdleAction implements IAction {

    public void Execute(IElevator elevator) {
        elevator.Next(ElevatorStates.Idle);
        System.out.printf("ElevatorID: %-2d State: %-10s Floor: %-2d TargetFloor: %-2d \n", elevator.GetID(),
                elevator.CurrentState(),
                elevator.GetCurrentFloor(),
                elevator.GetTargetFloor());
    }

}
