package org.usfirst.frc.team1306.robot.commands.autonomous;

import org.usfirst.frc.team1306.robot.Constants;
import org.usfirst.frc.team1306.robot.commands.CommandBase;
import edu.wpi.first.wpilibj.Timer;

/**
 * Command that fires balls into the boiler during autonomous
 * @author Jackson Goth
 */
public class Fire extends CommandBase {

	private final Timer timer;
	private final double time = Constants.SHOOT_TIME;
	
	public Fire() {
		requires(shooter);
		requires(hopper);
		timer = new Timer();
	}

	@Override
	protected void initialize() {
		timer.reset();
		timer.start();
	}

	/**
	 * Spins up shooter, indexers, and hopper
	 */
	@Override
	protected void execute() {
		hopper.spinHopper();
		shooter.spinShooter();
		shooter.spinIndexer();
	}

	/**
	 * Stops shooting after a certain amount of time
	 */
	@Override
	protected boolean isFinished() {
		return timer.hasPeriodPassed(time);
	}

	@Override
	protected void end() {
		hopper.stopAll();
		shooter.stopAll();
	}

	@Override
	protected void interrupted() {
		
	}
}
