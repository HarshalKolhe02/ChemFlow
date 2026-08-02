package stream;
import java.util.HashMap;
import java.util.Map;
public class MaterialStream {
    private String id;
    private String name;
    private double massFlowRate;
    private PhysicalProperties properties;
    private Map<Component,Double> composition;

    public MaterialStream(String id, String name, double massFlowRate, PhysicalProperties properties)
    {
        if (id==null || id.isBlank()) throw new IllegalArgumentException("id can't be blank");
        if (name==null || name.isBlank()) throw new IllegalArgumentException("name can't be blank");
        if (massFlowRate<0) throw new IllegalArgumentException("Mass flow can't be less than zero");
        if (properties==null) throw new IllegalArgumentException("Physical Properties not Provided");

        this.id=id;
        this.name=name;
        this.massFlowRate=massFlowRate;
        this.properties=properties;

        this.composition=new HashMap<>();

    }

    public void addComponent(Component component, double massFraction)
    {
        if(component==null) throw new IllegalArgumentException("Component cannot be null");
        if(massFraction<0) throw new IllegalArgumentException("Mass Fraction must be greater than or equals to zero");
        if (this.composition.containsKey(component)) throw new IllegalArgumentException("Component already exists in the stream.");

        this.composition.put(component,massFraction);
    }
    public void removeComponent(Component component)
    {
        if(component==null) throw new IllegalArgumentException("Component cannot be null");

        this.composition.remove(component);
    }

    public Map<Component,Double> getComposition()
    {
        return Map.copyOf(composition);
    }

    public double getMassFlowRate() {
        return this.massFlowRate;
    }

    public String getId()
    {
        return this.id;
    }

    public String getName()
    {
        return this.name;
    }

    public PhysicalProperties getProperties()
    {
        return this.properties;
    }

    public double getMassFraction(Component component)
    {
        if(component==null) throw new IllegalArgumentException("Component Can't be null");
        return this.composition.getOrDefault(component,0.0);
    }
    public boolean validateComposition()
    {
        double sum=0;
        for (Double fraction : composition.values()) {
            sum+=fraction;
        }
        return (Math.abs(sum-1.0)<1e-6);
    }

    @Override
    public String toString() {
        return "MaterialStream{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", massFlowRate=" + massFlowRate +
                ", properties=" + properties +
                ", composition=" + composition +
                '}';
    }

}
