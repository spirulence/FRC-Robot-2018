package org.usfirst.frc.team5700.robot.subsystems;

import org.usfirst.frc.team5700.robot.Constants;
import org.usfirst.frc.team5700.robot.Instrum;
import org.usfirst.frc.team5700.robot.Robot;
import org.usfirst.frc.team5700.robot.commands.MoveArmWithJoystick;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Arm extends Subsystem {
	
	//Motor Controller
	private TalonSRX _talon;
	
	//preferences
	Preferences prefs;

	//Constants
	public final double reductionToEncoder = 114.55; 
	public final double ticksPerDeg = (Constants.VersaEncoderTPR * reductionToEncoder) / 360;
	public final double encoderMaxSpeed = 33000; //ticks per 100 ms
	public double wCubeMaxNominalOutput; //Maximum nominal output, when arm is horizontal to ground
	public double noCubeMaxNominalOutput;
	
	public Arm() {
		
		_talon = new TalonSRX(2);
		/* first choose the sensor */
		_talon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute, Constants.kPIDLoopIdx, Constants.kTimeoutMs);
		_talon.setSensorPhase(true);
		_talon.setInverted(true);
	
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
		_talon.config_kF(0, Constants.TalonMaxOutput/encoderMaxSpeed, Constants.kTimeoutMs);
		_talon.config_kP(0, 0.1, Constants.kTimeoutMs);
		_talon.config_kI(0, 0, Constants.kTimeoutMs);
		_talon.config_kD(0, 0, Constants.kTimeoutMs);
		
		/* set acceleration and cruise velocity - see documentation */
		_talon.configMotionCruiseVelocity(25000 , Constants.kTimeoutMs);
		_talon.configMotionAcceleration(30000, Constants.kTimeoutMs);
	}
	
	public void moveArmWithJoystick(double stickValue) {
		StringBuilder sb = new StringBuilder();
		
		setTalon(stickValue + getFeedForward());
			
		/* instrumentation */
		Instrum.Process(_talon, sb);
    }
	
	public TalonSRX getTalon() {
		return _talon;
	}
	
	public void zeroEncoder() {
		_talon.setSelectedSensorPosition(0, Constants.kPIDLoopIdx, Constants.kTimeoutMs);
	}
	
	public double getRawEncoderTicks() {
		return _talon.getSelectedSensorPosition(0);
	}

    public void initDefaultCommand() {
        setDefaultCommand(new MoveArmWithJoystick());
    }
    
    public void log() {
    		_talon.getMotorOutputVoltage();
    }
    
    public double getFeedForward() {
    		prefs = Preferences.getInstance();
		wCubeMaxNominalOutput = prefs.getDouble("armWCubeOut", 0.15);
		noCubeMaxNominalOutput = prefs.getDouble("armNoCubeOut", 0.075);
		
    		//Feed Forward Logic
    		return Math.sin(Math.toRadians(getRawAngle())) * (Robot.grabber.hasCube() ? wCubeMaxNominalOutput : noCubeMaxNominalOutput);
    }
    
    /**
     * Sets TalonSRX to output value
     * @param output, as percent input (-1 through 1)
     */
    private void setTalon(double output) {
    		_talon.set(ControlMode.PercentOutput, output);
    }
    
//    /**
//     * @deprecated
//     * @param angle in deg, 0 - 359
//     * 
//     * TODO explain pls
//     * @return null if coordinate system is ok, starting angle in degrees if
//     * coordinate system must be restored to original.
//     */
//    public double moveToAngle(double targetAngleDeg) {
//    		double newTargetAngleDeg = (Double) null;
//    		double delta = getAngle() - targetAngleDeg;
//    		
//    		if (Math.abs(delta) > 180) {
//    			zeroEncoder();
//    			double newAngleToMove = delta / Math.abs(delta) * (360 - Math.abs(delta));
//    			_talon.set(ControlMode.MotionMagic, newAngleToMove * ticksPerDeg);
//    			newTargetAngleDeg = newAngleToMove;
//    		} else {
//    			_talon.set(ControlMode.MotionMagic, targetAngleDeg * ticksPerDeg);
//    		}
//    		
//    		/* append more signals to print when in speed mode. */
//    		StringBuilder sb = new StringBuilder();
//    		sb.append("\terror:");
//    		sb.append(_talon.getClosedLoopError(Constants.kPIDLoopIdx));
//    		sb.append("\ttarget:");
//    		sb.append(targetAngleDeg);
//    		
//    		Instrum.Process(_talon, sb);
//    		
//    		return newTargetAngleDeg;
//    }
//    
//    public void restoreCoordinateSystem(double targetAngleDeg, double newTargetAngleDeg) {
//    		Double restoredCurrentAngle = ((getAngle() - newTargetAngleDeg) + targetAngleDeg)* ticksPerDeg;
//    		_talon.setSelectedSensorPosition(restoredCurrentAngle.intValue(), Constants.kPIDLoopIdx, Constants.kTimeoutMs);
//    }
    
    public void moveToAngle(double targetAngleDeg) {
    		double currentAngleDeg = getRawAngle();
    		double angleToUse;
    		
    		double choiceA = targetAngleDeg + (currentAngleDeg > 0 ? 0 : -360) + 360 * Math.floor((currentAngleDeg/360));
    		double choiceB = choiceA + (choiceA < currentAngleDeg ? 360 : -360);
    		
    		if (Math.abs(currentAngleDeg - choiceA) < Math.abs(currentAngleDeg - choiceB)) {
    			angleToUse = choiceA;
    		} else {
    			angleToUse = choiceB;
    		}
    		
    		_talon.set(ControlMode.MotionMagic, angleToUse * ticksPerDeg);
    }
    
    /**
     * @return angle in degrees, not reseting at 360
     */
    public double getRawAngle() {
    		return _talon.getSelectedSensorPosition(0) / ticksPerDeg;
    }
    
    /**
     * @return angle in degrees between 0 and 360
     */
    public double getNormalizedAngle() {
    		return Math.abs((_talon.getSelectedSensorPosition(0) / ticksPerDeg) % 360);
    }
}

