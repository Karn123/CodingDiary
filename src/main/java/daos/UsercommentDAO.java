package daos;

import framework.dao.ExtHibernateDaoSupport;
import model.UsercommentEntity;
import model.UserinfoEntity;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
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
 * Created by 11022 on 2017/1/31.
 */
@Repository("usercommentDAO")
@Transactional
public class UsercommentDAO extends ExtHibernateDaoSupport {
    private static final Logger log = LoggerFactory
            .getLogger(UsercommentDAO.class);

    @Autowired
    public void setMySessionFactory(SessionFactory sessionFactory) {
        super.setSessionFactory(sessionFactory);
    }
    public UsercommentEntity save(final UsercommentEntity usercommentEntity, final int id, final Class cls) {
        log.debug("saving Usercomment instance");
        try {
            getHibernateTemplate().execute(new HibernateCallback<UsercommentEntity>() {
                @Override
                public UsercommentEntity doInHibernate(Session session) throws HibernateException, SQLException {
                    UserinfoEntity userinfoByCommenterId=(UserinfoEntity)session.get(UserinfoEntity.class,usercommentEntity.getCommenterId());
                    usercommentEntity.setUserinfoByCommenterId(userinfoByCommenterId);
                    session.save(usercommentEntity);
                    String table = cls.getSimpleName().toLowerCase();
                    table = table.substring(0, table.length() - 6);
                    System.out.println(table);
                    SQLQuery query = session.createSQLQuery("insert into " + table + "_comment values(?,?)");
                    query.setParameter(0, id);
                    query.setParameter(1, usercommentEntity.getCommentId());
                    query.executeUpdate();
                    return usercommentEntity;
                }
            });
            log.debug("save Usercomment successful");
            return usercommentEntity;
        } catch (RuntimeException re) {
            log.error("save Usercomment failed", re);
            throw re;
        }
    }

    public List<UsercommentEntity> findBlogCommentListByPage(final int blogID, final int pageNumber, final int pageSize) {
        final int offset = pageNumber * pageSize;
        log.debug("finding Usercomment instance list by page: offset= " + offset + " pageSize= " + pageSize);
        try {
            //List<UsercommentEntity> list = findByPage("from model.TagEntity where", offset, pageSize);
            List<UsercommentEntity> list= (List<UsercommentEntity>)getHibernateTemplate().executeFind(new HibernateCallback<Object>() {
                @Override
                public List<UsercommentEntity> doInHibernate(Session session) throws HibernateException, SQLException {
                    List<UsercommentEntity> usercommentEntityList=new ArrayList<UsercommentEntity>();
                    SQLQuery query = session.createSQLQuery("SELECT commentIDNum from blog_comment where commentBlogID =?");
                    query.setParameter(0,blogID);
                    query.setFirstResult(offset).setMaxResults(pageSize);
                    for(Integer commentID:(List<Integer>)query.list()){
                        usercommentEntityList.add((UsercommentEntity) session.get("model.UsercommentEntity",commentID));
                    }
                    return usercommentEntityList;
                }
            });
            log.debug("find Usercomment instance list by page successful");
            return list;
        } catch (RuntimeException re) {
            log.error("find Usercomment instance list by page failed", re);
            throw re;
        }
    }

    public List<UsercommentEntity> findResourceCommentListByPage(final int resourceID, final int pageNumber, final int pageSize) {
        final int offset = pageNumber * pageSize;
        log.debug("finding Usercomment instance list by page: offset= " + offset + " pageSize= " + pageSize);
        try {
            //List<UsercommentEntity> list = findByPage("from model.TagEntity where", offset, pageSize);
            List<UsercommentEntity> list= (List<UsercommentEntity>)getHibernateTemplate().executeFind(new HibernateCallback<Object>() {
                @Override
                public List<UsercommentEntity> doInHibernate(Session session) throws HibernateException, SQLException {
                    List<UsercommentEntity> usercommentEntityList=new ArrayList<UsercommentEntity>();
                    SQLQuery query = session.createSQLQuery("SELECT commentIDNum from resource_comment where commentResourceID =?");
                    query.setParameter(0,resourceID);
                    query.setFirstResult(offset).setMaxResults(pageSize);
                    for(Integer commentID:(List<Integer>)query.list()){
                        usercommentEntityList.add((UsercommentEntity) session.get("model.UsercommentEntity",commentID));
                    }
                    return usercommentEntityList;
                }
            });
            log.debug("find Usercomment instance list by page successful");
            return list;
        } catch (RuntimeException re) {
            log.error("find Usercomment instance list by page failed", re);
            throw re;
        }
    }
}
