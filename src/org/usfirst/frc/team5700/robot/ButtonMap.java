package org.usfirst.frc.team5700.robot;

public class ButtonMap {
	//class for button mapping, duh.
	
		//Drivetrain
		
		//Intake
		public static final int EXTEND_INTAKE = 1;//driveRightStick
		public static final int SPIN_INTAKE_IN = 3;//driveRightStick
		public static final int SPIN_INTAKE_OUT = 2;//driveRightStick
//		//Grabber 
		public static final int GRABBER_OPEN = 1;//auxRightstick
		public static final int GRABBER_CLOSE = 2;//auxRightstick
		//Climber
		public static final int CLIMBER_UP = 2;//auxRightstick
		public static final int CLIMBER_DOWN = 2;//auxLeftstick
		//Climber Assist
		public static final int ASSIST_RELEASE = 1;//auxRightstick
		public static final int ZERO_ELEVATOR_ENCODER = 6; //auxRightstick	
		//ELEVATOR
		public static final int MOVE_ELEVATOR_DISTANCE = 5;//driveRightstick
		public static final int RESET_ARM_ENCODER = 11; //auxRightStick
		public static final int MOVE_ARM_TO_ANGLE = 10; //auxRightStick
}
