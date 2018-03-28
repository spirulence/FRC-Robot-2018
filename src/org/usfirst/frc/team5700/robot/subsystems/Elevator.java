package org.usfirst.frc.team5700.robot.subsystems;

import org.usfirst.frc.team5700.robot.Constants;
import org.usfirst.frc.team5700.robot.Instrum;
import org.usfirst.frc.team5700.robot.Robot;
import org.usfirst.frc.team5700.robot.RobotMap;
import org.usfirst.frc.team5700.robot.commands.MoveElevator;
import org.usfirst.frc.team5700.robot.subsystems.Arm.ArmCollisionBounds;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * @author roman
 *
 */

public class Elevator extends Subsystem {
	
	/*
	 * FeedForwards
	 */
	private double lowNoCubeFF, highNoCubeFF, lowWCubeFF, highWCubeFF;
	
	//Motor Controller
	private TalonSRX _talon = new TalonSRX(1);

	//Limit Hall Effect Sensors
	private DigitalInput topLimit, interstageLimit, bottomLimit;

	//Constants
	public static final double heightIn = 58;
	public static final double winchRadiusIn = 1.125; //TODO Find actual
	public static final double reductionToEncoder = 5;
	public static final double winchCircumferenceIn = 2 * Math.PI * winchRadiusIn;
	//public static final double ticksPerIn = (Constants.VersaEncoderTPR * reductionToEncoder) / winchCircumferenceIn;
	public static final double inPerTick = 0.00034836721; 
	public static final double rotationsInSpan = heightIn/winchCircumferenceIn;
	public static final double encoderMaxSpeed = 30100; //ticks per 100 ms
	
	public Elevator() {
		
		//Preferences table
		Preferences prefs = Preferences.getInstance();
		
		//Instantiate limit sensors and variables
		topLimit = new DigitalInput(RobotMap.TOP_LIMIT_PORT);
		interstageLimit = new DigitalInput(RobotMap.INTERSTAGE_LIMIT_PORT);
		bottomLimit = new DigitalInput(RobotMap.BOTTOM_LIMIT_PORT);
		
		/* first choose the sensor */
		_talon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute, Constants.kPIDLoopIdx, Constants.kTimeoutMs);
		_talon.setSensorPhase(false);
		_talon.setInverted(false);
		
		//set talon max output
		_talon.configNominalOutputForward(1, Constants.kTimeoutMs);
		_talon.configNominalOutputReverse(-1, Constants.kTimeoutMs);
	
		/* Set relevant frame periods to be at least as fast as periodic rate */
		_talon.setStatusFramePeriod(StatusFrameEnhanced.Status_13_Base_PIDF0, 10, Constants.kTimeoutMs);
		_talon.setStatusFramePeriod(StatusFrameEnhanced.Status_10_MotionMagic, 10, Constants.kTimeoutMs);
	
		/* set the peak and nominal outputs */
		_talon.configNominalOutputForward(0, Constants.kTimeoutMs);
		_talon.configNominalOutputReverse(0, Constants.kTimeoutMs);
		_talon.configPeakOutputForward(1, Constants.kTimeoutMs);
		_talon.configPeakOutputReverse(-1, Constants.kTimeoutMs);
	
		/* set closed loop gains in slot 0 - see documentation */
		_talon.selectProfileSlot(Constants.kSlotIdx, Constants.kPIDLoopIdx);
		_talon.config_kF(0, Constants.TalonMaxOutput * 1.0/encoderMaxSpeed, Constants.kTimeoutMs);
		_talon.config_kP(0, 0.35, Constants.kTimeoutMs);
		_talon.config_kI(0, 0.001, Constants.kTimeoutMs);
		_talon.config_kD(0, 0.13511, Constants.kTimeoutMs);
		
		/* set acceleration and cruise velocity - see documentation */
		_talon.configMotionCruiseVelocity(16000, Constants.kTimeoutMs);
		_talon.configMotionAcceleration(16000, Constants.kTimeoutMs);
		
