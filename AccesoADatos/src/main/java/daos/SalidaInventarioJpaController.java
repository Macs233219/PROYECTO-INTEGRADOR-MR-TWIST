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
import entidades.Producto;
import entidades.SalidaInventario;
import entidades.Usuario;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author marlon
 */
public class SalidaInventarioJpaController implements Serializable {

    public SalidaInventarioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(SalidaInventario salidaInventario) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Producto producto = salidaInventario.getProducto();
            if (producto != null) {
                producto = em.getReference(producto.getClass(), producto.getId());
                salidaInventario.setProducto(producto);
            }
            Usuario usuario = salidaInventario.getUsuario();
            if (usuario != null) {
                usuario = em.getReference(usuario.getClass(), usuario.getId());
                salidaInventario.setUsuario(usuario);
            }
            em.persist(salidaInventario);
            if (producto != null) {
                producto.getSalidasInventario().add(salidaInventario);
                producto = em.merge(producto);
            }
            if (usuario != null) {
                usuario.getSalidasInventario().add(salidaInventario);
                usuario = em.merge(usuario);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(SalidaInventario salidaInventario) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SalidaInventario persistentSalidaInventario = em.find(SalidaInventario.class, salidaInventario.getId());
            Producto productoOld = persistentSalidaInventario.getProducto();
            Producto productoNew = salidaInventario.getProducto();
            Usuario usuarioOld = persistentSalidaInventario.getUsuario();
            Usuario usuarioNew = salidaInventario.getUsuario();
            if (productoNew != null) {
                productoNew = em.getReference(productoNew.getClass(), productoNew.getId());
                salidaInventario.setProducto(productoNew);
            }
            if (usuarioNew != null) {
                usuarioNew = em.getReference(usuarioNew.getClass(), usuarioNew.getId());
                salidaInventario.setUsuario(usuarioNew);
            }
            salidaInventario = em.merge(salidaInventario);
            if (productoOld != null && !productoOld.equals(productoNew)) {
                productoOld.getSalidasInventario().remove(salidaInventario);
                productoOld = em.merge(productoOld);
            }
            if (productoNew != null && !productoNew.equals(productoOld)) {
                productoNew.getSalidasInventario().add(salidaInventario);
                productoNew = em.merge(productoNew);
            }
            if (usuarioOld != null && !usuarioOld.equals(usuarioNew)) {
                usuarioOld.getSalidasInventario().remove(salidaInventario);
                usuarioOld = em.merge(usuarioOld);
            }
            if (usuarioNew != null && !usuarioNew.equals(usuarioOld)) {
                usuarioNew.getSalidasInventario().add(salidaInventario);
                usuarioNew = em.merge(usuarioNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = salidaInventario.getId();
                if (findSalidaInventario(id) == null) {
                    throw new NonexistentEntityException("The salidaInventario with id " + id + " no longer exists.");
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
            SalidaInventario salidaInventario;
            try {
                salidaInventario = em.getReference(SalidaInventario.class, id);
                salidaInventario.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The salidaInventario with id " + id + " no longer exists.", enfe);
            }
            Producto producto = salidaInventario.getProducto();
            if (producto != null) {
                producto.getSalidasInventario().remove(salidaInventario);
                producto = em.merge(producto);
            }
            Usuario usuario = salidaInventario.getUsuario();
            if (usuario != null) {
                usuario.getSalidasInventario().remove(salidaInventario);
                usuario = em.merge(usuario);
            }
            em.remove(salidaInventario);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<SalidaInventario> findSalidaInventarioEntities() {
        return findSalidaInventarioEntities(true, -1, -1);
    }

    public List<SalidaInventario> findSalidaInventarioEntities(int maxResults, int firstResult) {
        return findSalidaInventarioEntities(false, maxResults, firstResult);
    }

    private List<SalidaInventario> findSalidaInventarioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(SalidaInventario.class));
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

    public SalidaInventario findSalidaInventario(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(SalidaInventario.class, id);
        } finally {
            em.close();
        }
    }

    public int getSalidaInventarioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<SalidaInventario> rt = cq.from(SalidaInventario.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
