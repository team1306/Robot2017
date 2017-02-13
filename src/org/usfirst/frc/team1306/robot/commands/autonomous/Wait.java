package org.usfirst.frc.team1306.robot.commands.autonomous;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 * A command that simply waits for a certain amount of time. Used in autonomous
 * programs to wait for the turret to be targeted before firing.
 * 
 * @author Finn Voichick
 */
public class Wait extends Command {

	/** The Timer used to find the stop time. */
	private final Timer timer;
	/** The length of time to wait, in seconds. */
	private final double seconds;

	/**
	 * Constructs a Wait command.
	 * 
	 * @param seconds
	 *            the amount of time to wait before moving on to the next
	 *            command in the sequence.
	 */
	public Wait(double seconds) {
		timer = new Timer();
		this.seconds = seconds;
	}

	/**
	 * Called just before this Command runs the first time. Starts the timer
	 * counting.
	 */
	@Override
	protected void initialize() {
		timer.reset();
		timer.start();
	}

	/**
	 * Called repeatedly when this Command is scheduled to run. Does nothing.
	 */
	@Override
	protected void execute() {
	}

	/**
	 * Returns true when this Command no longer needs to run execute(). This
	 * command ends when the required time has passed.
	 * 
	 * @return true if the amount of time has passed, otherwise false.
	 */
	@Override
	protected boolean isFinished() {
		return timer.hasPeriodPassed(seconds);
	}

	/**
	 * Called once after isFinished returns true. Does nothing.
	 */
	@Override
	protected void end() {
	}

	/**
	 * Called when another command which requires one or more of the same
	 * subsystems is scheduled to run. Does nothing.
	 */
	@Override
	protected void interrupted() {
	}
}
