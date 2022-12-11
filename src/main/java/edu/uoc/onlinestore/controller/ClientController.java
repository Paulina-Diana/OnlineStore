package edu.uoc.onlinestore.controller;

import edu.uoc.onlinestore.dao.ClientDAO;
import edu.uoc.onlinestore.dao.entities.ClientEntity;
import edu.uoc.onlinestore.dao.entities.ClientType;
import edu.uoc.onlinestore.dao.factory.DAOFactory;
import edu.uoc.onlinestore.view.ClientView;


import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ClientController {

    private ClientView clientView;
    private DAOFactory daoFactory;

    ClientDAO<ClientEntity> clientDAO;

    public ClientController(ClientView clientView) {
        this.clientView = clientView;
        daoFactory = DAOFactory.getDAOFactory(DAOFactory.Factory.MYSQL_JPA);
        clientDAO = daoFactory.getClientDAO();
    }
    public void add() throws Exception {
        String [] clientData = clientView.addClient();
        String name = clientData[0];
        String address = clientData[1];
        String nif = clientData[2];
        String email = clientData[3];
        String quota = clientData[4];
        String discount = clientData[5];

        Optional<ClientEntity> clientEntity = clientDAO.findAll().stream().filter(c-> c.getEmail().equalsIgnoreCase(email)).findFirst();


        try {
            if(!clientEntity.isPresent()){

                if (clientData[4].equals("0.0") && clientData[5].equals("0.0")){
                    ClientEntity newClientStandard = new ClientEntity(name, address, nif, email, ClientType.STANDARD,Float.parseFloat(quota),Float.parseFloat(discount));
                    clientDAO.create(newClientStandard);
                    clientView.clientAdded();
                }
                else{
                    ClientEntity newClientPremium = new ClientEntity(name, address, nif, email, ClientType.PREMIUM,Float.parseFloat(quota),Float.parseFloat(discount));
                    clientDAO.create(newClientPremium);
                    clientView.clientAdded();
                }
            }
            else {
                throw new Exception();
            }

        }catch(Exception e){
            clientView.errorAdding(e);
        }

    }

    public void delete() throws Exception{
        String email = clientView.deleteClient();
        Optional<ClientEntity> client = clientDAO.findAll().stream().filter(c -> c.getEmail().equalsIgnoreCase(email)).findFirst();
        try {
            if(client.isPresent()){
                ClientEntity clientEntity = new ClientEntity();
                clientEntity.setEmail(email);
                clientDAO.delete(clientEntity);
                clientView.clientDeleted();
            }
            else {
                throw  new Exception();
            }
        }catch(Exception e){
            clientView.errorDeleting(e);
        }
    }

    public void update() throws Exception{
        String [] clientData = clientView.updateClient();
        String name = clientData[0];
        String address = clientData[1];
        String nif = clientData[2];
        String email = clientData[3];
        String quota = clientData[4];
        String discount = clientData[5];
        Optional<ClientEntity> client = clientDAO.findAll().stream().filter(c-> c.getEmail().equals(email)).findFirst();

        try {
            if(client.isPresent()){
                ClientEntity newClient = new ClientEntity(name, address, nif, email, ClientType.STANDARD,Float.parseFloat(quota),Float.parseFloat(discount));
                clientDAO.update(newClient);
                clientView.clientUpdated();
            }
            else {
                throw new Exception();
            }
        }catch(Exception e){
            clientView.errorUpdating(e);
        }
    }

    public void show() throws Exception{
        List<ClientEntity> clientsData = clientDAO.findAll();
        List<String> clients = Collections.emptyList();
        if(clientsData != null)
            clients = clientsData.stream().map(c -> c.toString()).collect(Collectors.toList());

        clientView.show(clients);
    }

}