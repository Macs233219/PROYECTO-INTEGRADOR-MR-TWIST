/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daos;

import daos.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades.DistribucionTotal;
import entidades.Sucursal;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author marlon
 */
public class SucursalJpaController implements Serializable {

    public SucursalJpaController() {
        this.emf = Persistence.createEntityManagerFactory("MR_TWIST");
    }

    public SucursalJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Sucursal sucursal) {
        if (sucursal.getDistribucionesTotales() == null) {
            sucursal.setDistribucionesTotales(new ArrayList<DistribucionTotal>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<DistribucionTotal> attachedDistribucionesTotales = new ArrayList<DistribucionTotal>();
            for (DistribucionTotal distribucionesTotalesDistribucionTotalToAttach : sucursal.getDistribucionesTotales()) {
                distribucionesTotalesDistribucionTotalToAttach = em.getReference(distribucionesTotalesDistribucionTotalToAttach.getClass(), distribucionesTotalesDistribucionTotalToAttach.getId());
                attachedDistribucionesTotales.add(distribucionesTotalesDistribucionTotalToAttach);
            }
            sucursal.setDistribucionesTotales(attachedDistribucionesTotales);
            em.persist(sucursal);
            for (DistribucionTotal distribucionesTotalesDistribucionTotal : sucursal.getDistribucionesTotales()) {
                Sucursal oldSucursalOfDistribucionesTotalesDistribucionTotal = distribucionesTotalesDistribucionTotal.getSucursal();
                distribucionesTotalesDistribucionTotal.setSucursal(sucursal);
                distribucionesTotalesDistribucionTotal = em.merge(distribucionesTotalesDistribucionTotal);
                if (oldSucursalOfDistribucionesTotalesDistribucionTotal != null) {
                    oldSucursalOfDistribucionesTotalesDistribucionTotal.getDistribucionesTotales().remove(distribucionesTotalesDistribucionTotal);
                    oldSucursalOfDistribucionesTotalesDistribucionTotal = em.merge(oldSucursalOfDistribucionesTotalesDistribucionTotal);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Sucursal sucursal) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Sucursal persistentSucursal = em.find(Sucursal.class, sucursal.getId());
            List<DistribucionTotal> distribucionesTotalesOld = persistentSucursal.getDistribucionesTotales();
            List<DistribucionTotal> distribucionesTotalesNew = sucursal.getDistribucionesTotales();
            List<DistribucionTotal> attachedDistribucionesTotalesNew = new ArrayList<DistribucionTotal>();
            for (DistribucionTotal distribucionesTotalesNewDistribucionTotalToAttach : distribucionesTotalesNew) {
                distribucionesTotalesNewDistribucionTotalToAttach = em.getReference(distribucionesTotalesNewDistribucionTotalToAttach.getClass(), distribucionesTotalesNewDistribucionTotalToAttach.getId());
                attachedDistribucionesTotalesNew.add(distribucionesTotalesNewDistribucionTotalToAttach);
            }
            distribucionesTotalesNew = attachedDistribucionesTotalesNew;
            sucursal.setDistribucionesTotales(distribucionesTotalesNew);
            sucursal = em.merge(sucursal);
            for (DistribucionTotal distribucionesTotalesOldDistribucionTotal : distribucionesTotalesOld) {
                if (!distribucionesTotalesNew.contains(distribucionesTotalesOldDistribucionTotal)) {
                    distribucionesTotalesOldDistribucionTotal.setSucursal(null);
                    distribucionesTotalesOldDistribucionTotal = em.merge(distribucionesTotalesOldDistribucionTotal);
                }
            }
            for (DistribucionTotal distribucionesTotalesNewDistribucionTotal : distribucionesTotalesNew) {
                if (!distribucionesTotalesOld.contains(distribucionesTotalesNewDistribucionTotal)) {
                    Sucursal oldSucursalOfDistribucionesTotalesNewDistribucionTotal = distribucionesTotalesNewDistribucionTotal.getSucursal();
                    distribucionesTotalesNewDistribucionTotal.setSucursal(sucursal);
                    distribucionesTotalesNewDistribucionTotal = em.merge(distribucionesTotalesNewDistribucionTotal);
                    if (oldSucursalOfDistribucionesTotalesNewDistribucionTotal != null && !oldSucursalOfDistribucionesTotalesNewDistribucionTotal.equals(sucursal)) {
                        oldSucursalOfDistribucionesTotalesNewDistribucionTotal.getDistribucionesTotales().remove(distribucionesTotalesNewDistribucionTotal);
                        oldSucursalOfDistribucionesTotalesNewDistribucionTotal = em.merge(oldSucursalOfDistribucionesTotalesNewDistribucionTotal);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = sucursal.getId();
                if (findSucursal(id) == null) {
                    throw new NonexistentEntityException("The sucursal with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Sucursal sucursal;
            try {
                sucursal = em.getReference(Sucursal.class, id);
                sucursal.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The sucursal with id " + id + " no longer exists.", enfe);
            }
            List<DistribucionTotal> distribucionesTotales = sucursal.getDistribucionesTotales();
            for (DistribucionTotal distribucionesTotalesDistribucionTotal : distribucionesTotales) {
                distribucionesTotalesDistribucionTotal.setSucursal(null);
                distribucionesTotalesDistribucionTotal = em.merge(distribucionesTotalesDistribucionTotal);
            }
            em.remove(sucursal);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Sucursal> findSucursalEntities() {
        return findSucursalEntities(true, -1, -1);
    }

    public List<Sucursal> findSucursalEntities(int maxResults, int firstResult) {
        return findSucursalEntities(false, maxResults, firstResult);
    }

    private List<Sucursal> findSucursalEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Sucursal.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Sucursal findSucursal(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Sucursal.class, id);
        } finally {
            em.close();
        }
    }

    public int getSucursalCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Sucursal> rt = cq.from(Sucursal.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
