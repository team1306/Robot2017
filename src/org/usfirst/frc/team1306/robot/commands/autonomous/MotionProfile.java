package org.usfirst.frc.team1306.robot.commands.autonomous;

import org.usfirst.frc.team1306.robot.Constants;
import org.usfirst.frc.team1306.robot.commands.CommandBase;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.Waypoint;
import jaci.pathfinder.followers.EncoderFollower;
import jaci.pathfinder.modifiers.TankModifier;

/**
 * Command being used to test motion profiling/pathfinder to do more advanced autonomous modes
 * @author Jackson Goth
 */
public class MotionProfile extends CommandBase {

	public final double max_velocity = 300;//0.30;
	public final double max_accel = 0.35;
	public int profile;
	public double desired_heading, gyro_heading, l, r, angleDifference, turn;
	EncoderFollower left;
	EncoderFollower right;
	AHRS ahrs; //Navx Gyro
	Timer timer;
	
	public MotionProfile(int profile) {
		requires(drivetrain);
		this.profile = profile;
		
		timer = new Timer();
		
		try {
			ahrs = new AHRS(SPI.Port.kMXP); //Trying to initialize the gyro
		} catch(RuntimeException ex) {
			SmartDashboard.putString("Gyro Failed to Connect","");
		}
	}
	
	@Override
	protected void initialize() {

		timer.reset();
		timer.start();
		
		drivetrain.resetEncoders();
		ahrs.reset();
		
		Trajectory.Config config = new Trajectory.Config(Trajectory.FitMethod.HERMITE_CUBIC, Trajectory.Config.SAMPLES_LOW, 0.05, max_velocity, max_accel, 60.0);
//		Waypoint[] points = new Waypoint[]	{
//			new Waypoint(1,0,initAngle),
//			new Waypoint(2,0,initAngle),
//			//new Waypoint(3,0,0)
//		};
		
		Waypoint[] points = getWaypoints(profile);
		
		Trajectory trajectory = Pathfinder.generate(points, config);
		TankModifier modifier = new TankModifier(trajectory).modify(0.6396); // 0.6096
		
		left = new EncoderFollower(modifier.getLeftTrajectory());
		right = new EncoderFollower(modifier.getRightTrajectory());
		
		left.configureEncoder(drivetrain.getLeftPosition(), 256, 0.1016);
		right.configureEncoder(-drivetrain.getRightPosition(),256, 0.1016);
		
		left.configurePIDVA(Constants.P, Constants.I, Constants.D, 1 / max_velocity, 0);
		right.configurePIDVA(Constants.P, Constants.I, Constants.D, 1 / max_velocity, 0);

	}
	
	private Waypoint[] getWaypoints(int profile) {
		
		Waypoint[] profileWaypoints;
		
		SmartDashboard.putNumber("profile",profile);
		if(profile == 0) {
			profileWaypoints = new Waypoint[] {
				new Waypoint(1,0,0),
				new Waypoint(8,0,0)
			};
			return profileWaypoints;
		} else if(profile == 1 || profile == 4) {
			profileWaypoints = new Waypoint[] {
				new Waypoint(1,0,0),
				new Waypoint(16.888,4.6177,Pathfinder.d2r(60))
			};
			return profileWaypoints;
		} else if(profile == 2 || profile == 5) {
			profileWaypoints = new Waypoint[] {
				new Waypoint(1,0,0),
				new Waypoint(9.4,0,0) //9.5
			};
			return profileWaypoints;
		} else if(profile == Constants.MP_FORWARD) {
			profileWaypoints = new Waypoint[] {
				new Waypoint(1,0,0),
				new Waypoint(2,0,0)
			};
			return profileWaypoints;
		} else {
			profileWaypoints = new Waypoint[] {
//				new Waypoint(1,0,Pathfinder.d2r(0)),
//				new Waypoint(8,-6,Pathfinder.d2r(-90))
			};
			return profileWaypoints;
		}
		
		/*Waypoint[] profileWaypoints = new Waypoint[] {*/ //7.66666 in per 1 unit  54 to baseline (106 to peg) (robot 31) (75 to 2 gear)

		
		//return profileWaypoints;
	}
	
	@Override
	protected void execute() {
		
		if(profile == 2 || profile == 5 || profile == 1 || profile == 4) {
			l = left.calculate(-drivetrain.getLeftPosition());
			r = right.calculate(drivetrain.getRightPosition());
		} else {
			l = left.calculate(drivetrain.getLeftPosition());
			r = right.calculate(-drivetrain.getRightPosition());
		}
		
		gyro_heading = ahrs.getAngle();
		desired_heading = Pathfinder.r2d(left.getHeading());
		
		SmartDashboard.putNumber("Gyro-Heading",gyro_heading);
		SmartDashboard.putNumber("Desired-Heading",desired_heading);
		
		double angleDifference = Pathfinder.boundHalfDegrees(desired_heading - gyro_heading);
		double turn = .75 * (-1/80.0) * angleDifference;
		
		
		
//		if(l + r < 0.20) {
//			turn = 0.15 * (-1/80.0) * angleDifference;
//		} else {
//			turn = 0.70 * (-1/80.0) * angleDifference;
//		}
		
		//double turn = 0.8 * (-1.0/80.0) * angleDifference; //0.8

//		SmartDashboard.putNumber("Gyro-X",ahrs.getRawGyroX());
//		SmartDashboard.putNumber("Gyro-Y",ahrs.getRawGyroY());
		SmartDashboard.putNumber("MP-Left",l);
		SmartDashboard.putNumber("MP-Right",r);
		SmartDashboard.putNumber("MP-Turn",turn);
		SmartDashboard.putNumber("Left-Speed",l + turn);
		SmartDashboard.putNumber("Right-Speed", r - turn);
		
//		drivetrain.tankDrive(l + turn, r - turn);
//		drivetrain.tankDrive(l,r);
		
		if(profile == 2 || profile == 5 || profile == 1 || profile == 4) {
			drivetrain.tankDrive(-(l + turn),-(r - turn));
		} else {
			drivetrain.tankDrive(l + turn,r - turn);
		}
	}

	@Override
	protected boolean isFinished() {
		if(l + r + turn < 0.15 && timer.hasPeriodPassed(4)) {
			SmartDashboard.putBoolean("profile ending",true);
			return true;
		} else {
			SmartDashboard.putBoolean("profile ending",false);
			return false;
		}
//		return false;
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
