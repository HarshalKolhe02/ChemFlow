import stream.Component;
public class Main{
    public static void main(String[] args)
        {
            Component water1 = new Component("Water", "H2O", 18.015);
            Component water2 = new Component("Water", "H2O", 18.015);
            Component water3 = water1;

            System.out.println(water1 == water2);
            System.out.println(water1.equals(water2));

            System.out.println(water1 == water3);
            System.out.println(water1.equals(water3));

    }
}
