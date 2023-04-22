package services;

import interfaces.Operations;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import models.EmpleadosEntity;
import utils.AdapterJPA;

import java.util.List;

public class empleadoService implements Operations<EmpleadosEntity> {
    private final EntityManager em = AdapterJPA.getEntityManagerFactory();

    @Override
    public List<EmpleadosEntity> listar() {
        em.clear();
        TypedQuery<EmpleadosEntity> query = em.createQuery("SELECT e FROM EmpleadosEntity e", EmpleadosEntity.class);
        return query.getResultList();
    }

    @Override
    public EmpleadosEntity buscar(int id) {
        return em.find(EmpleadosEntity.class, id);
    }

    @Override
    public void insertar(EmpleadosEntity empleadoEntity) {
        em.getTransaction().begin();
        em.persist(empleadoEntity);
        em.getTransaction().commit();
        em.clear();
    }

    @Override
    public void actualizar(EmpleadosEntity empleadoEntity) {
        em.getTransaction().begin();
        em.merge(empleadoEntity);
        em.getTransaction().commit();
        em.clear();
    }

    @Override
    public void eliminar(int id) {
        em.getTransaction().begin();
        EmpleadosEntity empleado = em.find(EmpleadosEntity.class, id);
        em.remove(em.merge(empleado));
        em.getTransaction().commit();
        em.clear();
    }
}
