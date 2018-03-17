package org.usfirst.frc.team5700.robot.commands;

import org.usfirst.frc.team5700.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class ArcadeDriveWithJoysticks extends Command {
	
	public ArcadeDriveWithJoysticks() {
		requires(Robot.drivetrain);
	}

	protected void execute() {
		Robot.drivetrain.arcadeDrive(Robot.oi.getDriveRightStick(), Robot.oi.getDriveLeftStick(), Robot.oi.getSquaredInput());
		//Robot.drivetrain.safeArcadeDrive(Robot.oi.getDriveRightStick(), Robot.oi.getDriveLeftStick());
	}

	protected boolean isFinished() {
		return false;
	}
}