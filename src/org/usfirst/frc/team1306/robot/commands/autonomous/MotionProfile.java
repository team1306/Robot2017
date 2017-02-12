package org.usfirst.frc.team1306.robot.commands.autonomous;

import org.usfirst.frc.team1306.robot.Constants;
import org.usfirst.frc.team1306.robot.commands.CommandBase;
import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.Waypoint;

public class MotionProfile extends CommandBase {

	public MotionProfile() {
		requires(drivetrain);
	}
	
	@Override
	protected void initialize() {
		Waypoint[] points = new Waypoint[]	{
			new Waypoint(0,0,Pathfinder.d2r(-45))
		};
	}
		
	@Override
	protected void execute() {
		drivetrain.driveMP(Constants.LEFT_ROTATIONS,Constants.RIGHT_ROTATIONS);
	}

	@Override
	protected boolean isFinished() {
		return true;
	}

	@Override
	protected void end() {
		drivetrain.stopAll();
	}

	@Override
	protected void interrupted() {
		end();
	}
}
