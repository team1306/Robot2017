package org.usfirst.frc.team1306.robot.commands.autonomous;

import org.usfirst.frc.team1306.robot.commands.CommandBase;
import edu.wpi.first.wpilibj.Timer;

/**
 * Command that drives the robot for a certain amount of time, started by either inputing a throttle for a time 
 * or by giving the robot position during autonomous.
 * @author Jackson Goth
 */
public class TimedDrive extends CommandBase {

	private double throttle; //Speed that the motors will run at.
	private double time; //Time the motors will run for.
	private Timer timer; //Timer used to track time passed.
	
	private Station station; //Alliance station the robot is located at.
	private AutoMode autoMode; //Autonomous routine being run.
	private boolean runNext; //Running second TimedDrive?
	private boolean autonomous; //If the robot is driving automously.
	
	/**
	 * Runs the command with a specified throttle for a specified time
	 */
	public TimedDrive(double throttle, double time) {
		requires(drivetrain);
		this.throttle = throttle;
		this.time = time;
		this.autonomous = false;
		
		timer = new Timer();
	}

	/**
	 * Runs the command using a specified robot position
	 * @param station
	 */
	public TimedDrive(Station station, AutoMode mode, boolean runNext ) {
		requires(drivetrain);
		this.station = station;
		this.autoMode = mode;
		this.runNext = runNext;
		this.autonomous = true;
		
		timer = new Timer();
	}
	
	@Override
	protected void initialize() {
		timer.reset(); //Resets the timer to clear old data.
		timer.start(); //Starts the timer again to begin comparing.
		
		if(autonomous) {
			throttle = station.getThrottle(runNext);
			time = station.getTime(runNext);
		}
	}

	@Override
	protected void execute() {
		drivetrain.tankDrive(throttle, throttle); //Drives the motors at the specified speed.
	}

	/**
	 * Returns true when this Command no longer needs to run execute(). This
	 * command ends after a designated amount of time.
	 */
	@Override
	protected boolean isFinished() {
		return timer.hasPeriodPassed(time);
	}

	/**
	 * Called once after isFinished returns true. Stops the drivetrain.
	 */
	@Override
	protected void end() {
		drivetrain.stopAll();
	}

	/**
	 * Called when another command which requires one or more of the same
	 * subsystems is scheduled to run. Stops the drivetrain so that whatever is
	 * interrupting can use it.
	 */
	@Override
	protected void interrupted() {
		end();
	}
}
