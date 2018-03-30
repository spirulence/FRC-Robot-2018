package org.usfirst.frc.team5700.robot.subsystems;

import org.usfirst.frc.team5700.robot.RobotMap;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;

public class BoxIntake extends Subsystem {
	
	private Solenoid solenoid;
	Spark intakeMotors;
	
	private double intakeSpeed = -0.65; //TODO number
	
    public BoxIntake() {
		super();
	    	solenoid = new Solenoid(RobotMap.EXTEND_INTAKES_CHANNEL);

	    	intakeMotors = new Spark(RobotMap.INTAKE_MOTORS);
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
		intakeMotors.set(intakeSpeed);
	}
	
	//these methods are for pulling in the box (intaking).
	public void spinMotorsIn() {
		intakeMotors.set(-intakeSpeed);
	}
	
	//this method is for stopping the intake motors.
	public void stopMotors() {
		intakeMotors.set(0);
	}
		
}

