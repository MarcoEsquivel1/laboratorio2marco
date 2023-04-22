package services;

import interfaces.Operations;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import models.VentasEntity;
import utils.AdapterJPA;

import java.util.List;

public class ventaService implements Operations<VentasEntity> {
private final EntityManager em = AdapterJPA.getEntityManagerFactory();

    @Override
    public List<VentasEntity> listar() {
        em.clear();
        TypedQuery<VentasEntity> query = em.createQuery("SELECT v FROM VentasEntity v JOIN FETCH v.empleadosByIdEmpleado", VentasEntity.class);
        return query.getResultList();
    }

    @Override
    public VentasEntity buscar(int id) {
        return em.find(VentasEntity.class, id);
    }

    @Override
    public void insertar(VentasEntity ventasEntity) {
        em.getTransaction().begin();
        em.persist(ventasEntity);
        em.getTransaction().commit();
        em.clear();
    }

    @Override
    public void actualizar(VentasEntity ventasEntity) {
        em.getTransaction().begin();
        em.merge(ventasEntity);
        em.getTransaction().commit();
        em.clear();
    }

    @Override
    public void eliminar(int id) {
        em.getTransaction().begin();
        VentasEntity ventasEntity = em.find(VentasEntity.class, id);
        em.remove(em.merge(ventasEntity));
        em.getTransaction().commit();
        em.clear();
    }

    public List<VentasEntity> listar(int id) {
        em.clear();
        TypedQuery<VentasEntity> query = em.createQuery("SELECT v FROM VentasEntity v JOIN FETCH v.empleadosByIdEmpleado WHERE v.empleadosByIdEmpleado.idEmpleado = :id", VentasEntity.class);
        query.setParameter("id", id);
        return query.getResultList();
    }
}
