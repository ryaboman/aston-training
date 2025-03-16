package Aston1Stage.Lesson6.PatternObserver;

public class TestPatternObserver implements IObserver {

    private static int value = 5;
    private static TimerObservable timerObservable = null;
    public static void main(String[] args) {
        timerObservable = new TimerObservable(2000);
        timerObservable.addObserver(new PrintObserver("Observer1"));
        timerObservable.addObserver(new PrintObserver("Observer2"));
        timerObservable.addObserver(new TestPatternObserver());
        timerObservable.run();
    }

    @Override
    public void update() {
        TestPatternObserver.value--;
        if (TestPatternObserver.value == 0) {
            TestPatternObserver.timerObservable.stop();
            System.exit(0);
        }
    }
}
