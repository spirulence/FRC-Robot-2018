package org.usfirst.frc.team5700.robot;

import edu.wpi.first.wpilibj.DoubleSolenoid;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	//PWM
	public static final int LEFT_DRIVE_MOTOR = 0;
	public static final int RIGHT_DRIVE_PWM = 1;
	public static final int LEFT_INTAKE_MOTOR = 2;
	public static final int RIGHT_INTAKE_MOTOR = 3;
	public static final int ELEVATOR_MOTOR = 4;
	public static final int CLIMBER_MOTOR = 6; 
	public static final int ARM_MOTOR = 5;
	
	//Pneumatics
	public static final int GRABBER_CHANNEL = 1;
	public static final int DINGUS_CHANNEL = 0; //TODO
	public static final int ASSIST_RELEASE_CHANNEL = 2;
	public static final int ASSIST_HOLD_CHANNEL = 3;
	public static final int LEFT_PISTON_EXTEND_CHANNEL = 5;
	public static final int LEFT_PISTON_RETRACT_CHANNEL = 4;
	public static final int RIGHT_PISTON_EXTEND_CHANNEL= 7;
	public static final int RIGHT_PISTON_RETRACT_CHANNEL = 6;
}
