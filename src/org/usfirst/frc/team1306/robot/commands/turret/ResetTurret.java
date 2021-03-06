package org.usfirst.frc.team1306.robot.commands.turret;

import org.usfirst.frc.team1306.robot.Constants;
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
		turret.moveRot(Constants.TURRET_RESET_POSITION);
	}

	@Override
	protected boolean isFinished() {
		return true;
	}

	@Override
	protected void end() {
	}

	@Override
	protected void interrupted() {
	}
}