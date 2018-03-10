package org.usfirst.frc.team5700.robot.commands;

import java.util.concurrent.TimeUnit;

import org.usfirst.frc.team5700.robot.Constants;
import org.usfirst.frc.team5700.robot.Instrum;
import org.usfirst.frc.team5700.robot.Robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class MoveElevatorDistance extends Command {

	StringBuilder _sb = new StringBuilder();
	TalonSRX _talon;
	Timer _timer;
	double _targetPosIn;

    public MoveElevatorDistance(double targetPosIn) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    		requires(Robot.elevator);
    		_targetPosIn = targetPosIn;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    		System.out.print("MED Init");
    		_talon = Robot.elevator.getTalon();
    		_timer = new Timer();
    		_timer.start();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
		_talon.set(ControlMode.MotionMagic, 5*4096*5);

		/* append more signals to print when in speed mode. */
		_sb.append("\terr:");
		_sb.append(_talon.getClosedLoopError(Constants.kPIDLoopIdx));
		_sb.append("\ttrg:");
		_sb.append(_targetPosIn);
		
		Instrum.Process(_talon, _sb);
		try {
			TimeUnit.MILLISECONDS.sleep(10);
		} catch (Exception e) {
		};
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return _timer.get() > 2;
    }

    // Called once after isFinished returns true
    protected void end() {
    		System.out.print("MED End");
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    		end();
    }
}