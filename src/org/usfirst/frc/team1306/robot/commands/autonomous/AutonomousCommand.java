package org.usfirst.frc.team1306.robot.commands.autonomous;

import org.usfirst.frc.team1306.lib.util.FalconPathPlanner;
import org.usfirst.frc.team1306.lib.util.Profile;
import org.usfirst.frc.team1306.robot.Constants;
import org.usfirst.frc.team1306.robot.commands.SetSetpoint;
import org.usfirst.frc.team1306.robot.commands.Setpoint;
import org.usfirst.frc.team1306.robot.commands.drivetrain.Follow2DPath;
import org.usfirst.frc.team1306.robot.commands.drivetrain.FollowPath;
import org.usfirst.frc.team1306.robot.commands.geartake.PlaceGear;
import org.usfirst.frc.team1306.robot.commands.shooter.FireFuel;
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

			addParallel(new DeployIntake());
			if(alliance.equals(Alliance.Red)) {
				double[][] waypoints = new double[][]{
					{0,0},
					{80.125/12,0},
					{80.125/12,-(54.5/12)},
				};
				
				FalconPathPlanner path = new FalconPathPlanner(waypoints);
				path.calculate(4,0.1,30/12);
				
				addSequential(new Follow2DPath(path,false));	
			} else {
				double[][] waypoints = new double[][]{
					{0,0},
					{80.125/12,0},
					{80.125/12,94.5/12},
				}; 
				
				FalconPathPlanner path = new FalconPathPlanner(waypoints);
				path.calculate(4,0.1,30/12);
				
				addSequential(new Follow2DPath(path,false));
			}
			addSequential(new SetSetpoint(Setpoint.AUTO_HOPPER));
			addSequential(new FireFuel(Constants.SHOOT_TIME));
			
		} else if(routine.equals(AutoMode.LEFT_GEAR)) {
			
			if(alliance.equals(Alliance.Red)) {
				double[][] waypoints = new double[][]{
					{0,0},
					{54.717/12,0}, //TODO Use 68.54
					{96.25/12,(48/12)}, //60
				}; 
				
				FalconPathPlanner path = new FalconPathPlanner(waypoints);
				path.calculate(4,0.1,30/12);
				
				addSequential(new Follow2DPath(path,true));
			} else {
				double[][] waypoints = new double[][]{
					{0,0},
					{70.44/12,0},
					{89.45/12,(32.93/12)},
				}; 
				
				FalconPathPlanner path = new FalconPathPlanner(waypoints);
				path.calculate(4,0.1,30/12);
				
				addSequential(new Follow2DPath(path,true));
			}
			addSequential(new PlaceGear());
			addSequential(new TimedDrive(0.3,1));
			addSequential(new DeployIntake());
			
		} else if(routine.equals(AutoMode.MIDDLE_GEAR)) {
			
			addSequential(new FollowPath(new Profile(76,20,50,100,4.75),true)); //Distance, Velocity, Accel, Jerk, Max Time
			addSequential(new PlaceGear());
			addSequential(new TimedDrive(0.3,1));
			addSequential(new DeployIntake());
			
		} else if(routine.equals(AutoMode.RIGHT_GEAR)) {
		 	
			if(alliance.equals(Alliance.Red)) {
				double[][] waypoints = new double[][]{
					{0,0},
					{70.44/12,0},
					{89.45/12,-(32.93/12)},
				}; 
				
				FalconPathPlanner path = new FalconPathPlanner(waypoints);
				path.calculate(4,0.1,30/12);
				
				addSequential(new Follow2DPath(path,true));
			} else {
				double[][] waypoints = new double[][]{
					{0,0},
					{54.717/12,0},
					{96.25/12,-(48/12)}, 
				}; 
				
				FalconPathPlanner path = new FalconPathPlanner(waypoints);
				path.calculate(4,0.1,30/12);
				
				addSequential(new Follow2DPath(path,true));
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
