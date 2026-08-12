package io;

import stream.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import stream.MaterialStream;
import stream.PhysicalProperties;
import units.Pump;
import units.UnitOpreation;

public class InputParser {
    public List<Component> loadComponents(Path file) throws IOException {
        List<Component> components = new ArrayList<>();

        try (BufferedReader reader = Files.newBufferedReader(file)) {


            String header = reader.readLine();
            if (!CsvConstants.COMPONENT_HEADER.equals(header))
                throw new IllegalArgumentException("        \"Expected header: \"\n" +
                        "                + CsvConstants.COMPONENT_HEADER\n" +
                        "                + \" but found: \"\n" +
                        "                + header\n" +
                        ");");

            String line;
            while ((line = reader.readLine()) != null) {
                components.add(parseComponent(line));
            }
        }

        return components;
    }

    private Component parseComponent(String line) {
        String[] tokens = line.split(CsvConstants.DELIMITER);
        if (tokens.length != 3) throw new IllegalArgumentException("Invalid record" + line);
        return new Component(tokens[0].trim(), tokens[1].trim(), Double.parseDouble(tokens[2].trim()));
    }

    public List<MaterialStream> loadStreams(Path file) throws IOException {
        List<MaterialStream> streams = new ArrayList<>();

        try (BufferedReader reader = Files.newBufferedReader(file)) {


            String header = reader.readLine();
            if (!CsvConstants.STREAM_HEADER.equals(header))
                throw new IllegalArgumentException("        \"Expected header: \"\n" +
                        "                + CsvConstants.STREAM_HEADER\n" +
                        "                + \" but found: \"\n" +
                        "                + header\n" +
                        ");");

            String line;
            while ((line = reader.readLine()) != null) {
                streams.add(parseStream(line));
            }
        }

        return streams;
    }

    private MaterialStream parseStream(String line) {
        String[] tokens = line.split(CsvConstants.DELIMITER);
        if (tokens.length != 7) throw new IllegalArgumentException("Invalid record" + line);
        PhysicalProperties properties = new PhysicalProperties(
                Double.parseDouble(tokens[3].trim()),
                Double.parseDouble(tokens[4].trim()),
                Double.parseDouble(tokens[5].trim()),
                Double.parseDouble(tokens[6].trim()));
        return new MaterialStream(tokens[0].trim(), tokens[1].trim(), Double.parseDouble(tokens[2].trim()), properties);
    }

    private Component findComponent(List<Component> components, String name) {

        for (Component component : components) {

            if (name.equals(component.getName())) {
                return component;
            }
        }

        throw new IllegalArgumentException(
                "Component not found: " + name
        );
    }

    public void loadCompositions(Path file, List<MaterialStream> streams, List<Component> components) throws IOException {

        try (BufferedReader reader = Files.newBufferedReader(file)) {
            String header = reader.readLine();
            if (!CsvConstants.COMPOSITION_HEADER.equals(header)) {
                throw new IllegalArgumentException(
                        "Expected header: " +
                                CsvConstants.COMPOSITION_HEADER +
                                " but found: " +
                                header
                );
            }

            String line;
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(CsvConstants.DELIMITER);
                if (tokens.length != 3) throw new IllegalArgumentException("Invalid record" + line);
                String streamId = tokens[0].trim();
                String componentName = tokens[1].trim();
                double massFraction = Double.parseDouble(tokens[2].trim());
                MaterialStream stream = findStream(streams, streamId);
                Component component = findComponent(components, componentName);
                stream.addComponent(component, massFraction);


            }

        }

    }

    private MaterialStream findStream(List<MaterialStream> streams, String id) {
        for (MaterialStream stream : streams) {
            if (id.equals(stream.getId())) {
                return stream;
            }
        }
        throw new IllegalArgumentException("Stream not Found:" + id);
    }

    public List<UnitOpreation> loadUnitOpreations(Path file) throws IOException {
        List<UnitOpreation> units = new ArrayList<>();

        try (BufferedReader reader = Files.newBufferedReader(file)) {
            String header = reader.readLine();
            if (!CsvConstants.UNIT_HEADER.equals(header)) {
                throw new IllegalArgumentException(
                        "Expected header: " +
                                CsvConstants.UNIT_HEADER +
                                " but found: " +
                                header
                );
            }

            String line;
            while ((line = reader.readLine()) != null) {
                    UnitOpreation unit=parseUnit(line);
                    units.add(unit);
            }

        }
        return units;
    }

    private UnitOpreation parseUnit(String line) {
        String[] tokens = line.split(CsvConstants.DELIMITER);
        if (tokens.length != 6) throw new IllegalArgumentException("Invalid record: " + line);

        switch (tokens[0].toLowerCase(Locale.ROOT).trim()) {
            case "pump":
                return new Pump(tokens[1].trim(), tokens[2].trim(), Double.parseDouble(tokens[5].trim()));

            default:
                throw new IllegalArgumentException("Unsupported Unit Mentioned in: " + tokens[0]);
        }

    }

}
