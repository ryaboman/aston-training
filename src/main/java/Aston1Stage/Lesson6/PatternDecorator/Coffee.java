package Aston1Stage.Lesson6.PatternDecorator;

import java.util.List;

public abstract class Coffee {
    private String name;
    private List<String> ingredients;

    public abstract double cost();

    protected void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    protected void setIngredients(List<String> ingredients){
        this.ingredients = ingredients;
    }

    public List<String> getIngredients(){
        return ingredients;
    }

}
