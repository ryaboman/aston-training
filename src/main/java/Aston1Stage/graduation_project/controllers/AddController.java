package Aston1Stage.graduation_project.controllers;

import Aston1Stage.graduation_project.dao.DAOModel;

public class AddController extends Controller {

    private final DAOModel daoModel;

    public AddController(Controller controller, DAOModel daoModel) {
        super("Меню добавления элементов", controller);
        this.daoModel = daoModel;
        menuController.addItem(1, "Ввести вручную", this::loadManual);
        menuController.addItem(2, "Загрузить из файла", this::loadFromFile);
        menuController.addItem(3, "Генерация случайных данных", this::loadRandom);
        menuController.addItem(0, "⮌ Назад", controller::getHandler);
        menuController.setAnnotation("Выберите способ добавления:");
    }

    public Controller loadManual() {
        daoModel.loadManual();
        return this;
    }

    public Controller loadFromFile() {
        daoModel.loadFromFile();
        return this;
    }

    public Controller loadRandom() {
        daoModel.loadRandom();
        return this;
    }
}