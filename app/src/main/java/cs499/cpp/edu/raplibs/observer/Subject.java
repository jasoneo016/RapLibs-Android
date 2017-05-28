package cs499.cpp.edu.raplibs.observer;

/**
 * Created by admin on 5/27/17.
 */

public interface Subject {

    public void register(Observer observer);

    public void unregister(Observer observer);

    public void notifyObserver();
}
