package org.usfirst.frc.team5700.robot.subsystems;

import org.usfirst.frc.team5700.robot.RobotMap;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class BoxIntake extends Subsystem {
	private SpeedController leftIntakeMotor = new Spark(RobotMap.LEFT_INTAKE_MOTOR);
	private SpeedController rightIntakeMotor = new Spark(RobotMap.RIGHT_INTAKE_MOTOR);
	
	private double intakeSpeed = 0.8;
	private double reverseIntakeSpeed = -0.8;
	
//	public BoxIntake() {
//		intakeMotor = new Spark(RobotMap.INTAKE_MOTOR);
//	}+
	
	

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	//This method is for intaking a box.
	public void setIntakeSpeed(double speed) {
		leftIntakeMotor.set(speed);
		rightIntakeMotor.set(speed);
		
	}
	
	public void setReverseIntakeSpeed(double speed) {
		leftIntakeMotor.set(speed);
		rightIntakeMotor.set(speed);
	}
	
	
	public void intakeBox() {
		leftIntakeMotor.set(intakeSpeed);
		rightIntakeMotor.set(intakeSpeed);
		
	}
	
	public void reverseIntakeBox() {
		leftIntakeMotor.set(reverseIntakeSpeed);
		rightIntakeMotor.set(reverseIntakeSpeed);
	}


	public void stopBoxIntake() {
		leftIntakeMotor.set(0.0);
		rightIntakeMotor.set(0.0);
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
		//setDefaultCommand(new MySpecialCommand());
    }

	public void setRightMotor(double speed) {
		rightIntakeMotor.set(speed);
	}

	public void setLeftMotor(double speed) {
		leftIntakeMotor.set(speed);
	}
}

