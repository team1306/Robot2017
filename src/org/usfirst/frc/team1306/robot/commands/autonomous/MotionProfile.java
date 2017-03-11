package org.usfirst.frc.team1306.robot.commands.autonomous;

import org.usfirst.frc.team1306.robot.Constants;
import org.usfirst.frc.team1306.robot.commands.CommandBase;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.SPI;
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

	public final double max_velocity = 0.3048;
	public final double max_accel = 0.15;
	public double initAngle;
	public int profile;
	EncoderFollower left;
	EncoderFollower right;
	AHRS ahrs; //Navx Gyro
	
	public MotionProfile(int profile) {
		requires(drivetrain);
		this.profile = profile;
		
		try {
			ahrs = new AHRS(SPI.Port.kMXP); //Trying to initialize the gyro
		} catch(RuntimeException ex) {
			SmartDashboard.putString("Gyro Failed to Connect","");
		}
	}
	
	@Override
	protected void initialize() {

		initAngle = ahrs.getAngle();
		
		Trajectory.Config config = new Trajectory.Config(Trajectory.FitMethod.HERMITE_CUBIC, Trajectory.Config.SAMPLES_HIGH, 0.05, max_velocity, max_accel, 60.0);
		Waypoint[] points = new Waypoint[]	{
			new Waypoint(1,0,initAngle),
			new Waypoint(2,0,initAngle),
			//new Waypoint(3,0,0)
		};
		Trajectory trajectory = Pathfinder.generate(points, config);
		TankModifier modifier = new TankModifier(trajectory).modify(0.5);
		
		left = new EncoderFollower(modifier.getLeftTrajectory());
		right = new EncoderFollower(modifier.getRightTrajectory());
		
		left.configureEncoder(drivetrain.getLeftPosition(), 256, 0.1143);
		right.configureEncoder(drivetrain.getRightPosition(),256, 0.1143);
		
		left.configurePIDVA(Constants.P, 0.0, 0.0, 1 / max_velocity, 0);
		right.configurePIDVA(Constants.P,0.0, 0.0, 1 / max_velocity, 0);
		
		ahrs.reset();
		drivetrain.resetEncoders();
		
		
	}
		
	@Override
	protected void execute() {
		
		double l = left.calculate(drivetrain.getLeftPosition());
		double r = right.calculate(drivetrain.getRightPosition());
		
		double gyro_heading = ahrs.getAngle();
		double desired_heading = initAngle;
		
		SmartDashboard.putNumber("Gyro-Heading",gyro_heading);
		SmartDashboard.putNumber("Desired-Heading",desired_heading);
		
		double angleDifference = Pathfinder.boundHalfDegrees(desired_heading - gyro_heading);
		double turn = 0.8 * (-1.0/80.0) * angleDifference;

		SmartDashboard.putNumber("Gyro-X",ahrs.getRawGyroX());
		SmartDashboard.putNumber("Gyro-Y",ahrs.getRawGyroY());
		SmartDashboard.putNumber("MP-Left",l);
		SmartDashboard.putNumber("MP-Right",r);
		SmartDashboard.putNumber("MP-Turn",turn);
		SmartDashboard.putNumber("Left-Speed",l + turn);
		SmartDashboard.putNumber("Right-Speed", r - turn);
		
		drivetrain.tankDrive(l + turn, r - turn);
	}

	@Override
	protected boolean isFinished() {
		return false;
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
