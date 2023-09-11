package frc.trigon.robot.subsystems.differentialdrive;


import com.ctre.phoenixpro.controls.VoltageOut;
import com.ctre.phoenixpro.hardware.TalonFX;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.FunctionalCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import java.util.function.Supplier;

public class DifferentialDrive extends SubsystemBase {
    private final TalonFX motor1 = DifferentialDriveConstants.MOTOR1;
    private final TalonFX motor2 = DifferentialDriveConstants.MOTOR2;
    private final TalonFX motor3 = DifferentialDriveConstants.MOTOR3;
    private final TalonFX motor4 = DifferentialDriveConstants.MOTOR4;
    private final edu.wpi.first.wpilibj.drive.DifferentialDrive DifferentialDrive1 = DifferentialDriveConstants.DIFFERENTIAL_DRIVE1;
    private final edu.wpi.first.wpilibj.drive.DifferentialDrive DifferentialDrive2 = DifferentialDriveConstants.DIFFERENTIAL_DRIVE2;
    private final static DifferentialDrive INSTANCE = new DifferentialDrive();
    public static DifferentialDrive getInstance() {
        return INSTANCE;
    }

    private DifferentialDrive() {
    }

    /**
     * gets a supplier of the forward speed, and a supplier of the turn speed, and uses it in arcade drive.
     * @param forward a supplier of the forward speed
     * @param turn a supplier of the turn speed
     */
    public CommandBase arcadeDrive(Supplier<Double> forward, Supplier<Double> turn){
        return new FunctionalCommand(
                () -> {},
                () -> arcadeDrive(forward.get(), turn.get()),
                (interrupted) -> stop(),
                () -> false,
                this
        );
    }

    /**
     * gets a supplier of the left side and a supplier of the right side, and then uses it in tank drive.
     * @param leftStick a supplier of the left side
     * @param rightStick a supplier of the right side
     */
    public CommandBase tankDrive(Supplier<Double> rightStick, Supplier<Double> leftStick){
        return new FunctionalCommand(
                () -> {},
                () -> tankDrive(rightStick.get(), leftStick.get()),
                (interrupted) -> stop(),
                () -> false,
                this
        );
    }

    private void arcadeDrive(double forward, double turn){
        DifferentialDrive1.arcadeDrive(forward, turn);
        DifferentialDrive2.arcadeDrive(forward,turn);
    }

    private void tankDrive(double leftStick, double rightStick){
        DifferentialDrive1.tankDrive(leftStick, rightStick);
        DifferentialDrive2.tankDrive(leftStick, rightStick);
    }

    private void stop(){
        DifferentialDrive1.stopMotor();
        DifferentialDrive2.stopMotor();
    }






}

