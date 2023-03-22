// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMax.IdleMode;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;

public class EncoderDrive extends CommandBase {
  double distance;
  double speed;
  private RelativeEncoder encoder;

  /** Creates a new EncoderDrive. */
  public EncoderDrive(double dist, double speed) {
  distance = dist;
  this.speed = speed;
  addRequirements(RobotContainer.m_drivetrain);//this was the problem the entire time!! DONT FORGET THIS
  }

  // Called when the command(is initially scheduled.
  @Override
  public void initialize() {
    encoder = RobotContainer.m_drivetrain.RFdriveEncoder;
    encoder.setPosition(0);
    RobotContainer.m_drivetrain.leftFrontSparkMax.setIdleMode(IdleMode.kBrake);
    RobotContainer.m_drivetrain.rightFrontSparkMax.setIdleMode(IdleMode.kBrake);
    RobotContainer.m_drivetrain.leftBackSparkMax.setIdleMode(IdleMode.kBrake);
    RobotContainer.m_drivetrain.rightBackSparkMax.setIdleMode(IdleMode.kBrake);
   // RobotContainer.m_drivetrain.differentialDrive.feed();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {//may need to change conditionsal
    if(distance > 0) {
    if(Math.abs(encoder.getPosition()) < Math.abs(distance)) {
      RobotContainer.m_drivetrain.arcadeDrive(speed, 0);
    }
    else {
      RobotContainer.m_drivetrain.differentialDrive.stopMotor();//may want to swap
    }
  }
  else {
    if(Math.abs(encoder.getPosition()) < Math.abs(distance)) {
      RobotContainer.m_drivetrain.arcadeDrive(-speed, 0);
    }
    else {
      RobotContainer.m_drivetrain.differentialDrive.stopMotor();//may want to swap
    }

  }
   // RobotContainer.m_drivetrain.differentialDrive.feed();
  }
  
  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    RobotContainer.m_drivetrain.differentialDrive.stopMotor();
   // RobotContainer.m_drivetrain.differentialDrive.feed();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if(Math.abs(distance) <= Math.abs(encoder.getPosition())) {
      RobotContainer.m_drivetrain.differentialDrive.stopMotor();
     // RobotContainer.m_drivetrain.differentialDrive.feed();
      return true;
    }
    else {
    //  RobotContainer.m_drivetrain.differentialDrive.feed();
      return false;
    }
  }
}
