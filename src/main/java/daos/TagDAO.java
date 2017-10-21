package daos;

import controller.Pagination;
import framework.dao.ExtHibernateDaoSupport;
import model.TagEntity;
import org.hibernate.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by 11022 on 2017/1/31.
 */
@Repository("tagDAO")
@Transactional
public class TagDAO extends ExtHibernateDaoSupport {
    private static final Logger log = LoggerFactory
            .getLogger(TagDAO.class);

    private static final String TAG_NAME = "tagName";

    @Autowired
    public void setMySessionFactory(SessionFactory sessionFactory) {
        super.setSessionFactory(sessionFactory);
    }

    /**
     * 将TagEntity保存到数据库中
     *
     * @param transientInstance 所要保存的TagEntity对象
     */
    public void save(TagEntity transientInstance) {
        log.debug("saving Tag instance");
        try {
            getHibernateTemplate().save(transientInstance);
            log.debug("save Tag successful");
        } catch (RuntimeException re) {
            log.error("save Tag failed", re);
            throw re;
        }
    }

    /**
     * 根据tagID查找TagEntity对象
     *
     * @param id tagID
     * @return tag对象
     */
    public TagEntity findById(Object id) {
        log.debug("getting Tag instance with id: " + id);
        try {
            TagEntity instance = (TagEntity) getHibernateTemplate().get("model.TagEntity", (Serializable) id);
            log.debug("get Tag by id successful");
            return instance;
        } catch (RuntimeException re) {
            log.error("get Tag by id failed", re);
            throw re;
        }
    }

    /**
     * 根据tagName查找TagEntity对象
     *
     * @param tagName tagName
     * @return TagEntity对象
     */
    public List<TagEntity> findByTagName(Object tagName) {
        log.debug("finding Tag instance with property: " + TAG_NAME + ", value: " + tagName);
        try {
            String queryString = "from model.TagEntity as model where model." + TAG_NAME + "= ?";
            List<TagEntity> list = (List<TagEntity>) getHibernateTemplate().find(queryString, tagName);
            log.debug("find by property name successful");
            return list;
        } catch (RuntimeException re) {
            log.error("find by property name failed", re);
            throw re;
        }
    }

    /**
     * 分页查找TagEntity对象列表
     *
     * @param pagination 分页
     * @return TagEntity对象列表
     */
    public List<TagEntity> findByPage(final Pagination pagination) {
        final int offset = pagination.getOffset();
        log.debug("finding Tag instance list by page: offset= " + offset + " pageSize= " + pagination.getPageSize());
        try {
            //List<TagEntity> list = findByPage("from model.TagEntity", offset, pagination.getPageSize());
            List<TagEntity> list=(List<TagEntity>)getHibernateTemplate().executeFind(new HibernateCallback<Object>() {
                @Override
                public List<TagEntity> doInHibernate(Session session) throws HibernateException, SQLException {
                    Query total=session.createQuery("select count(*) from model.TagEntity");
                    pagination.setTotalPageNumber(Integer.parseInt(total.list().get(0).toString()));
                    Query query = session.createQuery("from model.TagEntity");
                    query.setFirstResult(offset).setMaxResults(pagination.getPageSize());
                    return query.list();
                }
            });
            log.debug("find Tag instance list by page successful");
            return list;
        } catch (RuntimeException re) {
            log.error("find Tag instance list by page failed", re);
            throw re;
        }
    }
}
