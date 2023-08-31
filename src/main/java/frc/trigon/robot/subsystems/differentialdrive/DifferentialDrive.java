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
    private final static DifferentialDrive INSTANCE = new DifferentialDrive();

    public static DifferentialDrive getInstance() {
        return INSTANCE;
    }

    private DifferentialDrive() {
    }

    /**
     * Creates a command that receives a right motors voltage and a left motors voltage and applies the voltage.
     * @param rightVoltage the supplier of the right motor voltage
     * @param leftVoltage the supplier of the left motor voltage
     * @return the command
     */
    public CommandBase tankDrive(Supplier<Double> rightVoltage, Supplier<Double> leftVoltage){
        return new FunctionalCommand(
                () -> {},
                () -> getLeftSide(leftVoltage).andThen(getRightSide(rightVoltage)),
                (interrupted) -> stop(),
                () -> false,
                this
        );
    }

    /**
     * Creates a command that receives a voltage and if it should be applied to the right or left motors and does so according to the inputted parameters.
     * @param voltage a supplier of the voltage to be applied
     * @param isRight a supplier of whether the voltage should be applied to the right motors or not
     * @return the command
     */
    public CommandBase arcadeDrive(Supplier<Double> voltage, Supplier<Boolean> isRight){
        if (isRight.get()){
            return new FunctionalCommand(
                    () -> {},
                    () -> getRightSide(voltage),
                    (interrupted) -> stop(),
                    () -> false,
                    this
            );
        } else {
            return new FunctionalCommand(
                    () -> {},
                    () -> getLeftSide(voltage),
                    (interrupted) -> stop(),
                    () -> false,
                    this
            );
        }
    }

    private CommandBase getRightSide(Supplier<Double> voltage){
        return new FunctionalCommand(
                () -> {},
                () -> rightMotorVoltage(voltage.get()),
                (interrupted) -> stop(),
                () -> false,
                this
        );
    }

    private CommandBase getLeftSide(Supplier<Double> voltage){
        return new FunctionalCommand(
                () -> {},
                () -> leftMotorVoltage(voltage.get()),
                (interrupted) -> stop(),
                () -> false,
                this
        );
    }

    private void rightMotorVoltage(double voltage){
        VoltageOut request = new VoltageOut(voltage);
        motor1.setControl(request);
        motor2.setControl(request);
    }

    private void leftMotorVoltage(double voltage){
        VoltageOut request = new VoltageOut(voltage);
        motor3.setControl(request);
        motor4.setControl(request);
    }

    private void stop(){
        motor1.stopMotor();
        motor2.stopMotor();
        motor3.stopMotor();
        motor4.stopMotor();
    }
}

