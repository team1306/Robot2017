package org.usfirst.frc.team1306.robot.commands.turret;

import java.util.ArrayDeque;
import java.util.Queue;

import org.usfirst.frc.team1306.robot.commands.CommandBase;

import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class FindTarget extends CommandBase {

	NetworkTable table;
	private boolean scanning = false;
	private double turn_speed;
	private ScanDirection direction;
	private int counter;
	private double accumulator;
	Queue<Double> queue;
//	PWM leds;
	DigitalOutput leds;
	
	public FindTarget(ScanDirection direction) {
		requires(turret);
		this.direction = direction;
		this.turn_speed = direction.getTurnSpeed();
		
		scanning = true;
		
		leds = new DigitalOutput(0);
		queue = new ArrayDeque<Double>();
		
		NetworkTable.setServerMode();
		NetworkTable.setTeam(1306);
		NetworkTable.initialize();
		table = NetworkTable.getTable("1306");
	}
	
	public FindTarget() {
		requires(turret);
		
		scanning = false;
		
		leds = new DigitalOutput(0);
		queue = new ArrayDeque<Double>();
		
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
		counter = 0;
		accumulator = 0;
	}

	@Override
	protected void execute() {
//		SmartDashboard.putBoolean("Turret Scanning", scanning);
//		SmartDashboard.putBoolean("See Target", table.getBoolean("seeTarget",false));
//		/*if(scanning && !table.getBoolean("seeTarget",false)) { 
//			SmartDashboard.putBoolean("Move Turret Find Target", false);
//			turret.setSpeed(turn_speed);
//		} else */if(table.getBoolean("seeTarget",false)) {
////			if(!(Math.abs(table.getNumber("yaw",0)) < Constants.YAW_DEADBAND)) {
////				SmartDashboard.putBoolean("Move Turret Find Target", true);
////				turret.moveRot((turret.getPosition() + (table.getNumber("yaw",0)/360)));
////				SmartDashboard.putNumber("Turret vision",turret.getPosition() + (table.getNumber("yaw",0)/360));
////			}
////		} else {
//			SmartDashboard.putBoolean("Move Turret Find Target", false);
//			//turret.moveRot(0);s
//		}
		leds.set(true);
		
		turret.moveRot(0);
		
		if(table.getBoolean("seeTarget",false)) {
//			turret.moveRot((turret.getPosition() + (table.getNumber("yaw",0)/360)));
			
			double newYaw = 0;
			
			if( queue.size() < 10) {
				queue.add(table.getNumber("yaw",0) / 360);
				accumulator = 0;
			} else {
				queue.poll();
				queue.add(table.getNumber("yaw",0) / 360);
				for(Double elem : queue) {
					accumulator += queue.peek();
				}
				accumulator = accumulator / 10;
				
				if(Math.abs(queue.peek() - table.getNumber("yaw",0)) < 5) {
					newYaw = table.getNumber("yaw",0);
				} else {
					newYaw = accumulator;
				}
			}
			
			SmartDashboard.putNumber("Yaw",table.getNumber("yaw",0));
			SmartDashboard.putNumber("Averaged Yaw",newYaw);
			
//			turret.moveRot(turret.getPosition() + newYaw);
			
//			if(table.getNumber("yaw",0) < 5 && table.getNumber("yaw",0) > -5) {
//				
//			} else {
//				//turret.moveRot(turret.getPosition() + (table.getNumber("yaw",0)/360));
//			}
			
		//	turret.moveRot()
			
			SmartDashboard.putNumber("Turret vision",turret.getPosition() - (table.getNumber("yaw",0)/360));
			SmartDashboard.putNumber("yaw",table.getNumber("yaw",0));
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
		leds.set(false);
	}

	@Override
	protected void interrupted() {
		end();
	}
}
