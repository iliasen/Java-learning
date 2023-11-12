package coffees;


public class Coffee {
    private String type, grind,make, pour;
    public void grindCoffee(){
        System.out.println("Перемолка...");
    }
    public void makeCoffee(){
        System.out.println("Делание кофе...");

    }
    public void pourIntoCup(){
        System.out.println("Налитие кофе в чашку...");
    }

    public void setType(String value) {
        this.type = value;
    }

    public void setGrind(String value) {
        this.grind = value;
    }

    public void setMake(String value) {
        this.make = value;
    }

    public void setPour(String value) {
        this.pour = value;
    }

    @Override
    public String toString() {
        return "Coffee{" +
                "type='" + type + '\'' +
                ", grind='" + grind + '\'' +
                ", make='" + make + '\'' +
                ", pour='" + pour + '\'' +
                '}';
    }
}

