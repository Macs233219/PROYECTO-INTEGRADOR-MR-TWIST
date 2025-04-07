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
import entidades.Sucursal;
import entidades.DistribucionIndividual;
import entidades.DistribucionTotal;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author marlon
 */
public class DistribucionTotalJpaController implements Serializable {

    public DistribucionTotalJpaController() {
        this.emf = Persistence.createEntityManagerFactory("MR_TWIST");
    }

    public DistribucionTotalJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(DistribucionTotal distribucionTotal) {
        if (distribucionTotal.getDistribucionesIndividuales() == null) {
            distribucionTotal.setDistribucionesIndividuales(new ArrayList<DistribucionIndividual>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Sucursal sucursal = distribucionTotal.getSucursal();
            if (sucursal != null) {
                sucursal = em.getReference(sucursal.getClass(), sucursal.getId());
                distribucionTotal.setSucursal(sucursal);
            }
            List<DistribucionIndividual> attachedDistribucionesIndividuales = new ArrayList<DistribucionIndividual>();
            for (DistribucionIndividual distribucionesIndividualesDistribucionIndividualToAttach : distribucionTotal.getDistribucionesIndividuales()) {
                distribucionesIndividualesDistribucionIndividualToAttach = em.getReference(distribucionesIndividualesDistribucionIndividualToAttach.getClass(), distribucionesIndividualesDistribucionIndividualToAttach.getId());
                attachedDistribucionesIndividuales.add(distribucionesIndividualesDistribucionIndividualToAttach);
            }
            distribucionTotal.setDistribucionesIndividuales(attachedDistribucionesIndividuales);
            em.persist(distribucionTotal);
            if (sucursal != null) {
                sucursal.getDistribucionesTotales().add(distribucionTotal);
                sucursal = em.merge(sucursal);
            }
            for (DistribucionIndividual distribucionesIndividualesDistribucionIndividual : distribucionTotal.getDistribucionesIndividuales()) {
                DistribucionTotal oldDistribucionTotalOfDistribucionesIndividualesDistribucionIndividual = distribucionesIndividualesDistribucionIndividual.getDistribucionTotal();
                distribucionesIndividualesDistribucionIndividual.setDistribucionTotal(distribucionTotal);
                distribucionesIndividualesDistribucionIndividual = em.merge(distribucionesIndividualesDistribucionIndividual);
                if (oldDistribucionTotalOfDistribucionesIndividualesDistribucionIndividual != null) {
                    oldDistribucionTotalOfDistribucionesIndividualesDistribucionIndividual.getDistribucionesIndividuales().remove(distribucionesIndividualesDistribucionIndividual);
                    oldDistribucionTotalOfDistribucionesIndividualesDistribucionIndividual = em.merge(oldDistribucionTotalOfDistribucionesIndividualesDistribucionIndividual);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(DistribucionTotal distribucionTotal) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            DistribucionTotal persistentDistribucionTotal = em.find(DistribucionTotal.class, distribucionTotal.getId());
            Sucursal sucursalOld = persistentDistribucionTotal.getSucursal();
            Sucursal sucursalNew = distribucionTotal.getSucursal();
            List<DistribucionIndividual> distribucionesIndividualesOld = persistentDistribucionTotal.getDistribucionesIndividuales();
            List<DistribucionIndividual> distribucionesIndividualesNew = distribucionTotal.getDistribucionesIndividuales();
            if (sucursalNew != null) {
                sucursalNew = em.getReference(sucursalNew.getClass(), sucursalNew.getId());
                distribucionTotal.setSucursal(sucursalNew);
            }
            List<DistribucionIndividual> attachedDistribucionesIndividualesNew = new ArrayList<DistribucionIndividual>();
            for (DistribucionIndividual distribucionesIndividualesNewDistribucionIndividualToAttach : distribucionesIndividualesNew) {
                distribucionesIndividualesNewDistribucionIndividualToAttach = em.getReference(distribucionesIndividualesNewDistribucionIndividualToAttach.getClass(), distribucionesIndividualesNewDistribucionIndividualToAttach.getId());
                attachedDistribucionesIndividualesNew.add(distribucionesIndividualesNewDistribucionIndividualToAttach);
            }
            distribucionesIndividualesNew = attachedDistribucionesIndividualesNew;
            distribucionTotal.setDistribucionesIndividuales(distribucionesIndividualesNew);
            distribucionTotal = em.merge(distribucionTotal);
            if (sucursalOld != null && !sucursalOld.equals(sucursalNew)) {
                sucursalOld.getDistribucionesTotales().remove(distribucionTotal);
                sucursalOld = em.merge(sucursalOld);
            }
            if (sucursalNew != null && !sucursalNew.equals(sucursalOld)) {
                sucursalNew.getDistribucionesTotales().add(distribucionTotal);
                sucursalNew = em.merge(sucursalNew);
            }
            for (DistribucionIndividual distribucionesIndividualesOldDistribucionIndividual : distribucionesIndividualesOld) {
                if (!distribucionesIndividualesNew.contains(distribucionesIndividualesOldDistribucionIndividual)) {
                    distribucionesIndividualesOldDistribucionIndividual.setDistribucionTotal(null);
                    distribucionesIndividualesOldDistribucionIndividual = em.merge(distribucionesIndividualesOldDistribucionIndividual);
                }
            }
            for (DistribucionIndividual distribucionesIndividualesNewDistribucionIndividual : distribucionesIndividualesNew) {
                if (!distribucionesIndividualesOld.contains(distribucionesIndividualesNewDistribucionIndividual)) {
                    DistribucionTotal oldDistribucionTotalOfDistribucionesIndividualesNewDistribucionIndividual = distribucionesIndividualesNewDistribucionIndividual.getDistribucionTotal();
                    distribucionesIndividualesNewDistribucionIndividual.setDistribucionTotal(distribucionTotal);
                    distribucionesIndividualesNewDistribucionIndividual = em.merge(distribucionesIndividualesNewDistribucionIndividual);
                    if (oldDistribucionTotalOfDistribucionesIndividualesNewDistribucionIndividual != null && !oldDistribucionTotalOfDistribucionesIndividualesNewDistribucionIndividual.equals(distribucionTotal)) {
                        oldDistribucionTotalOfDistribucionesIndividualesNewDistribucionIndividual.getDistribucionesIndividuales().remove(distribucionesIndividualesNewDistribucionIndividual);
                        oldDistribucionTotalOfDistribucionesIndividualesNewDistribucionIndividual = em.merge(oldDistribucionTotalOfDistribucionesIndividualesNewDistribucionIndividual);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = distribucionTotal.getId();
                if (findDistribucionTotal(id) == null) {
                    throw new NonexistentEntityException("The distribucionTotal with id " + id + " no longer exists.");
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
            DistribucionTotal distribucionTotal;
            try {
                distribucionTotal = em.getReference(DistribucionTotal.class, id);
                distribucionTotal.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The distribucionTotal with id " + id + " no longer exists.", enfe);
            }
            Sucursal sucursal = distribucionTotal.getSucursal();
            if (sucursal != null) {
                sucursal.getDistribucionesTotales().remove(distribucionTotal);
                sucursal = em.merge(sucursal);
            }
            List<DistribucionIndividual> distribucionesIndividuales = distribucionTotal.getDistribucionesIndividuales();
            for (DistribucionIndividual distribucionesIndividualesDistribucionIndividual : distribucionesIndividuales) {
                distribucionesIndividualesDistribucionIndividual.setDistribucionTotal(null);
                distribucionesIndividualesDistribucionIndividual = em.merge(distribucionesIndividualesDistribucionIndividual);
            }
            em.remove(distribucionTotal);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<DistribucionTotal> findDistribucionTotalEntities() {
        return findDistribucionTotalEntities(true, -1, -1);
    }

    public List<DistribucionTotal> findDistribucionTotalEntities(int maxResults, int firstResult) {
        return findDistribucionTotalEntities(false, maxResults, firstResult);
    }

    private List<DistribucionTotal> findDistribucionTotalEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(DistribucionTotal.class));
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

    public DistribucionTotal findDistribucionTotal(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(DistribucionTotal.class, id);
        } finally {
            em.close();
        }
    }

    public int getDistribucionTotalCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<DistribucionTotal> rt = cq.from(DistribucionTotal.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
