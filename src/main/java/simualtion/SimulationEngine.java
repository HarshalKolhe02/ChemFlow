package simualtion;

import stream.MaterialStream;
import units.UnitOpreation;

public class SimulationEngine {
    public MaterialStream run(UnitOpreation opreation, MaterialStream inlet)
    {
        return opreation.process(inlet);
    }
}
