package org.usfirst.frc.team5700.robot.commands;

import org.usfirst.frc.team5700.robot.AutonomousPaths;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoLeftSideSwitch extends CommandGroup {

    public AutoLeftSideSwitch() {
    		addParallel(new DrivePastDistance(AutonomousPaths.distanceToCenterOfSwitchIn, 0.5, true, true));
    		addParallel(new MoveArmToAngle(90));
    		addParallel(new AutoOpenIntakes());
    		addSequential(new AutoDropCube());
    		addSequential(new MoveArmToAngle(0));
    }
}
