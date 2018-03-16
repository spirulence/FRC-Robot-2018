package org.usfirst.frc.team5700.robot.subsystems;

import org.usfirst.frc.team5700.robot.RobotMap;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Grabber extends Subsystem {
	
	private Solenoid piston;
	private boolean hasCube;
	
	public Grabber() {
		super();
		piston = new Solenoid(RobotMap.GRABBER_CHANNEL);
		open();
	}
	
	public void close() {
		piston.set(false);
		hasCube = true;
	}
	
	public void open() {
		piston.set(true);
		hasCube = false;
	}
   
    public void initDefaultCommand() {
    }

	public boolean hasCube() {
		return hasCube;
	}
}
