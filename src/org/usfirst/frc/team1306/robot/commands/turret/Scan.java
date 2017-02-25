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

	private final double direction;
	NetworkTable table;
	
	public Scan(ScanDirection direction) {
		requires(turret);
		this.direction = direction.getDir();
		SmartDashboard.putNumber("Dir", direction.getDir());
		NetworkTable.setServerMode();
		//NetworkTable.setIPAddress("172.22.11.2");
		NetworkTable.setTeam(1306);
		NetworkTable.initialize();
		//NetworkTable.setIPAddress(Constants.JETSON_IP);
		table = NetworkTable.getTable("1306");
	}
	
	@Override
	protected void initialize() {
		SmartDashboard.putString("Scan Done","false");
		//turret.resetEncoder();
	}

	@Override
	protected void execute() {

		turret.setSpeed(direction);

		SmartDashboard.putNumber("Scanning",direction);
		SmartDashboard.putNumber("Turret Enc Pos", turret.getEncPos());
	}

	@Override
	protected boolean isFinished() {
		SmartDashboard.putNumber("EncPos",turret.getEncPos());

//		if(turret.getEncPos() > 2700 || turret.getEncPos() < 1250) {
//			turret.stopAll();
//		}
//		
		if(turret.getEncPos() > Constants.TURRET_RIGHT_LIMIT && direction == ScanDirection.RIGHT.getDir()) {	//2700 0.18
			turret.stopAll();
			return true;
		} else if(turret.getEncPos() < Constants.TURRET_LEFT_LIMIT && direction == ScanDirection.LEFT.getDir()) {	//1250 -0.18
			turret.stopAll();
			return true;
		} else {
			return false;
		}
//		if(turret.getEncPos() > 2700 || turret.getEncPos() < 1250) {
//			SmartDashboard.putBoolean("Scanning: ",false);
//			turret.stopAll();
//			return true;
//		} else {
//			SmartDashboard.putBoolean("Scanning: ",true);
//			return false;
//		}
	}

	@Override
	protected void end() {
		turret.stopAll();
		new TurnTurret(2080).start();
//		if(direction == 0.18) {
//			new Scan(ScanDirection.LEFT).start();
//		} else {
//			new Scan(ScanDirection.RIGHT).start();
//		}
	}

	@Override
	protected void interrupted() {
		end();
	}
}
