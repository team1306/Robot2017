package org.usfirst.frc.team1306.robot.commands.turret;

import org.usfirst.frc.team1306.robot.Constants;
import org.usfirst.frc.team1306.robot.commands.CommandBase;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Command that is run once a target is located and will try and turn to it
 * @author Jackson Goth
 */
public class TurnTarget extends CommandBase {

	private boolean targetInSight = true;
	private final String direction;
	private Timer timeoutTimer;
	NetworkTable table;
	
	public TurnTarget(String direction) {
		requires(turret);
		this.direction = direction; //TODO Remove if still unused
		
		NetworkTable.setServerMode();
		NetworkTable.setTeam(1306);
		NetworkTable.initialize();
		table = NetworkTable.getTable("1306");
		
		timeoutTimer = new Timer(); //Timer used to stop command if target is lost for 5 seconds
	}
	
	@Override
	protected void initialize() {
		targetInSight = true; //Target must be in sight, because command is run
	}

	@Override
	protected void execute() {
		
		//Turns the turret based on yaw (0 is the center, so if yaw is negative the turret must turn left)
		if(table.getNumber("yaw",0) < 0) {
			//turret.setSpeed(Constants.TURRET_TURN_LEFT_SPEED); TODO Re-Add When Received Yaw is Correct
		} else if(table.getNumber("yaw",0) > 0) {
			//turret.setSpeed(Constants.TURRET_TURN_RIGHT_SPEED); TODO Re-Add When Received Yaw is Correct
		}
		
		//If the target is lost a timer will start
		if(!table.getBoolean("seeTarget",false)) {
			targetInSight = false;
			timeoutTimer.reset();
			timeoutTimer.start();
		} else {
			targetInSight = true;
			timeoutTimer.reset();
			timeoutTimer.stop();
		}
		SmartDashboard.putBoolean("Target In Sight",targetInSight);
		SmartDashboard.putNumber("Yaw",table.getNumber("yaw",0));
	}

	@Override
	protected boolean isFinished() {
		
		if(turret.getEncPos() < Constants.TURRET_RIGHT_LIMIT) { //Stops turning if the turret hits the right limit
			SmartDashboard.putBoolean("Hit Right Limit",true);
			turret.stopAll();
			return true;
		} else if(turret.getEncPos() > Constants.TURRET_LEFT_LIMIT) { //Stops turning if the turret hits the left limit
			SmartDashboard.putBoolean("Hit Left Limit",true);
			turret.stopAll();
			return true;
		} else if(Math.abs(table.getNumber("yaw",0)) < Constants.VISION_YAW_TOLERANCE) { //Stops turning if target is in tolerance
			SmartDashboard.putBoolean("Locked-On",true);
			return true;
		} else if(!targetInSight && timeoutTimer.hasPeriodPassed(5)) { //Stops turning if target has been lost for 5 seconds
			SmartDashboard.putBoolean("Jetson Timed Out?",true);
			turret.stopAll();
			return true;
		} else { //Continues turning turret
			if(!timeoutTimer.hasPeriodPassed(0.5)) {
				SmartDashboard.putBoolean("Jetson Timed Out?",false);
			}
			SmartDashboard.putBoolean("Locked-On",false);
			SmartDashboard.putBoolean("Hit Right Limit",false);
			SmartDashboard.putBoolean("Hit Left Limit",false);
			return false;
		}
	}

	@Override
	protected void end() {
		turret.stopAll();
		//new TurnTurret(Constants.TURRET_RESET_POSITION).start();
	}

	@Override
	protected void interrupted() {
		end();
	}

}
