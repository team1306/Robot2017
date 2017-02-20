package org.usfirst.frc.team1306.robot.vision;

import org.usfirst.frc.team1306.robot.Constants;
import org.usfirst.frc.team1306.robot.commands.CommandBase;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Command used to test receiving values from networktables
 * @author Jackson Goth
 */
public class GetData extends CommandBase {

	Timer timer;
	NetworkTable table;
	
	public GetData() {
		timer = new Timer();
		NetworkTable.setServerMode();
		//NetworkTable.setIPAddress("172.22.11.2");
		NetworkTable.setTeam(1306);
		NetworkTable.initialize();
		//NetworkTable.setIPAddress(Constants.JETSON_IP);
		table = NetworkTable.getTable("1306");
	}
	
	@Override
	protected void initialize() {
		timer.reset();
		timer.start();
	}

	@Override
	protected void execute() {
		SmartDashboard.putBoolean("Connected?: ",table.isConnected());
		if(timer.hasPeriodPassed(Constants.DATA_REFRESH_RATE)) {
			int defaultValue = 0;
			SmartDashboard.putNumber("Yaw",table.getNumber("Yaw",defaultValue));
			SmartDashboard.putNumber("Dist",table.getNumber("dist",defaultValue));
			SmartDashboard.putNumber("Pitch",table.getNumber("angle",defaultValue));
			SmartDashboard.putString("Getting Data: ","True");
			timer.reset();
			timer.start();
		} else {
			SmartDashboard.putString("Getting Data: ","False");
		}
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
		
	}

	@Override
	protected void interrupted() {
		
	}
}
