package org.usfirst.frc.team5700.robot.subsystems;

import org.usfirst.frc.team5700.robot.RobotMap;
import org.usfirst.frc.team5700.robot.commands.ArmMove;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Arm extends Subsystem {

	private SpeedController armMotor = new Spark(RobotMap.ARM_MOTOR);
	public double armSpeed = 0.5;
	 
	public void armSpeed(double speed) {
		armMotor.set(speed);	
	}

	public void armGo() {
		armMotor.set(armSpeed);
	}

	public void setElevatorMotor(double speed) {
		armMotor.set(speed);
	}
	
	public void stopArm() {
		armMotor.set(0.0);
	}
	
	public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new ArmMove());
    }
}