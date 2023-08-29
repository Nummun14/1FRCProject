package frc.trigon.robot.subsystems.turret;


import com.ctre.phoenixpro.controls.VoltageOut;
import com.ctre.phoenixpro.hardware.TalonFX;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.FunctionalCommand;
import edu.wpi.first.wpilibj2.command.StartEndCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import java.util.function.Supplier;

public class Turret extends SubsystemBase {

    private final TalonFX motor = TurretConstants.MOTOR;

    private final static Turret INSTANCE = new Turret();


    public static Turret getInstance() {
        return INSTANCE;
    }


    private Turret() {
    }

    /**
     * Creates a command that checks if there's a visible target, if there isn't, send a voltage request of 5. if there is, calculate the PID power to align to the target.
     *
     * @param reflectorPixelSupplier the distance from the pixel
     * @param hasTarget              if it can see a target
     * @return the command
     */
    public CommandBase getAlignToReflectorCommand(Supplier<Double> reflectorPixelSupplier, Supplier<Boolean> hasTarget) {
        return new FunctionalCommand(
                () -> {
                },
                () -> turretCalculate(reflectorPixelSupplier.get(), hasTarget.get()),
                (interrupted) -> stop(),
                () -> false,
                this
        );
    }

    private void turretCalculate(double reflectorPixel, boolean hasTarget) {
        if (!hasTarget) {
            VoltageOut request = new VoltageOut(5);
            motor.setControl(request);
            return;
        }
        double output = TurretConstants.PID_CONTROLLER.calculate(reflectorPixel);
        VoltageOut request = new VoltageOut(output);
        motor.setControl(request);
    }

    private void stop() {
        motor.stopMotor();
    }
}

