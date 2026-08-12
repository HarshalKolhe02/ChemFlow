package simulation;

import stream.MaterialStream;
import units.UnitOpreation;

import java.util.HashMap;
import java.util.Map;

public class Process {

    private final Map<String, MaterialStream> streams;
    private final Map<String, UnitOpreation> units;

    public Process() {
        this.streams = new HashMap<>();
        this.units = new HashMap<>();
    }

    public void addStream(MaterialStream stream) {
        if (stream == null) {
            throw new IllegalArgumentException("Stream cannot be null");
        }

        if (streams.containsKey(stream.getId())) {
            throw new IllegalArgumentException(
                    "Stream already exists: " + stream.getId()
            );
        }

        streams.put(stream.getId(), stream);
    }

    public void addUnit(UnitOpreation unit) {
        if (unit == null) {
            throw new IllegalArgumentException("Unit cannot be null");
        }

        if (units.containsKey(unit.getId())) {
            throw new IllegalArgumentException(
                    "Unit already exists: " + unit.getId()
            );
        }

        units.put(unit.getId(), unit);
    }

    public MaterialStream getStream(String id) {
        return streams.get(id);
    }

    public UnitOpreation getUnit(String id) {
        return units.get(id);
    }
}
