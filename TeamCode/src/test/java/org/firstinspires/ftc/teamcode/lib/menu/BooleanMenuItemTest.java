package org.firstinspires.ftc.teamcode.lib.menu;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BooleanMenuItemTest {

    @Test
    void setsTrueOnUp() {
        BooleanMenuItem item = new BooleanMenuItem(false, "Bool");

        item.stateChange(true, false);
        item.valueChange();

        assertTrue(item.getValue());
    }

    @Test
    void setsFalseOnDown() {
        BooleanMenuItem item = new BooleanMenuItem(true, "Bool");

        item.stateChange(false, true);
        item.valueChange();

        assertFalse(item.getValue());
    }
}
