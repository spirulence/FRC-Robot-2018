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
	private DigitalInput hallSensorTop = new DigitalInput(8);
	private DigitalInput hallSensorBottom = new DigitalInput(9);
	
	public Climber() {
		climberMotor.setInverted(true);
	}
	
	public void up(double speed) {
		if (hallSensorTop.get())
			climberMotor.set(speed);
		else
			climberMotor.set(0);
		
	}

	public void down(double speed) {
		if (hallSensorBottom.get())
			climberMotor.set(speed);
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

