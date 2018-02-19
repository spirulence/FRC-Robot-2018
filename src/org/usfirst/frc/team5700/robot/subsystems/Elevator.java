package org.usfirst.frc.team5700.robot.subsystems;

import org.usfirst.frc.team5700.robot.RobotMap;
import org.usfirst.frc.team5700.robot.commands.ElevatorMove;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Elevator extends Subsystem {
	
	private SpeedController elevatorMotor = new Spark(RobotMap.ELEVATOR_MOTOR);
	 
	public void setSpeed(double speed) {
		elevatorMotor.set(speed);
	}
	
	public void stopElevator() {
		elevatorMotor.set(0.0);
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new ElevatorMove());
    }
}

