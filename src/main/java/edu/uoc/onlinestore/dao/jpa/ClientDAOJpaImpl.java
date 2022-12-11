package edu.uoc.onlinestore.dao.jpa;

import edu.uoc.onlinestore.dao.ClientDAO;
import edu.uoc.onlinestore.dao.entities.ClientEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.sql.SQLException;
import java.util.List;

public class ClientDAOJpaImpl implements ClientDAO<ClientEntity> {

    private EntityManagerFactory entityManagerFactory;

    public ClientDAOJpaImpl(){
        entityManagerFactory = Persistence.createEntityManagerFactory( "edu.uoc.onlinestore.dao.jpa" );

    }

    @Override
    public ClientEntity findById(String id) throws SQLException {

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        ClientEntity clientEntity = entityManager.find( ClientEntity.class, id );
        entityManager.getTransaction().commit();
        entityManager.close();

        return clientEntity;
    }

    @Override
    public List<ClientEntity> findAll() throws SQLException {

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        List<ClientEntity> result = entityManager.createQuery( "from ClientEntity", ClientEntity.class ).getResultList();
        entityManager.getTransaction().commit();
        entityManager.close();

        return result;
    }

    @Override
    public ClientEntity findById(ClientEntity clientEntity) throws SQLException {
        return null;
    }

    @Override
    public void create(ClientEntity clientEntity) throws SQLException {


        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(clientEntity);
        entityManager.getTransaction().commit();
        entityManager.close();

    }

    @Override
    public void delete(ClientEntity clientEntity) throws SQLException {

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        ClientEntity clientEntityDelete = entityManager.find(ClientEntity.class,clientEntity.getEmail());
        entityManager.remove(clientEntityDelete);
        entityManager.getTransaction().commit();
        entityManager.close();

    }

    @Override
    public void update(ClientEntity clientEntity) throws SQLException {

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.merge(clientEntity);
        entityManager.getTransaction().commit();
        entityManager.close();

    }
}
