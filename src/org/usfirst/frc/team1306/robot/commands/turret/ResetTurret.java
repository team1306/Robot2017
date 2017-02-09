package org.usfirst.frc.team1306.robot.commands.turret;

import org.usfirst.frc.team1306.robot.commands.CommandBase;

/**
 * Command that resets turret to default position
 * @author Jakcson Goth
 */
public class ResetTurret extends CommandBase{

	public ResetTurret() {
		requires(turret);
	}
	
	@Override
	protected void initialize() {
		
	}

	@Override
	protected void execute() {
		turret.setPosition(0);
	}

	@Override
	protected boolean isFinished() {
		if(turret.getPos() == 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	protected void end() {
		turret.stopAll();
	}

	@Override
	protected void interrupted() {
		end();
	}
}