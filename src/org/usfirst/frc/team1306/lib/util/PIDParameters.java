package org.usfirst.frc.team1306.lib.util;

/**
 * @PIDParameters
 * 
 * This class allows you to make an object that can easily store a set of PIDF or PID parameters for a subsystem
 * @author Jackson Goth
 */
public class PIDParameters {

	public double f, p, i, d;
	
	public PIDParameters(double f, double p, double i, double d) {
		this.f = f;
		this.p = p;
		this.i = i;
		this.d = d;
	}
	
	public PIDParameters(double p, double i, double d) {
		this.f = 0.0;
		this.p = p;
		this.i = i;
		this.d = d;
	}
}
