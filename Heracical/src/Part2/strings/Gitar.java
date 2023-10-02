package Part2.strings;

import java.util.Objects;

public class Gitar extends Strings {
    private boolean electric;

    public Gitar(int yearORelease, String material, String manufacturer, int numberOfStrings, boolean electric) {
        super(yearORelease, material, manufacturer, numberOfStrings);
        this.electric = electric;
    }

    public boolean isElectric() {
        return electric;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Gitar gitar = (Gitar) o;
        return electric == gitar.electric;
    }

    @Override
    public int hashCode() {
        return Objects.hash(electric);
    }

    @Override
    public String toString() {
        return "Gitar " + "electric=" + electric +
                ", yearORelease=" + yearORelease +
                ", material='" + material + '\'' +
                ", manufacturer='" + manufacturer;
    }

    @Override
    public void Play() {
        System.out.println("Gitar play");
        //super.Play();
    }
}
