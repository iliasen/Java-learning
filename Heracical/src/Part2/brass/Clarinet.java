package Part2.brass;

import java.util.Objects;

public class Clarinet extends BrassInstrument{

    private final int weight;

    public Clarinet(int yearORelease, String material, String manufacturer, int volume, int weight) {
        super(yearORelease, material, manufacturer, volume);
        this.weight = weight;
    }

    @Override
    public void Play() {
        System.out.println("Clarinet playing");
    }

    public String toString() {

        String str = super.toString() + " weight: " + this.weight;
        return str;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Clarinet clarinet = (Clarinet) o;
        return weight == clarinet.weight;
    }

    @Override
    public int hashCode() {
        return Objects.hash(weight);
    }
}
