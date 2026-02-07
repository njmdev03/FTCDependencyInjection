package org.firstinspires.ftc.teamcode.lib.menu;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DoubleMenuItemTest {

    @Test
    void increasesValueOnUp() {
        DoubleMenuItem item = new DoubleMenuItem(5.0, 1.0, "Test");

        item.stateChange(true, false);
        item.valueChange();

        assertEquals(6.0, item.getValue(), 1e-9);
        assertEquals(MenuItem.State.MIDDLE, item.getState());
    }

    @Test
    void decreasesValueOnDown() {
        DoubleMenuItem item = new DoubleMenuItem(5.0, 1.0, "Test");

        item.stateChange(false, true);
        item.valueChange();

        assertEquals(4.0, item.getValue(), 1e-9);
    }

    @Test
    void formatsValueCorrectly() {
        DoubleMenuItem item = new DoubleMenuItem(5.6789, 1.0, "Test");
        assertEquals("5.68", item.getStringValue());
    }
}
