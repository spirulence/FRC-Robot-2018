package org.usfirst.frc.team5700.robot.commands;

import org.usfirst.frc.team5700.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class MoveArmToAngle extends Command {

    private double targetAngleDeg;
    private double delaySec;
	
	public MoveArmToAngle(double angleDeg) {
        requires(Robot.arm);
        
        targetAngleDeg = angleDeg;
        this.delaySec = 0;
    }
	
	public MoveArmToAngle(double angleDeg, double delaySec) {
        requires(Robot.arm);
        
        targetAngleDeg = angleDeg;
        this.delaySec = delaySec;
    }

    // Called just before this Command runs the first time
    protected void initialize() {	
    		//Timer.delay(delaySec);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    		Robot.arm.moveToAngle(targetAngleDeg);
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
