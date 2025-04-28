package Aston1Stage.graduation_project.dao;

import Aston1Stage.graduation_project.model.SerializableToCSVString;
import Aston1Stage.graduation_project.util.CustomArrayList;

import java.io.*;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.Random;


public interface DAOModel {

    void printElements();

    void loadManual();

    void sortElements();

    void magicSortElements();

    void findElement();

    void loadFromFile();

    void loadRandom();

    void saveToFile();

    default Optional<String[]> getRowsFromFile(String filename, int numberRows) {
        URL url = getClass().getResource("/" + filename);
        File file = new File(url.getFile());

        if (!file.exists()) {
            System.out.println("Файл '" + filename + "' не найден.");
            return Optional.empty();
        }
        String[] rows = new String[numberRows];
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            int index = 0;

            while (((line = reader.readLine()) != null) && (index < numberRows)) {
                rows[index] = line;
                index++;
            }

            return Optional.of(rows);

        } catch (IOException e) {
            System.out.println("Ошибка при чтении файла: " + e.getMessage());
        }

        return Optional.empty();
    }

    default String generateRandomString(int length) {
        Random random = new Random();
        String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        String digits = "0123456789";
        String result = "";
        for (int j = 0; j < length; j++) {
            if (j % 2 == 0) {
                result += letters.charAt(random.nextInt(letters.length()));
            } else {
                result += digits.charAt(random.nextInt(digits.length()));
            }
        }
        return result;
    }

    default void saveToFile(CustomArrayList<? extends SerializableToCSVString> elements, String filename) {
        Path source = Paths.get(this.getClass().getResource("/").getPath() + filename);
        File file = source.toFile();

        if (!file.exists()) {
            try {
                boolean created = file.createNewFile();
                if (!created) {
                    throw new IOException("Не удалось создать файл.");
                }
            } catch (IOException e) {
                System.out.println("Ошибка при создании файла: " + e.getMessage());
                return;
            }
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (int i = 0; i < elements.size(); i++) {
                writer.write(elements.get(i).toCSVString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Ошибка при записи в файл: " + e.getMessage());
        }
    }

    default void saveToFile(SerializableToCSVString element, String filename) {
        Path source = Paths.get(this.getClass().getResource("/").getPath() + filename);
        File file = source.toFile();

        if (!file.exists()) {
            try {
                boolean created = file.createNewFile();
                if (!created) {
                    throw new IOException("Не удалось создать файл.");
                }
            } catch (IOException e) {
                System.out.println("Ошибка при создании файла: " + e.getMessage());
                return;
            }
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            writer.write(element.toCSVString());
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Ошибка при записи в файл: " + e.getMessage());
        }
    }
}