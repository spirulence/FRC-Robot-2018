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

/**
 *
 */
/**
 * @author roman
 *
 */
public class Arm extends Subsystem {
	
	//Motor Controller
	private TalonSRX _talon;

	//Constants
	public final double reductionToEncoder = 35; 
	public final double ticksPerDeg = (Constants.VersaEncoderTPR * reductionToEncoder) / 360;
	public final double encoderMaxSpeed = 1; //ticks per 100 ms //TODO find
	public double wCubeMaxNominalOutput = 0; //Maximum nominal output, when arm is horizontal to ground
	public double noCubeMaxNominalOutput = 0;
	
	public Arm() {
		
		//Preferences table
		Preferences prefs = Preferences.getInstance();
		
		_talon = new TalonSRX(2);
		/* first choose the sensor */
		_talon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute, Constants.kPIDLoopIdx, Constants.kTimeoutMs);
		_talon.setSensorPhase(false); //TODO find
		_talon.setInverted(true); //TODO find
	
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
		_talon.config_kP(0, 0, Constants.kTimeoutMs);
		_talon.config_kI(0, 0, Constants.kTimeoutMs);
		_talon.config_kD(0, 0, Constants.kTimeoutMs);
		
		/* set acceleration and cruise velocity - see documentation */
		_talon.configMotionCruiseVelocity(8000, Constants.kTimeoutMs); //TODO find
		_talon.configMotionAcceleration(16000, Constants.kTimeoutMs); //TODO find
		
		wCubeMaxNominalOutput = prefs.getDouble("wCubeMaxNominalOutput", 0);
		noCubeMaxNominalOutput = prefs.getDouble("noCubeMaxNominalOutput", 0);
	}
	
	public void moveArmWithJoystick(double stickValue) {
		StringBuilder sb = new StringBuilder();
		
		setTalon(stickValue);
			
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
    
    private void configNominaloutPutForce(double nominalOutput) {
    		_talon.configNominalOutputForward(nominalOutput, Constants.kTimeoutMs);
		_talon.configNominalOutputReverse(nominalOutput, Constants.kTimeoutMs);
    }
    
    private double getFeedForward() {
    		//Feed Forward Logic
    	return Math.sin(getAngle()) * (Robot.grabber.hasCube() ? wCubeMaxNominalOutput : noCubeMaxNominalOutput);
    }
    
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
    public void moveToHeight(double angleDeg) {
    		StringBuilder sb = new StringBuilder();
    	
    		_talon.set(ControlMode.MotionMagic, angleDeg);

    		/* append more signals to print when in speed mode. */
    		sb.append("\terr:");
    		sb.append(_talon.getClosedLoopError(Constants.kPIDLoopIdx));
    		sb.append("\ttrg:");
    		sb.append(angleDeg);
    		
    		Instrum.Process(_talon, sb);
    }
    
    public double getAngle() {
    		return _talon.getSelectedSensorPosition(0) / ticksPerDeg;
    }
}

