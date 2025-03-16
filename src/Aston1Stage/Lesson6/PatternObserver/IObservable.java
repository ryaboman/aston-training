package Aston1Stage.Lesson6.PatternObserver;

public interface IObservable {
    void addObserver(IObserver o);
    void removeObserver(IObserver o);
    void notifyObservers();
}
