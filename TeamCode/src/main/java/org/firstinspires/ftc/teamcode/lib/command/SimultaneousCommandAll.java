package org.firstinspires.ftc.teamcode.lib.command;

public class SimultaneousCommandAll extends SimultaneousCommand {
    public SimultaneousCommandAll(Command... commands) {
        super(commands);
    }

    @Override
    public boolean isCompleted() {
        for (Command command : commandList) {
            if (!command.isCompleted()) {
                return false;
            }
        }
        return true;
    }
}
