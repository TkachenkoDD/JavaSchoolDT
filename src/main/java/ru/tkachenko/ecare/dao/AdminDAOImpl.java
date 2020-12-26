package ru.tkachenko.ecare.dao;

import org.springframework.stereotype.Repository;
import ru.tkachenko.ecare.models.Admin;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class AdminDAOImpl implements GenericDAO<Admin> {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Admin> showAll() {
        return entityManager.createQuery("FROM Admin").getResultList();
    }

    @Override
    public Admin showById(int id) {
        return entityManager.find(Admin.class, id);
    }

    @Override
    public void save(Admin admin) {
        entityManager.persist(admin);
    }

    @Override
    public void update(Admin admin) {
        entityManager.merge(admin);
    }

    @Override
    public void delete(Admin admin) {
        admin = entityManager.find(Admin.class, admin.getId());
        entityManager.remove(admin);
    }
}
