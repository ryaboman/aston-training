package Aston1Stage.graduation_project.controllers;

public class MainController extends Controller {

    public MainController(){
        super("Главное меню", null);

        Controller busController = new BusController(this);
        Controller studentController = new StudentController(this);
        Controller userController = new UserController(this);

        menuController.addItem(1, "\uD83D\uDDC0 Автобусы", busController::getHandler);
        menuController.addItem(2, "\uD83D\uDDC0 Студенты", studentController::getHandler);
        menuController.addItem(3, "\uD83D\uDDC0 Пользователи", userController::getHandler);
        menuController.addItem(0, "⮤ Выход", this::exit);
        menuController.setAnnotation("Выберите коллекцию для работы:");
    }

    public Controller exit(){
        System.exit(0);
        return parentController;
    }

}


