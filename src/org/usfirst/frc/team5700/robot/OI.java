package org.usfirst.frc.team5700.robot;

import org.usfirst.frc.team5700.robot.commands.ExtendLeft;
import org.usfirst.frc.team5700.robot.commands.ExtendRight;
import org.usfirst.frc.team5700.robot.commands.IntakeSpinIn;
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

	private Joystick rightStick = new Joystick(0);
	private Joystick leftStick = new Joystick(1);

	// Setting squaredInput to true decreases the sensitivity for tankdrive at lower speeds
	private boolean squaredInput = true;

	JoystickButton slowDrive;
	JoystickButton toggleDirection;
	//JoystickButton intakeBox;
	//JoystickButton reverseIntakeBox;
	JoystickButton extendRight;
	JoystickButton extendLeft;
	JoystickButton intakeSpinIn;
	JoystickButton intakeSpitOut;

	public OI() {
		slowDrive = new JoystickButton(rightStick, ButtonMap.SLOW_DRIVE);
		toggleDirection = new JoystickButton(rightStick, ButtonMap.TOGGLE_DIRECTION);
		extendRight = new JoystickButton(rightStick, ButtonMap.EXTEND_RIGHT);
		extendLeft = new JoystickButton(leftStick, ButtonMap.EXTEND_LEFT);
		intakeSpinIn = new JoystickButton(rightStick, ButtonMap.INTAKE_SPIN_IN);
		intakeSpitOut = new JoystickButton (leftStick, ButtonMap.INTAKE_SPIT_OUT);
		//set commands
		//box intake
		extendLeft.whileHeld(new ExtendLeft());
		extendRight.whileHeld(new ExtendRight());
		intakeSpinIn.whileHeld(new IntakeSpinIn());
		intakeSpitOut.whileHeld(new IntakeSpitOut());
	}
	
	public Joystick getLeftStick() {
		return leftStick;	
	}

	public Joystick getRightStick() {
		return rightStick;
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


