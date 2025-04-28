package Aston1Stage.Lesson6.PatternObserver;

public class PrintObserver implements IObserver {
    String name;

    public PrintObserver(String name) {
        this.name = name;
    }

    @Override
    public void update() {
        System.out.println("Hi, I " + name + "!");
    }
}
