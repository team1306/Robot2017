package org.usfirst.frc.team1306.robot.commands.turret;

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
		turret.set(direction);
		SmartDashboard.putNumber("Scanning",direction);
		SmartDashboard.putNumber("Turret Enc Pos", turret.getEncPos());
	}

	@Override
	protected boolean isFinished() {
		SmartDashboard.putNumber("EncPos",turret.getEncPos());
		if(turret.getEncPos() > 2800 || turret.getEncPos() < 1150) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	protected void end() {
		SmartDashboard.putString("Scan Done","true");
	}

	@Override
	protected void interrupted() {
		//end();
	}
}
