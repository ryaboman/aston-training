package Aston1Stage.Lesson6.PatternDecorator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AmericanoCoffee extends Coffee {

    public AmericanoCoffee() {
        setName("Americano Coffee");
        List<String> ingredients = new ArrayList<>();
        ingredients.add("Coffee");
        setIngredients(ingredients);
    }

    @Override
    public double cost() {
        return 70.00;
    }
}
