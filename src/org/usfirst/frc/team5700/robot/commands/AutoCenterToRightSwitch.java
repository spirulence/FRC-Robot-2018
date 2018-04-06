package org.usfirst.frc.team5700.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoCenterToRightSwitch extends CommandGroup {
	
	public AutoCenterToRightSwitch() {
		
		addParallel(new AutoOpenIntakes());
		addParallel(new MoveArmToAngle(270));
		addSequential(new DriveReplay("CenterToRightSwitch"));
		addSequential(new ReleaseCube());

    }
}
