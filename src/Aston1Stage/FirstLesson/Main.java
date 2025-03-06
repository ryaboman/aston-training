package Aston1Stage.FirstLesson;

public class Main {
    public static void main(String[] args) {
        Cat cat1 = new Cat("Арнольд", 2);
        System.out.println(cat1.getName());

        Cat cat2 = new Cat();
        cat2.setAge(3);
        cat2.setName("Семен");
        System.out.println(cat2.getAge());

        System.out.println(Cat.getCountCats());
        System.out.println(Cat.getFamily());
    }
}
