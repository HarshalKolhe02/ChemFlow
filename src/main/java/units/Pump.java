package units;

import stream.Component;
import stream.MaterialStream;
import stream.PhysicalProperties;
import java.util.Map;

public class Pump extends UnitOpreation {
    private final double pressureIncrease;

    public Pump(String id, String name, double pressureIncrease) {
        super(id, name);
        if (pressureIncrease < 0) throw new IllegalArgumentException("Pressure Increase Must be greater than Zero");
        this.pressureIncrease = pressureIncrease;
    }

    public double getPressureIncrease() {
        return this.pressureIncrease;
    }

    @Override
    public MaterialStream process(MaterialStream inlet) {
        PhysicalProperties inletProperties = inlet.getProperties();
        double newPressure = inletProperties.getPressure() + pressureIncrease;
        PhysicalProperties newProperties = new PhysicalProperties(inletProperties.getTemperature(), newPressure, inletProperties.getDensity(), inletProperties.getViscosity());
        MaterialStream outlet = new MaterialStream(inlet.getId() + "_Out", inlet.getName() + " Outlet", inlet.getMassFlowRate(), newProperties);
        for (Map.Entry<Component, Double> entry : inlet.getComposition().entrySet()) {
            outlet.addComponent(entry.getKey(), entry.getValue());
        }
        return outlet;
    }


}
