package org.usfirst.frc.team1306.robot.commands.shooter;

import org.usfirst.frc.team1306.robot.Constants;
import org.usfirst.frc.team1306.robot.commands.CommandBase;
import org.usfirst.frc.team1306.robot.commands.shooter.AdjustHood.HoodAngle;
import edu.wpi.first.wpilibj.Timer;

public class RangeTesting extends CommandBase {

	private Timer timer;
	
	public RangeTesting() {
		requires(shooter);
		
		timer = new Timer();
	}
	
	@Override
	protected void initialize() {
		timer.reset();
		timer.start();
		
		shooter.setHoodAngle(HoodAngle.UP);
	}

	@Override
	protected void execute() {
		
    	shooter.spinShooter();
		shooter.spinIndexer();
		
    	if(timer.hasPeriodPassed(Constants.SHOOTER_SPIN_UP_TIME)) {
			shooter.spinHopper();
			intake.spinIntake();
    	}
	}

	@Override
	protected boolean isFinished() {
		return false;
	}
}
