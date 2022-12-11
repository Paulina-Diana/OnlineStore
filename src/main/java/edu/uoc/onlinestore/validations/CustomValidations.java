package edu.uoc.onlinestore.validations;

public class CustomValidations {

    public CustomValidations(){

    }

    public boolean hasOnlyLetters(String value){
       return value.matches("[a-zA-Z\\s\'\"]+");
    }

    public boolean notEmptyString(String value){
        return value.isEmpty();
    }



}
