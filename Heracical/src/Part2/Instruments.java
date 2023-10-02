package Part2;

public abstract class Instruments implements  iOrchestraPlay{
    protected int yearORelease;
    protected String material;
    protected String manufacturer;

    public Instruments(int yearORelease, String material, String manufacturer) {
        this.yearORelease = yearORelease;
        this.material = material;
        this.manufacturer = manufacturer;
    }

    public int getYearORelease() {
        return yearORelease;
    }

    public String getMaterial() {
        return material;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    @Override
    public String toString() {
        return "Instruments " +
                "yearORelease=" + yearORelease +
                ", material='" + material + '\'' +
                ", manufacturer='" + manufacturer + '\'';
    }
}
