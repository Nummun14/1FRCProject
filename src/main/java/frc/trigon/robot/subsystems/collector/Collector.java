package frc.trigon.robot.subsystems.collector;


import com.ctre.phoenixpro.hardware.TalonFX;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.StartEndCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Collector extends SubsystemBase {
    private final static Collector INSTANCE = new Collector();
    private final TalonFX motor = CollectorConstants.MOTOR;

    public static Collector getInstance() {
        return INSTANCE;
    }

    private Collector() {
    }

    /**
     * @return a command that collects, waits 3 seconds, ejects, waits another 3 seconds, and stops
     */
    public CommandBase getCollectThenWaitThenEjectThenWait() {
        return new SequentialCommandGroup(
                getCollectCommand().withTimeout(3),
                getEjectCommand().withTimeout(3)
        );
    }

    /**
     * @return a command that collects
     */
    public CommandBase getCollectCommand() {
        return new StartEndCommand(
                this::collect,
                this::stop,
                this
        );
    }

    /**
     * @return a command that ejects
     */
    public CommandBase getEjectCommand() {
        return new StartEndCommand(
                this::eject,
                this::stop,
                this
        );
    }

    /**
     * @return a command that collects then ejects
     */
    public CommandBase getCollectThenEjectCommand() {
        return getCollectCommand().andThen(getEjectCommand());
    }

    private void eject() {
        motor.setVoltage(CollectorConstants.EJECT_VOLTAGE);
    }

    private void collect() {
        motor.setVoltage(CollectorConstants.COLLECT_VOLTAGE);
    }

    private void stop() {
        motor.stopMotor();
    }
}

