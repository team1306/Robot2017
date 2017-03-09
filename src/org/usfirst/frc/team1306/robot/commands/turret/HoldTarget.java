package org.usfirst.frc.team1306.robot.commands.turret;

import org.usfirst.frc.team1306.robot.Constants;
import org.usfirst.frc.team1306.robot.commands.CommandBase;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

public class HoldTarget extends CommandBase {

	NetworkTable table;
	Timer timer;
	private double initYaw;
	
	public HoldTarget(double yaw) {
		requires(turret);
		this.initYaw = yaw;
		
		timer = new Timer();
		
		NetworkTable.setServerMode();
		NetworkTable.setTeam(1306);
		NetworkTable.initialize();
		table = NetworkTable.getTable("1306");
	}
	
	@Override
	protected void initialize() {
		timer.reset();
		timer.start();
		
		turret.moveDeg(initYaw);
	}

	@Override
	protected void execute() {
		if(timer.hasPeriodPassed(Constants.TURRET_RECOVERY_TIME)) {
			turret.moveDeg(table.getNumber("yaw",0));
			timer.reset();
			timer.start();
		}
	}

	@Override
	protected boolean isFinished() {
		
		if(!table.getBoolean("seeTarget",false)) {
			new FindTarget().start();
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
