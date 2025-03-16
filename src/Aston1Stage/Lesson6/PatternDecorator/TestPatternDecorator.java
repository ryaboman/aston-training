package Aston1Stage.Lesson6.PatternDecorator;

public class TestPatternDecorator {
    public static void main(String[] args) {
        Coffee coffee = new AmericanoCoffee();
        print(coffee);

        //Декорируем сахаром
        coffee = new SugarDecorator(coffee);
        print(coffee);

        //Декорируем сиропом
        coffee = new SyrupDecorator(coffee);
        print(coffee);

    }

    public static void print(Coffee coffee) {
        System.out.println(coffee.getName());
        System.out.println(coffee.getIngredients());
        System.out.println(coffee.cost());
        System.out.println("-----------------------");
    }
}
