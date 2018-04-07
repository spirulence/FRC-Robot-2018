package org.usfirst.frc.team5700.robot.commands;

import org.usfirst.frc.team5700.robot.AutonomousPaths;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoLeftSideSwitch extends CommandGroup {

    public AutoLeftSideSwitch() {
		addParallel(new AutoOpenIntakes());
		addParallel(new MoveArmToAngle(90));
		addSequential(new DriveReplay("SideSwitch"));
		addSequential(new ReleaseCube());
    }
}
