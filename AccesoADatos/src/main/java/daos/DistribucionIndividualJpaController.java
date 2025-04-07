/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daos;

import daos.exceptions.NonexistentEntityException;
import entidades.DistribucionIndividual;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades.DistribucionTotal;
import entidades.Producto;
import entidades.Usuario;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author marlon
 */
public class DistribucionIndividualJpaController implements Serializable {

    public DistribucionIndividualJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(DistribucionIndividual distribucionIndividual) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            DistribucionTotal distribucionTotal = distribucionIndividual.getDistribucionTotal();
            if (distribucionTotal != null) {
                distribucionTotal = em.getReference(distribucionTotal.getClass(), distribucionTotal.getId());
                distribucionIndividual.setDistribucionTotal(distribucionTotal);
            }
            Producto producto = distribucionIndividual.getProducto();
            if (producto != null) {
                producto = em.getReference(producto.getClass(), producto.getId());
                distribucionIndividual.setProducto(producto);
            }
            Usuario usuario = distribucionIndividual.getUsuario();
            if (usuario != null) {
                usuario = em.getReference(usuario.getClass(), usuario.getId());
                distribucionIndividual.setUsuario(usuario);
            }
            em.persist(distribucionIndividual);
            if (distribucionTotal != null) {
                distribucionTotal.getDistribucionesIndividuales().add(distribucionIndividual);
                distribucionTotal = em.merge(distribucionTotal);
            }
            if (producto != null) {
                producto.getSalidasInventario().add(distribucionIndividual);
                producto = em.merge(producto);
            }
            if (usuario != null) {
                usuario.getSalidasInventario().add(distribucionIndividual);
                usuario = em.merge(usuario);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(DistribucionIndividual distribucionIndividual) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            DistribucionIndividual persistentDistribucionIndividual = em.find(DistribucionIndividual.class, distribucionIndividual.getId());
            DistribucionTotal distribucionTotalOld = persistentDistribucionIndividual.getDistribucionTotal();
            DistribucionTotal distribucionTotalNew = distribucionIndividual.getDistribucionTotal();
            Producto productoOld = persistentDistribucionIndividual.getProducto();
            Producto productoNew = distribucionIndividual.getProducto();
            Usuario usuarioOld = persistentDistribucionIndividual.getUsuario();
            Usuario usuarioNew = distribucionIndividual.getUsuario();
            if (distribucionTotalNew != null) {
                distribucionTotalNew = em.getReference(distribucionTotalNew.getClass(), distribucionTotalNew.getId());
                distribucionIndividual.setDistribucionTotal(distribucionTotalNew);
            }
            if (productoNew != null) {
                productoNew = em.getReference(productoNew.getClass(), productoNew.getId());
                distribucionIndividual.setProducto(productoNew);
            }
            if (usuarioNew != null) {
                usuarioNew = em.getReference(usuarioNew.getClass(), usuarioNew.getId());
                distribucionIndividual.setUsuario(usuarioNew);
            }
            distribucionIndividual = em.merge(distribucionIndividual);
            if (distribucionTotalOld != null && !distribucionTotalOld.equals(distribucionTotalNew)) {
                distribucionTotalOld.getDistribucionesIndividuales().remove(distribucionIndividual);
                distribucionTotalOld = em.merge(distribucionTotalOld);
            }
            if (distribucionTotalNew != null && !distribucionTotalNew.equals(distribucionTotalOld)) {
                distribucionTotalNew.getDistribucionesIndividuales().add(distribucionIndividual);
                distribucionTotalNew = em.merge(distribucionTotalNew);
            }
            if (productoOld != null && !productoOld.equals(productoNew)) {
                productoOld.getSalidasInventario().remove(distribucionIndividual);
                productoOld = em.merge(productoOld);
            }
            if (productoNew != null && !productoNew.equals(productoOld)) {
                productoNew.getSalidasInventario().add(distribucionIndividual);
                productoNew = em.merge(productoNew);
            }
            if (usuarioOld != null && !usuarioOld.equals(usuarioNew)) {
                usuarioOld.getSalidasInventario().remove(distribucionIndividual);
                usuarioOld = em.merge(usuarioOld);
            }
            if (usuarioNew != null && !usuarioNew.equals(usuarioOld)) {
                usuarioNew.getSalidasInventario().add(distribucionIndividual);
                usuarioNew = em.merge(usuarioNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = distribucionIndividual.getId();
                if (findDistribucionIndividual(id) == null) {
                    throw new NonexistentEntityException("The distribucionIndividual with id " + id + " no longer exists.");
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
            DistribucionIndividual distribucionIndividual;
            try {
                distribucionIndividual = em.getReference(DistribucionIndividual.class, id);
                distribucionIndividual.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The distribucionIndividual with id " + id + " no longer exists.", enfe);
            }
            DistribucionTotal distribucionTotal = distribucionIndividual.getDistribucionTotal();
            if (distribucionTotal != null) {
                distribucionTotal.getDistribucionesIndividuales().remove(distribucionIndividual);
                distribucionTotal = em.merge(distribucionTotal);
            }
            Producto producto = distribucionIndividual.getProducto();
            if (producto != null) {
                producto.getSalidasInventario().remove(distribucionIndividual);
                producto = em.merge(producto);
            }
            Usuario usuario = distribucionIndividual.getUsuario();
            if (usuario != null) {
                usuario.getSalidasInventario().remove(distribucionIndividual);
                usuario = em.merge(usuario);
            }
            em.remove(distribucionIndividual);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<DistribucionIndividual> findDistribucionIndividualEntities() {
        return findDistribucionIndividualEntities(true, -1, -1);
    }

    public List<DistribucionIndividual> findDistribucionIndividualEntities(int maxResults, int firstResult) {
        return findDistribucionIndividualEntities(false, maxResults, firstResult);
    }

    private List<DistribucionIndividual> findDistribucionIndividualEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(DistribucionIndividual.class));
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

    public DistribucionIndividual findDistribucionIndividual(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(DistribucionIndividual.class, id);
        } finally {
            em.close();
        }
    }

    public int getDistribucionIndividualCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<DistribucionIndividual> rt = cq.from(DistribucionIndividual.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
