package Aston1Stage.graduation_project.menu;

import Aston1Stage.graduation_project.controllers.Controller;
import Aston1Stage.graduation_project.controllers.IHandler;
import Aston1Stage.graduation_project.util.CustomArrayList;

public class MenuController {
    String title;
    String annotation;
    CustomArrayList<Integer> numbersItemMenu;
    CustomArrayList<String> labelItemsMenu;
    CustomArrayList<IHandler<Controller>> handlers;

    public MenuController(String title) {
        this.title = title;
        this.annotation = "";
        numbersItemMenu = new CustomArrayList<>();
        labelItemsMenu = new CustomArrayList<>();
        handlers = new CustomArrayList<>();
    }

    public void setAnnotation(String annotation) {
        this.annotation = annotation;
    }

    public String getTitle() {
        return title;
    }

    public void addItem(Integer numberItem, String labelItem, IHandler<Controller> handler) {
        numbersItemMenu.add(numberItem);
        labelItemsMenu.add(labelItem);
        handlers.add(handler);
    }

    public String buildMenu() {
        StringBuilder menuItems = new StringBuilder();
        menuItems.append("-----------------").append("\n");
        menuItems.append(title).append("\n");
        menuItems.append("-----------------").append("\n");
        if(!annotation.isEmpty()) {
            menuItems.append(annotation).append("\n");
        }
        for (int i = 0; i < numbersItemMenu.size(); i++) {
            menuItems.append(numbersItemMenu.get(i)).append(" > ");
            menuItems.append(labelItemsMenu.get(i)).append("\n");
        }
        menuItems.append("? > ");

        return menuItems.toString();
    }

    public IHandler<Controller> getHandler(int index) {
        for(int i = 0; i < numbersItemMenu.size(); i++) {
            if (numbersItemMenu.get(i) == index) {
                return handlers.get(i);
            }
        }
        return null;
    }

    public boolean containsItem(int numberMenu) {
        for(int i = 0; i < numbersItemMenu.size(); i++) {
            if(numbersItemMenu.get(i) == numberMenu) {
                return true;
            }
        }
        return false;
    }

}
