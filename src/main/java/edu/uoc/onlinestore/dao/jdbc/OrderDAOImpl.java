package edu.uoc.onlinestore.dao.jdbc;

import edu.uoc.onlinestore.dao.OrderDAO;
import edu.uoc.onlinestore.dao.factory.MysqlDAOFactory;
import edu.uoc.onlinestore.dao.entity.OrderEntity;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class OrderDAOImpl implements OrderDAO<OrderEntity> {
    @Override
    public List<OrderEntity> findAll() throws SQLException {
        List<OrderEntity> orderList = new ArrayList<>();
        String query = "select codeOrder, amount, email, code, order_date_time from orders";
        try (Connection con = MysqlDAOFactory.getConnection();
             PreparedStatement stmt = con.prepareStatement(query);) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {

                int codeOrder = rs.getInt(1);
                int amount = rs.getInt(2);
                String email = rs.getString(3);
                int code = rs.getInt(4);
                LocalDateTime order_date_time = rs.getTimestamp(5).toLocalDateTime();
                OrderEntity order = new OrderEntity(codeOrder, amount, email, code, order_date_time);
                orderList.add(order);
            }
        } catch (SQLException e) {
            throw e;
        }
        return orderList;
    }
    @Override
    public OrderEntity findById(Integer id) throws SQLException {
        OrderEntity order = null;
        String query = "select codeOrder, amount, email, code, order_date_time from orders where codeOrder = ?";
        try (Connection con = MysqlDAOFactory.getConnection();
             PreparedStatement stmt = con.prepareStatement(query);) {
            stmt.setInt(1,id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {

                int codeOrder = rs.getInt(1);
                int amount = rs.getInt(2);
                String email = rs.getString(3);
                int code = rs.getInt(4);
                LocalDateTime order_date_time = rs.getTimestamp(4).toLocalDateTime();
                order = new OrderEntity(codeOrder, amount, email, code, order_date_time);
            }
        } catch (SQLException e) {
            throw e;
        }
        return order;
    }
    @Override
    public OrderEntity findById(OrderEntity order) throws SQLException {return null;}

    @Override
    public void create(OrderEntity order) throws SQLException {

        String sqlInsert ="call create_order(?,?,?);";
        try (Connection con = MysqlDAOFactory.getConnection();
            CallableStatement stmt = con.prepareCall(sqlInsert)){
            if(order == null)
                throw  new IllegalStateException("The object Order is null");
            if(order.getEmail() == null || order.getEmail().isEmpty())
                throw  new IllegalStateException("The Order.getEmail is null or empty");
            if(order.getCode() == 0)
                throw  new IllegalStateException("The Order.getCode is invalid "+order.getCode());
            if(order.getAmount() <= 0)
                throw  new IllegalStateException("The Order.getAmount is invalid "+order.getAmount());

            stmt.setInt(1,order.getAmount());
            stmt.setString(2,order.getEmail());
            stmt.setInt(3,order.getCode());

            stmt.execute();
        }catch (SQLException e){
            throw e;
        } catch (IllegalStateException e){
            throw e;
        }
    }

    @Override
    public void delete(OrderEntity order) throws SQLException {
        String sqlDelete = "DELETE FROM orders where codeOrder = ?";
        try(Connection con = MysqlDAOFactory.getConnection();
            PreparedStatement stmt = con.prepareStatement(sqlDelete);){

            if(order == null)
                throw  new IllegalStateException("The object Order is null");

            stmt.setInt(1,order.getCodeOrder());
            stmt.executeUpdate();
        } catch (SQLException e){
            throw e;
        } catch (IllegalStateException e){
            throw e;
        }
    }

    @Override
    public void update(OrderEntity order) throws SQLException {
        String sqlUpdate = "UPDATE orders SET amount = ?, email = ?, code = ?, order_date_time = ? WHERE codeOrder = ?";
        try(Connection con = MysqlDAOFactory.getConnection();
            PreparedStatement stmt = con.prepareStatement(sqlUpdate);) {

            if(order == null)
                throw  new IllegalStateException("The object Client is null");
            if(order.getEmail() == null || order.getEmail().isEmpty())
                throw  new IllegalStateException("The Order.getEmail is null or empty");

            stmt.setInt(1,order.getAmount());
            stmt.setString(2,order.getEmail());
            stmt.setInt(3,order.getCode());
            stmt.setTimestamp(4,Timestamp.valueOf(order.getOrder_date_time()));
            stmt.setInt(5,order.getCodeOrder());
            stmt.executeUpdate();
        } catch (SQLException e){
            throw e;
        } catch (IllegalStateException e){
            throw e;
        }
    }
}
