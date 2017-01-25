package org.usfirst.frc.team1306.robot.commands.autonomous;

import org.usfirst.frc.team1306.robot.Constants;
import org.usfirst.frc.team1306.robot.commands.CommandBase;
import edu.wpi.first.wpilibj.Timer;

/**
 * A command that drives the robot in a direction for a certain amount of
 * time.
 * 
 * @author Jackson Goth
 */
public class TimedDrive extends CommandBase {

	/** The speed we wish the drivetrain to go */
	private final double desiredSpeed;
	/** The power for the motors, on a scale from -1.0 to 1.0. */
	private final double speed;
	/** The amount of time to drive before stopping, in seconds. */
	private final double time;
	/** The timer that controls how long the robot drives before stopping */
	private final Timer timer;

	/**
	 * Constructs a new TimedDrive command. Initializes the timer and requires
	 * the drivetrain.
	 */
	public TimedDrive(double desiredSpeed, double time) {
		this.desiredSpeed = desiredSpeed;
		this.time = time;
		this.speed = Constants.SPEED_DEFAULT;
		timer = new Timer();
		requires(drivetrain);
	}

	@Override
	protected void initialize() {
		timer.reset();
		timer.start();
	}

	@Override
	protected void execute() {
		
		//TODO Fix to work with tankDrive
	}

	@Override
	protected boolean isFinished() {
		return timer.hasPeriodPassed(time);
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
