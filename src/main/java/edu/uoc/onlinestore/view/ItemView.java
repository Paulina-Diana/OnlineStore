package edu.uoc.onlinestore.view;

import java.util.List;
import java.util.Scanner;
import edu.uoc.onlinestore.validations.CustomValidations;

public class ItemView {
    Scanner sc = new Scanner(System.in);
    CustomValidations validation = new CustomValidations();

    public String Options(){
        String option;
        System.out.println("Choose one option: ");
        System.out.println("(S)Show items, (A)Add item, (D)Delete item, (U)Update item, (X)Exit item menu");
        option = sc.nextLine();
        return option;
    }
    public String addCode(){
        System.out.println("Enter code of item: ");
        String code = sc.nextLine();

        while(validation.notEmptyString(code)){
            System.out.println("The code cannot be empty");
            code = sc.nextLine();
        }
        return code;
    }
    public String addDescription(){
        System.out.println("Enter description of item: ");
        String description = sc.nextLine();
        return description;
    }
    public String addSalePrice(){
        System.out.println("Enter sale price of item: ");
        String salePrice = sc.nextLine();

        while(validation.notEmptyString(salePrice)){
            System.out.println("The sale price cannot be empty");
            salePrice = sc.nextLine();
        }
        return salePrice;
    }
    public String addShippingCosts(){
        System.out.println("Enter shipping costs of item: ");
        String shippingCosts = sc.nextLine();

        while (validation.notEmptyString(shippingCosts)){
            System.out.println("The shipping costs cannot be empty");
            shippingCosts = sc.nextLine();
        }
        return shippingCosts;
    }
    public String addPreparationTime(){
        System.out.println("Enter preparation time of item: ");
        String preparationTime = sc.nextLine();

        while (validation.notEmptyString(preparationTime)){
            System.out.println("The preparation time cannot be empty");
            preparationTime = sc.nextLine();
        }
        return preparationTime;
    }
    public String[] addItem(){
        String code, description, salePrice, shippingCosts, preparationTime;
        code = addCode();
        description = addDescription();
        salePrice = addSalePrice();
        shippingCosts = addShippingCosts();
        preparationTime = addPreparationTime();
        return new String[]{code, description, salePrice, shippingCosts, preparationTime};
    }
    public String deleteItem(){
        String code = addCode();
        return code;
    }

    public String[] updateItem(){
        String code, description, salePrice, shippingCosts, preparationTime;
        code = addCode();
        description = addDescription();
        salePrice = addSalePrice();
        shippingCosts = addShippingCosts();
        preparationTime = addPreparationTime();
        return new String[]{code, description, salePrice, shippingCosts, preparationTime};
    }

    public void show(List<String> items ){

        System.out.println("## Item List");
        if(items != null && items.size() > 0) {
            for (String c : items) {
                System.out.println(c);
            }
        } else {
            System.out.println("## There are no items");
        }

    }
    public void itemAdded(){
        System.out.println("The item has been added successfully");
    }
    public void errorAdding(Exception e){
        System.out.println("The item already exists, please try again. Cause: " + e);
    }
    public void itemDeleted(){
        System.out.println("The item has been removed successfully");
    }
    public void errorDeleting(Exception e){
        System.out.println("The item doesnt exists, please try again. Cause: " + e);
    }
    public void itemUpdated(){
        System.out.println("The item has been updated successfully");
    }
    public void errorUpdating(Exception e){
        System.out.println("The item doesnt exists, please try again. Cause: " + e);
    }
}