		//Feed Forward Values
		lowNoCubeFF = prefs.getDouble("lowNoCubeFF", 0.1);
		highNoCubeFF = prefs.getDouble("highNoCubeFF", 0.1);
		lowWCubeFF = prefs.getDouble("lowWCubeFF", 0.15);
		highWCubeFF = prefs.getDouble("highWCubeFF", 0.15);
	}
	
	public void moveElevatorWithJoystick(double stickValue) {
		StringBuilder sb = new StringBuilder();
		//setFeedForward();
			
		//Limit Sensor Logic
		if (!Robot.oi.overrideLimits()) {
			if (atTopLimit()) {
				setTalon(Math.min(0, stickValue));
			} else if (atBottomLimit()) {
				setTalon(Math.max(0, stickValue));
			}	
		} else {
			setTalon(stickValue);
		}
		
			
		/* instrumentation */
		Instrum.Process(_talon, sb);
    }
	
	private boolean atTopLimit() {
		return getHeight() > heightIn;
	}

	public boolean getInterstageLimit() {
		return interstageLimit.get();
	}

	public boolean atBottomLimit() {
		return getHeight() <= 0.25 || isColliding();
	}
	
	public TalonSRX getTalon() {
		return _talon;
	}
	
	public void zeroEncoder() {
		_talon.setSelectedSensorPosition(0, Constants.kPIDLoopIdx, Constants.kTimeoutMs);
	}

    public void initDefaultCommand() {
        setDefaultCommand(new MoveElevator());
    }
    
    public double getTalonOutputVolatage() {
    		return _talon.getMotorOutputVoltage();
    }
    
//    private void configNominaloutPutForce(double nominalOutput) {
//    		_talon.configNominalOutputForward(nominalOutput, Constants.kTimeoutMs);
//		_talon.configNominalOutputReverse(nominalOutput, Constants.kTimeoutMs);
//    }
//    
//    private void setFeedForward() {
//    		//Feed Forward Logic
//		if (!interstageLimit.get()) {
//			if (Robot.grabber.hasCube()) {
//				configNominaloutPutForce(highWCubeFF);
//			} else {
//				configNominaloutPutForce(highNoCubeFF);
//			}
//		} else {
//			if (Robot.grabber.hasCube()) {
//				configNominaloutPutForce(lowWCubeFF);
//			} else {
//				configNominaloutPutForce(lowNoCubeFF);
//			}
//		}
//    }
    
    /**
     * Sets TalonSRX to output value
     * @param output, as percent input (-1 through 1)
     */
    private void setTalon(double output) {
    		_talon.set(ControlMode.PercentOutput, output);
    }
    
    /**
     * @param height in inches, absolute
     */
    public void moveToHeight(double heightIn) {
    		StringBuilder sb = new StringBuilder();
    	
    		_talon.set(ControlMode.MotionMagic, heightIn / inPerTick);

    		/* append more signals to print when in speed mode. */
    		sb.append(" err:");
    		sb.append(_talon.getClosedLoopError(Constants.kPIDLoopIdx));
    		sb.append(" trg:");
    		sb.append(heightIn);
    		
    		Instrum.Process(_talon, sb);
    }
    
    public double getEncoderTicks() {
		return _talon.getSelectedSensorPosition(0);
	}

	public double getHeight() {
		return getEncoderTicks() * inPerTick;
	} 
	
	public double getVelocityTicks() {
		return _talon.getSelectedSensorVelocity(0);
	}
	
	private boolean isColliding() {
		
		boolean isColliding = false;
		double armAngle = Robot.arm.getNormalizedAngle();
		
    	ArmCollisionBounds bounds = Robot.grabber.hasCube() ? Robot.arm.withCubeBounds: Robot.arm.noCubeBounds;
    	
    	if (Math.abs(getHeight()) <= bounds.heightIn
    			 && Math.abs(armAngle) <= bounds.outsideAngle
    			 && Math.abs(armAngle) <= bounds.insideAngle) {
    		isColliding = true;
    	}
    	return isColliding;
    }
}

