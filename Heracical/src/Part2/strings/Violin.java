package Part2.strings;

import java.util.Objects;

public class Violin extends Strings {

    private char size;

    public Violin(int yearORelease, String material, String manufacturer, int numberOfStrings, char size) {
        super(yearORelease, material, manufacturer, numberOfStrings);
        this.size = size;
    }

    public char getSize() {
        return size;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Violin violin = (Violin) o;
        return size == violin.size;
    }

    @Override
    public int hashCode() {
        return Objects.hash(size);
    }

    @Override
    public String toString() {
        return "Violin " +
                "size=" + size +
                ", yearORelease=" + yearORelease +
                ", material='" + material + '\'' +
                ", manufacturer='" + manufacturer;
    }

    @Override
    public void Play() {
        System.out.println("Violin play.");
    }
}
