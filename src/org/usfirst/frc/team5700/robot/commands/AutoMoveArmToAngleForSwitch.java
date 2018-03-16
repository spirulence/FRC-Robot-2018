package org.usfirst.frc.team5700.robot.commands;

import org.usfirst.frc.team5700.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoMoveArmToAngleForSwitch extends Command {

    private double targetAngleDeg;
    private boolean robotOnRight;
    private boolean switchOnRobotSide;
    private boolean doingCenter = false;
	
	public AutoMoveArmToAngleForSwitch(double angleDeg, boolean robotOnRight) {
        requires(Robot.arm);
        
        targetAngleDeg = angleDeg;
        this.robotOnRight = robotOnRight;
    }
	
	/**
	 * Constructor to use when doing center switch auto.
	 * @param angle in degrees
	 */
	public AutoMoveArmToAngleForSwitch(double angle) {
		targetAngleDeg = angle;
		doingCenter = true;
	}

    // Called just before this Command runs the first time
    protected void initialize() {
    		if (doingCenter) {
    			if (!Robot.switchOnRight) {
    				targetAngleDeg = -1 * targetAngleDeg;
    			}
    		} else {
    			switchOnRobotSide = robotOnRight ? Robot.switchOnRight : !Robot.switchOnRight;
    		}
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    		if (switchOnRobotSide) {
    			Robot.arm.moveToAngle(targetAngleDeg);
    		}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.atSwitch;
    }

    // Called once after isFinished returns true
    protected void end() {
    		Robot.grabber.open();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    		end();
    }
}
