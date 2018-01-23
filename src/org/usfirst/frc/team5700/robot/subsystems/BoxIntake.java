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
	private double boxRepositionSpeed = -0.8;
	
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
	
	public void intakeBox() {
		leftIntakeMotor.set(intakeSpeed);
		rightIntakeMotor.set(intakeSpeed);
		
	}
	//This method is for repositioning the box in the intake by making the intake motors spin opposite directions.
	public void setRepositionSpeed(double posSpeed, double negSpeed){
		leftIntakeMotor.set(posSpeed);
		rightIntakeMotor.set(negSpeed);
		
	}
	
	public void repositionBox() {
		leftIntakeMotor.set(boxRepositionSpeed);
		rightIntakeMotor.set(intakeSpeed);
	}

	public void stopBoxIntake() {
		leftIntakeMotor.set(0.0);
		rightIntakeMotor.set(0.0);
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
		//setDefaultCommand(new MySpecialCommand());
    }
}

