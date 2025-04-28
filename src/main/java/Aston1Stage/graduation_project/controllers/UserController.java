package Aston1Stage.graduation_project.controllers;

import Aston1Stage.graduation_project.dao.DAOModel;
import Aston1Stage.graduation_project.dao.DAOUser;

public class UserController extends Controller {
    private final DAOModel daoUser;

    public UserController(Controller controller) {
        super("Меню работы с пользователями", controller);
        daoUser = new DAOUser();
        Controller addUserController = new AddController(this, daoUser);
        menuController.addItem(1, "Добавить пользователя", addUserController::getHandler);
        menuController.addItem(2, "Сортировать коллекцию", this::sortCollection);
        menuController.addItem(3, "Найти пользователя", this::findUser);
        menuController.addItem(4, "Сохранить коллекцию", this::saveToFile);
        menuController.addItem(5, "Вывести на экран коллекцию", this::printElements);
        menuController.addItem(6, "Магическая сортировка", this::magicSortCollection);
        menuController.addItem(0, "⮌ Назад", controller::getHandler);
    }

    private Controller sortCollection() {
        daoUser.sortElements();
        return this;
    }

    private Controller magicSortCollection() {
        daoUser.magicSortElements();
        return this;
    }

    private Controller findUser() {
        daoUser.findElement();
        return this;
    }

    private Controller saveToFile() {
        daoUser.saveToFile();
        return this;
    }

    private Controller printElements(){
        daoUser.printElements();
        return this;
    }

}

