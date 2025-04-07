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
import entidades.EntradaInventario;
import entidades.Producto;
import java.util.ArrayList;
import java.util.List;
import entidades.SalidaInventario;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author marlon
 */
public class ProductoJpaController implements Serializable {

    public ProductoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Producto producto) {
        if (producto.getEntradasInventario() == null) {
            producto.setEntradasInventario(new ArrayList<EntradaInventario>());
        }
        if (producto.getSalidasInventario() == null) {
            producto.setSalidasInventario(new ArrayList<SalidaInventario>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<EntradaInventario> attachedEntradasInventario = new ArrayList<EntradaInventario>();
            for (EntradaInventario entradasInventarioEntradaInventarioToAttach : producto.getEntradasInventario()) {
                entradasInventarioEntradaInventarioToAttach = em.getReference(entradasInventarioEntradaInventarioToAttach.getClass(), entradasInventarioEntradaInventarioToAttach.getId());
                attachedEntradasInventario.add(entradasInventarioEntradaInventarioToAttach);
            }
            producto.setEntradasInventario(attachedEntradasInventario);
            List<SalidaInventario> attachedSalidasInventario = new ArrayList<SalidaInventario>();
            for (SalidaInventario salidasInventarioSalidaInventarioToAttach : producto.getSalidasInventario()) {
                salidasInventarioSalidaInventarioToAttach = em.getReference(salidasInventarioSalidaInventarioToAttach.getClass(), salidasInventarioSalidaInventarioToAttach.getId());
                attachedSalidasInventario.add(salidasInventarioSalidaInventarioToAttach);
            }
            producto.setSalidasInventario(attachedSalidasInventario);
            em.persist(producto);
            for (EntradaInventario entradasInventarioEntradaInventario : producto.getEntradasInventario()) {
                Producto oldProductoOfEntradasInventarioEntradaInventario = entradasInventarioEntradaInventario.getProducto();
                entradasInventarioEntradaInventario.setProducto(producto);
                entradasInventarioEntradaInventario = em.merge(entradasInventarioEntradaInventario);
                if (oldProductoOfEntradasInventarioEntradaInventario != null) {
                    oldProductoOfEntradasInventarioEntradaInventario.getEntradasInventario().remove(entradasInventarioEntradaInventario);
                    oldProductoOfEntradasInventarioEntradaInventario = em.merge(oldProductoOfEntradasInventarioEntradaInventario);
                }
            }
            for (SalidaInventario salidasInventarioSalidaInventario : producto.getSalidasInventario()) {
                Producto oldProductoOfSalidasInventarioSalidaInventario = salidasInventarioSalidaInventario.getProducto();
                salidasInventarioSalidaInventario.setProducto(producto);
                salidasInventarioSalidaInventario = em.merge(salidasInventarioSalidaInventario);
                if (oldProductoOfSalidasInventarioSalidaInventario != null) {
                    oldProductoOfSalidasInventarioSalidaInventario.getSalidasInventario().remove(salidasInventarioSalidaInventario);
                    oldProductoOfSalidasInventarioSalidaInventario = em.merge(oldProductoOfSalidasInventarioSalidaInventario);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Producto producto) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Producto persistentProducto = em.find(Producto.class, producto.getId());
            List<EntradaInventario> entradasInventarioOld = persistentProducto.getEntradasInventario();
            List<EntradaInventario> entradasInventarioNew = producto.getEntradasInventario();
            List<SalidaInventario> salidasInventarioOld = persistentProducto.getSalidasInventario();
            List<SalidaInventario> salidasInventarioNew = producto.getSalidasInventario();
            List<EntradaInventario> attachedEntradasInventarioNew = new ArrayList<EntradaInventario>();
            for (EntradaInventario entradasInventarioNewEntradaInventarioToAttach : entradasInventarioNew) {
                entradasInventarioNewEntradaInventarioToAttach = em.getReference(entradasInventarioNewEntradaInventarioToAttach.getClass(), entradasInventarioNewEntradaInventarioToAttach.getId());
                attachedEntradasInventarioNew.add(entradasInventarioNewEntradaInventarioToAttach);
            }
            entradasInventarioNew = attachedEntradasInventarioNew;
            producto.setEntradasInventario(entradasInventarioNew);
            List<SalidaInventario> attachedSalidasInventarioNew = new ArrayList<SalidaInventario>();
            for (SalidaInventario salidasInventarioNewSalidaInventarioToAttach : salidasInventarioNew) {
                salidasInventarioNewSalidaInventarioToAttach = em.getReference(salidasInventarioNewSalidaInventarioToAttach.getClass(), salidasInventarioNewSalidaInventarioToAttach.getId());
                attachedSalidasInventarioNew.add(salidasInventarioNewSalidaInventarioToAttach);
            }
            salidasInventarioNew = attachedSalidasInventarioNew;
            producto.setSalidasInventario(salidasInventarioNew);
            producto = em.merge(producto);
            for (EntradaInventario entradasInventarioOldEntradaInventario : entradasInventarioOld) {
                if (!entradasInventarioNew.contains(entradasInventarioOldEntradaInventario)) {
                    entradasInventarioOldEntradaInventario.setProducto(null);
                    entradasInventarioOldEntradaInventario = em.merge(entradasInventarioOldEntradaInventario);
                }
            }
            for (EntradaInventario entradasInventarioNewEntradaInventario : entradasInventarioNew) {
                if (!entradasInventarioOld.contains(entradasInventarioNewEntradaInventario)) {
                    Producto oldProductoOfEntradasInventarioNewEntradaInventario = entradasInventarioNewEntradaInventario.getProducto();
                    entradasInventarioNewEntradaInventario.setProducto(producto);
                    entradasInventarioNewEntradaInventario = em.merge(entradasInventarioNewEntradaInventario);
                    if (oldProductoOfEntradasInventarioNewEntradaInventario != null && !oldProductoOfEntradasInventarioNewEntradaInventario.equals(producto)) {
                        oldProductoOfEntradasInventarioNewEntradaInventario.getEntradasInventario().remove(entradasInventarioNewEntradaInventario);
                        oldProductoOfEntradasInventarioNewEntradaInventario = em.merge(oldProductoOfEntradasInventarioNewEntradaInventario);
                    }
                }
            }
            for (SalidaInventario salidasInventarioOldSalidaInventario : salidasInventarioOld) {
                if (!salidasInventarioNew.contains(salidasInventarioOldSalidaInventario)) {
                    salidasInventarioOldSalidaInventario.setProducto(null);
                    salidasInventarioOldSalidaInventario = em.merge(salidasInventarioOldSalidaInventario);
                }
            }
            for (SalidaInventario salidasInventarioNewSalidaInventario : salidasInventarioNew) {
                if (!salidasInventarioOld.contains(salidasInventarioNewSalidaInventario)) {
                    Producto oldProductoOfSalidasInventarioNewSalidaInventario = salidasInventarioNewSalidaInventario.getProducto();
                    salidasInventarioNewSalidaInventario.setProducto(producto);
                    salidasInventarioNewSalidaInventario = em.merge(salidasInventarioNewSalidaInventario);
                    if (oldProductoOfSalidasInventarioNewSalidaInventario != null && !oldProductoOfSalidasInventarioNewSalidaInventario.equals(producto)) {
                        oldProductoOfSalidasInventarioNewSalidaInventario.getSalidasInventario().remove(salidasInventarioNewSalidaInventario);
                        oldProductoOfSalidasInventarioNewSalidaInventario = em.merge(oldProductoOfSalidasInventarioNewSalidaInventario);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = producto.getId();
                if (findProducto(id) == null) {
                    throw new NonexistentEntityException("The producto with id " + id + " no longer exists.");
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
            Producto producto;
            try {
                producto = em.getReference(Producto.class, id);
                producto.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The producto with id " + id + " no longer exists.", enfe);
            }
            List<EntradaInventario> entradasInventario = producto.getEntradasInventario();
            for (EntradaInventario entradasInventarioEntradaInventario : entradasInventario) {
                entradasInventarioEntradaInventario.setProducto(null);
                entradasInventarioEntradaInventario = em.merge(entradasInventarioEntradaInventario);
            }
            List<SalidaInventario> salidasInventario = producto.getSalidasInventario();
            for (SalidaInventario salidasInventarioSalidaInventario : salidasInventario) {
                salidasInventarioSalidaInventario.setProducto(null);
                salidasInventarioSalidaInventario = em.merge(salidasInventarioSalidaInventario);
            }
            em.remove(producto);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Producto> findProductoEntities() {
        return findProductoEntities(true, -1, -1);
    }

    public List<Producto> findProductoEntities(int maxResults, int firstResult) {
        return findProductoEntities(false, maxResults, firstResult);
    }

    private List<Producto> findProductoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Producto.class));
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

    public Producto findProducto(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Producto.class, id);
        } finally {
            em.close();
        }
    }

    public int getProductoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Producto> rt = cq.from(Producto.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
