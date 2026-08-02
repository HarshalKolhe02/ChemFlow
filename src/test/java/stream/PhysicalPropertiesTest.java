package stream;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PhysicalPropertiesTest {

    @Test
    void shouldCreatePhysicalProperties() {
        PhysicalProperties properties =
                new PhysicalProperties(298.15, 101325, 997.0, 0.001);

        assertEquals(298.15, properties.getTemperature());
        assertEquals(101325, properties.getPressure());
        assertEquals(997.0, properties.getDensity());
        assertEquals(0.001, properties.getViscosity());
    }

    @Test
    void shouldThrowExceptionForInvalidTemperature() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new PhysicalProperties(-10, 101325, 997.0, 0.001)
        );
    }

    @Test
    void shouldThrowExceptionForInvalidPressure() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new PhysicalProperties(298.15, -1, 997.0, 0.001)
        );
    }

    @Test
    void shouldThrowExceptionForInvalidDensity() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new PhysicalProperties(298.15, 101325, 0, 0.001)
        );


    }

    @Test
    void shouldThrowExceptionForInvalidViscosity() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new PhysicalProperties(298.15, 101325, 997.0, 0)
        );
    }

    @Test
    void shouldUpdateTemperature() {
        PhysicalProperties properties =
                new PhysicalProperties(298.15, 101325, 997.0, 0.001);

        properties.setTemperature(320);

        assertEquals(320, properties.getTemperature());
    }

    @Test
    void shouldUpdatePressure() {
        PhysicalProperties properties =
                new PhysicalProperties(298.15, 101325, 997.0, 0.001);

        properties.setPressure(200000);

        assertEquals(200000, properties.getPressure());
    }

    @Test
    void shouldUpdateDensity() {
        PhysicalProperties properties =
                new PhysicalProperties(298.15, 101325, 997.0, 0.001);

        properties.setDensity(950);

        assertEquals(950, properties.getDensity());
    }

    @Test
    void shouldUpdateViscosity() {
        PhysicalProperties properties =
                new PhysicalProperties(298.15, 101325, 997.0, 0.001);

        properties.setViscosity(0.002);

        assertEquals(0.002, properties.getViscosity());
    }
    @Test
    void shouldRejectNegativeTemperatureInSetter() {
        PhysicalProperties properties =
                new PhysicalProperties(298.15, 101325, 997.0, 0.001);

        assertThrows(
                IllegalArgumentException.class,
                () -> properties.setTemperature(-5)
        );
    }

    @Test
    void shouldRejectNegativePressureInSetter() {
        PhysicalProperties properties =
                new PhysicalProperties(298.15, 101325, 997.0, 0.001);

        assertThrows(
                IllegalArgumentException.class,
                () -> properties.setPressure(-10)
        );
    }
}