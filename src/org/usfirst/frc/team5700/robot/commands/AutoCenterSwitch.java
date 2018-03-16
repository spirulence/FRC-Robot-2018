package org.usfirst.frc.team5700.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoCenterSwitch extends CommandGroup {
	
	public AutoCenterSwitch() {
    		
    		addSequential(new AutoSwitchDrivePastDistance(0.4, true, false, false));
    		addSequential(new AutoSwitchTurnRadiusPastAngle(0.4)); //TODO
    		addParallel(new MoveArmToAngle(90)); //TODO
    		addSequential(new AutoSwitchDrivePastDistance(0.4, false, true, true)); //TODO fix
    }
}
