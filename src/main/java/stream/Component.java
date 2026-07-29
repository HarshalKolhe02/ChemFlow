package stream;

import java.util.Objects;

public class Component {
    private final String name;
    private final double molecularWeight;
    private final String formula;

    // Constructor with validations
    public Component(String name, String formula, double MolecularWeight)
    {
        if(name==null|| name.isBlank())
        {
            throw new IllegalArgumentException("Component's Name can't be empty");

        }
        else if(formula==null|| formula.isBlank())
        {
            throw new IllegalArgumentException("Formula can't be empty");

        }
        else if(MolecularWeight<=0)
        {
            throw new IllegalArgumentException("Molecular Weight Should be Greater than zero");

        }
        else {
            this.name = name;
            this.formula = formula;
            this.molecularWeight = MolecularWeight;
        }
    }

    // Getter Functions
    public String getName() {
        return this.name;
    }
    public String getFormula() {
        return this.formula;
    }
    public double getMolecularWeight() {
        return this.molecularWeight;
    }

    // Override toString() function
    @Override
    public String toString() {
        return "Component{" +
                "name='" + this.name + '\'' +
                ", formula='" + this.formula + '\'' +
                ", MolecularWeight=" + this.molecularWeight +
                '}';
    }

    //Equal and hashCode Function Override
    @Override
    public boolean equals(Object o) {
        if(this==o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) return false;
        Component component = (Component) o;
        return Double.compare(this.molecularWeight, component.molecularWeight) == 0 && Objects.equals(this.name, component.name) && Objects.equals(this.formula, component.formula);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, molecularWeight, formula);
    }
}
