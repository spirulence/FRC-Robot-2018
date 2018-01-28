package org.usfirst.frc.team5700.robot.commands;

import org.usfirst.frc.team5700.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class IntakeBox extends Command {
	
	//int direction;

    public IntakeBox() {
       requires(Robot.boxIntake);
    }
    
    protected void initialize() {
    }

    protected void execute() {
    	//direction = (Robot.oi.getOrientBoxButton())? -1 : 1;
    	
    	//Robot.boxIntake.setRightMotor(1);
    	//Robot.boxIntake.setLeftMotor(-1);
    	
    	Robot.boxIntake.intakeBox();
    }
    

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    	Robot.boxIntake.stopBoxIntake();
    }


    protected void interrupted() {
    	end();
    }
}
