package Part1;

import java.util.ArrayList;

public class Stock {
    private String name;
    private int price;
    private int availability;

    public Stock(String name, int price, int availability){
        this.name = name;
        this.price = price;
        this.availability = availability;
    }

    public Stock(){
        this.name = "null";
        this.price = 0;
        this.availability = 0;
    }

    public void getAll(){
        System.out.printf("Name: %s, price: %d, availability: %d\n" , name, price, availability);
    }

    public int getPrice(){
        return price;
    }

    public int getAvailability(){
        return availability;
    }

    public static void calculate(ArrayList<Stock> mass){
        int fullPrice = 0, fullAvailability = 0;
        for(Stock s : mass) {
            fullPrice += s.getPrice() * s.getAvailability();
            fullAvailability += s.getAvailability();
        }
        System.out.printf("FullPrise: %d, fullAvailability: %d \n", fullPrice, fullAvailability);
    }
}
