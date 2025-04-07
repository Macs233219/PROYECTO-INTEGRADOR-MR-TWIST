/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daos;

import daos.exceptions.NonexistentEntityException;
import entidades.Merma;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades.Producto;
import entidades.Usuario;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author marlon
 */
public class MermaJpaController implements Serializable {

    public MermaJpaController() {
        this.emf = Persistence.createEntityManagerFactory("MR_TWIST");
    }

    public MermaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Merma merma) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Producto producto = merma.getProducto();
            if (producto != null) {
                producto = em.getReference(producto.getClass(), producto.getId());
                merma.setProducto(producto);
            }
            Usuario usuario = merma.getUsuario();
            if (usuario != null) {
                usuario = em.getReference(usuario.getClass(), usuario.getId());
                merma.setUsuario(usuario);
            }
            em.persist(merma);
            if (producto != null) {
                producto.getSalidasInventario().add(merma);
                producto = em.merge(producto);
            }
            if (usuario != null) {
                usuario.getSalidasInventario().add(merma);
                usuario = em.merge(usuario);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Merma merma) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Merma persistentMerma = em.find(Merma.class, merma.getId());
            Producto productoOld = persistentMerma.getProducto();
            Producto productoNew = merma.getProducto();
            Usuario usuarioOld = persistentMerma.getUsuario();
            Usuario usuarioNew = merma.getUsuario();
            if (productoNew != null) {
                productoNew = em.getReference(productoNew.getClass(), productoNew.getId());
                merma.setProducto(productoNew);
            }
            if (usuarioNew != null) {
                usuarioNew = em.getReference(usuarioNew.getClass(), usuarioNew.getId());
                merma.setUsuario(usuarioNew);
            }
            merma = em.merge(merma);
            if (productoOld != null && !productoOld.equals(productoNew)) {
                productoOld.getSalidasInventario().remove(merma);
                productoOld = em.merge(productoOld);
            }
            if (productoNew != null && !productoNew.equals(productoOld)) {
                productoNew.getSalidasInventario().add(merma);
                productoNew = em.merge(productoNew);
            }
            if (usuarioOld != null && !usuarioOld.equals(usuarioNew)) {
                usuarioOld.getSalidasInventario().remove(merma);
                usuarioOld = em.merge(usuarioOld);
            }
            if (usuarioNew != null && !usuarioNew.equals(usuarioOld)) {
                usuarioNew.getSalidasInventario().add(merma);
                usuarioNew = em.merge(usuarioNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = merma.getId();
                if (findMerma(id) == null) {
                    throw new NonexistentEntityException("The merma with id " + id + " no longer exists.");
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
            Merma merma;
            try {
                merma = em.getReference(Merma.class, id);
                merma.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The merma with id " + id + " no longer exists.", enfe);
            }
            Producto producto = merma.getProducto();
            if (producto != null) {
                producto.getSalidasInventario().remove(merma);
                producto = em.merge(producto);
            }
            Usuario usuario = merma.getUsuario();
            if (usuario != null) {
                usuario.getSalidasInventario().remove(merma);
                usuario = em.merge(usuario);
            }
            em.remove(merma);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Merma> findMermaEntities() {
        return findMermaEntities(true, -1, -1);
    }

    public List<Merma> findMermaEntities(int maxResults, int firstResult) {
        return findMermaEntities(false, maxResults, firstResult);
    }

    private List<Merma> findMermaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Merma.class));
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

    public Merma findMerma(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Merma.class, id);
        } finally {
            em.close();
        }
    }

    public int getMermaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Merma> rt = cq.from(Merma.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
