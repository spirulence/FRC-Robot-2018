package org.usfirst.frc.team5700.robot.subsystems;

import org.usfirst.frc.team5700.robot.RobotMap;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class BoxIntake extends Subsystem {
	
	public Compressor compressor = new Compressor();
	
	private DoubleSolenoid leftPiston, rightPiston;
	Spark leftIntakeMotor;
	Spark rightIntakeMotor;

	
	private double intakeSpeed = 0.8;
	
    public BoxIntake() {
		super();
	    	leftPiston = new DoubleSolenoid(0, 1);
	    	leftPiston.set(DoubleSolenoid.Value.kReverse);
	    	
	    	rightPiston = new DoubleSolenoid(2, 3);
	    	rightPiston.set(DoubleSolenoid.Value.kReverse);

	    	leftIntakeMotor = new Spark(RobotMap.LEFT_INTAKE_MOTOR);
	    	rightIntakeMotor = new Spark(RobotMap.RIGHT_INTAKE_MOTOR);
    }
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
		//setDefaultCommand(new MySpecialCommand());
    }
    //this method says what the intake pistons can do
    public void boxIntakeOutLeft(){
		leftPiston.set(DoubleSolenoid.Value.kForward);    	
    }
    
    public void boxIntakeOutRight(){
		rightPiston.set(DoubleSolenoid.Value.kForward);    	
    }
    
    public void boxIntakeInLeft(){
		leftPiston.set(DoubleSolenoid.Value.kReverse);
    }
    
    public void boxIntakeInRight(){
		rightPiston.set(DoubleSolenoid.Value.kReverse);
    }
    
    public void boxIntakeOut(){
		leftPiston.set(DoubleSolenoid.Value.kForward);
		rightPiston.set(DoubleSolenoid.Value.kForward);
    }
    
    public void boxIntakeIn(){
		leftPiston.set(DoubleSolenoid.Value.kReverse);
		rightPiston.set(DoubleSolenoid.Value.kReverse);
    }
    
	//These methods are for spitting out a box.
	public void reverseIntakeBox(){
		leftIntakeMotor.set(intakeSpeed * -1);
		rightIntakeMotor.set(intakeSpeed * -1);
	}
	
	public void reverseIntakeBoxLeft(){
		leftIntakeMotor.set(intakeSpeed * -1);
	}
	
	public void reverseIntakeBoxRight(){
		rightIntakeMotor.set(intakeSpeed * -1);
	}
	
	//these methods are for pulling in the box (intaking).
	public void intakeBox() {
		leftIntakeMotor.set(intakeSpeed);
		rightIntakeMotor.set(intakeSpeed);
		
	}
	
	public void intakeBoxLeft(){
		leftIntakeMotor.set(intakeSpeed);
	}
	
	public void intakeBoxRight(){
		rightIntakeMotor.set(intakeSpeed);
	}
	
	//this method is for stopping the intake motors.
	public void stopBoxIntake() {
		leftIntakeMotor.set(0.0);
		rightIntakeMotor.set(0.0);
	}
	public void stopBoxIntakeLeft(){
		leftIntakeMotor.set(0.0);
	}
	
	public void stopBoxIntakeRight(){
		rightIntakeMotor.set(0.0);
	}

	public void setRightMotor(double speed) {
		rightIntakeMotor.set(speed);
	}

	public void setLeftMotor(double speed) {
		leftIntakeMotor.set(speed);
	}
}

