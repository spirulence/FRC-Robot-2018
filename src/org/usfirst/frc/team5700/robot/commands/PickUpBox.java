package org.usfirst.frc.team5700.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class PickUpBox extends CommandGroup {

    public PickUpBox() {
    	addSequential(new MoveElevatorDistance(11, 0.2));
    	addParallel(new GrabCube());
    	addSequential(new MoveElevatorDistance(28));
    }
}
