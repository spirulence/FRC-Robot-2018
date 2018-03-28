package org.usfirst.frc.team5700.robot.commands;

import org.usfirst.frc.team5700.robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class MoveArmAndElevatorDistance extends CommandGroup {

    public MoveArmAndElevatorDistance(double elevatorHeightIn, double armAngleDeg) {

    	addParallel(new MoveElevatorDistance(elevatorHeightIn));
    	addParallel(new MoveArmToAngle(armAngleDeg));
    }
}
