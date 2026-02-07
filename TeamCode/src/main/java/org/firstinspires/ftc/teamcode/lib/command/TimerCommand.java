package org.firstinspires.ftc.teamcode.lib.command;

import com.qualcomm.robotcore.util.ElapsedTime;

import java.util.concurrent.TimeUnit;

public class TimerCommand extends Command {
    ElapsedTime timer;
    final int time;
    final TimeUnit unit;

    public TimerCommand(int milliseconds) {
        this(new ElapsedTime(), milliseconds, TimeUnit.MILLISECONDS);
    }

    public TimerCommand(int time, TimeUnit timeUnit) {
        this(new ElapsedTime(), time, timeUnit);
    }

    public TimerCommand(ElapsedTime timer, int milliseconds) {
        this(timer, milliseconds, TimeUnit.MILLISECONDS);
    }

    public TimerCommand(ElapsedTime timer, int time, TimeUnit timeUnit) {
        this.timer = timer;
        this.time = time;
        this.unit = timeUnit;
    }

    @Override
    public void init() {
        timer.reset();
    }

    @Override
    public boolean isCompleted() {
        return timer.time(unit) >= time;
    }

}
