package coffees.observer;
import java.util.ArrayList;
import java.util.List;

public class Subject {
    private List<Observer> observers;

    public Subject() {
        this.observers = new ArrayList<>();
    }

    public void attach(Observer observer) {
        observers.add(observer);
    }

    public void detach(Observer observer) {
        observers.remove(observer);
    }

    public void notifyObservers(String data) {
        for (Observer observer : observers) {
            observer.update(data);
        }
    }
}