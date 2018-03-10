package org.usfirst.frc.team5700.robot;

import org.usfirst.frc.team5700.robot.commands.ClimberDown;
import org.usfirst.frc.team5700.robot.commands.ClimberUp;
import org.usfirst.frc.team5700.robot.commands.DingusGo;
import org.usfirst.frc.team5700.robot.commands.ExtendLeft;
import org.usfirst.frc.team5700.robot.commands.ExtendRight;
import org.usfirst.frc.team5700.robot.commands.GrabberClose;
import org.usfirst.frc.team5700.robot.commands.GrabberOpen;
import org.usfirst.frc.team5700.robot.commands.IntakeBox;
import org.usfirst.frc.team5700.robot.commands.IntakeSpinIn;
import org.usfirst.frc.team5700.robot.commands.IntakeSpitAndExtend;
import org.usfirst.frc.team5700.robot.commands.IntakeSpitOut;
import org.usfirst.frc.team5700.robot.commands.MoveElevatorDistance;
import org.usfirst.frc.team5700.robot.commands.ReleaseAssist;
import org.usfirst.frc.team5700.robot.commands.ResetElevatorEncoder;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;


/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

	private boolean toggle = false;
	private boolean hasBeenPressed = false;

	private Joystick driveRightStick = new Joystick(0);
	private Joystick driveLeftStick = new Joystick(1);
	private Joystick auxRightStick = new Joystick(2);
	private Joystick auxLeftStick = new Joystick(3);
	
	// Setting squaredInput to true decreases the sensitivity for tankdrive at lower speeds
	private boolean squaredInput = true;

	JoystickButton slowDrive;
	JoystickButton toggleDirection;
	JoystickButton extendRight;
	JoystickButton extendLeft;
	JoystickButton intakeBox;
	JoystickButton spitAndExtend;
//	JoystickButton grabberOpen;
//	JoystickButton grabberClose;
	JoystickButton climberUp;
	JoystickButton climberDown;
	JoystickButton moveElevatorDistance; 
	JoystickButton releaseAssist;
	JoystickButton zeroElevatorEncoder;
	
	public OI() {
		slowDrive = new JoystickButton(driveRightStick, ButtonMap.SLOW_DRIVE);
		toggleDirection = new JoystickButton(driveRightStick, ButtonMap.TOGGLE_DIRECTION);
		extendRight = new JoystickButton(driveRightStick, ButtonMap.EXTEND_RIGHT);
		extendLeft = new JoystickButton(driveRightStick, ButtonMap.EXTEND_LEFT);
		intakeBox = new JoystickButton (driveRightStick, ButtonMap.INTAKE_BOX);
		spitAndExtend = new JoystickButton (driveLeftStick, ButtonMap.SPIT_AND_EXTEND);
//		grabberOpen = new JoystickButton(auxRightStick, ButtonMap.GRABBER_OPEN);
//		grabberClose = new JoystickButton(auxRightStick, ButtonMap.GRABBER_CLOSE);
		climberUp = new JoystickButton(auxRightStick, ButtonMap.CLIMBER_UP);
		climberDown = new JoystickButton(auxLeftStick, ButtonMap.CLIMBER_DOWN);
		moveElevatorDistance = new JoystickButton(auxRightStick, ButtonMap.MOVE_ELEVATOR_DISTANCE);
		releaseAssist = new JoystickButton(auxRightStick, ButtonMap.ASSIST_RELEASE);
		zeroElevatorEncoder  = new JoystickButton(auxRightStick, ButtonMap.ZERO_ELEVATOR_ENCODER);
		
		//set commands
		//box intake
		intakeBox.whileHeld(new IntakeBox());
		extendLeft.whileHeld(new ExtendLeft());
		extendRight.whileHeld(new ExtendRight());
		spitAndExtend.whileHeld(new IntakeSpitAndExtend());
		
		//Elevator
		moveElevatorDistance.whenPressed(new MoveElevatorDistance(1));
		zeroElevatorEncoder.whenPressed(new ResetElevatorEncoder());
		
//		//grabber
//		grabberOpen.whenPressed(new GrabberOpen());
//		grabberClose.whenPressed(new GrabberClose());
		//climber
		climberUp.whileHeld(new ClimberUp());
		climberDown.whileHeld(new ClimberDown());
		//climber assist
		releaseAssist.whileHeld(new ReleaseAssist());
		
	}
	
	public Joystick getDriveLeftStick() {
		return driveLeftStick;	
	}

	public Joystick getDriveRightStick() {
		return driveRightStick;
	}
	
	public Joystick getAuxLeftStick() {
		return auxLeftStick;	
	}

	public Joystick getAuxRightStick() {
		return auxRightStick;
	}

	public boolean getSquaredInput() {
		return squaredInput;
	}

	public boolean driveSlow() {
		return slowDrive.get();
	}
	

	
	public boolean directionToggle() {
		if (toggleDirection.get() && !hasBeenPressed) {
			toggle = !toggle;
			hasBeenPressed = true;
		}

		if(!toggleDirection.get()) {
			hasBeenPressed = false;
		}
		return toggle;
	}
}


