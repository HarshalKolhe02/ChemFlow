package io;

import org.junit.jupiter.api.Test;
import stream.Component;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InputParserTest {

    @Test
    void shouldLoadAllComponents() throws IOException {

        InputParser parser = new InputParser();

        List<Component> components =
                parser.loadComponents(
                        Path.of("input/component.csv")
                );

        assertEquals(3, components.size());

        assertEquals("Water", components.get(0).getName());
        assertEquals("H2O", components.get(0).getFormula());
        assertEquals(18.015, components.get(0).getMolecularWeight());

        assertEquals("Ethanol", components.get(1).getName());
        assertEquals("C2H5OH", components.get(1).getFormula());
        assertEquals(46.07, components.get(1).getMolecularWeight());

        assertEquals("Methanol", components.get(2).getName());
        assertEquals("CH3OH", components.get(2).getFormula());
        assertEquals(32.04, components.get(2).getMolecularWeight());
    }

    @Test
    void shouldThrowExceptionForMissingFile() {

        InputParser parser = new InputParser();

        assertThrows(
                IOException.class,
                () -> parser.loadComponents(
                        Path.of("input/fileDoesNotExist.csv")
                )
        );
    }
}