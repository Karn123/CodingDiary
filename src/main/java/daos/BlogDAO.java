package daos;

import controller.Pagination;
import framework.dao.ExtHibernateDaoSupport;
import model.BlogContentEntity;
import model.BlogEntity;
import model.UserinfoEntity;
import org.hibernate.*;
import org.hibernate.transform.Transformers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.registry.infomodel.User;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by 11022 on 2017/1/31.
 */
//@SuppressWarnings("ALL")
@Repository("blogDAO")
@Transactional
public class BlogDAO extends ExtHibernateDaoSupport {
    private static final Logger log = LoggerFactory.getLogger(BlogDAO.class);
    public static final String BLOG_TITLE = "blogTitle";
    public static final String BLOG_CONTENT = "blogContent";
    public static final String PRAISE_NUM = "praiseNum";
    public static final String COMMENT_NUM = "commentNum";
    public static final String FORWARD_NUM = "forwardNum";
    public static final String BROWSE_NUM = "browseNum";
    public static final String COLLECT_NUM = "collectNum";

    @Autowired
    public void setMySessionFactory(SessionFactory sessionFactory) {
        super.setSessionFactory(sessionFactory);
    }

    /**
     * 将BlogEntity保存到数据库中
     *
     * @param blogEntity BlogEntity对象
     */
    public BlogEntity save(final int userID, final BlogEntity blogEntity, final BlogContentEntity blogContentEntity, final Set<Integer> tagsList) {
        log.debug("saving Blog instance");
        try {
            BlogEntity result = getHibernateTemplate().execute(new HibernateCallback<BlogEntity>() {
                @Override
                public BlogEntity doInHibernate(Session session) throws HibernateException, SQLException {
                    UserinfoEntity userinfoEntityByAuthorId = (UserinfoEntity) session.get(model.UserinfoEntity.class, userID);
                    blogEntity.setUserinfoByAuthorId(userinfoEntityByAuthorId);
                    session.save(blogEntity);
                    for (int tagID : tagsList) {
                        SQLQuery query = session.createSQLQuery("insert into blog_tag values(?,?)");
                        query.setParameter(0, blogEntity.getBlogId());
                        query.setParameter(1, tagID);
                        query.executeUpdate();
                    }
                    int blogID=blogEntity.getBlogId();
                    blogContentEntity.setBlogId(blogID);
                    blogContentEntity.setBlogByBlogId(blogEntity);
                    session.save(blogContentEntity);
                    session.createSQLQuery("INSERT INTO blog_recommend_value VALUES (?,0,0,0,0,0,0,1)").setParameter(0,blogID).executeUpdate();
                    return blogEntity;
                }
            });
            log.debug("save Blog successful");
            return result;
        } catch (RuntimeException re) {
            log.error("save Blog failed", re);
            throw re;
        }
    }

    public BlogEntity update(final int blogID, final BlogEntity blogEntity, final BlogContentEntity blogContentEntity, final Set<Integer> tagsList) {
        log.debug("saving Blog instance");
        try {
            BlogEntity result = getHibernateTemplate().execute(new HibernateCallback<BlogEntity>() {
                @Override
                public BlogEntity doInHibernate(Session session) throws HibernateException, SQLException {
                    //UserinfoEntity userinfoEntityByAuthorId = (UserinfoEntity) session.get(model.UserinfoEntity.class, userID);
                    //blogEntity.setUserinfoByAuthorId(userinfoEntityByAuthorId);
                    BlogEntity old=(BlogEntity) session.get(BlogEntity.class,blogID);
                    old.setBlogTitle(blogEntity.getBlogTitle());
                    session.update(old);
                    session.createSQLQuery("DELETE  FROM  blog_tag WHERE BlogIDNum=?").setParameter(0,blogID).executeUpdate();
                    for (int tagID : tagsList) {
                        SQLQuery query = session.createSQLQuery("insert into blog_tag values(?,?)");
                        query.setParameter(0, blogID);
                        query.setParameter(1, tagID);
                        query.executeUpdate();
                    }
                    int blogID=blogEntity.getBlogId();
                    BlogContentEntity oldContent=old.getBlogContentByBlogId();
                    oldContent.setBlogContent(blogContentEntity.getBlogContent());
                    session.update(oldContent);
                    //session.createSQLQuery("INSERT INTO blog_recommend_value VALUES (?,0,0,0,0,0,0,1)").setParameter(0,blogID).executeUpdate();
                    return old;
                }
            });
            log.debug("save Blog successful");
            return result;
        } catch (RuntimeException re) {
            log.error("save Blog failed", re);
            throw re;
        }
    }

    public BlogEntity findById(Object id) {
        log.debug("getting Blog instance with id: " + id);
        try {
            BlogEntity instance = (BlogEntity) getHibernateTemplate().get("model.BlogEntity", (Serializable) id);
            log.debug("get Blog by id successful");
            return instance;
        } catch (RuntimeException re) {
            log.error("get Blog by id failed", re);
            throw re;
        }
    }

