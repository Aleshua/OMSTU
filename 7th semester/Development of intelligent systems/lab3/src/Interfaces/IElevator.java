package Interfaces;

import Constants.ElevatorStates;

public interface IElevator {

    public int GetID();

    public Integer GetCurrentFloor();

    public Integer GetTargetFloor();

    public void NextTargetFloor();

    public void AddFloorInQueue(Integer target);

    public void ClearQueue();

    public ElevatorStates CurrentState();

    public void Next(ElevatorStates state);

    public void CloseDoor();

    public void OpenDoor();

    public void GoUp();

    public void GoDown();
}
