package org.usfirst.frc.team1306.robot.commands.autonomous;

import org.usfirst.frc.team1306.robot.commands.CommandBase;
import edu.wpi.first.wpilibj.Timer;

/**
 * Spins the climber backwards so the intake will fall down and allow for the collection of balls
 * @author Jackson Goth
 */
public class DeployIntake extends CommandBase {

	private final double deployTime = 1.0;
	private final Timer timer;
	
	public DeployIntake() {
		requires(climber);
		timer = new Timer();
	}

	
	@Override
	protected void initialize() {
		timer.reset();
		timer.start();
	}

	/**
	 * Spins the climber backwards
	 */
	@Override
	protected void execute() {
		climber.spinClimberBack();
	}

	/**
	 * Stops spinning climber after one second
	 */
	@Override
	protected boolean isFinished() {
    	return timer.hasPeriodPassed(deployTime);
	}

	@Override
	protected void end() {
		climber.stopAll();
	}

	@Override
	protected void interrupted() {
		end();
	}
}
