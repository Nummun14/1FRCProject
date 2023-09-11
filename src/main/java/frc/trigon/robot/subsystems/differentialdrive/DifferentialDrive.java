package frc.trigon.robot.subsystems.differentialdrive;


import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.FunctionalCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import java.util.function.Supplier;

public class DifferentialDrive extends SubsystemBase {
    private final edu.wpi.first.wpilibj.drive.DifferentialDrive DifferentialDrive1 = DifferentialDriveConstants.DIFFERENTIAL_DRIVE1;
    private final static DifferentialDrive INSTANCE = new DifferentialDrive();

    public static DifferentialDrive getInstance() {
        return INSTANCE;
    }

    private DifferentialDrive() {
    }

    /**
     * gets a supplier of the forward speed, and a supplier of the turn speed, and uses it in arcade drive.
     *
     * @param forwardSpeed a supplier of the forward speed
     * @param turnSpeed    a supplier of the turn speed
     */
    public CommandBase arcadeDriveCommand(Supplier<Double> forwardSpeed, Supplier<Double> turnSpeed) {
        return new FunctionalCommand(
                () -> {
                },
                () -> arcadeDrive(forwardSpeed.get(), turnSpeed.get()),
                (interrupted) -> stop(),
                () -> false,
                this
        );
    }

    /**
     * gets a supplier of the left side and a supplier of the right side, and then uses it in tank drive.
     *
     * @param leftStick  a supplier of the left side
     * @param rightStick a supplier of the right side
     */
    public CommandBase tankDriveCommand(Supplier<Double> rightStick, Supplier<Double> leftStick) {
        return new FunctionalCommand(
                () -> {
                },
                () -> tankDrive(leftStick.get(), rightStick.get()),
                (interrupted) -> stop(),
                () -> false,
                this
        );
    }

    /**
     * gets a supplier of the forward speed, a supplier of the turn speed, and a supplier that checks if stationaryTurn is being used and uses it in arcade drive.
     *
     * @param forward        a supplier of the forward speed
     * @param turn           a supplier of the turn speed
     * @param stationaryTurn a supplier of that checks if stationary drive is being used
     * @return the command
     */
    public CommandBase curvatureDriveCommand(Supplier<Double> forward, Supplier<Double> turn, Supplier<Boolean> stationaryTurn) {
        return new FunctionalCommand(
                () -> {
                },
                () -> curvatureDrive(forward.get(), turn.get(), stationaryTurn.get()),
                (interrupted) -> stop(),
                () -> false,
                this
        );
    }

    private void arcadeDrive(double forward, double turn) {
        DifferentialDrive1.arcadeDrive(forward, turn);
    }

    private void tankDrive(double leftStick, double rightStick) {
        DifferentialDrive1.tankDrive(leftStick, rightStick);
    }

    private void curvatureDrive(double forward, double turn, boolean stationaryTurn) {
        DifferentialDrive1.curvatureDrive(forward, turn, stationaryTurn);
    }

    private void stop() {
        DifferentialDrive1.stopMotor();
    }


}

