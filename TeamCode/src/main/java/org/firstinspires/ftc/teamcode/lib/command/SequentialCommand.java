package org.firstinspires.ftc.teamcode.lib.command;

import java.util.Arrays;

public class SequentialCommand extends Command {
    private final CommandScheduler commandScheduler = new CommandScheduler();

    public SequentialCommand(Command... commands) {
        commandScheduler.addAll(Arrays.asList(commands));
    }

    @Override
    public void loop() {
        commandScheduler.run();

    }

    @Override
    public boolean isCompleted() {
        return commandScheduler.isCompleted();
    }
}
