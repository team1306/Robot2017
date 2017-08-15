package org.usfirst.frc.team1306.lib.util;

public class Point {

	public double position, velocity, acceleration, jerk;
	
	public Point(double p, double v, double a, double j) {
		position = p;
		velocity = v;
		acceleration = a;
		jerk = j;
	}
}