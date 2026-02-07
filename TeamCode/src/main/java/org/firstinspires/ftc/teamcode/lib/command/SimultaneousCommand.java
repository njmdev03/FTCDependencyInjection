package org.firstinspires.ftc.teamcode.lib.command;

import java.util.Arrays;

public class SimultaneousCommand extends Command {
    final CommandList commandList = new CommandList();

    public SimultaneousCommand(Command... commands) {
        commandList.addAll(Arrays.asList(commands));
    }

    @Override
    public void init() {
        for (Command command : commandList) {
            command.init();
        }
    }

    @Override
    public void loop() {
        for (Command command : commandList) {
                command.loop();
        }
    }

    @Override
    public boolean isCompleted() {
        for (Command command : commandList) {
            if (command.isCompleted()) {
                return true;
            }
        }
        return false;
    }
}
