package Part2.strings;

import Part2.Instruments;

import java.util.ArrayList;
import java.util.Iterator;

abstract class Strings extends Instruments {

    private int numberOfStrings;

    public Strings(int yearORelease, String material, String manufacturer, int numberOfStrings) {
        super(yearORelease, material, manufacturer);
        this.numberOfStrings = numberOfStrings;
    }

    /*public void printInstrument() {
        int i = 1;
        Iterator it = this.massOfObj.iterator();
        System.out.println("=================================================");

        while(it.hasNext()) {
            System.out.println(i + ")" + it.next().toString());
            ++i;
        }

        System.out.println();
        System.out.println("=================================================");
    }

    public void addFurniture(Strings obj) {
        this.massOfObj.add(obj);
    }*/

    public int getNumberOfStrings() {
        return numberOfStrings;
    }

    @Override
    public String toString() {
        return "Strings " +
                "numberOfStrings=" + numberOfStrings +
                ", yearORelease=" + yearORelease +
                ", material='" + material + '\'' +
                ", manufacturer='" + manufacturer;
    }

    @Override
    public void Play() {
        System.out.println("Strings are playing");
    }
}
