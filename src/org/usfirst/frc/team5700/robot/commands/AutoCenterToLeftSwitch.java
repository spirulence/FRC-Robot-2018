package org.usfirst.frc.team5700.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoCenterToLeftSwitch extends CommandGroup {
	
	double firstDistanceIn = 34;
	double turnRadiusIn = 24;
	double turnAngleDeg = 58;
	double secondDistanceIn = 80.45;
	double speed = 0.5;
	
	public AutoCenterToLeftSwitch() {
    		addSequential(new DrivePastDistance(firstDistanceIn, speed, false, false));
    		addSequential(new TurnRadiusPastAngle(turnRadiusIn, turnAngleDeg, speed));
    		addParallel(new MoveArmToAngle(270));
    		addParallel(new DrivePastDistance(secondDistanceIn, speed, true, true));
    		addSequential(new AutoDropCube());
    }
}
