package Aston1Stage.graduation_project.dao;

import Aston1Stage.graduation_project.model.Bus;
import Aston1Stage.graduation_project.util.BinarySearch;
import Aston1Stage.graduation_project.util.CustomArrayList;
import Aston1Stage.graduation_project.validator.DataValidator;

import java.util.Comparator;
import java.util.Optional;
import java.util.Random;
import java.util.Scanner;

public class DAOBus implements DAOModel {

    private CustomArrayList<Bus> buses;

    public DAOBus() {
        buses = new CustomArrayList<>();
    }

    @Override
    public void printElements(){
        for (int i = 0; i < buses.size(); i++) {
            Bus bus = buses.get(i);
            System.out.println(bus);
        }
    }

    public void add(Bus element) {
        buses.add(element);
    }

    public void addAll(CustomArrayList<Bus> elements) {
        for (int index = 0; index < elements.size(); index++) {
            buses.add(elements.get(index));
        }
    }

    public CustomArrayList<Bus> getElements() {
        return buses;
    }

    @Override
    public void sortElements() {
        CustomArrayList.selectionSort(buses, Bus::compareTo);
    }

    @Override
    public void magicSortElements(){
        CustomArrayList.selectionSort(buses, Bus::magicCompare);
    }

    @Override
    public void findElement() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Поиск проводится по номеру автобуса.");
        System.out.print("Введите номер автобуса: ");
        String busNumber = scanner.nextLine();

        Bus.BusBuilder busBuilder = new Bus.BusBuilder();
        Comparator<Bus> comparator = Comparator.comparing(Bus::getNumber);
        busBuilder.withNumber(busNumber);

        Bus bus = busBuilder.build();

        int index = BinarySearch.search(buses, bus, comparator);
        if (index >= 0) {
            System.out.println("Найден автобус: " + buses.get(index));
            System.out.println("Сохранить найденный автобус в файл? (y/n)");

            if (scanner.next().equalsIgnoreCase("y")) {
                saveToFile(buses.get(index), "busesFound.csv");
            }
        } else {
            System.out.println("Автобус не найден :(");
        }
    }

    @Override
    public void saveToFile(){
        saveToFile(buses, "busesCollection.csv");
    }

    @Override
    public void loadManual() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Введите данные автобуса в формате: номер, модель, пробег");
            System.out.println("Или введите 'exit' для завершения");
            System.out.print("? > ");

            String inputLine = scanner.nextLine();
            if (inputLine.equalsIgnoreCase("exit")) {
                break;
            }

            String[] busData = inputLine.split(",");
            if (busData.length != 3) {
                System.err.println("Ошибка в данных файла: строка не соответствует формату.");
                continue;
            }

            String number = busData[0].trim();
            String model = busData[1].trim();
            String mileage = busData[2].trim();

            Optional<Bus> busOptional = DataValidator.validateAndReturnBusWithComment(number, model, mileage);
            busOptional.ifPresent(buses::add);
            busOptional.ifPresentOrElse(
                    bus -> System.out.println("Вы добавили автобус: " + bus),
                    () -> System.out.println("Некорректные данные"));
        }
    }

    @Override
    public void loadFromFile() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите количество автобусов для загрузки из файла:");
        int numberToLoad = scanner.nextInt();

        Optional<String[]> resultOptional = getRowsFromFile("buses.csv", numberToLoad);
        if (resultOptional.isPresent()) {
            String[] rows = resultOptional.get();
            for (String stringObjectCSV : rows) {
                if (stringObjectCSV != null && !stringObjectCSV.trim().isEmpty()) {
                    Optional<Bus> busOptional = Bus.fromCSVString(stringObjectCSV);
                    //busOptional.ifPresent(buses::add);
                    busOptional.ifPresent(bus -> {
                        buses.add(bus);
                        System.out.println("Загружен автобус: " + bus.toString());
                    });
                }
            }
            System.out.println("Всего в файле автобусов: " + buses.size());
        } else {
            System.out.println("Не удалось загрузить данные из файла");
        }
    }

    @Override
    public void loadRandom() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите количество автобусов для генерации:");
        int numberToGenerate = scanner.nextInt();
        Random random = new Random();

        for (int i = 0; i < numberToGenerate; i++) {
            String numberBus = generateRandomString(6);
            String model = generateRandomString(5);
            int mileage = random.nextInt(99999) + 1;

            Bus bus = Bus.create()
                    .withNumber(numberBus)
                    .withModel(model)
                    .withMileage(mileage)
                    .build();
            buses.add(bus);

            System.out.println("Сгенерирован автобус: " + bus.toString());
        }
        System.out.println("Всего сгенерировано " + buses.size() + " автобусов.");
    }
}