    public List<BlogEntity> getListByPage(String type, final String typeValue, final int userID, final Pagination pagination) {
        final int offset = pagination.getOffset();
        List<BlogEntity> list = null;
        log.debug("finding Blog instance list by page: "+pagination);
        try {
            if (type .equals("null")) {
                if (userID != 0) {
                    list = (List<BlogEntity>) getHibernateTemplate().executeFind(new HibernateCallback<Object>() {
                        @Override
                        public List<BlogEntity> doInHibernate(Session session) throws HibernateException, SQLException {
                            UserinfoEntity userinfoEntity = (UserinfoEntity) session.get(UserinfoEntity.class, userID);
                            String hql="from model.BlogEntity as model where model.userinfoByAuthorId= ?";
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
                list = (List<BlogEntity>) getHibernateTemplate().executeFind(new HibernateCallback<Object>() {
                    @Override
                    public List<BlogEntity> doInHibernate(Session session) throws HibernateException, SQLException {
                        UserinfoEntity userinfoEntity = (UserinfoEntity) session.get(UserinfoEntity.class, userID);
                        String hql="SELECT BlogIDNum FROM blog_tag WHERE TagIDNum=?";
                        SQLQuery sqlQuery = session.createSQLQuery(hql);
                        sqlQuery.setParameter(0, typeValue);
                        sqlQuery.setFirstResult(offset).setMaxResults(pagination.getPageSize());
                        List<BlogEntity> list = new ArrayList<BlogEntity>();
                        for (int blogID : (List<Integer>) sqlQuery.list()) {
                            BlogEntity blogEntity = (BlogEntity) session.get(BlogEntity.class, blogID);
                            if (userID == 0 || blogEntity.getUserinfoByAuthorId().getUserId() == userID) {
                                list.add(blogEntity);
                            }
                        }
                        pagination.setTotalPageNumber(list.size());
                        return list;
                    }
                });
            } else if(type.equals("time")) {
                list = (List<BlogEntity>) getHibernateTemplate().executeFind(new HibernateCallback<Object>() {
                    @Override
                    public List<BlogEntity> doInHibernate(Session session) throws HibernateException, SQLException {
                        UserinfoEntity userinfoEntity = (UserinfoEntity) session.get(UserinfoEntity.class, userID);
                        String hql="SELECT blogID FROM blog WHERE date_format(publishTime,'%Y-%m')=?";
                        SQLQuery sqlQuery = session.createSQLQuery(hql);
                        sqlQuery.setParameter(0, typeValue);
                        sqlQuery.setFirstResult(offset).setMaxResults(pagination.getPageSize());
                        List<BlogEntity> list = new ArrayList<BlogEntity>();
                        for (int blogID : (List<Integer>) sqlQuery.list()) {
                            BlogEntity blogEntity = (BlogEntity) session.get(BlogEntity.class, blogID);
                            if (userID == 0 || blogEntity.getUserinfoByAuthorId().getUserId() == userID) {
                                list.add(blogEntity);
                            }
                        }
                        pagination.setTotalPageNumber(list.size());
                        return list;
                    }
                });
            }
            else {
                list = null;
            }
            //if (type == null);
            log.debug("find Blog instance list by page successful");
            return list;
        } catch (RuntimeException re) {
            log.error("find Blog instance list by page failed", re);
            throw re;
        }
    }

    public int getTotalSize(final String sql, final Object[] values) {
        log.debug("finding Total Size by sql" + sql);
        try {
            int totalNumber = getHibernateTemplate().execute(new HibernateCallback<Integer>() {
                @Override
                public Integer doInHibernate(Session session) throws HibernateException, SQLException {
                    SQLQuery query = session.createSQLQuery(sql);
                    if (values != null) {
                        for (int i = 0; i < values.length; i++) {
                            query.setParameter(i, values[i]);
                        }
                    }
                    return Integer.parseInt(query.list().get(0).toString());
                }
            });
            log.debug("finding Total Size by sql:" + sql + "successful");
            return totalNumber;
        } catch (RuntimeException re) {
            log.error("finding Total Size by sql:" + sql + "fail", re);
            throw re;
        }
    }

    @Override
    public List<BlogEntity> findByPage(String hql, final int pageNumber, final int pageSize) {
        final int offset = pageNumber * pageSize;
        log.debug("finding Blog instance list by page: offset= " + offset + " pageSize= " + pageSize);
        try {
            List<BlogEntity> list = super.findByPage(hql, offset, pageSize);
            log.debug("find Blog instance list by page successful");
            return list;
        } catch (RuntimeException re) {
            log.error("find Blog instance list by page failed", re);
            throw re;
        }
    }

    public List<BlogEntity> findByProperty(String propertyName, Object value) {
        log.debug("finding Blog instance with property: " + propertyName + ", value: " + value);
        try {
            String queryString = "from model.BlogEntity as model where model." + propertyName + "= ?";
            return (List<BlogEntity>)getHibernateTemplate().find(queryString, value);
        } catch (RuntimeException re) {
            log.error("find by property name failed", re);
            throw re;
        }
    }
}
