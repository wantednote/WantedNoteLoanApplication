package com.wn.loanapp.repository.impl;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.wn.loanapp.repository.PrimaryGenericRepository;

@Repository(PrimaryGenericRepositoryImpl.REPOSITORY_NAME)
@Transactional
public class PrimaryGenericRepositoryImpl<T, ID extends Serializable> implements PrimaryGenericRepository<T, ID> {

	public static final String REPOSITORY_NAME = "primaryGenericRepository";
	
	protected final Log log = LogFactory.getLog(getClass());
    
    protected boolean isDebugEnabled=log!=null?log.isDebugEnabled():false;
    protected boolean isInfoEnabled=log!=null?log.isInfoEnabled():false;
    protected boolean isErrorEnabled=log!=null?log.isErrorEnabled():false;
    protected boolean isWarnEnabled=log!=null?log.isWarnEnabled():false;
    
    private Class<T> type;
    
    public PrimaryGenericRepositoryImpl() {
    	
    }
    
    public PrimaryGenericRepositoryImpl(final Class<T> type) {
        this.type = type;
    }
    
    @SuppressWarnings("unchecked")
	public Class<T> newInstance() {
    	Class<T> newInst = null;

        try {
            newInst = (Class<T>) this.type.newInstance();
        } catch (Exception e) {
          //  LOG.error("Problem creating new instance of type: " + this.persistentClass.newInstance());
        }

        return newInst;
    }
    
    public Class<T> getPersistentClass() {
        return this.type;
    }
    
    /*@Autowired
	private SessionFactory sessionFactory;*/
    
    @Autowired
    //@PersistenceContext(unitName = Constants.PRIMAY_PERSITENCE_UNIT)
    private EntityManager entityManager;

	/**
	 * @return the sessionFactory
	 */
	/*public SessionFactory getSessionFactory() {
		return sessionFactory;
	}*/

	/**
	 * @param sessionFactory the sessionFactory to set
	 */
	/*public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}*/

	/**
	 * @return the entityManager
	 */
	public EntityManager getPrimaryEntityManager() {
		return entityManager;
	}

	/**
	 * @param entityManager the entityManager to set
	 */
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	/**
     * @return the session
     */
    /*public Session getSession(){
        Session session = getSessionFactory().getCurrentSession();
        if (session == null) {
            session = getSessionFactory().openSession();
        }
        return session;
    }*/
    
    public List<T> findAll() {
        CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<T> cq = builder.createQuery(getPersistentClass());
        Root<T> root = cq.from(getPersistentClass());
        cq.select(root);
        return getPrimaryEntityManager().createQuery(cq).getResultList();
    }

    public void saveOrUpdate( T object ){
    	//getEntityManager().setFlushMode(FlushModeType.COMMIT);
    	getPrimaryEntityManager().persist( object );
    }
    
    
	public void saveAll(List<T> objects) {
		objects.forEach(object -> getPrimaryEntityManager().persist(object));
	}
    
    public T update(T object) {
        return getPrimaryEntityManager().merge(object);
    }

    public T get(Long id) {
        return getPrimaryEntityManager().find(getPersistentClass(), id);
    }

    public void delete(T object) {
    	getPrimaryEntityManager().remove(getPrimaryEntityManager().merge(object));
    }

    public void insert(T object) {
    	getPrimaryEntityManager().persist(object);
    }

    public boolean exists(Long id) {
        T entity = getPrimaryEntityManager().find(this.type, id);
        return entity != null;
    }
}
