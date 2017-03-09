package org.usfirst.frc.team1306.robot.commands.turret;

import org.usfirst.frc.team1306.robot.Constants;
import org.usfirst.frc.team1306.robot.commands.CommandBase;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Command that turns the turret in a given direction, and will turn to the turret if it enters line of sight.
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
//			if(Math.abs(table.getNumber("yaw",0)) + 8 < 15) {
//				turret.setSpeed(0);
//			} else if(table.getNumber("yaw",0) + 8 < 0) {
//				turret.setSpeed(Constants.TURRET_TURN_LEFT_SPEED);
//			} else if(table.getNumber("yaw",0) + 8 > 0) {
//				turret.setSpeed(Constants.TURRET_TURN_RIGHT_SPEED);
//			}
			new TurnTurret((table.getNumber("yaw",0)/360)*Constants.TURRET_GEAR_CONVERSION).start();
			end();
		} else {
			//turret.setSpeed(turn_speed);
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
