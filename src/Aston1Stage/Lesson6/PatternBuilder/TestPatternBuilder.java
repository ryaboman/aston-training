package Aston1Stage.Lesson6.PatternBuilder;

public class TestPatternBuilder {
    public static void main(String[] args) {
        Burger burger = Burger.builder().beef().cheese().mayonnaise().build();
        System.out.println(burger);
    }
}
