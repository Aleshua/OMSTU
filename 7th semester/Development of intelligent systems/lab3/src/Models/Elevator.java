package Models;

import java.util.LinkedList;
import java.util.Queue;

import Constants.ElevatorStates;
import Interfaces.IElevator;

public class Elevator implements IElevator {
    public final int id;
    private ElevatorStates currentState;
    private Integer currentFloor;
    private Integer targetFloor;
    private Queue<Integer> queueCalls;

    public Elevator(int id, ElevatorStates state, int floor) {
        this.id = id;
        currentState = state;
        currentFloor = floor;
        queueCalls = new LinkedList<Integer>();
    }

    public void Next(ElevatorStates state) {
        currentState = state;
    }

    public int GetID() {
        return id;
    }

    public ElevatorStates CurrentState() {
        return currentState;
    }

    public Integer GetTargetFloor() {
        return targetFloor;
    }

    public void NextTargetFloor() {
        targetFloor = queueCalls.poll();
    }

    public void AddFloorInQueue(Integer target) {
        queueCalls.add(target);
    }

    public void ClearQueue() {
        queueCalls.clear();
    }

    public Integer GetCurrentFloor() {
        return currentFloor;
    }

    public void CloseDoor() {
        System.out.printf("ElevatorID: %-2d State: %-10s Floor: %-2d TargetFloor: %-2d \n", id, currentState,
                currentFloor,
                targetFloor);
    }

    public void OpenDoor() {
        System.out.printf("ElevatorID: %-2d State: %-10s Floor: %-2d TargetFloor: %-2d \n", id, currentState,
                currentFloor,
                targetFloor);
    }

    public void GoUp() {
        currentFloor += 1;
        System.out.printf("ElevatorID: %-2d State: %-10s Floor: %-2d TargetFloor: %-2d \n", id, currentState,
                currentFloor,
                targetFloor);
    }

    public void GoDown() {
        currentFloor -= 1;
        System.out.printf("ElevatorID: %-2d State: %-10s Floor: %-2d TargetFloor: %-2d \n", id, currentState,
                currentFloor,
                targetFloor);
    }
}
