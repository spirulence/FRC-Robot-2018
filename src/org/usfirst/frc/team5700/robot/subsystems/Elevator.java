package org.usfirst.frc.team5700.robot.subsystems;

import org.usfirst.frc.team5700.robot.Constants;
import org.usfirst.frc.team5700.robot.Instrum;
import org.usfirst.frc.team5700.robot.Robot;
import org.usfirst.frc.team5700.robot.commands.MoveElevatorWithJoystick;
import org.usfirst.frc.team5700.robot.subsystems.Arm.ArmCollisionBounds;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * @author roman
 * @author maddy
 * @author renatatatata
 *
 */

public class Elevator extends Subsystem {

	/*
	 * FeedForwards
	 */
	private double lowNoCubeFF, highNoCubeFF, lowWCubeFF, highWCubeFF;

	//Motor Controller
	private WPI_TalonSRX talon = new WPI_TalonSRX(1);


	//Limit Hall Effect Sensors
	//private DigitalInput topLimit, interstageLimit, bottomLimit;

	//Constants
	public static final double heightIn = 57;
	public static final double bottomHeightIn = 0.25;
	public static final double interstageEngagedHeightIn = 26.1;
	public static final double winchRadiusIn = 1.125;
	public static final double reductionToEncoder = 5;
	public static final double winchCircumferenceIn = 2 * Math.PI * winchRadiusIn;
	//public static final double ticksPerIn = (Constants.VersaEncoderTPR * reductionToEncoder) / winchCircumferenceIn;
	public static final double inPerTick = 0.00034836721; 
	public static final double rotationsInSpan = heightIn/winchCircumferenceIn;
	public static final double encoderMaxSpeed = 30100; //ticks per 100 ms

	public Elevator() {
		
		super();

		//Preferences table
		Preferences prefs = Preferences.getInstance();

		//Instantiate limit sensors and variables
		//		topLimit = new DigitalInput(RobotMap.TOP_LIMIT_PORT);
		//		interstageLimit = new DigitalInput(RobotMap.INTERSTAGE_LIMIT_PORT);
		//		bottomLimit = new DigitalInput(RobotMap.BOTTOM_LIMIT_PORT);
		
		/* add name for Shuffleboard */
		talon.setName("Elevator", "Talon");

		/* first choose the sensor */
		talon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute, Constants.PID_LOOP_IDX, Constants.TIMEOUT_MS);

		talon.setSelectedSensorPosition(0, Constants.PID_LOOP_IDX, Constants.TIMEOUT_MS); //this would reset it after reboot
		talon.setSensorPhase(false);
		talon.setInverted(false);

		//set talon max output
		talon.configNominalOutputForward(1, Constants.TIMEOUT_MS);
		talon.configNominalOutputReverse(-1, Constants.TIMEOUT_MS);

		/* Set relevant frame periods to be at least as fast as periodic rate */
		talon.setStatusFramePeriod(StatusFrameEnhanced.Status_13_Base_PIDF0, 10, Constants.TIMEOUT_MS);
		talon.setStatusFramePeriod(StatusFrameEnhanced.Status_10_MotionMagic, 10, Constants.TIMEOUT_MS);

		/* set the peak and nominal outputs */
		talon.configNominalOutputForward(0, Constants.TIMEOUT_MS);
		talon.configNominalOutputReverse(0, Constants.TIMEOUT_MS);
		talon.configPeakOutputForward(1, Constants.TIMEOUT_MS);
		talon.configPeakOutputReverse(-1, Constants.TIMEOUT_MS);

		/* set closed loop gains in slot 0 - see documentation */
		talon.selectProfileSlot(Constants.SLOT_IDX, Constants.PID_LOOP_IDX);
		talon.config_kF(0, Constants.TALON_MAX_OUTPUT * 1.0/encoderMaxSpeed, Constants.TIMEOUT_MS);
		talon.config_kP(0, 0.33, Constants.TIMEOUT_MS);
		talon.config_kI(0, 0.001, Constants.TIMEOUT_MS);
		talon.config_kD(0, 0.13511, Constants.TIMEOUT_MS);

