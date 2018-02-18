package org.usfirst.frc.team5700.robot.subsystems;

import org.usfirst.frc.team5700.robot.RobotMap;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Climber extends Subsystem {

	private SpeedController climberMotor = new Spark(RobotMap.CLIMBER_MOTOR);
	public double climberSpeedUp = 0.5;
	public double climberSpeedReverse = -0.5;
	
	public void climberSpeedUp(double speed) {
		climberMotor.set(speed);
		
	}
	
	public void climberSpeedReverse(double speed) {
		climberMotor.set(speed);
		
	}
	
	public void climberGoUp() {
		climberMotor.set(climberSpeedUp);
		
	}

	public void climberGoReverse() {
		climberMotor.set(climberSpeedReverse);
		
	}
	
	public void setClimberMotor(double speed) {
		climberMotor.set(speed);
	}
	
	public void stopClimber() {
		climberMotor.set(0.0);
	}
	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

