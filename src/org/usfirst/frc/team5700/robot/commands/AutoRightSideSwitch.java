package org.usfirst.frc.team5700.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoRightSideSwitch extends CommandGroup {

	public AutoRightSideSwitch() {
		addParallel(new AutoOpenIntakes());
		addParallel(new MoveArmToAngle(270));
		addSequential(new DriveReplay("SideSwitch"));
		addSequential(new ReleaseCube());

	}
}
