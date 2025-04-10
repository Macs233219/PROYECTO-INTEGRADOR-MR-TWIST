/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daos;

import daos.exceptions.NonexistentEntityException;
import entidades.EntradaInventario;
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
public class EntradaInventarioJpaController implements Serializable {

    public EntradaInventarioJpaController() {
        this.emf = Persistence.createEntityManagerFactory("MR_TWIST");
    }

    public EntradaInventarioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(EntradaInventario entradaInventario) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Producto producto = entradaInventario.getProducto();
            if (producto != null) {
                producto = em.getReference(producto.getClass(), producto.getId());
                entradaInventario.setProducto(producto);
            }
            Usuario usuario = entradaInventario.getUsuario();
            if (usuario != null) {
                usuario = em.getReference(usuario.getClass(), usuario.getId());
                entradaInventario.setUsuario(usuario);
            }
            em.persist(entradaInventario);
            if (producto != null) {
                producto.getEntradasInventario().add(entradaInventario);
                producto = em.merge(producto);
            }
            if (usuario != null) {
                usuario.getEntradasInventario().add(entradaInventario);
                usuario = em.merge(usuario);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(EntradaInventario entradaInventario) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            EntradaInventario persistentEntradaInventario = em.find(EntradaInventario.class, entradaInventario.getId());
            Producto productoOld = persistentEntradaInventario.getProducto();
            Producto productoNew = entradaInventario.getProducto();
            Usuario usuarioOld = persistentEntradaInventario.getUsuario();
            Usuario usuarioNew = entradaInventario.getUsuario();
            if (productoNew != null) {
                productoNew = em.getReference(productoNew.getClass(), productoNew.getId());
                entradaInventario.setProducto(productoNew);
            }
            if (usuarioNew != null) {
                usuarioNew = em.getReference(usuarioNew.getClass(), usuarioNew.getId());
                entradaInventario.setUsuario(usuarioNew);
            }
            entradaInventario = em.merge(entradaInventario);
            if (productoOld != null && !productoOld.equals(productoNew)) {
                productoOld.getEntradasInventario().remove(entradaInventario);
                productoOld = em.merge(productoOld);
            }
            if (productoNew != null && !productoNew.equals(productoOld)) {
                productoNew.getEntradasInventario().add(entradaInventario);
                productoNew = em.merge(productoNew);
            }
            if (usuarioOld != null && !usuarioOld.equals(usuarioNew)) {
                usuarioOld.getEntradasInventario().remove(entradaInventario);
                usuarioOld = em.merge(usuarioOld);
            }
            if (usuarioNew != null && !usuarioNew.equals(usuarioOld)) {
                usuarioNew.getEntradasInventario().add(entradaInventario);
                usuarioNew = em.merge(usuarioNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = entradaInventario.getId();
                if (findEntradaInventario(id) == null) {
                    throw new NonexistentEntityException("The entradaInventario with id " + id + " no longer exists.");
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
            EntradaInventario entradaInventario;
            try {
                entradaInventario = em.getReference(EntradaInventario.class, id);
                entradaInventario.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The entradaInventario with id " + id + " no longer exists.", enfe);
            }
            Producto producto = entradaInventario.getProducto();
            if (producto != null) {
                producto.getEntradasInventario().remove(entradaInventario);
                producto = em.merge(producto);
            }
            Usuario usuario = entradaInventario.getUsuario();
            if (usuario != null) {
                usuario.getEntradasInventario().remove(entradaInventario);
                usuario = em.merge(usuario);
            }
            em.remove(entradaInventario);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<EntradaInventario> findEntradaInventarioEntities() {
        return findEntradaInventarioEntities(true, -1, -1);
    }

    public List<EntradaInventario> findEntradaInventarioEntities(int maxResults, int firstResult) {
        return findEntradaInventarioEntities(false, maxResults, firstResult);
    }

    private List<EntradaInventario> findEntradaInventarioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(EntradaInventario.class));
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

    public EntradaInventario findEntradaInventario(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(EntradaInventario.class, id);
        } finally {
            em.close();
        }
    }

    public int getEntradaInventarioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<EntradaInventario> rt = cq.from(EntradaInventario.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