		/* set acceleration and cruise velocity - see documentation */
		talon.configMotionCruiseVelocity(16000, Constants.TIMEOUT_MS);
		talon.configMotionAcceleration(16000, Constants.TIMEOUT_MS);

		//Feed Forward Values
		lowNoCubeFF = prefs.getDouble("lowNoCubeFF", -0.01);
		highNoCubeFF = prefs.getDouble("highNoCubeFF", 0.03);
		lowWCubeFF = prefs.getDouble("lowWCubeFF", 0.0);
		highWCubeFF = prefs.getDouble("highWCubeFF", 0.06);
	}

	public void moveElevatorWithJoystick(double stickValue) {
		//addFeedForward();
		double input = stickValue + getFeedForward();

		//Limit Sensor Logic
		if (!Robot.oi.overrideLimits()) {
			if (atTopLimit()) {
				setTalon(Math.min(0, input));
			} else if (atBottomLimit()) {
				setTalon(Math.max(0, input));
			} else {
				setTalon(input);
			}
		} else {
			setTalon(input);
		}
	}

	public boolean atTopLimit() {
		return getHeight() > heightIn;
	}

	public boolean getInterstageLimit() {
		return getHeight() > interstageEngagedHeightIn;
	}

	public boolean atBottomLimit() {
		return getHeight() <= bottomHeightIn || isColliding();
	}

	public TalonSRX getTalon() {
		return talon;
	}

	public void zeroEncoder() {
		talon.setSelectedSensorPosition(0, Constants.PID_LOOP_IDX, Constants.TIMEOUT_MS);
		System.out.println("You just zeroed the elevator.");
	}

	public void initDefaultCommand() {
		setDefaultCommand(new MoveElevatorWithJoystick());
	}

	public double getTalonOutputVoltage() {
		return talon.getMotorOutputVoltage();
	}

	private double getFeedForward() {

		double feedForward = 0;

		//Feed Forward Logic
		if (getInterstageLimit()) {
			if (Robot.grabber.hasCube()) {
				feedForward = highWCubeFF;
			} else {
				feedForward = highNoCubeFF;
			}
		} else {
			if (Robot.grabber.hasCube()) {
				feedForward = lowWCubeFF;
			} else {
				feedForward = lowNoCubeFF;
			}
		}

		return feedForward;
	}

	/**
	 * Sets TalonSRX to output value
	 * @param output, as percent input (-1 through 1)
	 */
	private void setTalon(double output) {
		talon.set(ControlMode.PercentOutput, output);
	}

	/**
	 * @param height in inches, absolute
	 */
	public void moveToHeight(double heightIn) {
		StringBuilder sb = new StringBuilder();

		talon.set(ControlMode.MotionMagic, heightIn / inPerTick);

		/* append more signals to print when in speed mode. */
		sb.append(" err:");
		sb.append(talon.getClosedLoopError(Constants.PID_LOOP_IDX));
		sb.append(" trg:");
		sb.append(heightIn);

		Instrum.Process(talon, sb);
	}

	public double getEncoderTicks() {
		return talon.getSelectedSensorPosition(0);
	}

	public double getHeight() {
		return getEncoderTicks() * inPerTick;
	} 

	public double getVelocityTicks() {
		return talon.getSelectedSensorVelocity(0);
	}

	private boolean isColliding() {

		boolean isColliding = false;
		double armAngle = Robot.arm.get180NormalizedAngle();

		ArmCollisionBounds bounds = Robot.grabber.hasCube() ? Robot.arm.withCubeBounds: Robot.arm.noCubeBounds;

		if (Math.abs(getHeight()) <= bounds.heightIn
				&& Math.abs(armAngle) <= bounds.outsideAngle
				&& !(Math.abs(armAngle) <= bounds.insideAngle)) {
			isColliding = true;
		}
		return isColliding;
	}
}

