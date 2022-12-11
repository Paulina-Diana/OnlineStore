package edu.uoc.onlinestore.dao.jdbc;

import edu.uoc.onlinestore.dao.ItemDAO;
import edu.uoc.onlinestore.dao.factory.MysqlDAOFactory;
import edu.uoc.onlinestore.model.Item;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ItemDAOImpl implements ItemDAO<Item> {
    @Override
    public List<Item> findAll() throws SQLException {
        List<Item> itemList = new ArrayList<>();
        String query = "select code,description,sale_price,shipping_costs,preparation_time  from item";

        try (Connection con = MysqlDAOFactory.getConnection();
             Statement stmt = con.createStatement();) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                int code = rs.getInt("code");
                String description = rs.getString("description");
                float salePrice = rs.getFloat("sale_price");
                float shippingCosts = rs.getFloat("shipping_costs");
                int preparationTime = rs.getInt("preparation_time");
                itemList.add(new Item(code, description, salePrice, shippingCosts, preparationTime));
            }
        } catch (SQLException e) {
           throw e;
        }
        return itemList;
    }

    @Override
    public Item findById(int id) throws SQLException {

        Item item = null;
        String query = "select code, description, sale_price, shipping_costs, preparation_time from item where code = ?";
        try (Connection con = MysqlDAOFactory.getConnection();
             PreparedStatement pstmt = con.prepareStatement(query);) {
            pstmt.setInt(1,id);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                int code = rs.getInt("code");
                String description = rs.getString("description");
                float salePrice = rs.getFloat("sale_price");
                float shippingCosts = rs.getFloat("shipping_costs");
                int preparationTime = rs.getInt("preparation_time");

                item = new Item(code,description,salePrice,shippingCosts,preparationTime);


            }
            if(item == null)
                throw new SQLException("Item not found by "+id);

        } catch (SQLException e) {
            throw e;
        }
        return item;
    }
    @Override
    public Item findById(Item item) throws SQLException {
        throw new SQLException("method not implemented");
    }

    @Override
    public void create(Item item) throws SQLException {

        String sql ="INSERT INTO item (description,sale_price,shipping_costs,preparation_time) VALUES (?,?,?,?)";
        try(Connection con = MysqlDAOFactory.getConnection();
            PreparedStatement pstmt = con.prepareStatement(sql);
            ) {
            if(item == null)
                throw  new IllegalStateException("The object Item is null");
            if(item.getDescription() == null || item.getDescription().isEmpty())
                throw  new IllegalStateException("The Item.getDescription is null or empty");

            pstmt.setString(1,item.getDescription());
            pstmt.setFloat(2,item.getSalePrice());
            pstmt.setFloat(3,item.getShippingCosts());
            pstmt.setInt(4,item.getPreparationTime());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw e;
        } catch (IllegalStateException e){
            throw e;
        }
    }

    @Override
    public void delete(Item item) throws SQLException {
        String sql = "DELETE FROM item where code = ?";
        try(Connection con = MysqlDAOFactory.getConnection();
            PreparedStatement pstmt = con.prepareStatement(sql);) {
            if(item == null)
                throw new IllegalStateException("The object item is null");
            pstmt.setInt(1,item.getCode());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } catch (IllegalStateException e){
            throw e;
        }

    }

    @Override
    public void update(Item item) throws SQLException {
        String sql = "UPDATE item SET description=?,sale_price=?,shipping_costs=?,preparation_time=? where code=?";
        try(Connection con = MysqlDAOFactory.getConnection();
            PreparedStatement pstmt = con.prepareStatement(sql);
            ) {
            if(item == null)
                throw  new IllegalStateException("The object Item is null");
            if(item.getDescription() == null || item.getDescription().isEmpty())
                throw  new IllegalStateException("The Item.getDescription is null or empty");

            pstmt.setString(1,item.getDescription());
            pstmt.setFloat(2,item.getSalePrice());
            pstmt.setFloat(3,item.getShippingCosts());
            pstmt.setInt(4,item.getPreparationTime());
            pstmt.setInt(5,item.getCode());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw e;
        } catch (IllegalStateException e){
            throw e;
        }

    }
}
