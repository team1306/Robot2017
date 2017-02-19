package org.usfirst.frc.team1306.robot.commands.turret;

import org.usfirst.frc.team1306.robot.commands.CommandBase;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Command that scans with vision in given direction until a target is found.
 * @author Jackson Goth
 */
public class Scan extends CommandBase {

	private final double direction;
	
	public Scan(ScanDirection direction) {
		requires(turret);
		this.direction = direction.getDir();
	}
	
	@Override
	protected void initialize() {
		SmartDashboard.putString("finishedtttt","false");
	}

	@Override
	protected void execute() {
		turret.set(direction);
		SmartDashboard.putNumber("Scanning",direction);
	}

	@Override
	protected boolean isFinished() {
//		if(turret.getEncPos() > 600 || turret.getEncPos() < -500) {
//			return true;
//		} else {
//			return false;
//		}
		return false;
	}

	@Override
	protected void end() {
		SmartDashboard.putString("finishedtttt","true");
	}

	@Override
	protected void interrupted() {
		//end();
	}
}
