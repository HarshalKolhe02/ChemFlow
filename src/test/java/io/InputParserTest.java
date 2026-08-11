package io;

import org.junit.jupiter.api.Test;
import stream.MaterialStream;
import stream.PhysicalProperties;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InputParserTest {

    @Test
    void shouldLoadAllStreams() throws IOException {

        InputParser parser = new InputParser();

        List<MaterialStream> streams =
                parser.loadStreams(
                        Path.of("input/streams.csv")
                );

        assertEquals(3, streams.size());
    }

    @Test
    void shouldLoadFirstStreamCorrectly() throws IOException {

        InputParser parser = new InputParser();

        List<MaterialStream> streams =
                parser.loadStreams(
                        Path.of("input/streams.csv")
                );

        MaterialStream stream = streams.get(0);

        assertEquals("S101", stream.getId());
        assertEquals("Feed", stream.getName());
        assertEquals(100.0, stream.getMassFlowRate(), 1e-6);
    }

    @Test
    void shouldLoadPhysicalPropertiesCorrectly() throws IOException {

        InputParser parser = new InputParser();

        List<MaterialStream> streams =
                parser.loadStreams(
                        Path.of("input/streams.csv")
                );

        PhysicalProperties properties =
                streams.get(0).getProperties();

        assertEquals(298.15, properties.getTemperature(), 1e-6);
        assertEquals(101325.0, properties.getPressure(), 1e-6);
        assertEquals(997.0, properties.getDensity(), 1e-6);
        assertEquals(0.001, properties.getViscosity(), 1e-6);
    }

    @Test
    void shouldLoadAllStreamValuesCorrectly() throws IOException {

        InputParser parser = new InputParser();

        List<MaterialStream> streams =
                parser.loadStreams(
                        Path.of("input/streams.csv")
                );

        // S101
        assertEquals("S101", streams.get(0).getId());
        assertEquals("Feed", streams.get(0).getName());
        assertEquals(100.0, streams.get(0).getMassFlowRate(), 1e-6);

        // S102
        assertEquals("S102", streams.get(1).getId());
        assertEquals("Product", streams.get(1).getName());
        assertEquals(50.0, streams.get(1).getMassFlowRate(), 1e-6);

        // S103
        assertEquals("S103", streams.get(2).getId());
        assertEquals("Coolant", streams.get(2).getName());
        assertEquals(25.0, streams.get(2).getMassFlowRate(), 1e-6);
    }
}