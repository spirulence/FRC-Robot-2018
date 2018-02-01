package org.usfirst.frc.team5700.robot.commands;

import org.usfirst.frc.team5700.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class RightIntakeBox extends Command {

	
	double direction;

    public RightIntakeBox() {
    	requires(Robot.boxIntake);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.boxIntake.boxIntakeInRight();
    	
    	if (Robot.oi.getRightIntakeMotor()){
    		direction = 1;
    	}
    	else if (Robot.oi.getRightIntakeMotorReverse()){
    		direction = -1;
    	}
    	
    	Robot.boxIntake.setLeftMotor(1);
    	Robot.boxIntake.setRightMotor(direction);
    	Robot.boxIntake.intakeBoxRight();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.boxIntake.stopBoxIntakeRight();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
