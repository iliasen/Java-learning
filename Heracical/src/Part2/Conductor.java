package Part2;

import java.util.ArrayList;
import java.util.Iterator;

public class Conductor {
    private ArrayList<Instruments> massOfInstrumenst = new ArrayList<>();


    public void addInstruments(Instruments obj) {
        this.massOfInstrumenst.add(obj);
    }

    public void printInstruments() {
        int i = 1;
        Iterator it = this.massOfInstrumenst.iterator();
        System.out.println("=================================================");

        while(it.hasNext()) {
            System.out.println(i + ")" + it.next().toString());
            ++i;
        }

        System.out.println("=================================================");
    }
}
