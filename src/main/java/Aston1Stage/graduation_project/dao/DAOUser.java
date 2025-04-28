package Aston1Stage.graduation_project.dao;

import Aston1Stage.graduation_project.model.User;
import Aston1Stage.graduation_project.util.BinarySearch;
import Aston1Stage.graduation_project.util.CustomArrayList;
import Aston1Stage.graduation_project.validator.DataValidator;

import java.util.Comparator;
import java.util.Optional;
import java.util.Random;
import java.util.Scanner;

public class DAOUser implements DAOModel {

    CustomArrayList<User> users;

    public DAOUser() {
        users = new CustomArrayList<>();
    }

    @Override
    public void printElements() {
        for (int i = 0; i < users.size(); i++) {
            User user = users.get(i);
            System.out.println(user);
        }
    }

    public CustomArrayList<User> getElements() {
        return users;
    }

    @Override
    public void sortElements() {
        CustomArrayList.selectionSort(users, User::compareTo);
    }

    @Override
    public void magicSortElements() {
        CustomArrayList.selectionSort(users, User::magicCompare);
    }

    @Override
    public void findElement() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Поиск проводится по имени и e-mail пользователя");
        System.out.println("Введите данные искомого пользователя");
        System.out.print("Имя пользователя: ");
        String nameUser = scanner.nextLine();

        System.out.print("E-mail пользователя: ");
        String emailUser = scanner.nextLine();

        User.UserBuilder userBuilder = new User.UserBuilder();
        Comparator<User> comparator;

        if (nameUser.isEmpty() && emailUser.isEmpty()) {
            System.out.println("Не введены данные для поиска");
            System.out.println("Поиск прекращен!");
            return;
        } else if (nameUser.isEmpty()) {
            comparator = Comparator.comparing(User::getEmail);
            userBuilder.withEmail(emailUser);
        } else if (emailUser.isEmpty()) {
            comparator = Comparator.comparing(User::getName);
            userBuilder.withName(nameUser);
        } else {
            comparator = Comparator.comparing(User::getName).thenComparing(User::getEmail);
            userBuilder.withName(nameUser).withEmail(emailUser);
        }

        User user = userBuilder.build();
        int index = BinarySearch.search(users, user, comparator);
        if (index >= 0) {
            System.out.println("Найден пользователь: " + users.get(index));
            System.out.println("Сохранить найденного пользователя в файл? (y/n)");

            if (scanner.next().equalsIgnoreCase("y")) {
                saveToFile(users.get(index), "usersFound.csv");
            }
        } else {
            System.out.println("Пользователь не найден :(");
        }
    }

    @Override
    public void saveToFile() {
        saveToFile(users, "usersCollection.csv");
    }

    @Override
    public void loadManual() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Введите пользователя в формате: имя, e-mail, пароль");
            System.out.println("Или введите 'exit' для завершения");
            System.out.print("? > ");

            String inputLine = scanner.nextLine();
            if (inputLine.equalsIgnoreCase("exit")) {
                break;
            }
            String[] userData = inputLine.split(",");
            if (userData.length != 3) {
                System.err.println("Ошибка в данных файла: строка не соответствует формату.");
                continue;
            }

            String userName = userData[0].trim();
            String userEmail = userData[1].trim();
            String userPassword = userData[2].trim();

            Optional<User> userOptional = DataValidator.validateAndReturnUserWithComment(userName, userEmail, userPassword);
            userOptional.ifPresent(users::add);
            userOptional.ifPresentOrElse(
                    user -> System.out.println("Вы добавили пользователя: " + user),
                    () -> System.out.println("Некорректные данные"));
        }
    }

    @Override
    public void loadFromFile() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите количество пользователей для загрузки из файла:");
        int numberToLoad = scanner.nextInt();

        Optional<String[]> resultOptional = getRowsFromFile("users.csv", numberToLoad);
        if (resultOptional.isPresent()) {
            String[] rows = resultOptional.get();
            for (String stringObjectCSV : rows) {
                if (stringObjectCSV != null && !stringObjectCSV.trim().isEmpty()) {
                    Optional<User> userOptional = User.fromCSVString(stringObjectCSV);
                    userOptional.ifPresent(user -> {

                        Optional<String> validatedName = DataValidator.validateAndReturnUserName(user.getName());
                        Optional<String> validatedPassword = DataValidator.validateAndReturnPassword(user.getPassword());
                        Optional<String> validatedEmail = DataValidator.validateAndReturnEmail(user.getEmail());

                        if (validatedName.isPresent() && validatedPassword.isPresent() && validatedEmail.isPresent()) {
                            users.add(user);
                            System.out.println("Загружен пользователь: " + user.toString());
                        } else {
                            System.out.println("Ошибка: данные пользователя " + user + " невалидны");
                        }
                    });
                }
            }
            System.out.println("Пользователей в коллекции: " + users.size());
        } else {
            System.out.println("Не удалось загрузить данные из файла");
        }
    }

    public void loadRandom() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите количество пользователей для генерации:");
        int numberToGenerate = scanner.nextInt();

        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        String specialChars = "!@#$%*?&";  // Специальные символы, указанные в регулярке

        for (int i = 0; i < numberToGenerate; i++) {
            String name = generateRandomString(alphabet, 8);

            String password = generateValidPassword(9, 12, alphabet + specialChars);

            String email = "user" + generateRandomString(alphabet + "0123456789", 4)
                    + "@example.ru";

            User user = User.create()
                    .withName(name)
                    .withEmail(email)
                    .withPassword(password)
                    .build();
            users.add(user);

            System.out.println("Сгенерирован пользователь: " + user.toString());
        }
        System.out.println("Всего сгенерировано " + users.size() + " пользователей.");
    }

    private static String generateValidPassword(int minLength, int maxLength, String characterSet) {
        Random random = new Random();
        String password = "";
        String specialChars = "!@#$%*?&";

        password += characterSet.charAt(random.nextInt(specialChars.length()));

        while (password.length() < minLength) {
            password += characterSet.charAt(random.nextInt(characterSet.length()));
        }

        if (password.length() < maxLength) {
            int remainingLength = random.nextInt(maxLength - password.length()) + 1;
            for (int i = 0; i < remainingLength; i++) {
                password += characterSet.charAt(random.nextInt(characterSet.length()));
            }
        }
        return password;
    }

    private static String generateRandomString(String characterSet, int length) {
        Random random = new Random();
        String result = "";
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characterSet.length());
            result += characterSet.charAt(index);
        }
        return result;
    }
}