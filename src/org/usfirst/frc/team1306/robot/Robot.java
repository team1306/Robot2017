package org.usfirst.frc.team1306.robot;

import org.usfirst.frc.team1306.robot.commands.CommandBase;
import org.usfirst.frc.team1306.robot.commands.SmartDashboardUpdate;
import org.usfirst.frc.team1306.robot.commands.autonomous.AutoMode;
import org.usfirst.frc.team1306.robot.commands.autonomous.AutonomousCommand;
import edu.wpi.first.wpilibj.CameraServer;
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
 * documentation.
 * 
 * @author Jackson Goth, Sam Roquitte
 */
public class Robot extends IterativeRobot {

    Command autonomousCommand; //Command that gets run for auto
    SendableChooser<AutonomousCommand> chooser; //SmartDashboard selector of autonomous modes
    
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
    	
    	CommandBase.init(); //Initializes all Subsystems
    	CameraServer.getInstance().startAutomaticCapture("usb",0); //Geartake Camera
    	CameraServer.getInstance().startAutomaticCapture("usb2",1); //Shooter Camera
    	
    	chooser = new SendableChooser<AutonomousCommand>();
    	
    	/* Adding all our autonomous modes to the selector */
        chooser.addObject("Baseline", new AutonomousCommand(Alliance.Red,1,AutoMode.BASELINE));
        chooser.addObject("Middle", new AutonomousCommand(Alliance.Red,2,AutoMode.GEAR));
        chooser.addObject("Left Gear - Blue", new AutonomousCommand(Alliance.Blue,1,AutoMode.GEAR));
        chooser.addObject("Left Gear - Red", new AutonomousCommand(Alliance.Red,1,AutoMode.GEAR));
        chooser.addObject("Right Gear - Blue", new AutonomousCommand(Alliance.Blue,3,AutoMode.GEAR));
        chooser.addObject("Right Gear - Red", new AutonomousCommand(Alliance.Red,3,AutoMode.GEAR));
        chooser.addObject("Hopper - Blue", new AutonomousCommand(Alliance.Blue,1,AutoMode.HOPPER));
        chooser.addObject("Hopper - Red", new AutonomousCommand(Alliance.Red,3,AutoMode.HOPPER));
        chooser.addObject("Do Nothing", new AutonomousCommand(Alliance.Red,1,AutoMode.BLANK));
        chooser.addDefault("Default - Blank", new AutonomousCommand(Alliance.Red,1,AutoMode.BLANK));
        
        SmartDashboard.putData("Auto mode", chooser); //Pushing the selector to the SmartDashboard
    	
    	new SmartDashboardUpdate().start(); //Starts a command that pushes sensor data to the SmartDashboard even when disabled
    }
	
	/**
     * This function is called once each time the robot becomes disabled.
     */
    public void disabledInit() {
    
    }
	
    /**
     * This function is called continuously while the robot is disabled.
     */
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This function is called once each time the robot enters autonomous.
	 */
    public void autonomousInit() {
    	
    	autonomousCommand = (Command) chooser.getSelected();

        if (autonomousCommand != null) {
//        	autonomousCommand.start();
        }
    }

    /**
     * This function is called continously while the robot is in autonomous.
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    public void teleopInit() {
    	
        if (autonomousCommand != null) autonomousCommand.cancel(); //Forces the autonomous command to end once the robot enters tele-op.
    }

    /**
     * This function is called continously while the robot is in tele-op.
     */
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
    }
    
    /**
     * This function is called continously in test mode.
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
}
