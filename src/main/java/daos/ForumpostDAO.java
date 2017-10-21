package daos;

import controller.Pagination;
import framework.dao.ExtHibernateDaoSupport;

import model.ForumpostEntity;
import model.UserinfoEntity;
import org.hibernate.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 佳乐 on 2017/2/27.
 */
//@SuppressWarnings("ALL")
@Repository("forumpostDAO")
@Transactional
public class ForumpostDAO extends ExtHibernateDaoSupport {
    private static final Logger log = LoggerFactory.getLogger(ForumpostDAO.class);
    public static final String FORUMPOST_TITLE = "forumpostTitle";
    public static final String FORUMPOST_CONTENT = "forumpostContent";
    public static final String PRAISE_NUM = "praiseNum";
    public static final String COMMENT_NUM = "commentNum";
    public static final String FORWARD_NUM = "forwardNum";
    public static final String BROWSE_NUM = "browseNum";
    public static final String COLLECT_NUM = "collectNum";

    @Autowired
    public void setMySessionFactory(SessionFactory sessionFactory) {
        super.setSessionFactory(sessionFactory);
    }

    public List<ForumpostEntity> getListByPage(String type, final String typeValue, final int userID, final Pagination pagination) {
        final int offset = pagination.getOffset();
        List<ForumpostEntity> list = null;
        log.debug("finding Forumpost instance list by page: "+pagination);
        try {
            if (type == null) {
                if (userID != 0) {
                    list = (List<ForumpostEntity>) getHibernateTemplate().executeFind(new HibernateCallback<Object>() {
                        @Override
                        public List<ForumpostEntity> doInHibernate(Session session) throws HibernateException, SQLException {
                            UserinfoEntity userinfoEntity = (UserinfoEntity) session.get(UserinfoEntity.class, userID);
                            String hql="from model.ForumpostEntity as model where model.userinfoByAuthorId= ?";
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
            } else if (type.equals("tag")) {
                list = (List<ForumpostEntity>) getHibernateTemplate().executeFind(new HibernateCallback<Object>() {
                    @Override
                    public List<ForumpostEntity> doInHibernate(Session session) throws HibernateException, SQLException {
                        UserinfoEntity userinfoEntity = (UserinfoEntity) session.get(UserinfoEntity.class, userID);
                        String hql="SELECT ForumpostID FROM forumpost_tag WHERE tagID=?";
                        SQLQuery sqlQuery = session.createSQLQuery(hql);
                        sqlQuery.setParameter(0, typeValue);
                        sqlQuery.setFirstResult(offset).setMaxResults(pagination.getPageSize());
                        List<ForumpostEntity> list = new ArrayList<ForumpostEntity>();
                        for (int forumpostID : (List<Integer>) sqlQuery.list()) {
                            ForumpostEntity forumpostEntity = (ForumpostEntity) session.get(ForumpostEntity.class, forumpostID);
                            if (userID == 0 || forumpostEntity.getUserinfoByAuthorId().getUserId() == userID) {
                                list.add(forumpostEntity);
                            }
                        }
                        pagination.setTotalPageNumber(list.size());
                        return list;
                    }
                });
            } else {
                list = null;
            }
            //if (type == null);
            log.debug("find Forumpost instance list by page successful");
            return list;
        } catch (RuntimeException re) {
            log.error("find Forumpost instance list by page failed", re);
            throw re;
        }
    }
}
