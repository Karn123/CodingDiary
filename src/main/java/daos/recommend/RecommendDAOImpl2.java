package daos.recommend;

import framework.dao.ExtHibernateDaoSupport;
import model.BlogEntity;
import model.ForumpostEntity;
import model.ResourceEntity;
import model.UserActionHistoryEntity;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateCallback;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by 11022 on 2017/3/9.
 */
//@Service(value = "recommendDAO")
public class RecommendDAOImpl2  extends ExtHibernateDaoSupport implements RecommendDAO{
    private static final Logger log = LoggerFactory
            .getLogger(RecommendDAOImpl2.class);

    @Autowired
    public void setMySessionFactory(SessionFactory sessionFactory) {
        super.setSessionFactory(sessionFactory);
    }

    @Override
    public void saveAction(final UserActionHistoryEntity userActionHistoryEntity, int returnCode) {
        log.debug("saving UserActionHistory");
        try {
            getHibernateTemplate().execute(new HibernateCallback<Object>() {
                @Override
                public Object doInHibernate(Session session) throws HibernateException, SQLException {
                    userActionHistoryEntity.setActionTime(new Timestamp(new Date().getTime()));
                    session.createSQLQuery("INSERT INTO user_action_history (user_id, action_id, action_about_id, about_theme_id, action_time) " +
                            "VALUES (?, ?, ?, ?, ?) ON DUPLICATE KEY UPDATE action_type=action_type*-1")
                            .setParameter(0, userActionHistoryEntity.getUserId())
                            .setParameter(1, userActionHistoryEntity.getActionId())
                            .setParameter(2, userActionHistoryEntity.getActionAboutId())
                            .setParameter(3, userActionHistoryEntity.getAboutThemeId())
                            .setParameter(4, userActionHistoryEntity.getActionTime())
                            .executeUpdate();
                    return null;
                }
            });
            log.debug("save UserActionHistory successful");
        }catch (RuntimeException re) {
            log.error("save UserActionHistory failed", re);
            throw re;
        }
    }

    @Override
    public void updateRecommend(int recommendBlogNumber) {

    }

    @Override
    public void updateNewRecommend(int recommendBlogNumber) {

    }

    @Override
    public void updateTotalValue() {

    }

    @Override
    public void updateUserAction() {

    }

    @Override
    public int updateUserParameter(int userID, int[] action) {
        return 0;
    }

    @Override
    public int updateUserInterest(int userID, int tagID) {
        return 0;
    }

    @Override
    public int preUpdateUserInterest(int userID, int tagNumber) {
        return 0;
    }

    @Override
    public int setNewUserInterest(int userID, List<Integer> tagIDList) {
        return 0;
    }

    @Override
    public List<Integer> getUserInterest(int userID, int mostInterestNumber, int newestInterestNumber, int randomNumber) {
        return null;
    }

    @Override
    public Map<String, List<BlogEntity>> getRecommendBlogs(int hottestBlogNumber, int newestBlogNumber, int hottestInterestingBlogNumber, int newestInterestingBlogNumber, List<Integer> userInterests) {
        return null;
    }

    @Override
    public Map<String, List<ForumpostEntity>> getRecommendForumposts(int hottestForumpostNumber, int newestForumpostNumber, int hottestInterestingForumpostNumber, int newestInterestingForumpostNumber, List<Integer> userInterests) {
        return null;
    }

    @Override
    public Map<String, List<ResourceEntity>> getRecommendResources(int hottestResourceNumber, int newestResourceNumber, int hottestInterestingResourceNumber, int newestInterestingResourceNumber, List<Integer> userInterests) {
        return null;
    }
}
