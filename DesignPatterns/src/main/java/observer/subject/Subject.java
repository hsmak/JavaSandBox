package observer.subject;// This interface handles adding, deleting and updating
// all observers 


import observer.observer.Observer;

public interface Subject {

    void register(Observer o);
    void unregister(Observer o);
    void notifyObserver();

}