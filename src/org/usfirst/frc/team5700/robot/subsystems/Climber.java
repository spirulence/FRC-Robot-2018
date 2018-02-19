package org.usfirst.frc.team5700.robot.subsystems;

import org.usfirst.frc.team5700.robot.Robot;
import org.usfirst.frc.team5700.robot.RobotMap;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Climber extends Subsystem {

	private SpeedController climberMotor = new Spark(RobotMap.CLIMBER_MOTOR);
	//TODO find good home for climber speed throttle
	public double speedUp = (Robot.oi.getAuxLeftStick().getThrottle() + 1)/2.0;
	public double speedDown = (Robot.oi.getAuxRightStick().getThrottle() - 1)/2.0;
	
	private DigitalInput hallSensorTop = new DigitalInput(0);
	private DigitalInput hallSensorBottom = new DigitalInput(0);
	
//	public void setSpeed(double speed) {
//		climberMotor.set(speed);	
//	}
	
	public void up() {
		if (!hallSensorTop.get())
			climberMotor.set(speedUp);
		else
			climberMotor.set(0);
		
	}

	public void down() {
		if (!hallSensorBottom.get())
			climberMotor.set(speedDown);
		else
			climberMotor.set(0);
		
	}
	
	public void stop() {
		climberMotor.set(0.0);
	}
	
	public boolean getHallSensorTopValue() {
		return hallSensorTop.get();
	}
	
	public boolean getHallSensorBottomValue() {
		return hallSensorBottom.get();
	}
	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

