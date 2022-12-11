package edu.uoc.onlinestore.model;

import java.util.List;
import java.util.Optional;
import edu.uoc.onlinestore.exception.GlobalExceptionHandler;

public class ListClient extends ListStore<Client> {


    @Override
    public void add(Client client) throws Exception {

        if(client == null){
            throw new GlobalExceptionHandler("Object client is null");
        }

        if(client.getName() == null || client.getName().isEmpty()){
            throw new GlobalExceptionHandler("The name is required ");
        }

        if (client.getAddress() == null){
            throw new GlobalExceptionHandler("The address is required ");
        }

        if (client.getNif() == null || client.getNif().isEmpty()){
            throw new GlobalExceptionHandler("The id is required ");
        }

        if(client.getEmail() == null || client.getEmail().isEmpty()){
            throw new GlobalExceptionHandler("The Email is required");
        }

        Optional<Client> optionalClient = getLists()
                .stream().filter(c -> c.getEmail().equals(client.getEmail())).findFirst();

        if(optionalClient.isPresent()){
            throw new GlobalExceptionHandler("This client already exists with this Email "+ client.getEmail());
        }

        super.add(client);

    }

    @Override
    public void delete(Client client) throws Exception {

        if (super.getSize() == 0){
            throw new GlobalExceptionHandler("The client list is empty ");
        }

        if (super.getLists().contains(client)){
            super.delete(client);
        }
        else{
            throw new GlobalExceptionHandler("This client does not exist ");
        }
    }

    @Override
    public void clear() throws Exception{

        if (super.getSize() == 0){
            throw new GlobalExceptionHandler("The client list is already empty ");
        }
        super.clear();
    }
}
