package org.usfirst.frc.team5700.robot.subsystems;

import org.usfirst.frc.team5700.robot.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Grabber extends Subsystem {
	
	private Solenoid piston;
	
	public Grabber() {
		super();
		piston = new Solenoid(RobotMap.GRABBER_CHANNEL);
		
		piston.set(false); 
	}
	
	public void open() {
		piston.set(true); 
	}
	
	public void close() {
		piston.set(false);
	}
   
    public void initDefaultCommand() {
    }
}
