package org.usfirst.frc.team5700.robot.subsystems;

import org.usfirst.frc.team5700.robot.OI;
import org.usfirst.frc.team5700.robot.Robot;
import org.usfirst.frc.team5700.robot.RobotMap;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Climber extends Subsystem {

	private SpeedController climberMotor = new Spark(RobotMap.CLIMBER_MOTOR);
	//public double climberSpeedUp = (Robot.oi.getAuxLeftStick().getThrottle() + 1)/2;
	//public double climberSpeedReverse = (Robot.oi.getAuxRightStick().getThrottle() - 1)/2;
	
	private DigitalInput hallSensor = new DigitalInput(0);
	
	public void setSpeed(double speed) {
		climberMotor.set(speed);	
	}
	
//	public void goUp() {
//		climberMotor.set(climberSpeedUp);
//		
//	}
//
//	public void goReverse() {
//		climberMotor.set(climberSpeedReverse);
//		
//	}
	
	public void stop() {
		climberMotor.set(0.0);
	}
	
	public boolean getHallSensorValue() {
		return hallSensor.get();
	}
	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

