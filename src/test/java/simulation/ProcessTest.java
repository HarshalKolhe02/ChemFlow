package simulation;

import org.junit.jupiter.api.Test;
import stream.MaterialStream;
import stream.PhysicalProperties;
import units.Pump;
import units.UnitOpreation;
import simulation.Process;
import static org.junit.jupiter.api.Assertions.*;

class ProcessTest {

    private MaterialStream createStream(String id) {

        PhysicalProperties properties =
                new PhysicalProperties(
                        298.15,
                        101325,
                        997,
                        0.001
                );

        return new MaterialStream(
                id,
                "Test Stream",
                100,
                properties
        );
    }

    @Test
    void shouldAddAndRetrieveStream() {

        Process process = new Process();

        MaterialStream stream = createStream("S101");

        process.addStream(stream);

        assertSame(
                stream,
                process.getStream("S101")
        );
    }

    @Test
    void shouldRejectDuplicateStream() {

        Process process = new Process();

        process.addStream(createStream("S101"));

        assertThrows(
                IllegalArgumentException.class,
                () -> process.addStream(createStream("S101"))
        );
    }

    @Test
    void shouldRejectNullStream() {

        Process process = new Process();

        assertThrows(
                IllegalArgumentException.class,
                () -> process.addStream(null)
        );
    }

    @Test
    void shouldAddAndRetrieveUnit() {

        Process process = new Process();

        UnitOpreation pump =
                new Pump(
                        "P101",
                        "Feed Pump",
                        50000
                );

        process.addUnit(pump);

        assertSame(
                pump,
                process.getUnit("P101")
        );
    }

    @Test
    void shouldRejectDuplicateUnit() {

        Process process = new Process();

        process.addUnit(
                new Pump("P101", "Feed Pump", 50000)
        );

        assertThrows(
                IllegalArgumentException.class,
                () -> process.addUnit(
                        new Pump("P101", "Another Pump", 30000)
                )
        );
    }

    @Test
    void shouldRejectNullUnit() {

        Process process = new Process();

        assertThrows(
                IllegalArgumentException.class,
                () -> process.addUnit(null)
        );
    }

    @Test
    void shouldReturnNullForUnknownStream() {

        Process process = new Process();

        assertNull(
                process.getStream("UNKNOWN")
        );
    }

    @Test
    void shouldReturnNullForUnknownUnit() {

        Process process = new Process();

        assertNull(
                process.getUnit("UNKNOWN")
        );
    }
}