package org.usfirst.frc.team5700.robot.subsystems;

import org.usfirst.frc.team5700.robot.RobotMap;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Intake extends Subsystem {
	
		private double intakeRollerSpeed = 0.8;
		public Spark intakeMotor;
		
		
		public Intake() {
			intakeMotor = new Spark(RobotMap.INTAKE_MOTOR);
			
		}
		
		public void setRollerSpeed(double speed) {
			intakeMotor.set(speed);
		}
		
		public void intakeBox() {
			intakeMotor.set(intakeRollerSpeed);
		}
		
		public void stopRoller() {
			intakeMotor.set(0.0);
		}
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

