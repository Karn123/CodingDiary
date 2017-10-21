package daos;

import controller.Pagination;
import framework.dao.ExtHibernateDaoSupport;
import model.BlogEntity;
import model.ForumpostEntity;
import model.ResourceEntity;
import model.UserinfoEntity;
import org.hibernate.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 11022 on 2017/2/1.
 */
@Repository("collectDAO")
@Transactional
public class CollectDAO extends ExtHibernateDaoSupport {
    private static final Logger log = LoggerFactory.getLogger(CollectDAO.class);

    @Autowired
    public void setMySessionFactory(SessionFactory sessionFactory) {
        super.setSessionFactory(sessionFactory);
    }

    public int collect(final int userID, final int id, final String table){
        int result = (Integer) getHibernateTemplate().execute(new HibernateCallback<Object>() {
            @Override
            public Integer doInHibernate(Session session) throws HibernateException, SQLException {
                SQLQuery query = session.createSQLQuery("SELECT * FROM " + table.toLowerCase() + "_collect WHERE collectorID=? AND collect" + table + "ID=?");
                query.setParameter(0, userID);
                query.setParameter(1, id);
                String tableName = new String(table);
                if (query.list().size() == 0) {
                    SQLQuery insertQuery = session.createSQLQuery("INSERT INTO " + table.toLowerCase() + "_collect VALUES (?,?,?)");
                    insertQuery.setParameter(0, id);
                    insertQuery.setParameter(1, userID);
                    insertQuery.setParameter(2, 1);
                    insertQuery.executeUpdate();
                    session.createSQLQuery("UPDATE " + tableName.toLowerCase() + " SET collectNum = collectNum + 1 WHERE " + table + "ID =?").setParameter(0, id).executeUpdate();
                    return 1;
                } else {
                    SQLQuery deleteQuery = session.createSQLQuery("DELETE FROM " + table.toLowerCase() + "_collect WHERE collectorID=? AND collect" + table + "ID=?");
                    deleteQuery.setParameter(0, userID);
                    deleteQuery.setParameter(1, id);
                    deleteQuery.executeUpdate();
                    session.createSQLQuery("UPDATE " + tableName.toLowerCase() + " SET collectNum = collectNum - 1 WHERE " + table + "ID =?").setParameter(0, id).executeUpdate();
                    return -1;
                }
            }
        });
        return result;
    }

    public List<ForumpostEntity> getForumpostCollectListByPage(String type, final String typeValue, final int userID, final Pagination pagination) {

        final int offset = pagination.getOffset();
        List<ForumpostEntity> list = null;
        try {
            if (type == null) {
                if (userID != 0) {
                    list = (List<ForumpostEntity>) getHibernateTemplate().executeFind(new HibernateCallback<Object>() {
                        @Override
                        public List<ForumpostEntity> doInHibernate(Session session) throws HibernateException, SQLException {
                            UserinfoEntity userinfoEntity = (UserinfoEntity) session.get(UserinfoEntity.class, userID);
                            String hql="from ForumpostEntity as fe where fe.forumpostId in (select forumpostId from ForumpostCollectEntity where userinfoByCollectorId= ?)";
                            Query query = session.createQuery(hql);
                            query.setParameter(0, userinfoEntity);
                            query.setFirstResult(offset).setMaxResults(pagination.getPageSize());
                            Query totalNumber=session.createQuery("select count(*)"+hql);
                            totalNumber.setParameter(0, userinfoEntity);
                            pagination.setTotalPageNumber(Integer.parseInt(totalNumber.list().get(0).toString()));
                            return query.list();
                        }
                    });
                }
            }else {
                list = null;
            }
            return list;
        } catch (RuntimeException re) {
            throw re;
        }
    }

    public List<BlogEntity> getBlogCollectListByPage(String type, final String typeValue, final int userID, final Pagination pagination) {

        final int offset = pagination.getOffset();
        List<BlogEntity> list = null;
        try {
            if (type == null) {
                if (userID != 0) {
                    list = (List<BlogEntity>) getHibernateTemplate().executeFind(new HibernateCallback<Object>() {
                        @Override
                        public List<ForumpostEntity> doInHibernate(Session session) throws HibernateException, SQLException {
                            UserinfoEntity userinfoEntity = (UserinfoEntity) session.get(UserinfoEntity.class, userID);
                            String hql="from BlogEntity as be where be.blogId in (select collectBlogId from BlogCollectEntity where userinfoByCollectorId= ?)";
                            Query query = session.createQuery(hql);
                            query.setParameter(0, userinfoEntity);
                            query.setFirstResult(offset).setMaxResults(pagination.getPageSize());
                            Query totalNumber=session.createQuery("select count(*)"+hql);
                            totalNumber.setParameter(0, userinfoEntity);
                            pagination.setTotalPageNumber(Integer.parseInt(totalNumber.list().get(0).toString()));
                            return query.list();
                        }
                    });
                }
            }else {
                list = null;
            }
            return list;
        } catch (RuntimeException re) {
            throw re;
        }
    }

    public List<ResourceEntity> getResourceCollectListByPage(String type, final String typeValue, final int userID, final Pagination pagination) {

        final int offset = pagination.getOffset();
        List<ResourceEntity> list = null;
        try {
            if (type == null) {
                if (userID != 0) {
                    list = (List<ResourceEntity>) getHibernateTemplate().executeFind(new HibernateCallback<Object>() {
                        @Override
                        public List<ForumpostEntity> doInHibernate(Session session) throws HibernateException, SQLException {
                            UserinfoEntity userinfoEntity = (UserinfoEntity) session.get(UserinfoEntity.class, userID);
                            String hql="from ResourceEntity as rse where rse.resourceId in (select collectResourceId from ResourceCollectEntity where userinfoByCollectorId= ?)";
                            Query query = session.createQuery(hql);
                            query.setParameter(0, userinfoEntity);
                            query.setFirstResult(offset).setMaxResults(pagination.getPageSize());
                            Query totalNumber=session.createQuery("select count(*)"+hql);
                            totalNumber.setParameter(0, userinfoEntity);
                            pagination.setTotalPageNumber(Integer.parseInt(totalNumber.list().get(0).toString()));
                            return query.list();
                        }
                    });
                }
            }else {
                list = null;
            }
            return list;
        } catch (RuntimeException re) {
            throw re;
        }
    }
}
