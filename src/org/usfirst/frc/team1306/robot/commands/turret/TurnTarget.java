package org.usfirst.frc.team1306.robot.commands.turret;

import org.usfirst.frc.team1306.robot.Constants;
import org.usfirst.frc.team1306.robot.commands.CommandBase;

import edu.wpi.first.wpilibj.networktables.NetworkTable;

public class TurnTarget extends CommandBase {

	private boolean targetInSight = true;
	private final String direction;
	NetworkTable table;
	
	public TurnTarget(String direction) {
		requires(turret);
		this.direction = direction;
		
		NetworkTable.setServerMode();
		NetworkTable.setTeam(1306);
		NetworkTable.initialize();
		table = NetworkTable.getTable("1306");
	}
	
	@Override
	protected void initialize() {
		targetInSight = true;
	}

	@Override
	protected void execute() {
		if(table.getNumber("yaw",0) < 0) {
			turret.setSpeed(Constants.TURRET_TURN_LEFT_SPEED);
		} else if(table.getNumber("yaw",0) > 0) {
			turret.setSpeed(Constants.TURRET_TURN_RIGHT_SPEED);
		}
		if(!table.getBoolean("seeTarget",false)) {
			targetInSight = false;
		}
	}

	@Override
	protected boolean isFinished() {
		
		if(turret.getEncPos() < Constants.TURRET_RIGHT_LIMIT && !targetInSight) {
			turret.stopAll();
			return true;
		} else if(turret.getEncPos() > Constants.TURRET_LEFT_LIMIT && !targetInSight) {
			turret.stopAll();
			return true;
		} else if(Math.abs(table.getNumber("yaw",0)) < 50) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	protected void end() {
		turret.stopAll();
		//new TurnTurret(Constants.TURRET_RESET_POSITION).start();
	}

	@Override
	protected void interrupted() {
		end();
	}

}
