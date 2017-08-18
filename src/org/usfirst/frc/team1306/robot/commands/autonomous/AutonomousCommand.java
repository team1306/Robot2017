package org.usfirst.frc.team1306.robot.commands.autonomous;

import org.usfirst.frc.team1306.lib.util.FalconPathPlanner;
import org.usfirst.frc.team1306.lib.util.Profile;
import org.usfirst.frc.team1306.lib.util.ProfileParams;
import org.usfirst.frc.team1306.robot.commands.drivetrain.Follow2DPath;
import org.usfirst.frc.team1306.robot.commands.drivetrain.FollowPath;
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

//			addParallel(new DeployIntake());
			if(alliance.equals(Alliance.Red)) {
			
				double[][] waypoints = new double[][]{
					{0,0},
					{0,1*12},
					{0.127*12,2*12},
					{0.536*12,3*12},
					{1*12,3.646*12},
					{1.354*12,4*12},
					{2*12,4.464*12},
					{3*12,4.874*12},
					{4*12,5*12},
					{5*12,5*12},
				}; 
				
				FalconPathPlanner path = new FalconPathPlanner(waypoints);
				path.calculate(4,0.01,30);
				
				addSequential(new Follow2DPath(path));
				
			} else {
				
			}
//			addSequential(new SetSetpoint(Setpoint.AUTO_HOPPER));
//			addSequential(new SpinShooter(Constants.SHOOT_TIME,Constants.SHOOTER_RPM_SPEED));
			
		} else if(routine.equals(AutoMode.LEFT_GEAR)) {
			
			if(alliance.equals(Alliance.Red)) {
				
			} else {
				
			}
//			addSequential(new PlaceGear());
//			addSequential(new TimedDrive(0.3,1));
//			addSequential(new DeployIntake());
			
		} else if(routine.equals(AutoMode.MIDDLE_GEAR)) {
			
			addSequential(new FollowPath(new Profile(76,20,50,100,4.75),true)); //Distance, Velocity, Accel, Jerk, Max Time
			addSequential(new PlaceGear());
			addSequential(new TimedDrive(0.3,1));
			addSequential(new DeployIntake());
			
		} else if(routine.equals(AutoMode.RIGHT_GEAR)) {
		 	
			ProfileParams params = new ProfileParams(18.25,45,45);
			
			if(alliance.equals(Alliance.Red)) {
//				addSequential(new Follow2DPath(new Profile2D(params,60,60,60,15)));
			} else {
				
			}
//			addSequential(new PlaceGear());
//			addSequential(new TimedDrive(0.3,1));
//			addSequential(new DeployIntake());
			
		} else if(routine.equals(AutoMode.BASELINE)) {
			
			addSequential(new FollowPath(new Profile(70,18.25,45,45,15),true));
			addSequential(new DeployIntake());
		}
	}
}
