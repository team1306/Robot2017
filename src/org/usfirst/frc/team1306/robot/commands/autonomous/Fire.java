package org.usfirst.frc.team1306.robot.commands.autonomous;

import org.usfirst.frc.team1306.robot.commands.CommandBase;
import edu.wpi.first.wpilibj.Timer;

/**
 * Command that fires balls into the boiler during autonomous
 * @author Jackson Goth
 */
public class Fire extends CommandBase {

	private final Timer timer;
	private final double time = AutoConstants.SHOOT_TIME;
	
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

	@Override
	protected void execute() {
		hopper.spinHopper();
		shooter.bangBangSpinShooter();
	}

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
