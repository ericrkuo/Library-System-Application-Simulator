package model.observer;

import model.Media;

import java.util.ArrayList;
import java.util.List;

public class Subject {

    private List<Observer> observers = new ArrayList<>();

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void notifyObserver(Media media) {
        for (Observer observer : observers) {
            observer.update(media);
        }
    }
}
