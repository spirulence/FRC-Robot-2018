package org.usfirst.frc.team5700.robot.commands;

import org.usfirst.frc.team5700.robot.Robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class MoveElevatorWithJoystick extends Command {
	
	Joystick _stick;
	

    public MoveElevatorWithJoystick() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    		requires(Robot.elevator);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    		_stick = Robot.oi.getAuxRightStick();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    		Robot.elevator.moveElevatorWithJoystick(-_stick.getY());
    }
		

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }
    

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    		end();
    }
}