package org.usfirst.frc.team5700.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoCenterToLeftSwitch extends CommandGroup {
	
	public AutoCenterToLeftSwitch() {
		
		addParallel(new AutoOpenIntakes());
		addParallel(new MoveArmToAngle(90));
		addSequential(new DriveReplay("CenterToLeftSwitch"));
		addSequential(new ReleaseCube());

    }
}
