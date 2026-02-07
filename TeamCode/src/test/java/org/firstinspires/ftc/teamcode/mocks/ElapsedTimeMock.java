package org.firstinspires.ftc.teamcode.mocks;

import com.qualcomm.robotcore.util.ElapsedTime;

import java.util.concurrent.TimeUnit;

/**
 * Fake ElapsedTime for controlled testing
 */
public class ElapsedTimeMock extends ElapsedTime {
    private long fakeTime;

    public void setTime(long value, TimeUnit unit) {
        fakeTime = TimeUnit.NANOSECONDS.convert(value, unit);
    }

    @Override
    public long time(TimeUnit unit) {
        return unit.convert(fakeTime, TimeUnit.NANOSECONDS);
    }

    @Override
    public void reset() {
        fakeTime = 0;
    }
}