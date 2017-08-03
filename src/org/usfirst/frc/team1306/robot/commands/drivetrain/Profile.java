package org.usfirst.frc.team1306.robot.commands.drivetrain;

import java.util.ArrayList;

public class Profile {

	public double maxTime = 15;
	public ArrayList<Point> path;
	
	public Profile(double d, double v, double a, double j, double t) {
		double distance = d;
		double maxVelocity = v;
		double maxAcceleration = a;
		double maxJerk = j;
		double maxTime = t;

		ArrayList<Point> mirrorPath = new ArrayList<Point>();
		path = new ArrayList<Point>();
		
		ProfileStatus status = ProfileStatus.ACCELERATING;
		ProfileSubStatus subStatus = ProfileSubStatus.ACCEL_RAMP_UP;
		double stepTime = 0.01;
		int maxSteps = (int) (maxTime / stepTime);
		
		boolean mirroring = false;
		int accelPreMirrorCounter = 0;
		int accelPostMirrorCounter = 0;
		int profileMirrorCounter = 0;
		int profileMirrorStopPoint = 0;
		
		path.add(new Point(0,0,0,0));
	
		for(int i = 1; i < maxSteps; i++) {
		
			Point prevPoint = path.get(i - 1);
			
			if(prevPoint.acceleration > maxAcceleration) {
				subStatus = ProfileSubStatus.ACCEL_HOLD;
			}
			
			if(prevPoint.velocity > maxVelocity / 2) {
				mirroring = true;
			}
			
			if(subStatus.equals(ProfileSubStatus.ACCEL_HOLD) && accelPostMirrorCounter == accelPreMirrorCounter && mirroring) {
				subStatus = ProfileSubStatus.ACCEL_RAMP_DOWN;
			}
			
			if(subStatus.equals(ProfileSubStatus.ACCEL_RAMP_DOWN) && prevPoint.velocity > maxVelocity) {
				subStatus = ProfileSubStatus.CONSTANT;
			}
			
			if(prevPoint.position > distance / 2 && !(status.equals(ProfileStatus.MIRRORING))) {
				status = ProfileStatus.MIRRORING;
				for(int k = 0; k < path.size(); k++) {
					mirrorPath.add(0,path.get(k));
				}
				profileMirrorCounter = 0;
				profileMirrorStopPoint = mirrorPath.size();
			}
			
			double position;
			double velocity;
			double acceleration = 0;
			double jerk = 0;
			
			if(status.equals(ProfileStatus.ACCELERATING)) {
				if(subStatus.equals(ProfileSubStatus.ACCEL_RAMP_UP)) {
					jerk = maxJerk;
					acceleration = prevPoint.acceleration + (jerk*stepTime);
				} else if(subStatus.equals(ProfileSubStatus.ACCEL_HOLD)) {
					jerk = 0;
					acceleration = prevPoint.acceleration;
					if(!mirroring) {
						accelPreMirrorCounter++;
					} else {
						accelPostMirrorCounter++;
					}
				} else if(subStatus.equals(ProfileSubStatus.ACCEL_RAMP_DOWN)) {
					jerk = maxJerk;
					acceleration = prevPoint.acceleration - (jerk*stepTime);
				}
			} else if(status.equals(ProfileStatus.CONSTANT)) {
				if(subStatus.equals(ProfileSubStatus.CONSTANT)) {
					jerk = 0;
					acceleration = 0;
				}
			} else if(status.equals(ProfileStatus.MIRRORING)) {
				if(profileMirrorCounter >= profileMirrorStopPoint) {
					jerk = 0;
					acceleration = 0;
				} else {
					jerk = mirrorPath.get(profileMirrorCounter).jerk; 
					acceleration = mirrorPath.get(profileMirrorCounter).acceleration;
				}
				profileMirrorCounter++;
			}
			
			if(status.equals(ProfileStatus.MIRRORING)) {
				velocity = prevPoint.velocity - (acceleration*stepTime);
				position = prevPoint.position + (velocity*stepTime);
			} else {
				velocity = prevPoint.velocity + (acceleration*stepTime);
				position = prevPoint.position + (velocity*stepTime);
			}
			
			if(velocity < 0) velocity = 0;
			
			path.add(new Point(position,velocity,acceleration,jerk));
		}
	}
	
	public String toString() {
		String pathPrint = "";
		for(int i = 0; i < path.size(); i++) {
			pathPrint += path.get(i).velocity;
			pathPrint += " ";
		}
		return pathPrint;
	}
	
	public int getSteps() {
		return path.size();
	}
	
	public enum ProfileStatus {ACCELERATING, CONSTANT, MIRRORING};
	public enum ProfileSubStatus {ACCEL_RAMP_UP, ACCEL_HOLD, ACCEL_RAMP_DOWN, CONSTANT};
}