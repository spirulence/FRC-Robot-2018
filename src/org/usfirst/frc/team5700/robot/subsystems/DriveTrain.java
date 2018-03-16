package org.usfirst.frc.team5700.robot.subsystems;

import org.usfirst.frc.team5700.robot.Robot;
import org.usfirst.frc.team5700.robot.RobotMap;
import org.usfirst.frc.team5700.robot.commands.ArcadeDriveWithJoysticks;

import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Sendable;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class DriveTrain extends Subsystem {

	private SpeedController leftMotor = new Spark(RobotMap.LEFT_DRIVE_MOTOR);
	private SpeedController rightMotor = new Spark(RobotMap.RIGHT_DRIVE_PWM);		

	private RobotDrive drive = new RobotDrive(leftMotor, rightMotor);
	private BuiltInAccelerometer accel = new BuiltInAccelerometer();

	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	private double limitedY = 0;
	private double limitedX = 0;

	/**
	 * Arcade Drive
	 * @param rightStick joystick is for moving forwards and backwards
	 * @param leftStick joystick is for turning
	 */
	public void arcadeDrive(Joystick rightStick, Joystick leftStick, boolean squaredInputs) {
		double speed = 1;
		
		Preferences prefs = Preferences.getInstance();
		//get max change rates  from Preferences Table
		double maxChangeY = prefs.getDouble("MaxChangeY", 0.05);
		double maxChangeX = prefs.getDouble("MaxChangeX", 0.05);
		
		double y = rightStick.getY();
		double x = leftStick.getX();

		double changeY = y - limitedY;
		double changeX = x - limitedX;
		
		if (Math.abs(changeY) > maxChangeY)	
			limitedY += (changeY > 0) ? maxChangeY : - maxChangeY;
		else
			limitedY = y;
		
		if (Math.abs(changeX) > maxChangeX)	{
			limitedX += (changeX > 0) ? maxChangeX : - maxChangeX;
			System.out.println("Limiting x to " + limitedX);
		}
		else
			limitedX = x;


		drive.arcadeDrive(-limitedY, -limitedX, squaredInputs);	
	}

	public void drive(double outputMagnitude, double curve) {
		drive.drive(outputMagnitude, curve);
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
}


