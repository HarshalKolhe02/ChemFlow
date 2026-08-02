package stream;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class MaterialStreamTest {

    @Test
    void shouldCreateMaterialStream() {
        PhysicalProperties properties =
                new PhysicalProperties(298.15, 101325, 997, 0.001);

        MaterialStream stream =
                new MaterialStream(
                        "S-101",
                        "Feed Stream",
                        100.0,
                        properties
                );

        assertEquals("S-101", stream.getId());
        assertEquals("Feed Stream", stream.getName());
        assertEquals(100.0, stream.getMassFlowRate());
        assertEquals(properties, stream.getProperties());
        assertTrue(stream.getComposition().isEmpty());
    }

    @Test
    void shouldRejectBlankId() {
        PhysicalProperties properties =
                new PhysicalProperties(298.15, 101325, 997, 0.001);

        assertThrows(
                IllegalArgumentException.class,
                () -> new MaterialStream(
                        "",
                        "Feed",
                        100,
                        properties
                )
        );
    }

    @Test
    void shouldRejectBlankName() {
        PhysicalProperties properties =
                new PhysicalProperties(298.15, 101325, 997, 0.001);

        assertThrows(
                IllegalArgumentException.class,
                () -> new MaterialStream(
                        "S-101",
                        "",
                        100,
                        properties
                )
        );
    }

    @Test
    void shouldRejectNegativeMassFlowRate() {
        PhysicalProperties properties =
                new PhysicalProperties(298.15, 101325, 997, 0.001);

        assertThrows(
                IllegalArgumentException.class,
                () -> new MaterialStream(
                        "S-101",
                        "Feed",
                        -10,
                        properties
                )
        );
    }

    @Test
    void shouldRejectNullPhysicalProperties() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new MaterialStream(
                        "S-101",
                        "Feed",
                        100,
                        null
                )
        );
    }

    @Test
    void shouldAddComponent() {
        PhysicalProperties properties =
                new PhysicalProperties(298.15, 101325, 997, 0.001);

        MaterialStream stream =
                new MaterialStream(
                        "S-101",
                        "Feed",
                        100,
                        properties
                );

        Component water =
                new Component("Water", "H2O", 18.015);

        stream.addComponent(water, 1.0);

        assertEquals(1.0, stream.getMassFraction(water));
    }

    @Test
    void shouldRejectDuplicateComponent() {
        PhysicalProperties properties =
                new PhysicalProperties(298.15, 101325, 997, 0.001);

        MaterialStream stream =
                new MaterialStream(
                        "S-101",
                        "Feed",
                        100,
                        properties
                );

        Component water =
                new Component("Water", "H2O", 18.015);

        stream.addComponent(water, 1.0);

        assertThrows(
                IllegalArgumentException.class,
                () -> stream.addComponent(water, 0.5)
        );
    }

    @Test
    void shouldRemoveComponent() {
        PhysicalProperties properties =
                new PhysicalProperties(298.15, 101325, 997, 0.001);

        MaterialStream stream =
                new MaterialStream(
                        "S-101",
                        "Feed",
                        100,
                        properties
                );

        Component water =
                new Component("Water", "H2O", 18.015);

        stream.addComponent(water, 1.0);

        stream.removeComponent(water);

        assertEquals(0.0, stream.getMassFraction(water));
    }

    @Test
    void shouldValidateComposition() {
        PhysicalProperties properties =
                new PhysicalProperties(298.15, 101325, 997, 0.001);

        MaterialStream stream =
                new MaterialStream(
                        "S-101",
                        "Feed",
                        100,
                        properties
                );

        Component water =
                new Component("Water", "H2O", 18.015);

        Component ethanol =
                new Component("Ethanol", "C2H5OH", 46.07);

        stream.addComponent(water, 0.7);
        stream.addComponent(ethanol, 0.3);

        assertTrue(stream.validateComposition());
    }

    @Test
    void shouldRejectInvalidComposition() {
        PhysicalProperties properties =
                new PhysicalProperties(298.15, 101325, 997, 0.001);

        MaterialStream stream =
                new MaterialStream(
                        "S-101",
                        "Feed",
                        100,
                        properties
                );

        Component water =
                new Component("Water", "H2O", 18.015);

        stream.addComponent(water, 0.6);

        assertFalse(stream.validateComposition());
    }

    @Test
    void shouldReturnImmutableCompositionMap() {
        PhysicalProperties properties =
                new PhysicalProperties(298.15, 101325, 997, 0.001);

        MaterialStream stream =
                new MaterialStream(
                        "S-101",
                        "Feed",
                        100,
                        properties
                );

        assertThrows(
                UnsupportedOperationException.class,
                () -> stream.getComposition().clear()
        );
    }
}