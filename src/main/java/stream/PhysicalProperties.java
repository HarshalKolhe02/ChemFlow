package stream;

public class PhysicalProperties {
    private double temperature;
    private double pressure;
    private double viscosity;
    private double density;

    //Constuctor
    public PhysicalProperties(double temperature,double pressure,double density,double viscosity){
        if(temperature<=0)
        {
            throw new IllegalArgumentException("Temperature Can't be Zero");
        }
        else if(pressure<0)
        {
            throw new IllegalArgumentException("Pressure Can't be less than zero");
        }
        else if(viscosity<=0)
        {
            throw new IllegalArgumentException("Viscosity Can't be Zero");
        }
        else if(density<=0)
        {
            throw new IllegalArgumentException("density Can't be Zero");
        }
        else
        {
            this.temperature=temperature;
            this.pressure=pressure;
            this.viscosity=viscosity;
            this.density=density;
        }

    }

    // Getters
    public double getTemperature()
    {
        return this.temperature;
    }
    public double getPressure()
    {
        return this.pressure;
    }
    public double getViscosity()
    {
        return this.viscosity;
    }
    public double getDensity()
    {
        return this.density;
    }

    // Setters
    public void setTemperature(double temperature)
    {
        if(temperature<=0)
        {
            throw new IllegalArgumentException("Temperature Can't be Zero");
        }
        this.temperature=temperature;
    }
    public void setPressure(double pressure)
    {
        if(pressure<0)
        {
            throw new IllegalArgumentException("TPressure Can't be less than zero");
        }
        this.pressure=pressure;
    }
    public void setViscosity(double viscosity)
    {
        if(temperature<=0)
        {
            throw new IllegalArgumentException("Viscosity Can't be Zero");
        }
        this.viscosity=viscosity;
    }
    public void setDensity(double density)
    {
        if(temperature<=0)
        {
            throw new IllegalArgumentException("Density Can't be Zero");
        }
        this.density=density;
    }

    @Override
    public String toString() {
        return "PhysicalProperties{"+"Temperature="+this.temperature+" ,Pressure="+this.pressure+" ,Viscosity="+this.viscosity+" ,density="+this.density;
    }
}
