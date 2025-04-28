package Aston1Stage.graduation_project.application;

import Aston1Stage.graduation_project.controllers.Controller;
import Aston1Stage.graduation_project.controllers.MainController;

import java.util.Scanner;


public class Application {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Controller controller = new MainController();
        String inputLine;
        do {
            System.out.print(controller.getMenu());
            inputLine = scanner.nextLine();
            controller = controller.process(inputLine);
        }
        while (controller != null);
    }
}