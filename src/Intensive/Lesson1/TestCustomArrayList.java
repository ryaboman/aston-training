package Intensive.Lesson1;

public class TestCustomArrayList {
    public static void main(String[] args) {
        CustomArrayList<String> customArrayList = new CustomArrayList<>();
        customArrayList.add("A");
        customArrayList.add("B");
        customArrayList.add("C");

        for (int i = 0; i < customArrayList.size(); i++) {
            System.out.println(customArrayList.get(i));
        }
    }
}
