package edu.uoc.onlinestore.view;

import java.util.List;
import java.util.Scanner;
import edu.uoc.onlinestore.validations.CustomValidations;

public class OrderView {
    Scanner sc = new Scanner(System.in);
    CustomValidations validation = new CustomValidations();

    public String Options(){
        String option;
        System.out.println("Choose one option: ");
        System.out.println("(S)Show orders, (A)Add order, (D)Delete order, (U)Update order, (X)Exit order menu");
        option = sc.nextLine();
        return option;
    }
    public String addCode(){
        System.out.println("Enter code of order: ");
        String code = sc.nextLine();

        while (validation.notEmptyString(code)){
            System.out.println("The code cannot be empty");
            code = sc.nextLine();
        }
        return code;
    }
    public String addAmount(){
        System.out.println("Enter the amount of the item for the order: ");
        String amount = sc.nextLine();

        while (validation.notEmptyString(amount)){
            System.out.println("The amount cannot be empty");
            amount = sc.nextLine();
        }
        return amount;
    }
    public String addClient(){
        System.out.println("Enter e-mail address of the order's customer: ");
        String email = sc.nextLine();

        while (validation.notEmptyString(email)){
            System.out.println("The email cannot be empty");
            email = sc.nextLine();
        }
        return email;
    }
    public String addItem(){
        System.out.println("Enter item code of the order: ");
        String codeItem = sc.nextLine();

        while (validation.notEmptyString(codeItem)){
            System.out.println("The code cannot be empty");
            codeItem = sc.nextLine();
        }
        return codeItem;
    }
    public String[] addOrder(){
        String code = addCode();
        String amount = addAmount();
        String email = addClient();
        String codeItem = addItem();
        return new String[]{code, amount, email, codeItem};
    }

    public String deleteOrder(){
        String code = addCode();
        return code;
    }

    public String[] updateOrder(){
        String code = addCode();
        String amount = addAmount();
        String email = addClient();
        String codeItem = addItem();
        return new String[]{code, amount, email, codeItem};
    }


    public void show(List<String> orders ){
        System.out.println("## Order List");
        if(orders != null && orders.size() > 0) {
            for (String c : orders) {
                System.out.println(c);
            }
        } else {
            System.out.println("## There are no orders");
        }
    }
    public void orderAdded(){
        System.out.println("The order has been added successfully");
    }
    public void errorAdding(Exception e){
        System.out.println("The client or item does not exists, please try again. Cause: " + e);
    }
    public void orderDeleted(){
        System.out.println("The order has been removed successfully");
    }
    public void errorDeleting(Exception e){
        System.out.println("The order doesn't exists, please try again. Cause: " + e);
    }
    public void orderUpdated(){
        System.out.println("The order has been updated successfully");
    }
    public void errorUpdating(Exception e){
        System.out.println("The order doesn't exist, please try again. Cause: "+ e);
    }
}
