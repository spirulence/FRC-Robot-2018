package org.usfirst.frc.team5700.robot.subsystems;

import org.usfirst.frc.team5700.robot.RobotMap;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;

public class BoxIntake extends Subsystem {
	
	private DoubleSolenoid leftPiston, rightPiston;
	Spark leftIntakeMotor;
	Spark rightIntakeMotor;
	
	private Solenoid dingus;
	
	private double intakeSpeed = -0.65; //TODO number
	
    public BoxIntake() {
		super();
	    	leftPiston = new DoubleSolenoid(RobotMap.LEFT_PISTON_EXTEND_CHANNEL, RobotMap.LEFT_PISTON_RETRACT_CHANNEL);
	    	leftPiston.set(DoubleSolenoid.Value.kReverse);
	    	
	    	rightPiston = new DoubleSolenoid(RobotMap.RIGHT_PISTON_EXTEND_CHANNEL, RobotMap.RIGHT_PISTON_RETRACT_CHANNEL);
	    	rightPiston.set(DoubleSolenoid.Value.kReverse);

	    	leftIntakeMotor = new Spark(RobotMap.LEFT_INTAKE_MOTOR);
	    	rightIntakeMotor = new Spark(RobotMap.RIGHT_INTAKE_MOTOR);
	    	
	    	dingus = new Solenoid(RobotMap.DINGUS_CHANNEL);
	    	dingus.set(false);
    }
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
		//setDefaultCommand(new MySpecialCommand());
    }
    //this method says what the intake pistons can do
    public void extendLeft(){
		leftPiston.set(DoubleSolenoid.Value.kForward);    	
    }
    
    public void extendRight(){
		rightPiston.set(DoubleSolenoid.Value.kForward);    	
    }
    
    public void extendBoth(){
    	extendRight();
    	extendLeft();
    }
    
    public void retractLeft(){
		leftPiston.set(DoubleSolenoid.Value.kReverse);
    }
    
    public void retractRight(){
		rightPiston.set(DoubleSolenoid.Value.kReverse);
    }
    
    public void retractBoth(){
		retractRight();
		retractLeft();
    }
    
	//These methods are for spitting out a box.
	public void spitBothMotors(){
		spitLeft();
		spitRight();
	}
	
	public void spitLeft(){
		leftIntakeMotor.set(intakeSpeed * 1);
	}
	
	public void spitRight(){
		rightIntakeMotor.set(intakeSpeed * -1);
	}
	
	//these methods are for pulling in the box (intaking).
	public void spinMotorsIn() {
		leftMotorIn();
		rightMotorIn();
	}
	
	public void leftMotorIn(){
		leftIntakeMotor.set(-intakeSpeed);
	}
	
	public void rightMotorIn(){
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
	
	public void extendDingus() {
		dingus.set(true);
	}
	
	public void retractDingus() {
		dingus.set(false);
	}
}

