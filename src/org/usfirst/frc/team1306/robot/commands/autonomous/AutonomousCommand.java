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
	
	public enum AutoMode {HOPPER, HOPPER_GEAR, LEFT_GEAR, MIDDLE_GEAR, RIGHT_GEAR, BASELINE, BLANK};
	
	/**
	 * This is the advanced autonomous routine that will score gears or shoot a full hopper
	 * @param Alliance
	 * 		Which side of the field is the robot on (Red or Blue?)
	 * @param Routine
	 * 		Which autonomous routine should the robot run?
	 */
	public AutonomousCommand(Alliance alliance, AutoMode routine) {
		
		PathParams params = new PathParams(4,0.1,30/12); //4 seconds max, 0.1 seconds in-between points, 30 inch track-width converted to feet
		
		if(routine.equals(AutoMode.HOPPER)) {

			addParallel(new DeployIntake());
			if(alliance.equals(Alliance.Red)) {
				FalconPathPlanner path = new FalconPathPlanner(AutoPaths.hopperPathRed);
				path.calculate(params);
				
				addSequential(new Follow2DPath(path,false));	
			} else {
				FalconPathPlanner path = new FalconPathPlanner(AutoPaths.hopperPathBlue);
				path.calculate(params);
				
				addSequential(new Follow2DPath(path,false));
			}
			addSequential(new SetSetpoint(Setpoint.AUTO_HOPPER));
			addSequential(new FireFuel(Constants.SHOOT_TIME));
			
		} else if(routine.equals(AutoMode.HOPPER_GEAR)) {
			
			addParallel(new DeployIntake());
			if(alliance.equals(Alliance.Red)) {
				FalconPathPlanner path = new FalconPathPlanner(AutoPaths.rightGearRed);
				path.calculate(params);
				
				addSequential(new Follow2DPath(path,true));
				addSequential(new PlaceGear());
				
				FalconPathPlanner path2 = new FalconPathPlanner(AutoPaths.hopperGearPathRed);
				path.calculate(params);
				
				addSequential(new Follow2DPath(path2,false));
			} else {
				FalconPathPlanner path = new FalconPathPlanner(AutoPaths.leftGearBlue);
				path.calculate(params);
				
				addSequential(new Follow2DPath(path,true));
				addSequential(new PlaceGear());
				
				FalconPathPlanner path2 = new FalconPathPlanner(AutoPaths.hopperGearPathBlue);
				path.calculate(params);
				
				addSequential(new Follow2DPath(path2,false));
			}
			addSequential(new SetSetpoint(Setpoint.AUTO_HOPPER));
			addSequential(new FireFuel(Constants.SHOOT_TIME));
			
		} else if(routine.equals(AutoMode.LEFT_GEAR)) {
			
			if(alliance.equals(Alliance.Red)) {
				
				FalconPathPlanner path = new FalconPathPlanner(AutoPaths.leftGearRed);
				path.calculate(params);
				
				addSequential(new Follow2DPath(path,true));
			} else {
				
				FalconPathPlanner path = new FalconPathPlanner(AutoPaths.leftGearBlue);
				path.calculate(params);
				
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
				
				FalconPathPlanner path = new FalconPathPlanner(AutoPaths.rightGearRed);
				path.calculate(params);
				
				addSequential(new Follow2DPath(path,true));
			} else {
				
				FalconPathPlanner path = new FalconPathPlanner(AutoPaths.rightGearBlue);
				path.calculate(params);
				
				addSequential(new Follow2DPath(path,true));
			}
			addSequential(new PlaceGear());
			addSequential(new TimedDrive(0.3,1));
			addSequential(new DeployIntake());
			
		} else if(routine.equals(AutoMode.BASELINE)) {
			
			addSequential(new FollowPath(new Profile(70,18.25,45,45,15),true));
			addSequential(new DeployIntake());
		}
	}
}
