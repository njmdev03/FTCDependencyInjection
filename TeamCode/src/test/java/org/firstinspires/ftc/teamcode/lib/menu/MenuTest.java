package org.firstinspires.ftc.teamcode.lib.menu;

import org.firstinspires.ftc.teamcode.mocks.MockElapsedTime;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.concurrent.TimeUnit;

class MenuTest {

    @Test
    void selectionMovesForward() {
        MockElapsedTime t1 = new MockElapsedTime();
        MockElapsedTime t2 = new MockElapsedTime();
        Menu menu = new Menu(t1, t2);

        DoubleMenuItem a = new DoubleMenuItem(0, 1, "A");
        DoubleMenuItem b = new DoubleMenuItem(0, 1, "B");

        menu.add(a, b);

        menu.itemSelection(false, true, false, false);

        String report = menu.reportMenuItemValue();

        System.out.println("Actual Output:\n" + report);

        assertTrue(report.startsWith("> B"));
    }

    @Test
    void selectionWrapsAroundForward() {
        MockElapsedTime t1 = new MockElapsedTime();
        MockElapsedTime t2 = new MockElapsedTime();
        Menu menu = new Menu(t1, t2);

        menu.add(
                new DoubleMenuItem(0,1,"A"),
                new DoubleMenuItem(0,1,"B")
        );

        menu.itemSelection(false, true, false, false);
        menu.itemSelection(false, true, false, false);

        String report = menu.reportMenuItemValue();

        System.out.println("Actual Output:\n" + report);

        assertTrue(report.startsWith("> A"));
    }

    @Test
    void valueChangesOnSinglePress() {
        MockElapsedTime t1 = new MockElapsedTime();
        MockElapsedTime t2 = new MockElapsedTime();
        Menu menu = new Menu(t1, t2);

        DoubleMenuItem item = new DoubleMenuItem(0, 1, "A");
        menu.add(item);

        menu.itemSelection(false, false, true, false);

        assertEquals(1.0, item.getValue(), 1e-9);
    }

    @Test
    void holdRepeatsAfterDelay() {
        MockElapsedTime t1 = new MockElapsedTime();
        MockElapsedTime t2 = new MockElapsedTime();
        Menu menu = new Menu(t1, t2);

        DoubleMenuItem item = new DoubleMenuItem(0, 1, "A");
        menu.add(item);

        // First press
        menu.itemSelection(false, false, true, false);
        assertEquals(1.0, item.getValue());

        // Simulate holding past 1 second
        t1.setTime(1001, TimeUnit.MILLISECONDS);

        menu.itemSelection(false, false, true, false);
        assertEquals(2.0, item.getValue());
    }
}
