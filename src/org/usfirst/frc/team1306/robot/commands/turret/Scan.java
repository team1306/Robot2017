package org.usfirst.frc.team1306.robot.commands.turret;

import org.usfirst.frc.team1306.robot.Constants;
import org.usfirst.frc.team1306.robot.commands.CommandBase;

import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

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
		table.putBoolean("seeTarget",false);
	}

	@Override
	protected void execute() {
		
		targetInSight = table.getBoolean("seeTarget",false);

		if(targetInSight) {
			if(table.getNumber("yaw",0) < 0) {
				turret.setSpeed(Constants.TURRET_TURN_LEFT_SPEED);
			} else if(table.getNumber("yaw",0) > 0) {
				turret.setSpeed(Constants.TURRET_TURN_RIGHT_SPEED);
			}
		} else {
			turret.setSpeed(turn_speed);
		}
		SmartDashboard.putBoolean("Target In Sight",targetInSight);
		SmartDashboard.putNumber("Yaw",table.getNumber("yaw",0));
	}

	@Override
	protected boolean isFinished() {
		
		if(turret.getEncPos() < Constants.TURRET_RIGHT_LIMIT && direction.equals("Right")) {
			turret.stopAll();
			return true;
		} else if(turret.getEncPos() > Constants.TURRET_LEFT_LIMIT && direction.equals("Left")) {
			turret.stopAll();
			return true;
		}/* else if(Math.abs(table.getNumber("yaw",0)) < Constants.VISION_YAW_TOLERANCE) { //Stops turning if target is in tolerance
			SmartDashboard.putBoolean("Locked-On",true);
			turret.stopAll();
			return true;
		}*/ else {
			return false;
		}
	}

	@Override
	protected void end() {
		turret.stopAll();
//		if(targetInSight) {
//			new TurnTarget(direction).start();
//		} else {
//			new TurnTurret(Constants.TURRET_RESET_POSITION).start();
//		}
		
	}

	@Override
	protected void interrupted() {
		end();
	}
}
