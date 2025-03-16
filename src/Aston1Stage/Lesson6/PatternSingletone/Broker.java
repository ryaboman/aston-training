package Aston1Stage.Lesson6.PatternSingletone;

public class Broker {

    private Broker() {}

    private static class BrokerSingleton {
        private static final Broker INSTANCE = new Broker();
    }

    public static Broker getInstance() {
        return BrokerSingleton.INSTANCE;
    }
}
