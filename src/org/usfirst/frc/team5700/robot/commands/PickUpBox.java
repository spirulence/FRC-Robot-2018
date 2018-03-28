package org.usfirst.frc.team5700.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class PickUpBox extends CommandGroup {

    public PickUpBox() {
    	addSequential(new MoveElevatorDistance(11));
    	addSequential(new GrabCube());
    	addParallel(new MoveElevatorDistance(28));
    }
}
