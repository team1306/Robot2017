package org.usfirst.frc.team1306.robot.commands.turret;

import org.usfirst.frc.team1306.robot.commands.CommandBase;

/**
 * Command that turns turret to a given rotations
 * @author Sam Roquitte and Jackson Goth
 */
public class TurnTurret extends CommandBase {

	private double rotations;
	
	public TurnTurret(double rotations) {
		requires(turret);
		this.rotations = rotations;
	}
	
    protected void execute() {
    	turret.moveRot(rotations);
    }

    protected boolean isFinished() {
    	new AutoTurret().start();
    	return true;
    }
}
