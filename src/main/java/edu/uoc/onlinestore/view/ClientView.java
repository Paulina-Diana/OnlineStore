package edu.uoc.onlinestore.view;

import java.util.List;
import java.util.Scanner;

import edu.uoc.onlinestore.validations.CustomValidations;


public class ClientView {
    Scanner sc = new Scanner(System.in);
    CustomValidations validation = new CustomValidations();
    public String Options(){
        String option;
        System.out.println("Choose one option: ");
        System.out.println("(S)Show clients, (A)Add client, (D)Delete client, (U)Update client, (X)Exit client menu");
        option = sc.nextLine();
        return option;
    }
    public String addName(){
        System.out.println("Enter name of client: ");
        String name = sc.nextLine();
        while(!validation.hasOnlyLetters(name)){
            System.out.println("Name not valid, try again");
            name = sc.nextLine();
        }
        return name;
    }
    public String addAddress(){
        System.out.println("Enter address of client: ");
        String address = sc.nextLine();
        while(validation.notEmptyString(address)){
            System.out.println("Address cannot be empty, try again");
            address = sc.nextLine();
        }
        return address;
    }
    public String addNif(){
        System.out.println("Enter NIF of client: ");
        String nif = sc.nextLine();
        while(validation.notEmptyString(nif)){
            System.out.println("Nif cannot be empty, try again");
            nif = sc.nextLine();
        }
        return nif;
    }
    public String addEmail(){
        System.out.println("Enter email of client: ");
        String email = sc.nextLine();
        while(validation.notEmptyString(email)){
            System.out.println("Email cannot be empty, try again");
            email = sc.nextLine();
        }
        return email;
    }

    public String addQuota(){
        System.out.println("Enter quota of client: ");
        String quota = sc.nextLine();
        while(validation.notEmptyString(quota)){
            System.out.println("The quota cannot be empty");
            quota = sc.nextLine();

        }
        return quota;
    }
    public String addDiscount(){
        System.out.println("Enter discount of client: ");
        String discount = sc.nextLine();
        while(validation.notEmptyString(discount)){
            System.out.println("The discount cannot be empty");
            discount = sc.nextLine();
        }
        return discount;
    }
    public String[] addClient(){
        String option, name, address, nif, email, quota = "", discount = "";
        name = addName();
        address = addAddress();
        nif = addNif();
        email = addEmail();
        System.out.println("Choose client type: ");

        System.out.println("(S)Standard client, (P)Premium client");
        option = sc.nextLine();
        switch (option.toLowerCase()) {
            case "s":
                quota = "0.0";
                discount = "0.0";
                break;
            case "p":
                quota = addQuota();
                discount = addDiscount();
                break;
            default:
                System.out.println("Not valid option. Please enter your option again");
        }

        return new String[]{name, address, nif, email, quota, discount};
    }

    public String deleteClient(){
        String email = addEmail();
        return email;
    }

    public String[] updateClient(){
        String name, address, nif, email, quota, discount;
        name = addName();
        address = addAddress();
        nif = addNif();
        email = addEmail();
        quota = addQuota();
        discount = addDiscount();
        return new String[]{name, address, nif, email, quota, discount};
    }

    public void show(List<String> clients ){

        System.out.println("## Client List");
        if(clients != null && clients.size() > 0 ) {
            for (String c : clients) {
                System.out.println(c);
            }
        } else {
            System.out.println("## There are no clients");
        }
    }
    public void clientAdded(){
        System.out.println("The client has been added successfully");
    }
    public void errorAdding(Exception e){
        System.out.println("The client already exists, please try again. Cause: " + e);
    }
    public void clientDeleted(){
        System.out.println("The client has been removed successfully");
    }
    public void errorDeleting(Exception e){
        System.out.println("The client doesnt exists, please try again. Cause: " + e);
    }
    public void clientUpdated(){
        System.out.println("The client has been updated successfully");
    }
    public void errorUpdating(Exception e){
        System.out.println("The client doesnt exists, please try again. Cause: " + e);
    }
}
