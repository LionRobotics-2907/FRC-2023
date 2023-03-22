// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import com.kauailabs.navx.frc.AHRS;
import frc.robot.RobotContainer;
import frc.robot.autoBalance;



public class AutoBalance extends CommandBase {
  private autoBalance autobalance;

  /** Creates a new AutoBalance. */
  public AutoBalance() {

    //autobalance = new autoBalance();
    addRequirements(RobotContainer.m_drivetrain);
  }



  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    autobalance = new autoBalance();

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    double speed = autobalance.autoBalanceRoutine();
    RobotContainer.m_drivetrain.arcadeDrive(-speed,0);





  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
