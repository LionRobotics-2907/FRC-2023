// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;

public class GyroTurn extends CommandBase {
  public final double t_Angle;
  public final double t_Speed;
  private final AHRS gyro;
  /** Creates a new GyroTurn. */
  public GyroTurn(double angle, double speed) {
    // Use addRequirements() here to declare subsystem dependencies.
    gyro = RobotContainer.m_drivetrain.ahrs;
    t_Angle = angle;
    t_Speed = speed;
    addRequirements(RobotContainer.m_drivetrain);

  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    gyro.reset();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double error = gyro.getYaw();
    if(Math.abs(t_Angle) > Math.abs(error)) {
        RobotContainer.m_drivetrain.differentialDrive.arcadeDrive(0,t_Speed);
    }
    else{
      RobotContainer.m_drivetrain.arcadeDrive(0,0);
    }
    }

  

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if(Math.abs(t_Angle) > Math.abs(gyro.getYaw())) {
      return false;
    }
    else {
      RobotContainer.m_drivetrain.arcadeDrive(0,0);
      return true;
    }
    }
    
  }

