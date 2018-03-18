package org.usfirst.frc.team5700.robot.commands;

import org.usfirst.frc.team5700.robot.AutonomousPaths;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoLeftSideSwitch extends CommandGroup {

    public AutoLeftSideSwitch() {
    		addParallel(new MoveArmToAngle(90));
		addParallel(new AutoOpenIntakes());
    		addParallel(new DrivePastDistance(AutonomousPaths.distanceToCenterOfSwitchIn - 0.8, 0.6, true, true));
    		addSequential(new AutoDropCube());
    }
}
