package org.usfirst.frc.team1306.robot.commands.turret;

import org.usfirst.frc.team1306.robot.Constants;
import org.usfirst.frc.team1306.robot.commands.CommandBase;

import edu.wpi.first.wpilibj.networktables.NetworkTable;

/**
 * Command that scans with vision in given direction until a target is found.
 * @author Jackson Goth
 */
public class Scan extends CommandBase {

	private final double turn_speed;
	private final String direction;
	private boolean targetInSight = false;
	NetworkTable table;
	
	public Scan(ScanDirection scan) {
		requires(turret);
		this.turn_speed = scan.getTurnSpeed();
		this.direction = scan.getDirection();
		
		NetworkTable.setServerMode();
		NetworkTable.setTeam(1306);
		NetworkTable.initialize();
		table = NetworkTable.getTable("1306");
	}
	
	@Override
	protected void initialize() {
		targetInSight = false;
	}

	@Override
	protected void execute() {
		turret.setSpeed(turn_speed);
		if(table.getBoolean("seeTarget",false)) {
			targetInSight = true;
		}
	}

	@Override
	protected boolean isFinished() {
		
		if(turret.getEncPos() < Constants.TURRET_RIGHT_LIMIT && direction.equals("Right") && !targetInSight) {
			turret.stopAll();
			return true;
		} else if(turret.getEncPos() > Constants.TURRET_LEFT_LIMIT && direction.equals("Left") && !targetInSight) {
			turret.stopAll();
			return true;
		} else {
			return false;
		}
	}

	@Override
	protected void end() {
		turret.stopAll();
		if(targetInSight) {
			new TurnTarget(direction).start();
		} else {
			new TurnTurret(Constants.TURRET_RESET_POSITION).start();
		}
		
	}

	@Override
	protected void interrupted() {
		end();
	}
}
