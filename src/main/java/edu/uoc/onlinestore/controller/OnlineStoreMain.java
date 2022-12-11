package edu.uoc.onlinestore.controller;

import edu.uoc.onlinestore.view.ClientView;
import edu.uoc.onlinestore.view.ItemView;
import edu.uoc.onlinestore.view.MainMenu;
import edu.uoc.onlinestore.view.OrderView;

public class OnlineStoreMain {

    public static void main(String[] args) throws Exception {
        ClientView clientView = new ClientView();
        ClientController clientController = new ClientController(clientView);
        ItemView itemView = new ItemView();
        ItemController itemController = new ItemController(itemView);
        OrderView orderView = new OrderView();
        OrderController orderController = new OrderController(orderView);
        MainMenu mainMenu = new MainMenu();
        String menuOption;
        String subMenuOption;

        do{
            mainMenu.showOptions();
            menuOption = mainMenu.sendOption();
            switch (menuOption.toLowerCase()){
                case "c":
                    do {
                        subMenuOption = clientView.Options();
                        if (subMenuOption.toLowerCase().equals("s")) {
                            clientController.show();
                        } else if (subMenuOption.toLowerCase().equals("a")) {
                            clientController.add();
                        }
                        else if (subMenuOption.toLowerCase().equals("d")) {
                            clientController.delete();
                        }
                        else if (subMenuOption.toLowerCase().equals("u")) {
                            clientController.update();
                        }
                    }while (!subMenuOption.toLowerCase().equals("x"));
                    break;
                case "i":
                    do {
                        subMenuOption = itemView.Options();
                        if (subMenuOption.toLowerCase().equals("s")) {
                            itemController.show();
                        } else if (subMenuOption.toLowerCase().equals("a")) {
                            itemController.add();
                        }
                        else if (subMenuOption.toLowerCase().equals("d")) {
                            itemController.delete();
                        }
                        else if (subMenuOption.toLowerCase().equals("u")) {
                            itemController.update();
                        }
                    }while (!subMenuOption.toLowerCase().equals("x"));
                    break;
                case "o":
                    do {
                        subMenuOption = orderView.Options();
                        if (subMenuOption.toLowerCase().equals("s")) {
                            orderController.show();
                        } else if (subMenuOption.toLowerCase().equals("a")) {
                            orderController.add();
                        }
                        else if (subMenuOption.toLowerCase().equals("d")) {
                            orderController.delete();
                        }
                        else if (subMenuOption.toLowerCase().equals("u")) {
                            orderController.update();
                        }
                    }while (!subMenuOption.toLowerCase().equals("x"));
                    break;
                case "x":
                    mainMenu.exitingProgram();
                    break;
                default:
                    mainMenu.invalidOption();
            }
        }while (!menuOption.toLowerCase().equals("x"));
    }
}