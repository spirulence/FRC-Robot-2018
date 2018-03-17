package org.usfirst.frc.team5700.robot;

public class ButtonMap {
		
		//Intake
		public static final int EXTEND_INTAKE = 1; //rightstick
		public static final int SPIN_INTAKE_IN = 2; // //Robot.oi.getDriveRightStick());
		public static final int SPIN_INTAKE_OUT = 3; ////Robot.oi.getDriveRightStick());
		//Grabber 
		public static final int GRABBER_OPEN = 1; //Robot.oi.getAuxRightStick());
		public static final int GRABBER_CLOSE = 2; //Robot.oi.getAuxRightStick());
		//Climber
		public static final int CLIMBER_UP = 3; //Robot.oi.getAuxRightStick());
		public static final int CLIMBER_DOWN = 4; //Robot.oi.getAuxRightStick());
		//Climber Assist
		public static final int ASSIST_RELEASE = 12; //Robot.oi.getAuxLeftStick());
		//ELEVATOR
		public static final int ZERO_ELEVATOR_ENCODER = 7; //Robot.oi.getAuxRightStick());
		public static final int MOVE_ELEVATOR_DISTANCE = 5; //Robot.oi.getAuxRightStick());
		//Arm
		public static final int RESET_ARM_ENCODER = 7; //Robot.oi.getAuxLeftStick());
		public static final int ROTATE_ARM_TO_0 = 3; //Robot.oi.getAuxLeftStick());
		public static final int ROTATE_ARM_TO_180 = 4; //Robot.oi.getAuxLeftStick());
}
