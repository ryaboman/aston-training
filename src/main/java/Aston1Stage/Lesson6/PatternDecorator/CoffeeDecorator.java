package Aston1Stage.Lesson6.PatternDecorator;

import java.util.List;

public abstract class CoffeeDecorator extends Coffee {
    protected Coffee decoratedCoffee;
    public CoffeeDecorator(Coffee decoratedCoffee){
        this.decoratedCoffee = decoratedCoffee;
    }

    @Override
    public double cost(){
        return decoratedCoffee.cost();
    }

    @Override
    public String getName(){
        return decoratedCoffee.getName();
    }

    @Override
    public List<String> getIngredients(){
        return decoratedCoffee.getIngredients();
    }
}
