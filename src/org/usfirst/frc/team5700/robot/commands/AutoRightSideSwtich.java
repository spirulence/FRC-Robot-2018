package org.usfirst.frc.team5700.robot.commands;

import org.usfirst.frc.team5700.robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoRightSideSwtich extends CommandGroup {

    public AutoRightSideSwtich() {
    addParallel(new DrivePastDistance(156, 0.4, true));
    	addParallel(new AutoMoveArmToAngleForSwitch(270, true));
    }
}
