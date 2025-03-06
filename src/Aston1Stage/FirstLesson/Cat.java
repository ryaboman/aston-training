package Aston1Stage.FirstLesson;

public class Cat {
    private static int countCats;
    private static final String FAMILY;

    private String name;
    private int age;

    static{
        FAMILY = "Кошачьи";
        countCats = 0;
    }

    public Cat() {
        countCats++;
    }

    public Cat(String name, int age) {
        this.name = name;
        this.age = age;
        countCats++;
    }

    public static int getCountCats() { return countCats; }

    public static String getFamily() { return FAMILY; }

    public int getAge() { return age; }

    public void setAge(int age) { this.age = age; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name;}
}
