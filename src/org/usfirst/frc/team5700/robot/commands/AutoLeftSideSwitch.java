package org.usfirst.frc.team5700.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoLeftSideSwitch extends CommandGroup {

    public AutoLeftSideSwitch() {
    		addParallel(new DrivePastDistance(156, 0.4, true));
    		addParallel(new AutoMoveArmToAngleForSwitch(90, false));
    }
}
