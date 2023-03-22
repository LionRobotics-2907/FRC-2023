// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.GearChange;

public class SwitchGear extends CommandBase {
  private final GearChange gearChange;
  /** Creates a new SwitchGear. */
  public SwitchGear(GearChange gearchange) {
    // Use addRequirements() here to declare subsystem dependencies.
    gearChange = gearchange;
    addRequirements(RobotContainer.m_gearchange);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    gearChange.switchGear();//may need to move this to execute 3/21/23
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return true;
  }
}
