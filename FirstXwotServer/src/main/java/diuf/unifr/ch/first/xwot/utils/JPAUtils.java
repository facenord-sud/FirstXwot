/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package diuf.unifr.ch.first.xwot.utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Add here common DB operations on the classes residing in the persistence
 * package. Such operations include getXXXX(), updateXXXX() or deleteXXXX().
 * Further, this class provides methods for merging, persisting and deleting
 * objects from the {@link EntityManager}.
 *
 * @author <a href="mailto:andreas.ruppen@gmail.com">Andreas Ruppen</a>
 */
public class JPAUtils {

    protected static final EntityManagerFactory EMF = Persistence.createEntityManagerFactory("eHealth");
    protected EntityManager em = EMF.createEntityManager();

    /**
     * Persist given Entity to database.
     *
     * @param _newEntity the object to persist.
     * @return the persisted object.
     */
    public <T> T persistEntity(T _newEntity) {
        T mergedEntity;
        em.getTransaction().begin();
        em.persist(_newEntity);
        mergedEntity = em.merge(_newEntity);
        em.getTransaction().commit();
        return mergedEntity;
    }

    /**
     * Merges an object into the DB.
     *
     * @param <T>
     * @param _newEnt the object to merge.
     * @return the merged object.
     */
    public <T> T mergeEntity(T _newEnt) {
        T newobject;
        em.getTransaction().begin();
        newobject = em.merge(_newEnt);
        em.flush();
        em.getTransaction().commit();
        return newobject;
    }

    /**
     * Removes given Entity from Database
     *
     * @param _oldEntity the object to delete.
     * @return the removed object.
     */
    public <T> T removeEntity(T _oldEnt) {
        em.getTransaction().begin();
        em.remove(_oldEnt);
        em.flush();
        em.getTransaction().commit();
        return _oldEnt;
    }
}
