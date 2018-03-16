package org.usfirst.frc.team5700.utils;

import edu.wpi.first.wpilibj.Joystick;

public class ButtonLocator {
	
	public final int buttonNum;
	public final Joystick joystick;
	
	public ButtonLocator(int buttonNum, Joystick joystick) {
		
		this.buttonNum = buttonNum;
		this.joystick = joystick;
		
	}

}

