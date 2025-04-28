package Aston1Stage.graduation_project.controllers;

import Aston1Stage.graduation_project.dao.DAOBus;

public class BusController extends Controller {

    private final DAOBus daoBus;

    public BusController(Controller controller) {
        super("Меню работы с автобусами", controller);
        daoBus = new DAOBus();
        Controller addBusController = new AddController(this, daoBus);
        menuController.addItem(1, "Добавить автобус", addBusController::getHandler);
        menuController.addItem(2, "Сортировать коллекцию", this::sortCollection);
        menuController.addItem(3, "Найти автобус", this::findBus);
        menuController.addItem(4, "Сохранить коллекцию", this::saveToFile);
        menuController.addItem(5, "Вывести на экран коллекцию", this::printElements);
        menuController.addItem(6, "Магическая сортировка", this::magicSortCollection);
        menuController.addItem(0, "⮌ Назад", controller::getHandler);
    }

    private Controller sortCollection() {
        daoBus.sortElements();
        return this;
    }

    private Controller magicSortCollection() {
        daoBus.magicSortElements();
        return this;
    }

    private Controller findBus() {
        daoBus.findElement();
        return this;
    }

    private Controller saveToFile() {
        daoBus.saveToFile();
        return this;
    }

    private Controller printElements(){
        daoBus.printElements();
        return this;
    }
}

