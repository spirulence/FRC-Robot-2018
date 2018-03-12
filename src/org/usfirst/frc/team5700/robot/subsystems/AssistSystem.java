package org.usfirst.frc.team5700.robot.subsystems;

import org.usfirst.frc.team5700.robot.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class AssistSystem extends Subsystem {

	private DoubleSolenoid pistons; 
	
	public AssistSystem() {
		super();
		//pistons = new DoubleSolenoid(RobotMap.ASSIST_HOLD_CHANNEL, RobotMap.ASSIST_RELEASE_CHANNEL);
		//pistons.set(DoubleSolenoid.Value.kForward);
	}

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }	
    public void releaseAssist(){
    		//pistons.set(DoubleSolenoid.Value.kReverse);    	
    }
    public void holdAssist() {
    		//pistons.set(DoubleSolenoid.Value.kForward);
    }
}

