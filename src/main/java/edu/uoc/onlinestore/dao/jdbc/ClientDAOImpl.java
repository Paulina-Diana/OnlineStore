package edu.uoc.onlinestore.dao.jdbc;

import edu.uoc.onlinestore.dao.ClientDAO;
import edu.uoc.onlinestore.dao.entity.ClientEntity;
import edu.uoc.onlinestore.dao.entities.ClientType;
import edu.uoc.onlinestore.dao.factory.MysqlDAOFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientDAOImpl implements ClientDAO<ClientEntity> {

    @Override
    public List<ClientEntity> findAll() throws SQLException {
        List<ClientEntity> clientList = new ArrayList<>();
        String query = "select * from client";
        try (Connection con = MysqlDAOFactory.getConnection();
             Statement stmt = con.createStatement();) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                String name = rs.getString("name");
                String address = rs.getString("address");
                String nif = rs.getString("nif");
                String email = rs.getString("email");
                String type = rs.getString("type");
                float quota = rs.getFloat("quota");
                float discount = rs.getFloat("discount");
                ClientType clientType = ClientType.PREMIUM.name().equalsIgnoreCase(type)? ClientType.PREMIUM:ClientType.STANDARD;
                ClientEntity client = new ClientEntity(name,address,nif,email,clientType,quota,discount);
                clientList.add(client);
            }
        } catch (SQLException e) {
            System.err.println(e);
        }
        return clientList;
    }
    @Override
    public ClientEntity findById(String id) throws SQLException {

        ClientEntity client = null;
        String query = "select name, address, nif, email, type, quota, discount from client where email = ?";
        try (Connection con = MysqlDAOFactory.getConnection();
             PreparedStatement stmt = con.prepareStatement(query);) {
            stmt.setString(1,id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String name = rs.getString("name");
                String address = rs.getString("address");
                String nif = rs.getString("nif");
                String email = rs.getString("email");
                String type = rs.getString("type");
                float quota = rs.getFloat("quota");
                float discount = rs.getFloat("discount");

                ClientType clientType = ClientType.PREMIUM.name().equalsIgnoreCase(type)? ClientType.PREMIUM:ClientType.STANDARD;
                client = new ClientEntity(name,address,nif,email,clientType,quota,discount);


            }
            if(client == null)
                throw new SQLException("Client not found by "+id);

        } catch (SQLException e) {
            throw e;
        }
        return client;
    }
    @Override
    public ClientEntity findById(ClientEntity client) throws SQLException { return null;}


    @Override
    public void create(ClientEntity client) throws SQLException {


        String sqlInsert ="INSERT INTO client (email,name,address,nif,type,quota,discount) VALUES (?,?,?,?,?,?,?);";
        try(Connection con = MysqlDAOFactory.getConnection();
            PreparedStatement stmt = con.prepareStatement(sqlInsert);) {

            if(client == null)
                throw  new IllegalStateException("The object Client is null");
            if(client.getEmail() == null || client.getEmail().isEmpty())
                throw  new IllegalStateException("The Client.getEmail is null or empty");

            if(client.getName() == null || client.getName().isEmpty())
                throw  new IllegalStateException("The Client.getName is null or empty");

            if(client.getAddress() == null || client.getAddress().isEmpty())
                throw  new IllegalStateException("The Client.getAddress is null or empty");

            if(client.getNif() == null || client.getNif().isEmpty())
                throw  new IllegalStateException("The Client.getNif is null or empty");


            stmt.setString(1,client.getEmail());
            stmt.setString(2,client.getName());
            stmt.setString(3,client.getAddress());
            stmt.setString(4,client.getNif());
            stmt.setString(5,client.getType().name());
            stmt.setFloat(6,client.getQuota());
            stmt.setFloat(7,client.getDiscount());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } catch (IllegalStateException e){
            throw e;
        }

    }

    @Override
    public void delete(ClientEntity client) throws SQLException {
        String sqlDelete = "DELETE FROM client where email = ?";
        try(Connection con = MysqlDAOFactory.getConnection();
            PreparedStatement stmt = con.prepareStatement(sqlDelete);) {

            if(client == null)
                throw  new IllegalStateException("The object Client is null");
            if(client.getEmail() == null || client.getEmail().isEmpty())
                throw  new IllegalStateException("The Client.getEmail is null or empty");

            stmt.setString(1,client.getEmail());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } catch (IllegalStateException e){
            throw e;
        }
    }

    @Override
    public void update(ClientEntity client) throws SQLException {
        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE client ");
        sql.append(" SET name = ?,");
        sql.append("  address = ?,");
        sql.append("  nif = ?,");
        sql.append("  type = ?,");
        sql.append("  quota = ?,");
        sql.append("  discount = ?");
        sql.append(" WHERE email = ?");
        try(Connection con = MysqlDAOFactory.getConnection();
            PreparedStatement stmt = con.prepareStatement(sql.toString());) {

            if(client == null)
                throw  new IllegalStateException("The object Client is null");
            if(client.getEmail() == null || client.getEmail().isEmpty())
                throw  new IllegalStateException("The Client.getEmail is null or empty");

            if(client.getName() == null || client.getName().isEmpty())
                throw  new IllegalStateException("The Client.getName is null or empty");

            if(client.getAddress() == null || client.getAddress().isEmpty())
                throw  new IllegalStateException("The Client.getAddress is null or empty");

            if(client.getNif() == null || client.getNif().isEmpty())
                throw  new IllegalStateException("The Client.getNif is null or empty");

            stmt.setString(1,client.getName());
            stmt.setString(2,client.getAddress());
            stmt.setString(3,client.getNif());
            stmt.setString(4,client.getType().name());
            stmt.setFloat(5,client.getQuota());
            stmt.setFloat(6,client.getDiscount());
            stmt.setString(7,client.getEmail());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw e;
        }catch (IllegalStateException e){
            throw e;
        }
    }


}
