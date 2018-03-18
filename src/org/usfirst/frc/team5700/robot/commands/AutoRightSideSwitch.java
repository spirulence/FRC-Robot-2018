package org.usfirst.frc.team5700.robot.commands;

import org.usfirst.frc.team5700.robot.AutonomousPaths;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoRightSideSwitch extends CommandGroup {

    public AutoRightSideSwitch() {
		addParallel(new MoveArmToAngle(270));
		addParallel(new AutoOpenIntakes());
    		addParallel(new DrivePastDistance(AutonomousPaths.distanceToCenterOfSwitchIn - 8, 0.5, true, true));
    		addSequential(new AutoDropCube());
    		//addSequential(new MoveArmToAngle(0));
    }
}
