package org.usfirst.frc.team5700.robot;

import org.usfirst.frc.team5700.utils.ButtonLocator;

public class ButtonMap {
		
		//Intake
		public static final ButtonLocator EXTEND_INTAKE = new ButtonLocator(1, Robot.oi.getDriveRightStick());
		public static final ButtonLocator SPIN_INTAKE_IN = new ButtonLocator(3, Robot.oi.getDriveRightStick());
		public static final ButtonLocator SPIN_INTAKE_OUT = new ButtonLocator(2, Robot.oi.getDriveRightStick());
//		//Grabber 
		public static final ButtonLocator GRABBER_OPEN = new ButtonLocator(1, Robot.oi.getAuxRightStick());
		public static final ButtonLocator GRABBER_CLOSE = new ButtonLocator(2, Robot.oi.getAuxRightStick());
		//Climber
		public static final ButtonLocator CLIMBER_UP = new ButtonLocator(2, Robot.oi.getAuxRightStick());
		public static final ButtonLocator CLIMBER_DOWN = new ButtonLocator(2, Robot.oi.getAuxLeftStick());
		//Climber Assist
		public static final ButtonLocator ASSIST_RELEASE = new ButtonLocator(1, Robot.oi.getAuxRightStick());
		public static final ButtonLocator ZERO_ELEVATOR_ENCODER = new ButtonLocator(6, Robot.oi.getAuxRightStick());
		//ELEVATOR
		public static final ButtonLocator MOVE_ELEVATOR_DISTANCE = new ButtonLocator(5, Robot.oi.getDriveRightStick());
}
