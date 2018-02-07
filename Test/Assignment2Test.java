import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by brown on 2/6/2018.
 */
class Assignment2Test {
    @Test
    void haversine() {
        assertEquals(56.327, Assignment2.haversine(39.54,-94.32,39.09,-94.58), 5.0);
        assertEquals(75.6392,Assignment2.haversine(39.76, -94.85,39.09,-94.58), 5.0);
        assertEquals(51.5,Assignment2.haversine(39.54,-94.32,39.76, -94.85), 5.0);
        assertEquals(1488.64,Assignment2.haversine(39.54,-94.32,38.89,-77.03), 5.0);
    }
}