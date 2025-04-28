package Aston1Stage.graduation_project.dao;

import Aston1Stage.graduation_project.model.Student;
import Aston1Stage.graduation_project.util.BinarySearch;
import Aston1Stage.graduation_project.util.CustomArrayList;
import Aston1Stage.graduation_project.validator.DataValidator;

import java.util.Comparator;
import java.util.Optional;
import java.util.Random;
import java.util.Scanner;

public class DAOStudent implements DAOModel {

    CustomArrayList<Student> students;

    public DAOStudent() {
        students = new CustomArrayList<>();
    }

    @Override
    public void printElements() {
        for (int i = 0; i < students.size(); i++) {
            Student student = students.get(i);
            System.out.println(student);
        }
    }

    public void add(Student element) {
        students.add(element);
    }

    public void addAll(CustomArrayList<Student> elements) {
        for (int i = 0; i < elements.size(); i++) {
            students.add(elements.get(i));
        }
    }

    public CustomArrayList<Student> getElements() {
        return students;
    }

    @Override
    public void sortElements() {
        CustomArrayList.selectionSort(students, Student::compareTo);
    }

    @Override
    public void magicSortElements() {
        CustomArrayList.selectionSort(students, Student::magicCompare);
    }

    @Override
    public void findElement() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Поиск проводится по номеру студенческого билета.");
        System.out.println("Введите номер студенческого билета:");

        long studentBookNumber = scanner.nextLong();

        Student.StudentBuilder studentBuilder = new Student.StudentBuilder();
        Comparator<Student> comparator = Comparator.comparing(Student::getStudentBookNumber);
        studentBuilder.withStudentBookNumber(studentBookNumber);

        Student student = studentBuilder.build();

        int index = BinarySearch.search(students, student, comparator);
        if (index >= 0) {
            System.out.println("Найден студент: " + students.get(index));
            System.out.println("Сохранить найденного студента в файл? (y/n)");

            if (scanner.next().equalsIgnoreCase("y")) {
                saveToFile(students.get(index), "studentsFound.csv");
            }
        } else {
            System.out.println("Студент не найден :(");
        }
    }

    @Override
    public void saveToFile() {
        saveToFile(students, "studentsCollection.csv");
    }

    @Override
    public void loadManual() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Введите студента в формате: номер группы ; средний бал ; номер читательского билета");
            System.out.println("Или введите 'exit' для завершения");
            System.out.print("? > ");

            String inputLine = scanner.nextLine();
            if (inputLine.equalsIgnoreCase("exit")) {
                break;
            }

            String[] studentData = inputLine.split(",");
            if (studentData.length != 3) {
                System.err.println("Ошибка в данных файла: строка не соответствует формату.");
                continue;
            }

            String studentGroupNumber = studentData[0].trim();
            String studentAverageGrade = studentData[1].trim();
            String studentBookNumber = studentData[2].trim();

            Optional<Student> studentOptional = DataValidator.validateAndReturnStudentWithComment(
                    studentGroupNumber, studentAverageGrade, studentBookNumber);

            studentOptional.ifPresent(students::add);
            studentOptional.ifPresentOrElse(
                    student -> System.out.println("Вы добавили студента: " + student),
                    () -> System.out.println("Некорректные данные"));
        }
    }

    @Override
    public void loadFromFile() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите количество студентов для загрузки из файла:");
        int numberToLoad = scanner.nextInt();

        Optional<String[]> resultOptional = getRowsFromFile("students.csv", numberToLoad);
        if (resultOptional.isPresent()) {
            String[] rows = resultOptional.get();
            for (String stringObjectCSV : rows) {
                if (stringObjectCSV != null && !stringObjectCSV.trim().isEmpty()) {
                    Optional<Student> studentOptional = Student.fromCSVString(stringObjectCSV);
                    //busOptional.ifPresent(students::add);
                    studentOptional.ifPresent(student -> {
                        students.add(student);
                        System.out.println("Загружен студент: " + student.toString());
                    });
                }
            }
            System.out.println("Всего в файле студентов: " + students.size());
        } else {
            System.out.println("Не удалось загрузить данные из файла");
        }
    }

    @Override
    public void loadRandom() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите количество студентов для генерации:");
        int numberToGenerate = scanner.nextInt();

        Random random = new Random();

        for (int i = 0; i < numberToGenerate; i++) {
            int groupNumber = random.nextInt(10) + 1;
            double averageGrade = random.nextDouble() * 5.0;
            long studentBookNumber = Math.abs(random.nextLong()) + 1L;

            Student student = Student.create()
                    .withGroupNumber(groupNumber)
                    .withAverageGrade(averageGrade)
                    .withStudentBookNumber(studentBookNumber)
                    .build();

            students.add(student);

            System.out.println("Сгенерирован студент: " + student.toString());
        }
        System.out.println("Всего сгенерировано " + students.size() + " студентов.");
    }
}