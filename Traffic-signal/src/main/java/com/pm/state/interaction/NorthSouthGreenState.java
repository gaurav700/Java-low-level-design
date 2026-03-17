package com.pm.state.interaction;


import com.pm.IntersectionController;
import com.pm.enums.Direction;
import com.pm.enums.Signal;

public class NorthSouthGreenState implements IntersectionState {

    @Override
    public void handle(IntersectionController controller) throws InterruptedException {

        controller.setGroupSignal(Direction.NORTH, Signal.GREEN);
        controller.setGroupSignal(Direction.SOUTH, Signal.GREEN);

        controller.setGroupSignal(Direction.EAST, Signal.RED);
        controller.setGroupSignal(Direction.WEST, Signal.RED);

        Thread.sleep(controller.getGreenDuration());

        controller.setGroupSignal(Direction.NORTH, Signal.YELLOW);
        controller.setGroupSignal(Direction.SOUTH, Signal.YELLOW);

        Thread.sleep(controller.getYellowDuration());

        controller.setGroupSignal(Direction.NORTH, Signal.RED);
        controller.setGroupSignal(Direction.SOUTH, Signal.RED);

        controller.setState(new EastWestGreenState());
    }
}