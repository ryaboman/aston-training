package Aston1Stage.Lesson6.PatternDecorator;

import java.util.List;

public class SyrupDecorator extends CoffeeDecorator{
    public SyrupDecorator(Coffee coffee) {
        super(coffee);

        String name = coffee.getName();
        coffee.setName(name + " Syrup");

        List<String> ingredients = coffee.getIngredients();
        ingredients.add("Syrup");
        coffee.setIngredients(ingredients);
    }

    @Override
    public double cost(){
        return (super.cost() + 2.5);
    }
}
