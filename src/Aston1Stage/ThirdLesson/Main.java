package Aston1Stage.ThirdLesson;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Book> libraryBooks = new ArrayList<>();
        libraryBooks.add(new Book("Философия Java", "Брюс Эккель", 1168, 2024));
        libraryBooks.add(new Book("Совершенный код", "Стив Макконнелл", 896, 2018));
        libraryBooks.add(new Book("IBM PC", "П. Нортон", 328, 1991));
        libraryBooks.add(new Book("Архитектура компьютера", "Э. Таненбаум", 816, 2024));
        libraryBooks.add(new Book("Компьютерные сети", "Э. Таненбаум", 992, 2024));
        libraryBooks.add(new Book("Операционные системы", "Э. Таненбаум", 1120, 2022));
        libraryBooks.add(new Book("Проектирование систем машинного обучения", "Ч. Хьюен", 368, 2023));
        libraryBooks.add(new Book("Алгоритмы. Руководство по разработке", "Стивен Скиена", 720, 2021));
        libraryBooks.add(new Book("Структуры данных и алгоритмы Java", "Роберт Лафоре", 704, 2018));
        libraryBooks.add(new Book("Java: эффективное программирование", "Джошуа Блох", 464, 2019));
        libraryBooks.add(new Book("Грокаем алгоритмы", "Адитья Бхаргава", 2017, 288));
        libraryBooks.add(new Book("Паттерны объектно-ориентированного проектирования", "Э. Гамма, Р. Хелм, Р. Дхонсон, Дж. Влиссидес", 448, 2022));
        libraryBooks.add(new Book("Чистый код", "Роберт Мартин", 464, 2022));
        libraryBooks.add(new Book("Spring в действии", "Крейг Уоллс", 480, 2024));
        libraryBooks.add(new Book("Java 8. Полное руководство", "Герберт Шилдт", 1376, 2016));

        Student studentAleksey = new Student("Алексей", libraryBooks.subList(0, 5));
        Student studentMikhail = new Student("Михаил", libraryBooks.subList(1, 7));
        Student studentVladimir = new Student("Владимир", libraryBooks.subList(2, 9));
        Student studentYuri = new Student("Юрий", libraryBooks.subList(0, libraryBooks.size()));
        Student studentAleksander = new Student("Александр", libraryBooks.subList(3, libraryBooks.size()));

        List<Student> students = new ArrayList<>();
        students.add(studentAleksey);
        students.add(studentMikhail);
        students.add(studentVladimir);
        students.add(studentYuri);
        students.add(studentAleksander);

        var unmodifiableStudents = List.copyOf(students);

        unmodifiableStudents.stream().peek(System.out::println)
                .map(s -> s.getBooks())
                .flatMap(lst -> lst.stream())
                .sorted()
                .distinct()
                .filter(b -> b.getYear() >= 2000)
                .limit(3)
                .map(b -> b.getYear())
                .findAny().ifPresentOrElse(
                        y -> System.out.println("Year: " + y),
                        () -> System.out.println("Книга отсутствует")
                );

    }
}
