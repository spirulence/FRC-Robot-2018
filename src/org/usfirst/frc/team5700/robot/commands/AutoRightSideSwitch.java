package org.usfirst.frc.team5700.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoRightSideSwitch extends CommandGroup {

    public AutoRightSideSwitch() {
    		addParallel(new DrivePastDistance(156, 0.5, true, true));
    		addParallel(new MoveArmToAngle(270));
    		addSequential(new AutoDropCube());
    		addSequential(new MoveArmToAngle(0));
    }
}
