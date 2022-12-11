package edu.uoc.onlinestore.dao.jpa;

import edu.uoc.onlinestore.dao.ItemDAO;
import edu.uoc.onlinestore.dao.entities.ClientEntity;
import edu.uoc.onlinestore.dao.entities.ItemEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.sql.SQLException;
import java.util.List;

public class ItemDAOJpaImpl implements ItemDAO<ItemEntity> {

    private EntityManagerFactory entityManagerFactory;

    public ItemDAOJpaImpl(){
        entityManagerFactory = Persistence.createEntityManagerFactory( "edu.uoc.onlinestore.dao.jpa" );

    }

    @Override
    public ItemEntity findById(int id) throws SQLException {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        ItemEntity itemEntity = entityManager.find( ItemEntity.class, id );
        entityManager.getTransaction().commit();
        entityManager.close();

        return itemEntity;
    }

    @Override
    public List<ItemEntity> findAll() throws SQLException {

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        List<ItemEntity> result = entityManager.createQuery( "from ItemEntity", ItemEntity.class ).getResultList();
        entityManager.getTransaction().commit();
        entityManager.close();

        return result;

    }

    @Override
    public ItemEntity findById(ItemEntity itemEntity) throws SQLException {
        if(itemEntity == null)
            throw new SQLException("item invalid");

        return findById(itemEntity.getCode());
    }

    @Override
    public void create(ItemEntity itemEntity) throws SQLException {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(itemEntity);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public void delete(ItemEntity itemEntity) throws SQLException {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        ItemEntity itemEntityDelete = entityManager.find(ItemEntity.class,itemEntity.getCode());
        entityManager.remove(itemEntityDelete);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public void update(ItemEntity itemEntity) throws SQLException {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.merge(itemEntity);
        entityManager.getTransaction().commit();
        entityManager.close();

    }


}
