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
import entidades.Mantenimiento;
import entidades.Maquina;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author marlon
 */
public class MaquinaJpaController implements Serializable {

    public MaquinaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Maquina maquina) {
        if (maquina.getMantenimientos() == null) {
            maquina.setMantenimientos(new ArrayList<Mantenimiento>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Mantenimiento> attachedMantenimientos = new ArrayList<Mantenimiento>();
            for (Mantenimiento mantenimientosMantenimientoToAttach : maquina.getMantenimientos()) {
                mantenimientosMantenimientoToAttach = em.getReference(mantenimientosMantenimientoToAttach.getClass(), mantenimientosMantenimientoToAttach.getId());
                attachedMantenimientos.add(mantenimientosMantenimientoToAttach);
            }
            maquina.setMantenimientos(attachedMantenimientos);
            em.persist(maquina);
            for (Mantenimiento mantenimientosMantenimiento : maquina.getMantenimientos()) {
                Maquina oldMaquinaOfMantenimientosMantenimiento = mantenimientosMantenimiento.getMaquina();
                mantenimientosMantenimiento.setMaquina(maquina);
                mantenimientosMantenimiento = em.merge(mantenimientosMantenimiento);
                if (oldMaquinaOfMantenimientosMantenimiento != null) {
                    oldMaquinaOfMantenimientosMantenimiento.getMantenimientos().remove(mantenimientosMantenimiento);
                    oldMaquinaOfMantenimientosMantenimiento = em.merge(oldMaquinaOfMantenimientosMantenimiento);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Maquina maquina) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Maquina persistentMaquina = em.find(Maquina.class, maquina.getId());
            List<Mantenimiento> mantenimientosOld = persistentMaquina.getMantenimientos();
            List<Mantenimiento> mantenimientosNew = maquina.getMantenimientos();
            List<Mantenimiento> attachedMantenimientosNew = new ArrayList<Mantenimiento>();
            for (Mantenimiento mantenimientosNewMantenimientoToAttach : mantenimientosNew) {
                mantenimientosNewMantenimientoToAttach = em.getReference(mantenimientosNewMantenimientoToAttach.getClass(), mantenimientosNewMantenimientoToAttach.getId());
                attachedMantenimientosNew.add(mantenimientosNewMantenimientoToAttach);
            }
            mantenimientosNew = attachedMantenimientosNew;
            maquina.setMantenimientos(mantenimientosNew);
            maquina = em.merge(maquina);
            for (Mantenimiento mantenimientosOldMantenimiento : mantenimientosOld) {
                if (!mantenimientosNew.contains(mantenimientosOldMantenimiento)) {
                    mantenimientosOldMantenimiento.setMaquina(null);
                    mantenimientosOldMantenimiento = em.merge(mantenimientosOldMantenimiento);
                }
            }
            for (Mantenimiento mantenimientosNewMantenimiento : mantenimientosNew) {
                if (!mantenimientosOld.contains(mantenimientosNewMantenimiento)) {
                    Maquina oldMaquinaOfMantenimientosNewMantenimiento = mantenimientosNewMantenimiento.getMaquina();
                    mantenimientosNewMantenimiento.setMaquina(maquina);
                    mantenimientosNewMantenimiento = em.merge(mantenimientosNewMantenimiento);
                    if (oldMaquinaOfMantenimientosNewMantenimiento != null && !oldMaquinaOfMantenimientosNewMantenimiento.equals(maquina)) {
                        oldMaquinaOfMantenimientosNewMantenimiento.getMantenimientos().remove(mantenimientosNewMantenimiento);
                        oldMaquinaOfMantenimientosNewMantenimiento = em.merge(oldMaquinaOfMantenimientosNewMantenimiento);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = maquina.getId();
                if (findMaquina(id) == null) {
                    throw new NonexistentEntityException("The maquina with id " + id + " no longer exists.");
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
            Maquina maquina;
            try {
                maquina = em.getReference(Maquina.class, id);
                maquina.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The maquina with id " + id + " no longer exists.", enfe);
            }
            List<Mantenimiento> mantenimientos = maquina.getMantenimientos();
            for (Mantenimiento mantenimientosMantenimiento : mantenimientos) {
                mantenimientosMantenimiento.setMaquina(null);
                mantenimientosMantenimiento = em.merge(mantenimientosMantenimiento);
            }
            em.remove(maquina);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Maquina> findMaquinaEntities() {
        return findMaquinaEntities(true, -1, -1);
    }

    public List<Maquina> findMaquinaEntities(int maxResults, int firstResult) {
        return findMaquinaEntities(false, maxResults, firstResult);
    }

    private List<Maquina> findMaquinaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Maquina.class));
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

    public Maquina findMaquina(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Maquina.class, id);
        } finally {
            em.close();
        }
    }

    public int getMaquinaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Maquina> rt = cq.from(Maquina.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
