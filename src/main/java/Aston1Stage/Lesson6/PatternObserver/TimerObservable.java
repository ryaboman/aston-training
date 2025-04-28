package Aston1Stage.Lesson6.PatternObserver;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class TimerObservable implements IObservable {
    private Timer timer;
    private int milliseconds;
    private List<IObserver> observers;

    public TimerObservable(int milliseconds) {
        this.milliseconds = milliseconds;
        observers = new ArrayList<>();
        timer = new Timer();
    }

    public void run(){
        timer.schedule(task, milliseconds, milliseconds);
    }

    public void stop(){
        timer.cancel();
        timer.purge();
    }

    TimerTask task = new TimerTask() {
        public void run() {
            notifyObservers();
        }
    };

    @Override
    public void addObserver(IObserver o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(IObserver o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers() {
        for (IObserver observer : observers) {
            observer.update();
        }
    }
}
