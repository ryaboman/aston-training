package Aston1Stage.Lesson6.PatternBuilder;

public class Burger {

    private boolean bun = true;
    private boolean beef;
    private boolean chicken;
    private boolean cheese;
    private boolean tomato;
    private boolean cucumber;
    private boolean onion;
    private boolean pepper;
    private boolean ketchup;
    private boolean mayonnaise;

    private Burger() {}

    private Burger(boolean bun, boolean beef, boolean chicken, boolean cheese, boolean tomato, boolean cucumber,
    boolean onion, boolean pepper, boolean ketchup, boolean mayonnaise){
        this.bun = bun;
        this.beef = beef;
        this.chicken = chicken;
        this.cheese = cheese;
        this.tomato = tomato;
        this.cucumber = cucumber;
        this.onion = onion;
        this.pepper = pepper;
        this.ketchup = ketchup;
        this.mayonnaise = mayonnaise;
    }

    public static BuilderBurgerClass builder() {
        return new BuilderBurgerClass();
    }

    public static class BuilderBurgerClass {
        private boolean bun = true;
        private boolean beef;
        private boolean chicken;
        private boolean cheese;
        private boolean tomato;
        private boolean cucumber;
        private boolean onion;
        private boolean pepper;
        private boolean ketchup;
        private boolean mayonnaise;

        public BuilderBurgerClass beef() {
            beef = true;
            return this;
        }
        public BuilderBurgerClass chicken() {
            chicken = true;
            return this;
        }
        public BuilderBurgerClass cheese() {
            cheese = true;
            return this;
        }
        public BuilderBurgerClass tomato() {
            tomato = true;
            return this;
        }
        public BuilderBurgerClass cucumber() {
            cucumber = true;
            return this;
        }
        public BuilderBurgerClass onion() {
            onion = true;
            return this;
        }
        public BuilderBurgerClass pepper() {
            pepper = true;
            return this;
        }
        public BuilderBurgerClass ketchup() {
            ketchup = true;
            return this;
        }
        public BuilderBurgerClass mayonnaise() {
            mayonnaise = true;
            return this;
        }

        public Burger build() {
            Burger burger = new Burger(bun, beef, chicken, cheese, tomato, cucumber,
            onion, pepper, ketchup, mayonnaise);
            return burger;
        }
    }

    @Override
    public String toString() {
        return "BuilderBurgerClass{" +
                "bun=" + bun +
                ", beef=" + beef +
                ", chicken=" + chicken +
                ", cheese=" + cheese +
                ", tomato=" + tomato +
                ", cucumber=" + cucumber +
                ", onion=" + onion +
                ", pepper=" + pepper +
                ", ketchup=" + ketchup +
                ", mayonnaise=" + mayonnaise +
                '}';
    }
}
