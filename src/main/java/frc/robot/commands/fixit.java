// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import java.util.function.DoubleSupplier;

public class fixit extends CommandBase {
    private final DoubleSupplier m_forward;
    private final DoubleSupplier m_rotation;
  /** Creates a new fixit. */
  public fixit(DoubleSupplier forward, DoubleSupplier rotation) {
    m_forward = forward;
    m_rotation = rotation;
    addRequirements(RobotContainer.m_drivetrain);
  }

  // Called when the command is initially scheduled.

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    RobotContainer.m_drivetrain.arcadeDrive(m_forward.getAsDouble(),m_rotation.getAsDouble());
  }

  // Called once the command ends or is interrupted.
  public void end(boolean interrupted) {
    RobotContainer.m_drivetrain.arcadeDrive(0,0);
  }

  public boolean isFinished() {return false;}

}
