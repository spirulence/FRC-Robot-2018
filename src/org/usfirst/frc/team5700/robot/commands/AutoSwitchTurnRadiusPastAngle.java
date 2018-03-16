/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.frc.team5700.robot.commands;

import org.usfirst.frc.team5700.robot.Robot;
import org.usfirst.frc.team5700.robot.AutonomousPaths;
import org.usfirst.frc.team5700.robot.Dimensions;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Drive the given distance straight (negative values go backwards). Uses a
 * local PID controller to run a simple PID loop that is only enabled while this
 * command is running. The input is the averaged values of the left and right
 * encoders.
 */
public class AutoSwitchTurnRadiusPastAngle extends Command {
	
	private double targetAngleDeg;
	private double turnSpeed;
	private double turnRadiusIn;
	private AutonomousPaths paths;

	/**
	 * No PID
	 * @param radius
	 * @param angle
	 * @param speed
	 * @param left
	 */
	public AutoSwitchTurnRadiusPastAngle(double speed) {
		requires(Robot.drivetrain);
		this.turnSpeed = speed;
		paths = new AutonomousPaths();
	}

	@Override
	protected void initialize() {
		
		//logs
		System.out.println();
		System.out.println("TurnRadiusPastAngle Initiated");
		
		if (Robot.scaleOnRight) {
			targetAngleDeg = paths.rightSide.turnAngle;
			turnRadiusIn = paths.rightSide.turnRadius;
		} else {
			targetAngleDeg = paths.leftSide.turnAngle;
			turnRadiusIn = paths.leftSide.turnRadius;
		}

		System.out.println("  Turn Radius: " + turnRadiusIn);
	    	System.out.println("  Turn Angle: " + targetAngleDeg + " Degrees");
	    	System.out.println("  Drive Speed: " + turnSpeed);
    	
		Robot.drivetrain.reset();
	}

	@Override
	protected void execute() {
		Robot.drivetrain.drive(turnSpeed, Math.exp(-turnRadiusIn / Robot.drivetrain.WHEEL_BASE_WIDTH_IN));
	}

	@Override
	protected boolean isFinished() {
		return Math.abs(Robot.drivetrain.getHeading()) >= targetAngleDeg;
	}

	@Override
	protected void end() {
		Robot.drivetrain.reset();
		System.out.println("Turn Radius To Angle Command Complete");
	}
	
	protected void interrupted() {
		end();
	}
}
