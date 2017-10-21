package daos;

import framework.dao.ExtHibernateDaoSupport;
import model.UserinfoEntity;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

/**
 * Created by 11022 on 2017/1/31.
 */
@Repository("userinfoDAO")
@Transactional
public class UserinfoDAO extends ExtHibernateDaoSupport {
    private static final Logger log = LoggerFactory
            .getLogger(UserinfoDAO.class);

    @Autowired
    public void setMySessionFactory(SessionFactory sessionFactory) {
        super.setSessionFactory(sessionFactory);
    }

    public void save(UserinfoEntity transientInstance) {
        log.debug("saving Userinfo instance");
        try {
            getHibernateTemplate().save(transientInstance);
            log.debug("save Userinfo successful");
        } catch (RuntimeException re) {
            log.error("save Userinfo failed", re);
            throw re;
        }
    }

    public UserinfoEntity findById(Object id) {
        log.debug("getting Userinfo instance with id: " + id);
        try {
            UserinfoEntity instance = (UserinfoEntity) getHibernateTemplate().get("model.UserinfoEntity", (Serializable) id);
            log.debug("get Userinfo by id successful");
            return instance;
        } catch (RuntimeException re) {
            log.error("get Userinfo by id failed", re);
            throw re;
        }
    }
}
