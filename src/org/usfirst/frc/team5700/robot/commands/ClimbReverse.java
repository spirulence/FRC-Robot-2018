package org.usfirst.frc.team5700.robot.commands;

import org.usfirst.frc.team5700.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ClimbReverse extends Command {

    public ClimbReverse() {
    		requires(Robot.climber);
       
    }

    protected void initialize() {
    }

    protected void execute() {
    	Robot.climber.climberGoReverse();
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

