package org.usfirst.frc.team1306.robot.subsystems;

/**
 * This subsystem controls the hood from the robot
 * 
 * @author Sam Roquitte
 */

import org.usfirst.frc.team1306.robot.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Hood extends Subsystem {
	private final CANTalon hoodTalon;
	
	public Hood() {
		hoodTalon = new CANTalon(RobotMap.HOOD_TALON_PORT);
	}
	
	public double getRawHoodPos() {
		return hoodTalon.get();
	}
	
	public void setHood(double pos) {
		hoodTalon.set(pos);
	}

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}
}
