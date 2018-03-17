package org.usfirst.frc.team5700.robot.commands;

import org.usfirst.frc.team5700.robot.AutonomousPaths;
import org.usfirst.frc.team5700.robot.Robot;
import org.usfirst.frc.team5700.utils.DriveTurnDrivePath;
import org.usfirst.frc.team5700.utils.LinearAccelerationFilter;

import edu.wpi.first.wpilibj.command.Command;

public class AutoSwitchDrivePastDistance extends Command {

	private double distanceIn;
	private double speed;
	private boolean stop;
	private LinearAccelerationFilter filter;
	private boolean dropCubeAtEnd;
	private AutonomousPaths paths;
	private boolean first;

	public AutoSwitchDrivePastDistance(double speed, boolean first, boolean dropCubeAtEnd, boolean stop) {
		this.speed = speed;
		this.first = first;
		this.dropCubeAtEnd = dropCubeAtEnd;
		this.stop = stop;
		paths = new AutonomousPaths();
	}

    protected void initialize() {
    		//logs
    		System.out.println("Initializing DrivePastDistance Command");

    		if (Robot.switchOnRight) {
    			if (first) {
    				distanceIn = paths.rightSide.firstDistance;
    			} else {
    				distanceIn = paths.rightSide.secondDistance;
    			}
    		} else {
    			if (first) {
    				distanceIn = paths.leftSide.firstDistance;
    			} else {
    				distanceIn = paths.leftSide.secondDistance;
    			}
    		}
    		
    		
    		System.out.println("First Distance: " + distanceIn);
	    	System.out.println("Drive Speed: " + speed);
	    	
	    	Robot.drivetrain.resetSensors();
	    	double filterSlopeTime = Robot.prefs.getDouble("FilterSlopeTime", 1);
		filter = new LinearAccelerationFilter(filterSlopeTime);
    }

    protected void execute() {
    		Robot.drivetrain.drive(speed * filter.output(), 0);
    }

    protected boolean isFinished() {
        return Math.abs(Robot.drivetrain.getDistance()) > distanceIn;
    }

    protected void end() {
    		if (dropCubeAtEnd) {
    			Robot.atSwitch = true;
    		}
    		
    		Robot.drivetrain.resetSensors();
    		
    		if (stop) {
    			Robot.drivetrain.stop();
    		}
    		
    		System.out.println("DrivePastDistance Command Finished");
    }

    protected void interrupted() {
    		end();
    }
}
