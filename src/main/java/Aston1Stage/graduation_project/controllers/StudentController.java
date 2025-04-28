package Aston1Stage.graduation_project.controllers;

import Aston1Stage.graduation_project.dao.DAOModel;
import Aston1Stage.graduation_project.dao.DAOStudent;

public class StudentController extends Controller {

    private final DAOModel daoStudent;

    public StudentController(Controller controller) {
        super("Меню работы со студентами", controller);
        daoStudent = new DAOStudent();
        Controller addStudentController = new AddController(this, daoStudent);
        menuController.addItem(1, "Добавить студента", addStudentController::getHandler);
        menuController.addItem(2, "Сортировать коллекцию", this::sortCollection);
        menuController.addItem(3, "Найти студента", this::findStudent);
        menuController.addItem(4, "Сохранить коллекцию", this::saveToFile);
        menuController.addItem(5, "Вывести на экран коллекцию", this::printElements);
        menuController.addItem(6, "Магическая сортировка", this::magicSortCollection);
        menuController.addItem(0, "⮌ Назад", controller::getHandler);
    }


    private Controller sortCollection() {
        daoStudent.sortElements();
        return this;
    }

    private Controller magicSortCollection() {
        daoStudent.magicSortElements();
        return this;
    }

    private Controller findStudent() {
        daoStudent.findElement();
        return this;
    }

    private Controller saveToFile() {
        daoStudent.saveToFile();
        return this;
    }

    private Controller printElements(){
        daoStudent.printElements();
        return this;
    }

}
