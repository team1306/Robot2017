package org.usfirst.frc.team1306.robot.commands.turret;

import org.usfirst.frc.team1306.robot.Constants;
import org.usfirst.frc.team1306.robot.commands.CommandBase;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Command used to adjust and aim turrets with a controller instead of vision
 * @author Jackson Goth
 */
public class Aim extends CommandBase {
	
	private Direction direction;
	
	public Aim(Direction direction) {
		requires(turret);
		this.direction = direction;
	}
	
	@Override
	protected void initialize() {
		
	}

	@Override
	protected void execute() {
		
		/**
		 * If the step will stay inside the turret soft limits, it will turn it positive (left) or negative (right) based on driver input
		 */
//		if(!(Constants.DPAD_TURRET_STEP + turret.getPosition() > Constants.TURRET_RIGHT_ROT_LIMIT) && !(Constants.DPAD_TURRET_STEP + turret.getPosition() < Constants.TURRET_LEFT_ROT_LIMIT)) {
//			if(direction.equals(Direction.LEFT)) {
//				turret.moveRot(Constants.DPAD_TURRET_STEP + turret.getPosition());
//			} else {
//				turret.moveRot(-Constants.DPAD_TURRET_STEP + turret.getPosition());
//			}
//		}
		SmartDashboard.putNumber("Aim: ", Constants.DPAD_TURRET_STEP + turret.getPosition());
		if(direction.equals(Direction.LEFT)) {
			SmartDashboard.putString("Aim Direction: ", "LEFT");
			turret.moveRot((Constants.DPAD_TURRET_STEP + turret.getPosition()));
		} else {
			SmartDashboard.putString("Aim Direction: ", "RIGHT");
			turret.moveRot((-Constants.DPAD_TURRET_STEP + turret.getPosition()));
		}
	}

	@Override
	protected boolean isFinished() {
		return false;
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
