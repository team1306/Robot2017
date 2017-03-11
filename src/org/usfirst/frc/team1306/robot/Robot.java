package org.usfirst.frc.team1306.robot;

import org.usfirst.frc.team1306.robot.commands.CommandBase;
import org.usfirst.frc.team1306.robot.commands.SmartDashboardUpdate;
import org.usfirst.frc.team1306.robot.commands.autonomous.AutoMode;
import org.usfirst.frc.team1306.robot.commands.autonomous.AutonomousCommand;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

    Command autonomousCommand;
    SendableChooser<AutonomousCommand> chooser;
    
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
    	
    	CommandBase.init(); //Initializes all Subsystems
    	CameraServer.getInstance().startAutomaticCapture(); //GearMech Camera
    	
    	chooser = new SendableChooser<AutonomousCommand>();
    	
    	Alliance alliance = DriverStation.getInstance().getAlliance(); //Alliance (Red or Blue)
        int station = DriverStation.getInstance().getLocation(); //Station on alliance (1-3)
        
        chooser.addObject("Hopper/Gear", new AutonomousCommand(alliance,station,AutoMode.HOPPER_GEAR));
        chooser.addObject("Gear", new AutonomousCommand(alliance,station,AutoMode.GEAR));
        chooser.addObject("Ten_Ball", new AutonomousCommand(alliance,station,AutoMode.TEN_KPA));
        chooser.addObject("Do Nothing", new AutonomousCommand(alliance,station,AutoMode.BLANK));
        SmartDashboard.putData("Auto mode", chooser);
    	
    	new SmartDashboardUpdate().start(); //Starts Running SmartDashboardUpdate
    }
	
	/**
     * This function is called once each time the robot enters Disabled mode.
     * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
     */
    public void disabledInit() {

    }
	
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * Gets chosen autonomous mode from SmartDashboard and starts it
	 */
    public void autonomousInit() {
    	
    	autonomousCommand = (Command) chooser.getSelected();
    	
        if (autonomousCommand != null) {
        	autonomousCommand.start();
        }
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    public void teleopInit() {
		// This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to 
        // continue until interrupted by another command, remove
        // this line or comment it out.
        if (autonomousCommand != null) autonomousCommand.cancel();
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
}
