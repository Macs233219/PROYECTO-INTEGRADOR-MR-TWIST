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
import java.util.ArrayList;
import java.util.List;
import entidades.SalidaInventario;
import entidades.Usuario;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author marlon
 */
public class UsuarioJpaController implements Serializable {

    public UsuarioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Usuario usuario) {
        if (usuario.getEntradasInventario() == null) {
            usuario.setEntradasInventario(new ArrayList<EntradaInventario>());
        }
        if (usuario.getSalidasInventario() == null) {
            usuario.setSalidasInventario(new ArrayList<SalidaInventario>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<EntradaInventario> attachedEntradasInventario = new ArrayList<EntradaInventario>();
            for (EntradaInventario entradasInventarioEntradaInventarioToAttach : usuario.getEntradasInventario()) {
                entradasInventarioEntradaInventarioToAttach = em.getReference(entradasInventarioEntradaInventarioToAttach.getClass(), entradasInventarioEntradaInventarioToAttach.getId());
                attachedEntradasInventario.add(entradasInventarioEntradaInventarioToAttach);
            }
            usuario.setEntradasInventario(attachedEntradasInventario);
            List<SalidaInventario> attachedSalidasInventario = new ArrayList<SalidaInventario>();
            for (SalidaInventario salidasInventarioSalidaInventarioToAttach : usuario.getSalidasInventario()) {
                salidasInventarioSalidaInventarioToAttach = em.getReference(salidasInventarioSalidaInventarioToAttach.getClass(), salidasInventarioSalidaInventarioToAttach.getId());
                attachedSalidasInventario.add(salidasInventarioSalidaInventarioToAttach);
            }
            usuario.setSalidasInventario(attachedSalidasInventario);
            em.persist(usuario);
            for (EntradaInventario entradasInventarioEntradaInventario : usuario.getEntradasInventario()) {
                Usuario oldUsuarioOfEntradasInventarioEntradaInventario = entradasInventarioEntradaInventario.getUsuario();
                entradasInventarioEntradaInventario.setUsuario(usuario);
                entradasInventarioEntradaInventario = em.merge(entradasInventarioEntradaInventario);
                if (oldUsuarioOfEntradasInventarioEntradaInventario != null) {
                    oldUsuarioOfEntradasInventarioEntradaInventario.getEntradasInventario().remove(entradasInventarioEntradaInventario);
                    oldUsuarioOfEntradasInventarioEntradaInventario = em.merge(oldUsuarioOfEntradasInventarioEntradaInventario);
                }
            }
            for (SalidaInventario salidasInventarioSalidaInventario : usuario.getSalidasInventario()) {
                Usuario oldUsuarioOfSalidasInventarioSalidaInventario = salidasInventarioSalidaInventario.getUsuario();
                salidasInventarioSalidaInventario.setUsuario(usuario);
                salidasInventarioSalidaInventario = em.merge(salidasInventarioSalidaInventario);
                if (oldUsuarioOfSalidasInventarioSalidaInventario != null) {
                    oldUsuarioOfSalidasInventarioSalidaInventario.getSalidasInventario().remove(salidasInventarioSalidaInventario);
                    oldUsuarioOfSalidasInventarioSalidaInventario = em.merge(oldUsuarioOfSalidasInventarioSalidaInventario);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Usuario usuario) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario persistentUsuario = em.find(Usuario.class, usuario.getId());
            List<EntradaInventario> entradasInventarioOld = persistentUsuario.getEntradasInventario();
            List<EntradaInventario> entradasInventarioNew = usuario.getEntradasInventario();
            List<SalidaInventario> salidasInventarioOld = persistentUsuario.getSalidasInventario();
            List<SalidaInventario> salidasInventarioNew = usuario.getSalidasInventario();
            List<EntradaInventario> attachedEntradasInventarioNew = new ArrayList<EntradaInventario>();
            for (EntradaInventario entradasInventarioNewEntradaInventarioToAttach : entradasInventarioNew) {
                entradasInventarioNewEntradaInventarioToAttach = em.getReference(entradasInventarioNewEntradaInventarioToAttach.getClass(), entradasInventarioNewEntradaInventarioToAttach.getId());
                attachedEntradasInventarioNew.add(entradasInventarioNewEntradaInventarioToAttach);
            }
            entradasInventarioNew = attachedEntradasInventarioNew;
            usuario.setEntradasInventario(entradasInventarioNew);
            List<SalidaInventario> attachedSalidasInventarioNew = new ArrayList<SalidaInventario>();
            for (SalidaInventario salidasInventarioNewSalidaInventarioToAttach : salidasInventarioNew) {
                salidasInventarioNewSalidaInventarioToAttach = em.getReference(salidasInventarioNewSalidaInventarioToAttach.getClass(), salidasInventarioNewSalidaInventarioToAttach.getId());
                attachedSalidasInventarioNew.add(salidasInventarioNewSalidaInventarioToAttach);
            }
            salidasInventarioNew = attachedSalidasInventarioNew;
            usuario.setSalidasInventario(salidasInventarioNew);
            usuario = em.merge(usuario);
            for (EntradaInventario entradasInventarioOldEntradaInventario : entradasInventarioOld) {
                if (!entradasInventarioNew.contains(entradasInventarioOldEntradaInventario)) {
                    entradasInventarioOldEntradaInventario.setUsuario(null);
                    entradasInventarioOldEntradaInventario = em.merge(entradasInventarioOldEntradaInventario);
                }
            }
            for (EntradaInventario entradasInventarioNewEntradaInventario : entradasInventarioNew) {
                if (!entradasInventarioOld.contains(entradasInventarioNewEntradaInventario)) {
                    Usuario oldUsuarioOfEntradasInventarioNewEntradaInventario = entradasInventarioNewEntradaInventario.getUsuario();
                    entradasInventarioNewEntradaInventario.setUsuario(usuario);
                    entradasInventarioNewEntradaInventario = em.merge(entradasInventarioNewEntradaInventario);
                    if (oldUsuarioOfEntradasInventarioNewEntradaInventario != null && !oldUsuarioOfEntradasInventarioNewEntradaInventario.equals(usuario)) {
                        oldUsuarioOfEntradasInventarioNewEntradaInventario.getEntradasInventario().remove(entradasInventarioNewEntradaInventario);
                        oldUsuarioOfEntradasInventarioNewEntradaInventario = em.merge(oldUsuarioOfEntradasInventarioNewEntradaInventario);
                    }
                }
            }
            for (SalidaInventario salidasInventarioOldSalidaInventario : salidasInventarioOld) {
                if (!salidasInventarioNew.contains(salidasInventarioOldSalidaInventario)) {
                    salidasInventarioOldSalidaInventario.setUsuario(null);
                    salidasInventarioOldSalidaInventario = em.merge(salidasInventarioOldSalidaInventario);
                }
            }
            for (SalidaInventario salidasInventarioNewSalidaInventario : salidasInventarioNew) {
                if (!salidasInventarioOld.contains(salidasInventarioNewSalidaInventario)) {
                    Usuario oldUsuarioOfSalidasInventarioNewSalidaInventario = salidasInventarioNewSalidaInventario.getUsuario();
                    salidasInventarioNewSalidaInventario.setUsuario(usuario);
                    salidasInventarioNewSalidaInventario = em.merge(salidasInventarioNewSalidaInventario);
                    if (oldUsuarioOfSalidasInventarioNewSalidaInventario != null && !oldUsuarioOfSalidasInventarioNewSalidaInventario.equals(usuario)) {
                        oldUsuarioOfSalidasInventarioNewSalidaInventario.getSalidasInventario().remove(salidasInventarioNewSalidaInventario);
                        oldUsuarioOfSalidasInventarioNewSalidaInventario = em.merge(oldUsuarioOfSalidasInventarioNewSalidaInventario);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = usuario.getId();
                if (findUsuario(id) == null) {
                    throw new NonexistentEntityException("The usuario with id " + id + " no longer exists.");
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
            Usuario usuario;
            try {
                usuario = em.getReference(Usuario.class, id);
                usuario.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The usuario with id " + id + " no longer exists.", enfe);
            }
            List<EntradaInventario> entradasInventario = usuario.getEntradasInventario();
            for (EntradaInventario entradasInventarioEntradaInventario : entradasInventario) {
                entradasInventarioEntradaInventario.setUsuario(null);
                entradasInventarioEntradaInventario = em.merge(entradasInventarioEntradaInventario);
            }
            List<SalidaInventario> salidasInventario = usuario.getSalidasInventario();
            for (SalidaInventario salidasInventarioSalidaInventario : salidasInventario) {
                salidasInventarioSalidaInventario.setUsuario(null);
                salidasInventarioSalidaInventario = em.merge(salidasInventarioSalidaInventario);
            }
            em.remove(usuario);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Usuario> findUsuarioEntities() {
        return findUsuarioEntities(true, -1, -1);
    }

    public List<Usuario> findUsuarioEntities(int maxResults, int firstResult) {
        return findUsuarioEntities(false, maxResults, firstResult);
    }

    private List<Usuario> findUsuarioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Usuario.class));
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

    public Usuario findUsuario(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Usuario.class, id);
        } finally {
            em.close();
        }
    }

    public int getUsuarioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Usuario> rt = cq.from(Usuario.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
