package org.firstinspires.ftc.teamcode.lib.command;

class MockCommand extends Command {

    int initCalls = 0;
    int loopCalls = 0;
    int loopsUntilComplete;
    boolean completed = false;

    MockCommand(int loopsUntilComplete) {
        this.loopsUntilComplete = loopsUntilComplete;
    }

    @Override
    public void init() {
        initCalls++;
    }

    @Override
    public void loop() {
        loopCalls++;
        if (loopCalls >= loopsUntilComplete) {
            completed = true;
        }
    }

    @Override
    public boolean isCompleted() {
        return completed;
    }
}
