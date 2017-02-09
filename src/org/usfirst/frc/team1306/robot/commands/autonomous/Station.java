package org.usfirst.frc.team1306.robot.commands.autonomous;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Enum that contains commands for different alliance stations
 * @author Jackson Goth
 */
public enum Station {
	
	RED_ONE(AutoConstants.RED_ONE_TIME_INITIAL,AutoConstants.RED_ONE_ANGLE,AutoConstants.RED_ONE_TIME_FINAL),
	RED_TWO(AutoConstants.RED_TWO_TIME_INITIAL,AutoConstants.RED_TWO_ANGLE,AutoConstants.RED_TWO_TIME_FINAL),
	RED_THREE(AutoConstants.RED_THREE_TIME_INITIAL,AutoConstants.RED_THREE_ANGLE,AutoConstants.RED_THREE_TIME_FINAL),
	BLUE_ONE(AutoConstants.BLUE_ONE_TIME_INITIAL,AutoConstants.BLUE_ONE_ANGLE,AutoConstants.BLUE_ONE_TIME_FINAL),
	BLUE_TWO(AutoConstants.BLUE_TWO_TIME_INITIAL,AutoConstants.BLUE_TWO_ANGLE,AutoConstants.BLUE_TWO_TIME_FINAL),
	BLUE_THREE(AutoConstants.BLUE_THREE_TIME_INITIAL,AutoConstants.BLUE_THREE_ANGLE,AutoConstants.BLUE_THREE_TIME_FINAL);
		
	private final double initial_time;
	private final double final_time;
	private final double angle;
	private final double speed = AutoConstants.AUTO_SPEED;
	
	private Station(double initial_time, double angle, double final_time) {
		this.initial_time = initial_time;
		this.final_time = final_time;
		this.angle = angle;
	}
	
	public TimedDrive getDriveCommand(int tracker) {
		
		switch(tracker) {
			case 0:
				SmartDashboard.putString("case 0","true");
				return new TimedDrive(speed,initial_time);
			case 1:
				SmartDashboard.putString("case 1","true");
				return new TimedDrive(speed,final_time);
			default:
				return null;
		}
		
	}
	
	public AngledTurn getTurnCommand() {
		
		SmartDashboard.putString("angle", "true");
		return new AngledTurn(angle);
	}
}
