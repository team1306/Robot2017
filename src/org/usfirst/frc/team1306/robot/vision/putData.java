package org.usfirst.frc.team1306.robot.vision;

import org.usfirst.frc.team1306.robot.Constants;
import org.usfirst.frc.team1306.robot.commands.CommandBase;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class putData extends CommandBase {
	
	Timer timer;
	NetworkTable table;
	int counter;
	
	public putData() {
		table = NetworkTable.getTable("1306");
	}
	
	@Override
	protected void initialize() {
		timer.reset();
		timer.start();
		counter = 1;
	}

	@Override
	protected void execute() {
		if(timer.hasPeriodPassed(Constants.DATA_REFRESH_RATE)) {
			counter++;
			table.putNumber("area",counter);
			SmartDashboard.putString("Putting Data: ","True");
		} else {
			SmartDashboard.putString("Putting Data: ","False");
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
