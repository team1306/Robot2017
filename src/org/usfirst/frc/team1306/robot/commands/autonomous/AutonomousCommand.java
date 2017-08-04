package org.usfirst.frc.team1306.robot.commands.autonomous;

import org.usfirst.frc.team1306.robot.ProfileParams;
import org.usfirst.frc.team1306.robot.commands.drivetrain.Follow2DPath;
import org.usfirst.frc.team1306.robot.commands.drivetrain.FollowPath;
import org.usfirst.frc.team1306.robot.commands.drivetrain.Profile;
import org.usfirst.frc.team1306.robot.commands.drivetrain.Profile2D;
import org.usfirst.frc.team1306.robot.commands.geartake.PlaceGear;

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
	 * @param Routine
	 * 		Which autonomous routine should the robot run?
	 */
	public AutonomousCommand(Alliance alliance, AutoMode routine) {
		
		if(routine.equals(AutoMode.HOPPER)) {
			
			ProfileParams params = new ProfileParams(18.25,45,45);
			addSequential(new Follow2DPath(new Profile2D(params,60,60,90,15))); //Params, DistanceX, DistanceY, Exit Angle, Max Time
			
		} else if(routine.equals(AutoMode.LEFT_GEAR)) {
			
		} else if(routine.equals(AutoMode.MIDDLE_GEAR)) {
			
			addSequential(new FollowPath(new Profile(76,20,50,100,4.75),true)); //Distance, Velocity, Accel, Jerk, Max Time
			addSequential(new PlaceGear());
			addSequential(new TimedDrive(0.3,1.5));
			addSequential(new DeployIntake());
			
		} else if(routine.equals(AutoMode.RIGHT_GEAR)) {
		 	
		} else if(routine.equals(AutoMode.BASELINE)) {
			
			addSequential(new FollowPath(new Profile(100,18.25,45,45,15),true));
		}
	}
}
