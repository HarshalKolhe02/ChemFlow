package io;

import stream.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import io.CsvConstants;

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
    private Component parseComponent(String line)
    {
        String[] tokens = line.split(CsvConstants.DELIMITER);
        if (tokens.length != 3) throw new IllegalArgumentException("Invalid record" + line);
        return new Component(tokens[0].trim(), tokens[1].trim(), Double.parseDouble(tokens[2].trim()));
    }

}
