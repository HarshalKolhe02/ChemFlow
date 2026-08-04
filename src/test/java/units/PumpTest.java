package units;

import org.junit.jupiter.api.Test;
import stream.Component;
import stream.MaterialStream;
import stream.PhysicalProperties;

import static org.junit.jupiter.api.Assertions.*;

class PumpTest {

    @Test
    void shouldCreatePump() {
        Pump pump = new Pump(
                "P101",
                "Feed Pump",
                500000
        );

        assertEquals("P101", pump.getId());
        assertEquals("Feed Pump", pump.getName());
        assertEquals(500000, pump.getPressureIncrease());
    }

    @Test
    void shouldRejectBlankId() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Pump(
                        "",
                        "Feed Pump",
                        500000
                )
        );
    }

    @Test
    void shouldRejectBlankName() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Pump(
                        "P101",
                        "",
                        500000
                )
        );
    }

    @Test
    void shouldRejectInvalidPressureIncrease() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Pump(
                        "P101",
                        "Feed Pump",
                        -1
                )
        );
    }

    @Test
    void shouldIncreasePressure() {

        PhysicalProperties properties =
                new PhysicalProperties(
                        298.15,
                        101325,
                        997,
                        0.001
                );

        MaterialStream inlet =
                new MaterialStream(
                        "S101",
                        "Feed",
                        100,
                        properties
                );

        Component water =
                new Component(
                        "Water",
                        "H2O",
                        18.015
                );

        inlet.addComponent(water, 1.0);

        Pump pump =
                new Pump(
                        "P101",
                        "Feed Pump",
                        500000
                );

        MaterialStream outlet = pump.process(inlet);

        assertEquals(
                601325,
                outlet.getProperties().getPressure()
        );

        assertEquals(
                inlet.getMassFlowRate(),
                outlet.getMassFlowRate()
        );

        assertEquals(
                inlet.getProperties().getTemperature(),
                outlet.getProperties().getTemperature()
        );

        assertEquals(
                inlet.getProperties().getDensity(),
                outlet.getProperties().getDensity()
        );

        assertEquals(
                inlet.getProperties().getViscosity(),
                outlet.getProperties().getViscosity()
        );

        assertEquals(
                1.0,
                outlet.getMassFraction(water)
        );
    }

    @Test
    void shouldNotModifyInletStream() {

        PhysicalProperties properties =
                new PhysicalProperties(
                        298.15,
                        101325,
                        997,
                        0.001
                );

        MaterialStream inlet =
                new MaterialStream(
                        "S101",
                        "Feed",
                        100,
                        properties
                );

        Pump pump =
                new Pump(
                        "P101",
                        "Feed Pump",
                        500000
                );

        pump.process(inlet);

        assertEquals(
                101325,
                inlet.getProperties().getPressure()
        );
    }
}