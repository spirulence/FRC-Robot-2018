package org.usfirst.frc.team5700.robot.commands;

import org.usfirst.frc.team5700.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ClimbUp extends Command {

    public ClimbUp() {
    		requires(Robot.climber);
       
    }

    protected void initialize() {
    }

    protected void execute() {
    	Robot.climber.climberGoUp();
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    	Robot.climber.stopClimber();
    }

    protected void interrupted() {
    		end();
    }
}
