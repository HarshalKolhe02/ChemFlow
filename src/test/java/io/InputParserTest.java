package io;

import org.junit.jupiter.api.Test;
import stream.Component;
import stream.MaterialStream;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CompositionParserTest {

    @Test
    void shouldLoadCompositionsCorrectly() throws IOException {

        InputParser parser = new InputParser();

        // Load components
        List<Component> components =
                parser.loadComponents(
                        Path.of("input/component.csv")
                );

        // Load streams
        List<MaterialStream> streams =
                parser.loadStreams(
                        Path.of("input/streams.csv")
                );

        // Load compositions
        parser.loadCompositions(
                Path.of("input/compositions.csv"),
                streams,
                components
        );

        Component water = components.get(0);
        Component ethanol = components.get(1);

        // S101 = 70% Water + 30% Ethanol
        MaterialStream s101 = streams.get(0);

        assertEquals(0.7, s101.getMassFraction(water), 1e-6);
        assertEquals(0.3, s101.getMassFraction(ethanol), 1e-6);

        // S102 = 50% Water + 50% Ethanol
        MaterialStream s102 = streams.get(1);

        assertEquals(0.5, s102.getMassFraction(water), 1e-6);
        assertEquals(0.5, s102.getMassFraction(ethanol), 1e-6);

        // S103 = 100% Water
        MaterialStream s103 = streams.get(2);

        assertEquals(1.0, s103.getMassFraction(water), 1e-6);
    }


    @Test
    void shouldValidateCompositionAfterLoading() throws IOException {

        InputParser parser = new InputParser();

        List<Component> components =
                parser.loadComponents(
                        Path.of("input/component.csv")
                );

        List<MaterialStream> streams =
                parser.loadStreams(
                        Path.of("input/streams.csv")
                );

        parser.loadCompositions(
                Path.of("input/compositions.csv"),
                streams,
                components
        );

        assertTrue(streams.get(0).validateComposition());
        assertTrue(streams.get(1).validateComposition());
        assertTrue(streams.get(2).validateComposition());
    }
}