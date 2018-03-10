package org.usfirst.frc.team5700.robot;

public class Constants {

	/**
	 * Which PID slot to pull gains from. Starting 2018, you can choose from
	 * 0,1,2 or 3. Only the first two (0,1) are visible in web-based
	 * configuration.
	 */
	public static final int kSlotIdx = 0;

	/**
	 * Talon SRX/ Victor SPX will supported multiple (cascaded) PID loops. For
	 * now we just want the primary one.
	 */
	public static final int kPIDLoopIdx = 0;

	/**
	 * set to zero to skip waiting for confirmation, set to nonzero to wait and
	 * report to DS if action fails.
	 */
	public static final int kTimeoutMs = 10;

	/**
	 * Talon
	 */
	public static final int TalonMaxOutput = 1023;
	
	/**
	 * Elevator
	 */
	public static final int ElevatorEncoderMaxSpeed = 30100; //ticks per 100 ms
	
	public static final int VersaEncoderTPR = 4096;

	public static final double ElevatorSpanIn = 60; //TODO Find actual
	
	public static final double ElevatorWinchRadiusIn = 1.125; //TODO Find actual
	
	public static final double ElevatorReductionToEncoder = 5;

	public static final double ElevatorWinchCircumferenceIn = 2 * Math.PI * ElevatorWinchRadiusIn;
	
	public static final double ElevatorTicksPerIn = (VersaEncoderTPR * ElevatorReductionToEncoder) / ElevatorWinchCircumferenceIn;

	public static final double ElevatorRotationsInSpan = ElevatorSpanIn/ElevatorWinchCircumferenceIn;
}
