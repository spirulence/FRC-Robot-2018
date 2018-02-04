package org.usfirst.frc.team5700.robot;

import org.usfirst.frc.team5700.robot.commands.IntakeBox;
import org.usfirst.frc.team5700.robot.commands.LeftIntakeBox;
import org.usfirst.frc.team5700.robot.commands.ReverseIntakeBox;
import org.usfirst.frc.team5700.robot.commands.RightIntakeBox;

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
	JoystickButton leftIntake;
	JoystickButton rightIntake;
	JoystickButton leftIntakeMotor;
	JoystickButton rightIntakeMotor;
	JoystickButton leftIntakeMotorReverse;
	JoystickButton rightIntakeMotorReverse;

	public OI() {
		slowDrive = new JoystickButton(rightStick, ButtonMap.SLOW_DRIVE);
		toggleDirection = new JoystickButton(rightStick, ButtonMap.TOGGLE_DIRECTION);
		//intakeBox = new JoystickButton(leftStick, ButtonMap.INTAKE_BOX);
		//reverseIntakeBox = new JoystickButton(rightStick, ButtonMap.REVERSE_INTAKE_BOX);
		leftIntake = new JoystickButton(leftStick, ButtonMap.LEFT_INTAKE);
		rightIntake = new JoystickButton(rightStick, ButtonMap.RIGHT_INTAKE);
		leftIntakeMotor = new JoystickButton (leftStick, ButtonMap.LEFT_INTAKE_MOTOR);
		rightIntakeMotor = new JoystickButton (rightStick, ButtonMap.RIGHT_INTAKE_MOTOR);
		leftIntakeMotorReverse = new JoystickButton (leftStick, ButtonMap.LEFT_INTAKE_MOTOR_REVERSE);
		rightIntakeMotorReverse = new JoystickButton (rightStick, ButtonMap.RIGHT_INTAKE_MOTOR_REVERSE);
		//set commands
		//box intake
		leftIntake.whileHeld(new LeftIntakeBox());
		rightIntake.whileHeld(new RightIntakeBox());
		//intakeBox.whileHeld(new IntakeBox());
		//reverseIntakeBox.whileHeld(new ReverseIntakeBox());
	}
	

	public boolean getLeftIntakeMotorReverse(){
		return leftIntakeMotorReverse.get();
	}
	
	public boolean getRightIntakeMotorReverse(){
		return rightIntakeMotorReverse.get();
	}
	
	public boolean getLeftIntakeMotor(){
		return leftIntakeMotor.get();
	}
	
	public boolean getRightIntakeMotor(){
		return rightIntakeMotor.get();
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


