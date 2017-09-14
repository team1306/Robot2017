package org.usfirst.frc.team1306.robot.commands.turret;

import java.util.ArrayList;
import org.usfirst.frc.team1306.robot.Constants;
import org.usfirst.frc.team1306.robot.OI;
import org.usfirst.frc.team1306.robot.OI.Axis;
import org.usfirst.frc.team1306.robot.OI.Controller;
import org.usfirst.frc.team1306.robot.OI.Joystick;
import org.usfirst.frc.team1306.robot.commands.CommandBase;

/**
 * @AutoTurret
 * 
 * @author Jackson Goth
 */
public class AutoTurret extends CommandBase {

	private double averagedYaw, accumulator, visionRotAdj;
	private ArrayList<Double> yawList;
	
	public AutoTurret() {
		requires(turret);
		
		yawList = new ArrayList<Double>(); //Array used for storing and averaging yaw values from the jetson
	}

	@Override
	protected void execute() {
		
		double manualVal = OI.getJoyVal(Controller.S,Joystick.L,Axis.X);
		
		if(vision.seeTarget()) {
			
			if(yawList.size() < 10) { //Fills up initial array
				yawList.add(vision.getYaw());
			} else {
				yawList.remove(0); //Removes oldest data from list
				yawList.add(vision.getYaw()); //Adds newest data to the top of the list
			}
			
			accumulator = 0;
			for(int i = 0; i < yawList.size(); i++) {
				accumulator += yawList.get(i);
			}
			averagedYaw = accumulator / yawList.size();
			
			if(Math.abs(averagedYaw) > 4.5) {
				if(averagedYaw > 0) {
					visionRotAdj = ((averagedYaw / 15)/12) + 0.075;
				} else {
					visionRotAdj = ((averagedYaw / 15)/12) - 0.075;
				}
			} else {
				visionRotAdj = 0;
			}

			turret.setSpeed(visionRotAdj);
			
		} else if(manualVal > Constants.DEADBAND || manualVal < -Constants.DEADBAND) {
			turret.setSpeed(OI.getJoyVal(Controller.S, Joystick.L, Axis.X) * -0.2);
		} else {
			turret.stop();
		}
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
		turret.stop();
	}

	@Override
	protected void interrupted() {
		end();
	}
}
