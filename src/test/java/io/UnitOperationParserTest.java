package io;

import org.junit.jupiter.api.Test;
import units.Pump;
import units.UnitOpreation;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UnitOperationParserTest {

    @Test
    void shouldLoadPumpFromCsv() throws IOException {

        InputParser parser = new InputParser();

        List<UnitOpreation> units =
                parser.loadUnitOpreations(
                        Path.of("input/unit_operations.csv")
                );

        assertEquals(1, units.size());

        UnitOpreation unit = units.get(0);

        assertInstanceOf(Pump.class, unit);

        Pump pump = (Pump) unit;

        assertEquals("P101", pump.getId());
        assertEquals("Feed Pump", pump.getName());
        assertEquals(50000, pump.getPressureIncrease(), 1e-6);
    }

    @Test
    void shouldRejectInvalidUnitType() {

        InputParser parser = new InputParser();

        assertThrows(
                IllegalArgumentException.class,
                () -> parser.loadUnitOpreations(
                        Path.of("input/invalid_unit_operations.csv")
                )
        );
    }

    @Test
    void shouldRejectInvalidRecord() {

        InputParser parser = new InputParser();

        assertThrows(
                IllegalArgumentException.class,
                () -> parser.loadUnitOpreations(
                        Path.of("input/invalid_unit_record.csv")
                )
        );
    }
}