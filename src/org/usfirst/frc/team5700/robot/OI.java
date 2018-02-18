package org.usfirst.frc.team5700.robot;

import org.usfirst.frc.team5700.robot.commands.ExtendLeft;
import org.usfirst.frc.team5700.robot.commands.ExtendRight;
import org.usfirst.frc.team5700.robot.commands.IntakeBox;
import org.usfirst.frc.team5700.robot.commands.IntakeSpinIn;
import org.usfirst.frc.team5700.robot.commands.IntakeSpitAndExtend;
import org.usfirst.frc.team5700.robot.commands.IntakeSpitOut;

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
	private Joystick auxRightStick = new Joystick(3);
	private Joystick auxLeftStick = new Joystick(4);
	
	// Setting squaredInput to true decreases the sensitivity for tankdrive at lower speeds
	private boolean squaredInput = true;

	JoystickButton slowDrive;
	JoystickButton toggleDirection;
	JoystickButton extendRight;
	JoystickButton extendLeft;
	JoystickButton intakeSpinIn;
	JoystickButton intakeSpitOut;
	JoystickButton intakeBox;
	JoystickButton spitAndExtend;
	public OI() {
		slowDrive = new JoystickButton(driveRightStick, ButtonMap.SLOW_DRIVE);
		toggleDirection = new JoystickButton(driveRightStick, ButtonMap.TOGGLE_DIRECTION);
		extendRight = new JoystickButton(driveRightStick, ButtonMap.EXTEND_RIGHT);
		extendLeft = new JoystickButton(driveRightStick, ButtonMap.EXTEND_LEFT);
		intakeSpinIn = new JoystickButton(driveRightStick, ButtonMap.INTAKE_SPIN_IN);
		intakeSpitOut = new JoystickButton (driveLeftStick, ButtonMap.INTAKE_SPIT_OUT);
		intakeBox = new JoystickButton (driveRightStick, ButtonMap.INTAKE_BOX);
		spitAndExtend = new JoystickButton (driveLeftStick, ButtonMap.SPIT_AND_EXTEND);
		//set commands
		//box intake
		intakeBox.whileHeld(new IntakeBox());
		extendLeft.whileHeld(new ExtendLeft());
		extendRight.whileHeld(new ExtendRight());
		intakeSpinIn.whileHeld(new IntakeSpinIn());
		intakeSpitOut.whileHeld(new IntakeSpitOut());
		spitAndExtend.whileHeld(new IntakeSpitAndExtend());
		
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


