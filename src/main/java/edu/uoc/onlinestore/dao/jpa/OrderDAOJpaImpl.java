package edu.uoc.onlinestore.dao.jpa;

import edu.uoc.onlinestore.dao.OrderDAO;
import edu.uoc.onlinestore.dao.entities.OrderEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.sql.SQLException;
import java.util.List;

public class OrderDAOJpaImpl implements OrderDAO<OrderEntity> {


    private EntityManagerFactory entityManagerFactory;

    public OrderDAOJpaImpl(){
        entityManagerFactory = Persistence.createEntityManagerFactory( "edu.uoc.onlinestore.dao.jpa" );

    }


    @Override
    public List<OrderEntity> findAll() throws SQLException {

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        List<OrderEntity> result = entityManager.createQuery( "from OrderEntity", OrderEntity.class ).getResultList();
        entityManager.getTransaction().commit();
        entityManager.close();

        return result;
    }

    @Override
    public OrderEntity findById(OrderEntity orderEntity) throws SQLException {

        if(orderEntity == null)
            throw new SQLException("ORDER invalid");

        return findById(orderEntity.getCode());

    }

    @Override
    public void create(OrderEntity orderEntity) throws SQLException {

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(orderEntity);
        entityManager.getTransaction().commit();
        entityManager.close();

    }

    @Override
    public void delete(OrderEntity orderEntity) throws SQLException {

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        OrderEntity orderEntityDelete = entityManager.find(OrderEntity.class,orderEntity.getCode());
        entityManager.remove(orderEntityDelete);
        entityManager.getTransaction().commit();
        entityManager.close();

    }

    @Override
    public void update(OrderEntity orderEntity) throws SQLException {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.merge(orderEntity);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public OrderEntity findById(Integer id) throws SQLException {

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        OrderEntity orderEntity = entityManager.find( OrderEntity.class, id );
        entityManager.getTransaction().commit();
        entityManager.close();

        return orderEntity;

    }
}
