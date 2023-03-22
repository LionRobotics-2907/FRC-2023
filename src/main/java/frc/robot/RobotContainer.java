// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;


import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.PS4Controller; //Remember we may want to switch to gamepad instead of ps4
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.PS4Controller.Button;
import edu.wpi.first.wpilibj.event.BooleanEvent;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
//import edu.wpi.first.wpilibj.XboxController;
import frc.robot.commands.DriveArcade;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.GearChange;
import frc.robot.subsystems.Gripper;
import frc.robot.subsystems.Winch;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.commands.EncoderDrive;
import frc.robot.commands.GyroTurn;
import frc.robot.commands.RunWinch;
import frc.robot.commands.AutoTest;
import frc.robot.commands.fixit;
import frc.robot.commands.SimpleAuto;
import frc.robot.commands.SwitchGear;
import frc.robot.commands.UseGripper;
/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  public static final Drivetrain m_drivetrain = new Drivetrain();
  public static final GearChange m_gearchange = new GearChange();
  public static final Winch m_winch = new Winch();
  public static final Gripper m_gripper = new Gripper();
  public static final UseGripper useGripper = new UseGripper(m_gripper,0);
  public static final RunWinch runwinch = new RunWinch(m_winch, 0);
  public static PS4Controller driverController = new PS4Controller(Constants.DRIVER_CONTROLLER);
  public static PS4Controller maniController = new PS4Controller(Constants.MANI_CONTROLLER);
  public static final EncoderDrive encoderDrive = new EncoderDrive(0,0);
  public static final GyroTurn gyroTurn = new GyroTurn(0,0);
  public static final AutoTest autoTest = new AutoTest();
  public static final SimpleAuto simpleAuto = new SimpleAuto();
  public static final Trigger triangleButton = new JoystickButton(driverController, 0);

  public static SendableChooser<Command> chooser = new SendableChooser<>();
  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the button bindings

    configureButtonBindings();
     //setting options for auto paths
     chooser.addOption("In Front of ChargeStation", autoTest);
     chooser.setDefaultOption("Not in Front of Charge Station", simpleAuto);
     SmartDashboard.putData(chooser);
    //set default commands on subsystem
    //m_drivetrain.setDefaultCommand(new DriveArcade());
    m_drivetrain.setDefaultCommand(new fixit( () -> -driverController.getLeftY(), () -> -driverController.getRightX()));
    //m_drivetrain.setDefaultCommand(new fixit(m_drivetrain, () -> driverController.getLeftX(), () -> driverController.getRightY()));
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    //for gear change
   //final JoystickButton d_triangle = new JoystickButton(maniController,Button.kTriangle.value);
   //for gripper
   final JoystickButton d_square = new JoystickButton(maniController, Button.kSquare.value);
   final JoystickButton d_circle = new JoystickButton(maniController, Button.kCircle.value);
   //for winch
   final JoystickButton d_triangle = new JoystickButton(maniController, Button.kTriangle.value);
   final JoystickButton d_cross = new JoystickButton(maniController, Button.kCross.value);

   //d_triangle.whileTrue(new SwitchGear(m_gearchange));

  //button bindings for gripper
   d_square.whileTrue(new UseGripper(m_gripper, 0.2));
   d_circle.whileTrue(new UseGripper(m_gripper,-0.2));
   //button bindings for winch
   d_triangle.whileTrue(new RunWinch(m_winch, 0.2));
   d_cross.whileTrue(new RunWinch(m_winch,-0.2));

  // new JoystickButton(driverController,PS4Controller.Button.kTriangle.value).whileTrue(new SwitchGear(m_gearchange));
 //  new JoystickButton(driverController, PS4Controller.Button.kSquare.value).whileTrue(new UseGripper(m_gripper, 0.2));
 //  new JoystickButton(driverController, PS4Controller.Button.kCircle.value).whileTrue(new UseGripper(m_gripper,-0.2));


  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public static Command getAutonomousCommand() {
   //  An ExampleCommand will run in autonomous
   return chooser.getSelected();
  }

  // jake did this
  private final Solenoid solenoid = new Solenoid(PneumaticsModuleType.REVPH,
    Constants.SOLENOID);
    
  private boolean toggledSolenoid = false;

  public void toggleSolenoid() {
    toggleSolenoid(!toggledSolenoid);
  }

  public void toggleSolenoid(boolean state) {
    toggledSolenoid = state;
    solenoid.set(toggledSolenoid);
  }
}
