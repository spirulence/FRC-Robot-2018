package org.usfirst.frc.team5700.robot.subsystems;

import org.usfirst.frc.team5700.robot.Constants;
import org.usfirst.frc.team5700.robot.commands.MoveElevator;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Elevator extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	private TalonSRX _talon = new TalonSRX(1);
	
	
	public Elevator() {
		/* first choose the sensor */
		_talon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute, Constants.kPIDLoopIdx, Constants.kTimeoutMs);
		_talon.setSensorPhase(false);
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
		_talon.config_kF(0, Constants.TalonMaxOutput * 1.0/Constants.ElevatorEncoderMaxSpeed, Constants.kTimeoutMs);
		_talon.config_kP(0, 0.2, Constants.kTimeoutMs);
		_talon.config_kI(0, 0.0017, Constants.kTimeoutMs);
		_talon.config_kD(0, 1.8, Constants.kTimeoutMs);
		
		/* set acceleration and cruise velocity - see documentation */
		_talon.configMotionCruiseVelocity(8000, Constants.kTimeoutMs);
		_talon.configMotionAcceleration(16000, Constants.kTimeoutMs);
		
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
    
    public void log() {
    		_talon.getMotorOutputVoltage();
    }
}

