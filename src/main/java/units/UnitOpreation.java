package units;

import stream.MaterialStream;

public abstract class UnitOpreation {
    private String id;
    private String name;

    public UnitOpreation(String id, String name) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("id can't be blank");
        }
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("name can't be blank");
        }
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public abstract MaterialStream process(MaterialStream inlet);
}