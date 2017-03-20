package org.usfirst.frc.team1306.robot.commands.turret;

import org.usfirst.frc.team1306.robot.Constants;
import org.usfirst.frc.team1306.robot.commands.CommandBase;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

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
		SmartDashboard.putBoolean("Turret Scanning", scanning);
		SmartDashboard.putBoolean("See Target", table.getBoolean("seeTarget",false));
		/*if(scanning && !table.getBoolean("seeTarget",false)) { 
			SmartDashboard.putBoolean("Move Turret Find Target", false);
			turret.setSpeed(turn_speed);
		} else */if(table.getBoolean("seeTarget",false)) {
			if(!(Math.abs(table.getNumber("yaw",0)) < Constants.YAW_DEADBAND)) {
				SmartDashboard.putBoolean("Move Turret Find Target", true);
				turret.moveDeg(-(turret.getPosition() + table.getNumber("yaw",0)));
			}
		} else {
			SmartDashboard.putBoolean("Move Turret Find Target", false);
			//turret.moveRot(0);
		}
	}

	@Override
	protected boolean isFinished() {
		return false;
//		if(turret.getEncPos() < Constants.TURRET_RIGHT_LIMIT && direction.equals(ScanDirection.RIGHT)) {
//			turret.stopAll();
//			//new ResetTurret().start();
//			return true;
//		} else if(turret.getEncPos() > Constants.TURRET_LEFT_LIMIT && direction.equals(ScanDirection.LEFT)) {
//			turret.stopAll();
//			//new ResetTurret().start();
//			return true;
//		} else {
//			return false;
//		}
		
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
