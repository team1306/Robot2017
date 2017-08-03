package org.usfirst.frc.team1306.robot.commands.autonomous;

import org.usfirst.frc.team1306.robot.commands.drivetrain.FollowPath;
import org.usfirst.frc.team1306.robot.commands.drivetrain.Profile;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * The autonomous command station containing many different autonomous routines
 * @author Jackson Goth
 */
public class AutonomousCommand extends CommandGroup {
	
	public enum AutoMode {HOPPER, LEFT_GEAR, MIDDLE_GEAR, RIGHT_GEAR, BASELINE, BLANK};
	
	/**
	 * This is the advanced autonomous routine that will score gears or shoot a full hopper
	 * @param Alliance
	 * 		Which side of the field is the robot on (Red or Blue?)
	 * @param Position
	 * 		Which field position is robot located
	 * @param Routine
	 * 		Which autonomous routine should the robot run?
	 */
	public AutonomousCommand(Alliance alliance, AutoMode routine) {
	
		if(routine.equals(AutoMode.HOPPER)) {
			
		} else if(routine.equals(AutoMode.LEFT_GEAR)) {
			
		} else if(routine.equals(AutoMode.MIDDLE_GEAR)) {
			
			addSequential(new FollowPath(new Profile(96,18.25,45,45,15),false)); //Distance, Velocity, Accel, Jerk, Max Time
			
		} else if(routine.equals(AutoMode.RIGHT_GEAR)) {
			
		}
	}
}
