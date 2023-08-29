package frc.trigon.robot.subsystems.steer;


import com.ctre.phoenixpro.controls.PositionVoltage;
import com.ctre.phoenixpro.hardware.TalonFX;
import edu.wpi.first.wpilibj2.command.*;

import java.util.function.Supplier;

public class Steer extends SubsystemBase {


    private final static Steer INSTANCE = new Steer();

    private final TalonFX motor = frc.trigon.robot.subsystems.steer.SteerConstants.MOTOR;

    public static Steer getInstance() {
        return INSTANCE;
    }


    private Steer() {

    }

    /**
     * Creates a command that gets a supplier and sets the target angle to its value.
     *
     * @param angleSupplier a supplier of the target angle
     * @return the command
     */
    public CommandBase getSetTargetAngleCommand(Supplier<Double> angleSupplier) {
        return new FunctionalCommand(
                () -> {
                },
                () -> setTargetAngle(angleSupplier.get()),
                (interrupted) -> stop(),
                () -> false,
                this
        );
    }

    /**
     * Creates a command that sets the target angle.
     *
     * @param targetAngle the target angle
     * @return the command
     */
    public CommandBase getSetTargetAngleCommand(double targetAngle) {
        return new StartEndCommand(
                () -> setTargetAngle(targetAngle),
                this::stop,
                this
        );
    }

    /**
     * @return a command that turns to 90 degrees, waits 3 seconds, then turns to 180, waits 3 seconds, and turns back to 0
     */
    public CommandBase getAngleSequenceCommand() {
        return new SequentialCommandGroup(
                getSetTargetAngleCommand(90).withTimeout(3),
                getSetTargetAngleCommand(90).withTimeout(3),
                getSetTargetAngleCommand(180)
        );
    }

    private void setTargetAngle(double angleDegrees) {
        double systemRevs = angleDegrees / 360;
        double motorRevs = systemRevs * SteerConstants.GEAR_RATIO;
        PositionVoltage request = new PositionVoltage(motorRevs);
        motor.setControl(request);
    }

    private void stop() {
        motor.stopMotor();
    }
}

