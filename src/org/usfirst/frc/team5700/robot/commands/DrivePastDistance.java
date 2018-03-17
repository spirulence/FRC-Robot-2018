package org.usfirst.frc.team5700.robot.commands;

import org.usfirst.frc.team5700.robot.Robot;
import org.usfirst.frc.team5700.utils.LinearAccelerationFilter;

import edu.wpi.first.wpilibj.command.Command;

public class DrivePastDistance extends Command {

    private int direction = 1;
	private double distanceIn;
	private double speed;
	private boolean stop;
	private LinearAccelerationFilter filter;
	private boolean dropCubeAtEnd;

	public DrivePastDistance(double distanceIn, double speed, boolean stop, boolean dropCubeAtEnd) {
        requires(Robot.drivetrain);
        
        this.distanceIn = distanceIn;
        this.speed = speed;
        this.stop = stop;
        this.dropCubeAtEnd = dropCubeAtEnd;
    }
	
	public DrivePastDistance(double speed, boolean stop) {
        requires(Robot.drivetrain);
        this.speed = speed;
        this.stop = stop;
    }

    protected void initialize() {
    		//logs
    		System.out.println("Initializing DrivePastDistance Command");
    		System.out.println("Using preset distance");
    		
    		System.out.println("First Distance: " + distanceIn * direction);
	    	System.out.println("driveSpeed: " + speed);
	    	
	    	Robot.drivetrain.resetSensors();
	    	double filterSlopeTime = Robot.prefs.getDouble("FilterSlopeTime", 1);
		filter = new LinearAccelerationFilter(filterSlopeTime);
    }

    protected void execute() {
    		Robot.drivetrain.drive(direction * speed * filter.output(), 0);
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
