package org.usfirst.frc.team1306.robot.commands.autonomous;

public class AutoPaths {
	
	/** Hopper Autos */
	
	public final static double[][] hopperPathRed = new double[][]{
		{0,0},
		{80.125/12,0},
		{80.125/12,-(54.5/12)},
	};
	
	public final static double[][] hopperPathBlue = new double[][]{
		{0,0},
		{80.125/12,0},
		{80.125/12,54.5/12},
	}; 
	
	/** Hopper Gear Autos */
	
	public final static double[][] hopperGearPathRed = new double[][] {
		{0,0},
		{6.8/12,0},
		{100/12,50.5/12},
	};
	
	public final static double[][] hopperGearPathBlue = new double[][] {
		{0,0},
		{6.8/12,0},
		{100/12,-50.5/12},
	};
	
	/** Gear Autos */
	
	public final static double[][] leftGearRed = new double[][]{
		{0,0},
		{68.54/12,0},
		{96.25/12,(48/12)},
	}; 
	
	public final static double[][] leftGearBlue = new double[][]{
		{0,0},
		{80.041/12,0},
		{95.25/12,26.344/12},
	}; 
	
	public final static double[][] rightGearRed = new double[][]{
		{0,0},
		{80.041/12,0},
		{95.25/12,-(26.344/12)},
	};
	
	public final static double[][] rightGearBlue = new double[][]{
		{0,0},
		{68.54/12,0},
		{96.25/12,-(48/12)},
	}; 
}
