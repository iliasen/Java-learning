package coffees;

import coffees.observer.Observer;
import coffees.observer.Subject;

import java.util.ArrayList;
import java.util.List;

public class CoffeeShop {
    private final List<Coffee> coffees;
    private final Subject subject;
    private final SimpleCoffeeFactory coffeeFactory;

    public CoffeeShop() {
        coffees = new ArrayList<>();
        subject = new Subject();
        coffeeFactory = new SimpleCoffeeFactory();
    }

    public void addObserver(Observer observer) {
        subject.attach(observer);
    }

    public void removeObserver(Observer observer) {
        subject.detach(observer);
    }

    public void notifyObservers(String data) {
        subject.notifyObservers(data);
    }

    public Coffee orderCoffee(CoffeeType type) {
        Coffee coffee = coffeeFactory.createCoffee(type);
        coffee.grindCoffee();
        coffee.makeCoffee();
        coffee.pourIntoCup();

        coffees.add(coffee); // Добавляем кофе в список

        System.out.println("Вот ваш кофе! Спасибо, приходите еще!");
        return coffee;
    }

    public void addCoffee(Coffee currentCoffee) {
        coffees.add(currentCoffee);
    }

    public void printCoffeeShop() {
        for (Coffee coffee : coffees) {
            System.out.println(coffee);
        }
    }
}