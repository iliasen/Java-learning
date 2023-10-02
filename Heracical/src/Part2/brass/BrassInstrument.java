package Part2.brass;

import Part2.Instruments;

public abstract class BrassInstrument extends Instruments {

    protected int volume;

    public BrassInstrument(int yearORelease, String material, String manufacturer, int volume) {
        super(yearORelease, material, manufacturer);
        this.volume = volume;
    }

    public int getVolume() {
        return volume;
    }

    @Override
    public void Play() {
        System.out.println("Brass playing");
    }
}
