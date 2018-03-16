package org.usfirst.frc.team5700.robot;

import org.usfirst.frc.team5700.utils.ButtonLocator;

public class ButtonMap {
		
		//Intake
		public static final ButtonLocator EXTEND_INTAKE = new ButtonLocator(1, Robot.oi.getDriveRightStick());
		public static final ButtonLocator SPIN_INTAKE_IN = new ButtonLocator(3, Robot.oi.getDriveRightStick());
		public static final ButtonLocator SPIN_INTAKE_OUT = new ButtonLocator(2, Robot.oi.getDriveRightStick());
		//Grabber 
		public static final ButtonLocator GRABBER_OPEN = new ButtonLocator(1, Robot.oi.getAuxRightStick());
		public static final ButtonLocator GRABBER_CLOSE = new ButtonLocator(2, Robot.oi.getAuxRightStick());
		//Climber
		public static final ButtonLocator CLIMBER_UP = new ButtonLocator(3, Robot.oi.getAuxRightStick());
		public static final ButtonLocator CLIMBER_DOWN = new ButtonLocator(3, Robot.oi.getAuxLeftStick());
		//Climber Assist
		public static final ButtonLocator ASSIST_RELEASE = new ButtonLocator(12, Robot.oi.getAuxRightStick());
		//ELEVATOR
		public static final ButtonLocator ZERO_ELEVATOR_ENCODER = new ButtonLocator(7, Robot.oi.getAuxRightStick());
		public static final ButtonLocator MOVE_ELEVATOR_DISTANCE = new ButtonLocator(5, Robot.oi.getAuxRightStick());
		//Arm
		public static final ButtonLocator RESET_ARM_ENCODER = new ButtonLocator(7, Robot.oi.getAuxLeftStick());
		public static final ButtonLocator ROTATE_ARM_TO_0 = new ButtonLocator(3, Robot.oi.getAuxLeftStick());
		public static final ButtonLocator ROTATE_ARM_TO_180 = new ButtonLocator(4, Robot.oi.getAuxLeftStick());
}
