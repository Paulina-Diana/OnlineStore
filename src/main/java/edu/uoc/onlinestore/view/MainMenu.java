package edu.uoc.onlinestore.view;

import java.util.Scanner;

public class MainMenu {
    public void showOptions(){
        System.out.println("Choose one option: ");
        System.out.println("(C)Client management, (I)Item management, (O)Order management, (X)Exit");
    }
    public String sendOption(){
        Scanner sc = new Scanner(System.in);
        String option = sc.nextLine();
        return option;
    }
    public void exitingProgram(){
        System.out.println("Exiting the program");
    }
    public void invalidOption() {
        System.out.println("Invalid option");
    }
}
