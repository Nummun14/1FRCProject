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
     * creates a command that sets the voltage for the right motors of the robot from the given supplier.
     * @param voltage a supplier of the voltage
     * @return the command
     */
    public CommandBase getRightSide(Supplier<Double> voltage){
        return new FunctionalCommand(
                () -> {},
                () -> rightMotorVoltage(voltage.get()),
                (interrupted) -> stop(),
                () -> false,
                this
        );
    }

    /**
     * creates a command that sets the voltage for the left motors of the robot from the given supplier.
     * @param voltage a supplier of the voltage
     * @return the command
     */
    public CommandBase getLeftSide(Supplier<Double> voltage){
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

