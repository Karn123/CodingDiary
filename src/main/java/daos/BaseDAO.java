package daos;

import framework.dao.ExtHibernateDaoSupport;
import org.hibernate.*;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * 数据库操作封装层，供service使用
 */
@SuppressWarnings("ALL")
@Repository("baseDAO")
@Transactional
public class BaseDAO extends ExtHibernateDaoSupport {
	private static final Logger log = LoggerFactory
			.getLogger(BaseDAO.class);

	@Autowired  
	public void setMySessionFactory(SessionFactory sessionFactory){  
	    super.setSessionFactory(sessionFactory);  
	}  
	
	public void save(Object transientInstance) {
		log.debug("saving Object instance");
		try {
			getHibernateTemplate().save(transientInstance);
			//getSession().flush();//save直接flush
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Object persistentInstance) {
		log.debug("deleting Object instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}
	
	public void update(Object persistentInstance) {
		try {
			getHibernateTemplate().update(persistentInstance);
		} catch (RuntimeException re) {
			log.error("update failed", re);
			throw re;
		}
	}

	public <T> T findById(Object id, Class<T> cls) {
		String tableName = cls.getName();
		log.debug("getting "+tableName+" instance with id: " + id);
		try {
			T instance = (T) getHibernateTemplate().get(
					tableName, (Serializable)id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	
	//cls
	public List findByExample(Object instance, Class cls) {
		String tableName = cls.getName();
		log.debug("finding "+tableName+" instance by example");
		try {
			List results = getSession()
					.createCriteria(tableName)
					.add(Example.create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	//cls
	public List findByProperty(String propertyName, Object value, Class cls) {
		String tableName = cls.getName();
		log.debug("finding "+tableName+" instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from "+tableName+" as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString,value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List find(String sql)
	{
		try {
			String queryString = sql;
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	//cls
	public List findAll(Class cls) {
		String tableName = cls.getName();
		log.debug("finding all "+tableName+" instances");
		try {
			String queryString = "from "+tableName;
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public <T>T merge(T detachedInstance) {
		log.debug("merging Object instance");
		try {
			T result = (T) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void saveOrUpdate(Object instance) {
		log.debug("attaching dirty Object instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Object instance) {
		log.debug("attaching clean Object instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	public void saveAuto(final Object instance) {
		log.debug("saving instance");
		try {
			Session session = getSession();
			session.setFlushMode(FlushMode.AUTO);
			session.save(instance);
			session.flush();
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}
}