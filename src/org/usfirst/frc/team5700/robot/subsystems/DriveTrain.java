package org.usfirst.frc.team5700.robot.subsystems;

import org.usfirst.frc.team5700.robot.Constants;
import org.usfirst.frc.team5700.robot.Robot;
import org.usfirst.frc.team5700.robot.RobotMap;
import org.usfirst.frc.team5700.robot.commands.ArcadeDriveWithJoysticks;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class DriveTrain extends Subsystem {

	public static double MAX_SPEED_IN_PER_SEC; //TODO find
	public static double MAX_FORWARD_ACCEL;
	public static double MAX_BACKWARD_ACCEL;
	public static double MAX_SIDE_ACCEL;
	public double previousSpeedInput = 0;

	private SpeedController leftMotor = new Spark(RobotMap.LEFT_DRIVE_MOTOR);
	private SpeedController rightMotor = new Spark(RobotMap.RIGHT_DRIVE_PWM);

	private RobotDrive drive = new RobotDrive(leftMotor, rightMotor);
	private BuiltInAccelerometer accel = new BuiltInAccelerometer();

	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	private double limitedY = 0;
	private double limitedX = 0;

	private ADXRS450_Gyro gyro = new ADXRS450_Gyro();

	//Encoder specs: S4T-360-250-S-D (usdigital.com)
	//S4T Shaft Encoder, 360 CPR, 1/4" Dia Shaft, Single-Ended, Default Torque
	//Encoder Distance Constants
	public final static double WHEEL_BASE_WIDTH_IN = 25; //TOOD find
	public final static double WHEEL_DIAMETER = 6;
	public final static double PULSE_PER_REVOLUTION = 360;

	private Encoder leftEncoder = new Encoder(4, 5, false);
	private Encoder rightEncoder = new Encoder(6, 7, true);
	private boolean overrideDriveStick;
	private boolean overrideTurnStick;
	public double wantedChangeInSpeedInPerCycle;

	final static double distancePerPulseIn = Math.PI * WHEEL_DIAMETER / PULSE_PER_REVOLUTION;

	public DriveTrain() {
		leftEncoder.setDistancePerPulse(distancePerPulseIn);
		rightEncoder.setDistancePerPulse(distancePerPulseIn);
		
		leftMotor.setInverted(false);
		rightMotor.setInverted(false);
		
		resetSensors();
	}


	/**
	 * Arcade Drive
	 * @param rightStick joystick is for moving forwards and backwards
	 * @param leftStick joystick is for turning
	 */
	public void arcadeDrive(Joystick rightStick, Joystick leftStick, boolean squaredInputs) {

		Preferences prefs = Preferences.getInstance();
		//get max change rates  from Preferences Table
		//		double maxChangeY = prefs.getDouble("MaxChangeY", 0.05);
		//		double maxChangeX = prefs.getDouble("MaxChangeX", 0.05);
		//		
		//		double y = rightStick.getY();
		//		double x = leftStick.getX();
		//
		//		double changeY = y - limitedY;
		//		double changeX = x - limitedX;
		//		
		//		if (Math.abs(changeY) > maxChangeY)	
		//			limitedY += (changeY > 0) ? maxChangeY : - maxChangeY;
		//		else
		//			limitedY = y;
		//		
		//		if (Math.abs(changeX) > maxChangeX)	{
		//			limitedX += (changeX > 0) ? maxChangeX : - maxChangeX;
		//			System.out.println("Limiting x to " + limitedX);
		//		}
		//		else
		//			limitedX = x;


		drive.arcadeDrive(-rightStick.getY() * 0.8, -(leftStick.getX() + 0.05) * 0.8, squaredInputs);	
	}

	public void safeArcadeDrive(double speed, double turn) {

		double currentSpeedInPerSec = -getAverageEncoderRate(); //positive or negative

		setOverrideDriveStick(false);
		setOverrideTurnStick(false);

		//linear accel.
		double newSpeedInput = Math.signum(speed) * Math.pow(speed, 2); //squared input with sign
		Preferences prefs = Preferences.getInstance();
		
		//prevent getting 0 or negative values from prefs
		MAX_FORWARD_ACCEL = Math.max(prefs.getDouble("Max Forward Accel", 60), 5); //0.3 g -> 116 in/s^2
		MAX_BACKWARD_ACCEL = Math.max(prefs.getDouble("Max Backward Accel", 60), 5); //0.3 g -> 116 in/s^2
		MAX_SPEED_IN_PER_SEC = Math.max(prefs.getDouble("Max Speed", 168), Math.abs(currentSpeedInPerSec)); //14 ft/s
		wantedChangeInSpeedInPerCycle = newSpeedInput * MAX_SPEED_IN_PER_SEC - currentSpeedInPerSec;
		

		double speedThreshold = Math.max(prefs.getDouble("Speed Threshold", 1), 1);
		if (Math.abs(currentSpeedInPerSec) > speedThreshold) {
			if (wantedChangeInSpeedInPerCycle > MAX_FORWARD_ACCEL * Constants.CYCLE_SEC) {
				newSpeedInput = previousSpeedInput + (MAX_FORWARD_ACCEL * Constants.CYCLE_SEC / MAX_SPEED_IN_PER_SEC );
				setOverrideDriveStick(true);

			} else if (wantedChangeInSpeedInPerCycle < -MAX_BACKWARD_ACCEL * Constants.CYCLE_SEC) {
				newSpeedInput = previousSpeedInput - (MAX_BACKWARD_ACCEL * Constants.CYCLE_SEC / MAX_SPEED_IN_PER_SEC);
				setOverrideDriveStick(true);
			}
		}

		//rotational accel.
		double newTurnInput = Math.signum(turn) * Math.pow(turn, 2);
//		double turnRadiusIn = -Math.log(newTurnInput) * WHEEL_BASE_WIDTH_IN;
//		MAX_SIDE_ACCEL = prefs.getDouble("Max Side Accel", 60);
//		double radiusThreshhold = prefs.getDouble("radius threshhold", 10);
//		double wantedSideAccel = Math.pow(currentSpeedInPerSec, 2) / turnRadiusIn;
//
//		if (turnRadiusIn > radiusThreshhold && wantedSideAccel > MAX_SIDE_ACCEL) {
//			newTurnInput = Math.exp((-Math.pow(currentSpeedInPerSec, 2)/MAX_SIDE_ACCEL)/WHEEL_BASE_WIDTH_IN);
//			setOverrideTurnStick(true);
//		}


		drive.arcadeDrive(-newSpeedInput, -newTurnInput);
		previousSpeedInput = newSpeedInput;

	}

	public void drive(double outputMagnitude, double curve) {
		drive.drive(-outputMagnitude, -(curve));
	}

	public void stop() {
		drive.drive(0.0, 0.0);
	}

	public void initDefaultCommand() {
		setDefaultCommand(new ArcadeDriveWithJoysticks());
	}

	public double getXAccel() {
		return accel.getX();
	}

	public double getYAccel() {
		return accel.getY();
	}

	public double getZAccel() {
		return accel.getZ();
	}

	public void resetSensors() {
		gyro.reset();
		leftEncoder.reset();
		rightEncoder.reset();
	}

	public double getDistance() {
		return (leftEncoder.getDistance() + rightEncoder.getDistance()) / 2;
	}

	/**
	 * @return rate, ticks per second
	 */
	public double getAverageEncoderRate() {
		return ((leftEncoder.getRate() + rightEncoder.getRate())/2);
	}

	public double getHeading() {
		return gyro.getAngle();
	}

	public Encoder getRightEncoder() {
		return rightEncoder;
	}

	public Encoder getLeftEncoder() {
		return leftEncoder;
	}


	public boolean isOverrideDriveStick() {
		return overrideDriveStick;
	}


	public void setOverrideDriveStick(boolean overrideDriveStick) {
		this.overrideDriveStick = overrideDriveStick;
	}


	public boolean isOverrideTurnStick() {
		return overrideTurnStick;
	}


	public void setOverrideTurnStick(boolean overrideTurnStick) {
		this.overrideTurnStick = overrideTurnStick;
	}
}


