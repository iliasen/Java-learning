import coffees.CoffeeShop;
import coffees.CoffeeType;
import coffees.SimpleCoffeeFactory;
import coffees.observer.Observer;
import coffees.xml.XMLHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;

public class Main {
    public static void main(String[] args) {
        SimpleCoffeeFactory coffeeFactory = new SimpleCoffeeFactory();
        //фабрика
        coffeeFactory.createCoffee(CoffeeType.CAPPUCCINO).grindCoffee();
        coffeeFactory.createCoffee(CoffeeType.CAFFE_LATTE).makeCoffee();
        coffeeFactory.createCoffee(CoffeeType.AMERICANO).pourIntoCup();


        //наблюдатель
        CoffeeShop coffeeShop = new CoffeeShop();
        coffeeShop.addObserver(new Observer() {
            @Override
            public void update(String data) {
                System.out.println("Наблюдатель 1: " + data);
            }
        });
        coffeeShop.addObserver(new Observer() {
            @Override
            public void update(String data) {
                System.out.println("Наблюдатель 2: " + data);
            }
        });

        try {
            SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
            SAXParser saxParser = saxParserFactory.newSAXParser();
            XMLHandler xmlHandler = new XMLHandler(coffeeShop);

            File xmlFile = new File("src/data.xml");

            // Запуск парсера
            saxParser.parse(xmlFile, xmlHandler);
        } catch (Exception e) {
            e.printStackTrace();
        }
        coffeeShop.printCoffeeShop();
    }
}