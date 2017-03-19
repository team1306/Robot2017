package org.usfirst.frc.team1306.robot.commands.turret;

import org.usfirst.frc.team1306.robot.Constants;
import org.usfirst.frc.team1306.robot.commands.CommandBase;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

public class FindTarget extends CommandBase {

	NetworkTable table;
	private boolean scanning = false;
	private double turn_speed;
	private ScanDirection direction;
	
	public FindTarget(ScanDirection direction) {
		requires(turret);
		this.direction = direction;
		this.turn_speed = direction.getTurnSpeed();
		
		scanning = true;
		
		NetworkTable.setServerMode();
		NetworkTable.setTeam(1306);
		NetworkTable.initialize();
		table = NetworkTable.getTable("1306");
	}
	
	public FindTarget() {
		requires(turret);
		
		scanning = false;
		
		NetworkTable.setServerMode();
		NetworkTable.setTeam(1306);
		NetworkTable.initialize();
		table = NetworkTable.getTable("1306");
	}
	
	@Override
	protected void initialize() {
		if(!scanning) {
			//new ResetTurret().start();
		}
	}

	@Override
	protected void execute() {
		if(scanning && !table.getBoolean("seeTarget",false)) { 
			turret.setSpeed(turn_speed);
		} else if(table.getBoolean("seeTarget",false)) {
			if(!(table.getNumber("yaw",0) < Constants.YAW_DEADBAND)) {
				turret.moveDeg(/*turret.getPosition() +*/ table.getNumber("yaw",0));
			}
		} else {
			//turret.moveRot(0);
		}
	}

	@Override
	protected boolean isFinished() {
		
		if(turret.getEncPos() < Constants.TURRET_RIGHT_LIMIT && direction.equals(ScanDirection.RIGHT)) {
			turret.stopAll();
			//new ResetTurret().start();
			return true;
		} else if(turret.getEncPos() > Constants.TURRET_LEFT_LIMIT && direction.equals(ScanDirection.LEFT)) {
			turret.stopAll();
			//new ResetTurret().start();
			return true;
		} else {
			return false;
		}
		
//		return false;
//		} else if(table.getBoolean("seeTarget",false)) {
//			new HoldTarget(table.getNumber("yaw",0)).start();
//			return true;
//		} else {
//			return false;
//		}
//		if(table.getBoolean("seeTarget",false)) {
//			turret.stopAll();
//			new HoldTarget(table.getNumber("yaw",0)).start();
//			return true;
//		} else {
//			return false;
//		}
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
