package edu.uoc.onlinestore.model;

import java.time.LocalDateTime;
import java.util.Optional;
import edu.uoc.onlinestore.exception.GlobalExceptionHandler;

public class ListOrder extends ListStore<Order> {

    @Override
    public void add(Order order) throws Exception {

        if(order == null){
            throw new GlobalExceptionHandler("Object order is null");
        }

        if(order.getCode() == 0 ){
            throw new GlobalExceptionHandler("The code is required ");
        }

        Optional<Order> optionalOrder = getLists()
                .stream().filter(c -> c.getCode() == order.getCode()).findFirst();

        if(optionalOrder.isPresent()){
            throw new GlobalExceptionHandler("Exists an order with this Code "+ order.getCode());
        }

        if (order.getAmount() <= 0){
            throw new GlobalExceptionHandler("The amount cannot be zero or less ");
        }

        if (order.getClient() == null){
            throw new GlobalExceptionHandler("The client cannot be null ");
        }

        if (order.getItem() == null){
            throw new GlobalExceptionHandler("The item cannot be null ");
        }

        if (order.getOrderDateTime() == null){
            throw new GlobalExceptionHandler("The date time cannot be null ");
        }

        super.add(order);
    }

    @Override
    public void delete(Order order) throws Exception {

        if (super.getSize() == 0){
            throw new GlobalExceptionHandler("The order list is empty ");
        }

        if (super.getLists().contains(order)){
            super.delete(order);
        }
        else{
            throw new GlobalExceptionHandler("This order does not exist ");
        }
    }

    @Override
    public void clear() throws Exception{

        if (super.getSize() == 0){
            throw new GlobalExceptionHandler("The order list is already empty ");
        }
        super.clear();
    }
}
