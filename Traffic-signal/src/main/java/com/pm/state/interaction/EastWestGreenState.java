package com.pm.state.interaction;


import com.pm.IntersectionController;
import com.pm.enums.Direction;
import com.pm.enums.Signal;


public class EastWestGreenState implements IntersectionState {

    @Override
    public void handle(IntersectionController controller) throws InterruptedException {

        controller.setGroupSignal(Direction.EAST, Signal.GREEN);
        controller.setGroupSignal(Direction.WEST, Signal.GREEN);

        controller.setGroupSignal(Direction.NORTH, Signal.RED);
        controller.setGroupSignal(Direction.SOUTH, Signal.RED);

        Thread.sleep(controller.getGreenDuration());

        controller.setGroupSignal(Direction.EAST, Signal.YELLOW);
        controller.setGroupSignal(Direction.WEST, Signal.YELLOW);

        Thread.sleep(controller.getYellowDuration());

        controller.setGroupSignal(Direction.EAST, Signal.RED);
        controller.setGroupSignal(Direction.WEST, Signal.RED);

        controller.setState(new NorthSouthGreenState());
    }
}