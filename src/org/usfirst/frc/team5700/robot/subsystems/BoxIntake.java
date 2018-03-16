package org.usfirst.frc.team5700.robot.subsystems;

import org.usfirst.frc.team5700.robot.RobotMap;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;

public class BoxIntake extends Subsystem {
	
	private Solenoid solenoid;
	Spark leftIntakeMotor;
	Spark rightIntakeMotor;
	
	private double intakeSpeed = -0.65; //TODO number
	
    public BoxIntake() {
		super();
	    	solenoid = new Solenoid(RobotMap.EXTEND_INTAKES_CHANNEL);

	    	leftIntakeMotor = new Spark(RobotMap.LEFT_INTAKE_MOTOR);
	    	rightIntakeMotor = new Spark(RobotMap.RIGHT_INTAKE_MOTOR);
    }
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
		//setDefaultCommand(new MySpecialCommand());
    }
    
    public void extendBoth(){
    		solenoid.set(true);
    }
    
    public void retractBoth(){
		solenoid.set(false);
    }
    
	//These methods are for spitting out a box.
	public void spinBothMotorsOut(){
		spinLeftOut();
		spinRightOut();
	}
	
	public void spinLeftOut(){
		leftIntakeMotor.set(intakeSpeed * 1);
	}
	
	public void spinRightOut(){
		rightIntakeMotor.set(intakeSpeed * -1);
	}
	
	//these methods are for pulling in the box (intaking).
	public void spinMotorsIn() {
		spinLeftIn();
		spinRightIn();
	}
	
	public void spinLeftIn(){
		leftIntakeMotor.set(-intakeSpeed);
	}
	
	public void spinRightIn(){
		rightIntakeMotor.set(intakeSpeed);
	}
	
	//this method is for stopping the intake motors.
	public void stopMotors() {
		stopLeftMotor();
		stopRightMotor();
		
	}
	
	public void stopLeftMotor(){
		leftIntakeMotor.set(0.0);
	}
	
	public void stopRightMotor(){
		rightIntakeMotor.set(0.0);
	}

	public void setRightMotor(double speed) {
		rightIntakeMotor.set(speed);
	}

	public void setLeftMotor(double speed) {
		leftIntakeMotor.set(speed);
	}
}

