package edu.uoc.onlinestore.model;


import java.util.List;
import java.util.Optional;


import edu.uoc.onlinestore.exception.GlobalExceptionHandler;


public class ListItem extends ListStore<Item> {


    @Override
    public void add(Item item) throws Exception {

        if(item == null){
            throw new GlobalExceptionHandler("Object client is null");
        }

        if(item.getCode() == 0 ){
            throw new GlobalExceptionHandler("Code is mandatory");
        }

        Optional<Item> optionalItem = getLists()
                .stream().filter(c -> c.getCode() == item.getCode()).findFirst();

        if(optionalItem.isPresent()){
            throw new GlobalExceptionHandler("Exists an item with the Code "+ item.getCode());
        }

        if(Float.isNaN(item.getSalePrice())){
            throw new GlobalExceptionHandler("The price has to be a number ");
        }

        if (Float.valueOf(item.getSalePrice()) <= 0 ){
            throw new GlobalExceptionHandler("The price cannot be zero ");
        }

        if(Float.isNaN(item.getShippingCosts())){
            throw new GlobalExceptionHandler("The shipping costs has to be a number ");
        }

        if (Float.valueOf(item.getShippingCosts()) <= 0 ){
            throw new GlobalExceptionHandler("The shipping costs cannot be zero ");
        }


        if (item.getPreparationTime() <= 0){
            throw new GlobalExceptionHandler("The preparation time cannot be zero or less ");
        }
        super.add(item);
        this.getItemDAO().create(super.getGenericItem());
        //super.add(item);

    }
    @Override
    public List<Item> getAll(List<Item> itemList) throws Exception{
        List<Item> itemListDao = this.getItemDAO().findAll();
        return super.getAll(itemListDao);
    }

    /*@Override
    public void delete(Item item) throws Exception {

        if (super.getSize() == 0){
            throw new GlobalExceptionHandler("The item list is empty ");
        }

        if (super.getLists().contains(item)){
            super.delete(item);
        }
        else{
            throw new GlobalExceptionHandler("This item does not exist ");
        }
        this.getItemDAO().delete(item);
    }*/

    @Override
    public void clear() throws Exception{

        if (super.getSize() == 0){
            throw new GlobalExceptionHandler("The item list is already empty ");
        }
        super.clear();
    }
}
