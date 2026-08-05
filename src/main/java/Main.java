import simualtion.SimulationEngine;
import stream.Component;
import stream.MaterialStream;
import stream.PhysicalProperties;
import units.Pump;

public class Main {
    public static void main(String[] args) {
        PhysicalProperties properties =
                new PhysicalProperties(
                        298.15,
                        101325,
                        997,
                        0.001
                );

        MaterialStream inlet =
                new MaterialStream(
                        "S101",
                        "Feed",
                        100,
                        properties
                );

        Component water =
                new Component(
                        "Water",
                        "H2O",
                        18.015
                );

        inlet.addComponent(water, 1.0);

        Pump pump =
                new Pump(
                        "P101",
                        "Feed Pump",
                        500000
                );

        SimulationEngine engine =
                new SimulationEngine();

        MaterialStream outlet =
                engine.run(pump, inlet);

        System.out.println(
                outlet.getProperties().getPressure()
        );

    }
}
