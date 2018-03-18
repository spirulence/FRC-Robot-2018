package org.usfirst.frc.team5700.robot;

import org.usfirst.frc.team5700.robot.commands.ClimberDown;
import org.usfirst.frc.team5700.robot.commands.ClimberUp;
import org.usfirst.frc.team5700.robot.commands.ExtendIntake;
import org.usfirst.frc.team5700.robot.commands.GrabCube;
import org.usfirst.frc.team5700.robot.commands.ReleaseCube;
import org.usfirst.frc.team5700.robot.commands.ResetArmEncoder;
import org.usfirst.frc.team5700.robot.commands.IntakeBox;
import org.usfirst.frc.team5700.robot.commands.IntakeSpinIn;
import org.usfirst.frc.team5700.robot.commands.IntakeSpitOut;
import org.usfirst.frc.team5700.robot.commands.MoveArmToAngle;
import org.usfirst.frc.team5700.robot.commands.MoveElevatorDistance;
import org.usfirst.frc.team5700.robot.commands.ReleaseAssistArm;
import org.usfirst.frc.team5700.robot.commands.ResetElevatorEncoder;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.Joystick;


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

	JoystickButton spinIntakeIn;
	JoystickButton spitIntakeOut;
	JoystickButton extendIntake;
	JoystickButton releaseCube;
	JoystickButton grabCube;
	JoystickButton climberUp;
	JoystickButton climberDown;
	JoystickButton moveElevatorDistance; 
	JoystickButton releaseAssist;
	JoystickButton zeroElevatorEncoder;
	JoystickButton resetArmEncoder;
	//JoystickButton moveArmToAngle;
	JoystickButton rotateArmTo0;
	JoystickButton rotateArmTo180;
	
	public OI() {
		spinIntakeIn = new JoystickButton (driveRightStick, ButtonMap.SPIN_INTAKE_IN);
		spitIntakeOut = new JoystickButton (driveLeftStick, ButtonMap.SPIN_INTAKE_OUT);
		extendIntake = new JoystickButton (driveRightStick, ButtonMap.EXTEND_INTAKE);
		releaseCube = new JoystickButton(auxRightStick, ButtonMap.GRABBER_OPEN);
		grabCube = new JoystickButton(auxRightStick, ButtonMap.GRABBER_CLOSE);
		climberUp = new JoystickButton(auxRightStick, ButtonMap.CLIMBER_UP);
		climberDown = new JoystickButton(auxRightStick, ButtonMap.CLIMBER_DOWN);
		moveElevatorDistance = new JoystickButton(auxRightStick, ButtonMap.MOVE_ELEVATOR_DISTANCE);
		releaseAssist = new JoystickButton(auxLeftStick, ButtonMap.ASSIST_RELEASE);
		zeroElevatorEncoder  = new JoystickButton(auxRightStick, ButtonMap.ZERO_ELEVATOR_ENCODER);
		//Arm
		resetArmEncoder = new JoystickButton(auxLeftStick, ButtonMap.RESET_ARM_ENCODER);
		rotateArmTo0 = new JoystickButton(auxLeftStick, ButtonMap.ROTATE_ARM_TO_0);
		rotateArmTo180 = new JoystickButton(auxLeftStick, ButtonMap.ROTATE_ARM_TO_180);
		
		
		//set commands
		//box intake
		spinIntakeIn.whileHeld(new IntakeSpinIn());
		extendIntake.whileHeld(new ExtendIntake());
		spitIntakeOut.whileHeld(new IntakeSpitOut());
		
		//Elevator
		moveElevatorDistance.whenPressed(new MoveElevatorDistance(1));
		zeroElevatorEncoder.whenPressed(new ResetElevatorEncoder());
		
		//grabber
		grabCube.whenPressed(new GrabCube());
		releaseCube.whenPressed(new ReleaseCube());
		//climber
		climberUp.whileHeld(new ClimberUp());
		climberDown.whileHeld(new ClimberDown());
		//climber assist
		releaseAssist.whileHeld(new ReleaseAssistArm());
		
		//arm 
		resetArmEncoder.whenPressed(new ResetArmEncoder());
		rotateArmTo0.whileHeld(new MoveArmToAngle(0));
		rotateArmTo180.whileHeld(new MoveArmToAngle(180));
		//moveArmToAngle.whileHeld(new MoveArmToAngle(90));
		
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
	
	public double getRightStickThrottle() {
		return (driveRightStick.getThrottle() + 1)/2.0;
	}
}


