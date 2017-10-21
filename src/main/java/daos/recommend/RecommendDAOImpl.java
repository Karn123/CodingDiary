package daos.recommend;

import cst.TopNPriorityQueue;
import cst.UserAction;
import cst.UserActionAbout;
import framework.dao.ExtHibernateDaoSupport;
import model.BlogEntity;
import model.ForumpostEntity;
import model.ResourceEntity;
import model.UserActionHistoryEntity;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.*;

/**
 * Created by 11022 on 2017/2/27.
 */
//@Deprecated
@Repository("recommendDAO")
//@Transactional
public class RecommendDAOImpl extends ExtHibernateDaoSupport implements RecommendDAO {
    private static final Logger log = LoggerFactory
            .getLogger(RecommendDAOImpl.class);

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
                    if (session.createSQLQuery("SELECT * FROM user_action_history WHERE user_id=? AND action_id=? AND action_about_id=? AND about_theme_id=?")
                            .setParameter(0, userActionHistoryEntity.getUserId()).setParameter(1, userActionHistoryEntity.getActionId())
                            .setParameter(2, userActionHistoryEntity.getActionAboutId()).setParameter(3, userActionHistoryEntity.getAboutThemeId()).list().size() == 0) {
                        session.createSQLQuery("insert into user_action_history (user_id, action_id, action_about_id, about_theme_id, action_time) values (?, ?, ?, ?, ?)")
                                .setParameter(0, userActionHistoryEntity.getUserId())
                                .setParameter(1, userActionHistoryEntity.getActionId())
                                .setParameter(2, userActionHistoryEntity.getActionAboutId())
                                .setParameter(3, userActionHistoryEntity.getAboutThemeId())
                                .setParameter(4, userActionHistoryEntity.getActionTime())
                                .executeUpdate();
                    } else {
                        if (userActionHistoryEntity.getActionId() != UserAction.BROWSE.ordinal()) {
                            session.createSQLQuery("UPDATE user_action_history SET action_type=action_type*-1 WHERE user_id=? AND action_id=? AND action_about_id=? AND about_theme_id=?")
                                    .setParameter(0, userActionHistoryEntity.getUserId()).setParameter(1, userActionHistoryEntity.getActionId())
                                    .setParameter(2, userActionHistoryEntity.getActionAboutId()).setParameter(3, userActionHistoryEntity.getAboutThemeId())
                                    .executeUpdate();
                        }
                    }
                    return null;
                }
            });
            log.debug("save UserActionHistory successful");
        } catch (RuntimeException re) {
            log.error("save UserActionHistory failed", re);
            throw re;
        }
    }

    @Override
    public void updateRecommend(int recommendNumber) {
        updateBlogRecommend(recommendNumber);
        updateForumpostRecommend(recommendNumber);
        updateResourceRecommend(recommendNumber);
    }

    @Override
    public void updateNewRecommend(int recommendNumber) {
        updateNewBlogRecommend(recommendNumber);
        updateNewForumpostRecommend(recommendNumber);
        updateNewResourceRecommend(recommendNumber);
    }

    protected void updateBlogRecommend(final int recommendBlogNumber) {
        log.debug("updating blog_recommend");
        try {
            getHibernateTemplate().execute(new HibernateCallback<Object>() {
                @Override
                public Object doInHibernate(Session session) throws HibernateException, SQLException {
                    session.createSQLQuery("DELETE FROM blog_recommend").executeUpdate();
                    int total = Integer.parseInt(session.createSQLQuery("select count(1) from blog_recommend_value WHERE isNew = ?").setParameter(0, 0).uniqueResult().toString());
                    int tagTotal = Integer.parseInt(session.createSQLQuery("select count(1) from tag").uniqueResult().toString());
                    int remainTagNumber = tagTotal;
                    int[] tag = new int[tagTotal + 1];
                    for (int i = 0; i < total; i += tagTotal) {
                        SQLQuery sqlQuery = session.createSQLQuery("SELECT blogID FROM blog_recommend_value WHERE isNew = ? ORDER BY totalValue DESC");
                        sqlQuery.setParameter(0, 0).setFirstResult(i * tagTotal).setMaxResults(tagTotal);
                        List<Integer> blogIDList = (List<Integer>) sqlQuery.list();
                        for (Integer blogID : blogIDList) {
                            SQLQuery query = session.createSQLQuery("SELECT TagIDNum FROM blog_tag WHERE BlogIDNum=?");
                            query.setParameter(0, blogID);
                            List<Integer> tagIDList = (List<Integer>) query.list();
                            for (Integer tagID : tagIDList) {
                                tag[tagID]++;
                                if (tag[tagID] == recommendBlogNumber) {
                                    remainTagNumber--;
                                    if (remainTagNumber == 0) {
                                        return null;
                                    }
                                } else if (tag[tagID] <= recommendBlogNumber) {
                                    session.createSQLQuery("INSERT INTO blog_recommend VALUES (?,?)")
                                            .setParameter(0, tagID).setParameter(1, blogID).executeUpdate();
                                }
                            }
                        }
                    }
                    return null;
                }
            });
            log.debug("update blog_recommend successful");
        } catch (RuntimeException re) {
            log.error("update blog_recommend failed", re);
            throw re;
        }
    }


    protected void updateNewBlogRecommend(final int recommendBlogNumber) {
        log.debug("updating blog_recommend_new");
        try {
            getHibernateTemplate().execute(new HibernateCallback<Object>() {
                @Override
                public Object doInHibernate(Session session) throws HibernateException, SQLException {
                    int total = Integer.parseInt(session.createSQLQuery("select count(1) from blog_recommend_value WHERE isNew = ?").setParameter(0, 1).uniqueResult().toString());
                    int tagTotal = Integer.parseInt(session.createSQLQuery("select count(1) from tag").uniqueResult().toString());
                    int remainTagNumber = tagTotal;
                    int[] tag = new int[tagTotal + 1];
                    for (int i = 0; i < total; i += tagTotal) {
                        SQLQuery sqlQuery = session.createSQLQuery("SELECT blogID FROM blog_recommend_value WHERE isNew = ? ORDER BY todayValue DESC");
                        sqlQuery.setParameter(0, 1).setFirstResult(i * tagTotal).setMaxResults(tagTotal);
                        List<Integer> blogIDList = (List<Integer>) sqlQuery.list();
                        for (Integer blogID : blogIDList) {
                            SQLQuery query = session.createSQLQuery("SELECT TagIDNum FROM blog_tag WHERE BlogIDNum=?");
                            query.setParameter(0, blogID);
                            List<Integer> tagIDList = (List<Integer>) query.list();
                            for (Integer tagID : tagIDList) {
                                tag[tagID]++;
                                if (tag[tagID] == recommendBlogNumber) {
                                    remainTagNumber--;
                                    if (remainTagNumber == 0) {
                                        return null;
                                    }
                                } else if (tag[tagID] <= recommendBlogNumber) {
                                    session.createSQLQuery("INSERT INTO blog_recommend_new VALUES (?,?)")
                                            .setParameter(0, tagID).setParameter(1, blogID).executeUpdate();
                                }
                            }
                        }
                    }
                    return null;
                }
            });
            log.debug("update blog_recommend_new successful");
        } catch (RuntimeException re) {
            log.error("update blog_recommend_new failed", re);
            throw re;
        }
    }


    protected void updateResourceRecommend(final int recommendResourceNumber) {
        log.debug("updating resource_recommend");
        try {
            getHibernateTemplate().execute(new HibernateCallback<Object>() {
                @Override
                public Object doInHibernate(Session session) throws HibernateException, SQLException {
                    session.createSQLQuery("DELETE FROM resource_recommend").executeUpdate();
                    int total = Integer.parseInt(session.createSQLQuery("select count(1) from resource_recommend_value WHERE isNew = ?").setParameter(0, 0).uniqueResult().toString());
                    int tagTotal = Integer.parseInt(session.createSQLQuery("select count(1) from tag").uniqueResult().toString());
                    int remainTagNumber = tagTotal;
                    int[] tag = new int[tagTotal + 1];
                    for (int i = 0; i < total; i += tagTotal) {
                        SQLQuery sqlQuery = session.createSQLQuery("SELECT resourceID FROM resource_recommend_value WHERE isNew = ? ORDER BY totalValue DESC");
                        sqlQuery.setParameter(0, 0).setFirstResult(i * tagTotal).setMaxResults(tagTotal);
                        List<Integer> resourceIDList = (List<Integer>) sqlQuery.list();
                        for (Integer resourceID : resourceIDList) {
                            SQLQuery query = session.createSQLQuery("SELECT tagIDNum FROM resource_tag WHERE resourceIDNum=?");
                            query.setParameter(0, resourceID);
                            List<Integer> tagIDList = (List<Integer>) query.list();
                            for (Integer tagID : tagIDList) {
                                tag[tagID]++;
                                if (tag[tagID] == recommendResourceNumber) {
                                    remainTagNumber--;
                                    if (remainTagNumber == 0) {
                                        return null;
                                    }
                                } else if (tag[tagID] <= recommendResourceNumber) {
                                    session.createSQLQuery("INSERT INTO resource_recommend VALUES (?,?)")
                                            .setParameter(0, tagID).setParameter(1, resourceID).executeUpdate();
                                }
                            }
                        }
                    }
                    return null;
                }
            });
            log.debug("update resource_recommend successful");
        } catch (RuntimeException re) {
            log.error("update resource_recommend failed", re);
            throw re;
        }
    }


    protected void updateNewResourceRecommend(final int recommendResourceNumber) {
        log.debug("updating resource_recommend_new");
        try {
            getHibernateTemplate().execute(new HibernateCallback<Object>() {
                @Override
                public Object doInHibernate(Session session) throws HibernateException, SQLException {
                    int total = Integer.parseInt(session.createSQLQuery("select count(1) from resource_recommend_value WHERE isNew = ?").setParameter(0, 1).uniqueResult().toString());
                    int tagTotal = Integer.parseInt(session.createSQLQuery("select count(1) from tag").uniqueResult().toString());
                    int remainTagNumber = tagTotal;
                    int[] tag = new int[tagTotal + 1];
                    for (int i = 0; i < total; i += tagTotal) {
                        SQLQuery sqlQuery = session.createSQLQuery("SELECT resourceID FROM resource_recommend_value WHERE isNew = ? ORDER BY todayValue DESC");
                        sqlQuery.setParameter(0, 1).setFirstResult(i * tagTotal).setMaxResults(tagTotal);
                        List<Integer> resourceIDList = (List<Integer>) sqlQuery.list();
                        for (Integer resourceID : resourceIDList) {
                            SQLQuery query = session.createSQLQuery("SELECT tagIDNum FROM resource_tag WHERE resourceIDNum=?");
                            query.setParameter(0, resourceID);
                            List<Integer> tagIDList = (List<Integer>) query.list();
                            for (Integer tagID : tagIDList) {
                                tag[tagID]++;
                                if (tag[tagID] == recommendResourceNumber) {
                                    remainTagNumber--;
                                    if (remainTagNumber == 0) {
                                        return null;
                                    }
                                } else if (tag[tagID] <= recommendResourceNumber) {
                                    session.createSQLQuery("INSERT INTO resource_recommend_new VALUES (?,?)")
                                            .setParameter(0, tagID).setParameter(1, resourceID).executeUpdate();
                                }
                            }
                        }
                    }
                    return null;
                }
            });
            log.debug("update resource_recommend_new successful");
        } catch (RuntimeException re) {
            log.error("update resource_recommend_new failed", re);
            throw re;
        }
    }


    protected void updateForumpostRecommend(final int recommendForumpostNumber) {
        log.debug("updating forumpost_recommend");
        try {
            getHibernateTemplate().execute(new HibernateCallback<Object>() {
                @Override
                public Object doInHibernate(Session session) throws HibernateException, SQLException {
                    session.createSQLQuery("DELETE FROM forumpost_recommend").executeUpdate();
                    int total = Integer.parseInt(session.createSQLQuery("select count(1) from forumpost_recommend_value WHERE isNew = ?").setParameter(0, 0).uniqueResult().toString());
                    int tagTotal = Integer.parseInt(session.createSQLQuery("select count(1) from tag").uniqueResult().toString());
                    int remainTagNumber = tagTotal;
                    int[] tag = new int[tagTotal + 1];
                    for (int i = 0; i < total; i += tagTotal) {
                        SQLQuery sqlQuery = session.createSQLQuery("SELECT forumpostID FROM forumpost_recommend_value WHERE isNew = ? ORDER BY totalValue DESC");
                        sqlQuery.setParameter(0, 0).setFirstResult(i * tagTotal).setMaxResults(tagTotal);
                        List<Integer> forumpostIDList = (List<Integer>) sqlQuery.list();
                        for (Integer forumpostID : forumpostIDList) {
                            SQLQuery query = session.createSQLQuery("SELECT tagID FROM forumpost_tag WHERE forumpostID=?");
                            query.setParameter(0, forumpostID);
                            List<Integer> tagIDList = (List<Integer>) query.list();
                            for (Integer tagID : tagIDList) {
                                tag[tagID]++;
                                if (tag[tagID] == recommendForumpostNumber) {
                                    remainTagNumber--;
                                    if (remainTagNumber == 0) {
                                        return null;
                                    }
                                } else if (tag[tagID] <= recommendForumpostNumber) {
                                    session.createSQLQuery("INSERT INTO forumpost_recommend VALUES (?,?)")
                                            .setParameter(0, tagID).setParameter(1, forumpostID).executeUpdate();
                                }
                            }
                        }
                    }
                    return null;
                }
            });
            log.debug("update forumpost_recommend successful");
        } catch (RuntimeException re) {
            log.error("update forumpost_recommend failed", re);
            throw re;
        }
    }


    protected void updateNewForumpostRecommend(final int recommendForumpostNumber) {
        log.debug("updating forumpost_recommend_new");
        try {
            getHibernateTemplate().execute(new HibernateCallback<Object>() {
                @Override
                public Object doInHibernate(Session session) throws HibernateException, SQLException {
                    int total = Integer.parseInt(session.createSQLQuery("select count(1) from forumpost_recommend_value WHERE isNew = ?").setParameter(0, 1).uniqueResult().toString());
                    int tagTotal = Integer.parseInt(session.createSQLQuery("select count(1) from tag").uniqueResult().toString());
                    int remainTagNumber = tagTotal;
                    int[] tag = new int[tagTotal + 1];
                    for (int i = 0; i < total; i += tagTotal) {
                        SQLQuery sqlQuery = session.createSQLQuery("SELECT forumpostID FROM forumpost_recommend_value WHERE isNew = ? ORDER BY todayValue DESC");
                        sqlQuery.setParameter(0, 1).setFirstResult(i * tagTotal).setMaxResults(tagTotal);
                        List<Integer> forumpostIDList = (List<Integer>) sqlQuery.list();
                        for (Integer forumpostID : forumpostIDList) {
                            SQLQuery query = session.createSQLQuery("SELECT tagID FROM forumpost_tag WHERE forumpostID=?");
                            query.setParameter(0, forumpostID);
                            List<Integer> tagIDList = (List<Integer>) query.list();
                            for (Integer tagID : tagIDList) {
                                tag[tagID]++;
                                if (tag[tagID] == recommendForumpostNumber) {
                                    remainTagNumber--;
                                    if (remainTagNumber == 0) {
                                        return null;
                                    }
                                } else if (tag[tagID] <= recommendForumpostNumber) {
                                    session.createSQLQuery("INSERT INTO forumpost_recommend_new VALUES (?,?)")
                                            .setParameter(0, tagID).setParameter(1, forumpostID).executeUpdate();
                                }
                            }
                        }
                    }
                    return null;
                }
            });
            log.debug("update forumpost_recommend_new successful");
        } catch (RuntimeException re) {
            log.error("update forumpost_recommend_new failed", re);
            throw re;
        }
    }

    @Override
    public void updateTotalValue() {
        log.debug("updating TotalValue");
        try {
            getHibernateTemplate().execute(new HibernateCallback<Object>() {
                @Override
                public Object doInHibernate(Session session) throws HibernateException, SQLException {
                    int total = Integer.parseInt(session.createSQLQuery("select count(1) from blog_recommend_value").uniqueResult().toString());
                    int pageSize = 100;
                    for (int i = 0; i < total; i += pageSize) {
                        SQLQuery sqlQuery = session.createSQLQuery("SELECT blog.blogID,blog.publishTime,blog_recommend_value.todayValue,blog_recommend_value.isNew FROM blog INNER JOIN blog_recommend_value ON blog.blogID=blog_recommend_value.blogID");
                        sqlQuery.setFirstResult(i * pageSize).setMaxResults(pageSize).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
                        List<Map<String, Object>> list = sqlQuery.list();
                        Long time = new Date().getTime();
                        for (Map<String, Object> map : list) {
                            int blogID = (Integer) map.get("blogID");
                            int isNew = (Integer) map.get("isNew");
                            double todayValue = (Double) map.get("todayValue");
                            Timestamp publishTime = (Timestamp) map.get("publishTime");
                            int interval = (int) ((time - publishTime.getTime()) / (1000 * 60 * 60 * 24));
                            if (interval > 7) {
                                if (isNew == 1) {
                                    session.createSQLQuery("UPDATE blog_recommend_value set isNew=0 where blogID=?")
                                            .setParameter(0, blogID).executeUpdate();
                                }
                                session.createSQLQuery("UPDATE blog_recommend_value set totalValue=totalValue+? where blogID=?")
                                        .setParameter(0, todayValue / interval).setParameter(1, blogID).executeUpdate();
                            }
                        }
                    }

                    return null;
                }
            });
            log.debug("update TotalValue successful");
        } catch (RuntimeException re) {
            log.error("update TotalValue failed", re);
            throw re;
        }
    }

    @Override
    public void updateUserAction() {
        getHibernateTemplate().execute(new HibernateCallback<Object>() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
                int total = Integer.parseInt(session.createSQLQuery("select count(distinct user_id) from user_action_history WHERE action_type=1;").uniqueResult().toString());
                int tagTotal = Integer.parseInt(session.createSQLQuery("select count(1) from tag").uniqueResult().toString());
                int pageSize = 100;
                for (int i = 0; i < total; i += pageSize) {
                    SQLQuery sqlQuery = session.createSQLQuery("SELECT distinct user_id FROM user_action_history WHERE action_type=1");
                    sqlQuery.setFirstResult(i * pageSize).setMaxResults(pageSize);
                    List<Integer> list = sqlQuery.list();
                    Long time = new Date().getTime();
                    for (Integer userID : list) {
                        preUpdateUserInterest(userID, tagTotal);
                        int[] action = new int[4];
                        List<Map<String, Object>> maps =
                                session.createSQLQuery("SELECT action_id,action_about_id,about_theme_id FROM user_action_history WHERE action_type=1 AND user_id=?")
                                        .setParameter(0, userID).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
                        for (Map<String, Object> map : maps) {
                            UserAction userAction = UserAction.values()[(Integer) map.get("action_id")];
                            switch (userAction) {
                                case BROWSE:
                                    action[0]++;
                                    break;
                                case COLLECT:
                                    action[1]++;
                                    break;
                                case PRAISE:
                                    action[2]++;
                                    break;
                                case FORWARD:
                                    action[3]++;
                                    break;
                                default:
                                    break;
                            }
                            UserActionAbout userActionAbout = UserActionAbout.values()[(Integer) map.get("action_about_id")];
                            int id = (Integer) map.get("about_theme_id");
                            switch (userActionAbout) {
                                case BLOG:
                                    List<Integer> tagIdList = session.createSQLQuery("SELECT TagIDNum FROM blog_tag WHERE BlogIDNum=?")
                                            .setParameter(0, id).list();
                                    for (Integer tagID : tagIdList) {
                                        updateUserInterest(userID, tagID);
                                    }
                                    break;
                                default:
                                    break;
                            }
                        }
                        updateUserParameter(userID, action);
                    }
                }
                return null;
            }
        });
    }

    @Override
    public int updateUserParameter(final int userID, final int[] action) {
        return 0;
    }

    @Override
    public int updateUserInterest(final int userID, final int tagID) {
        getHibernateTemplate().execute(new HibernateCallback<Integer>() {
            @Override
            public Integer doInHibernate(Session session) throws HibernateException, SQLException {
                int mask = 0x40000000;
                List<Integer> list = session.createSQLQuery("SELECT interestCount FROM user_interest WHERE userID=? AND tagID=?").
                        setParameter(0, userID).setParameter(1, tagID).list();
                if (list.size() == 0) {
                    session.createSQLQuery("INSERT INTO  user_interest VALUES (?,?,?,?)")
                            .setParameter(0, userID).setParameter(1, tagID).setParameter(2, mask).setParameter(3, mask)
                            .executeUpdate();
                } else {
                    session.createSQLQuery("UPDATE user_interest SET interestCount=?,chance=? WHERE userID=? AND tagID=?").
                            setParameter(0, list.get(0) | mask).setParameter(1, mask).setParameter(2, userID).setParameter(3, tagID)
                            .executeUpdate();
                }
                return null;
            }
        });
        return 0;
    }

    @Override
    public int preUpdateUserInterest(final int userID, final int tagNumber) {
        return getHibernateTemplate().execute(new HibernateCallback<Integer>() {
            @Override
            public Integer doInHibernate(Session session) throws HibernateException, SQLException {
                int interestNumber = Integer.parseInt(session.createSQLQuery("SELECT count(1) FROM user_interest WHERE userID=?")
                        .setParameter(0, userID).uniqueResult().toString());
                int n = interestNumber / tagNumber * 28;
                int mask = (0x80000000 >> (n + 3));
                session.createSQLQuery("UPDATE user_interest SET interestCount=interestCount>>1,chance=chance>>1 WHERE userID=?")
                        .setParameter(0, userID).executeUpdate();
                session.createSQLQuery("DELETE FROM user_interest WHERE interestCount&?=0 OR chance&?=0")
                        .setParameter(0, mask).setParameter(1, mask).executeUpdate();
                return n;
            }
        });
    }

    @Override
    public int setNewUserInterest(final int userID, final List<Integer> tagIDList) {
        getHibernateTemplate().execute(new HibernateCallback<Integer>() {
            @Override
            public Integer doInHibernate(Session session) throws HibernateException, SQLException {
                int mask = 0x40000000;
                for (int tagID : tagIDList) {
                    session.createSQLQuery("INSERT INTO user_interest VALUES (?,?,?,?)")
                            .setParameter(0, userID).setParameter(1, tagID).setParameter(2, mask).setParameter(3, mask)
                            .executeUpdate();
                }
                return null;
            }
        });
        return 0;
    }

    @Override
    public List<Integer> getUserInterest(final int userID, final int mostInterestNumber, final int newestInterestNumber, final int randomNumber) {
        try {
            List<Integer> resultList = getHibernateTemplate().execute(new HibernateCallback<List<Integer>>() {
                @Override
                public List<Integer> doInHibernate(Session session) throws HibernateException, SQLException {
                    List<Map<String, Object>> list = session.createSQLQuery("SELECT tagID,interestCount FROM user_interest WHERE userID=?")
                            .setParameter(0, userID).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
                    List<Integer> result = new ArrayList<Integer>();
                    if (list.size() == 0) {
                        result.add(0);
                        return result;
                    }
                    Set<Map<String, Object>> mapSet = new HashSet<Map<String, Object>>(list);
                    TopNPriorityQueue<Map<String, Object>> mostInterestTopNPriorityQueue = new TopNPriorityQueue<Map<String, Object>>(mostInterestNumber, new Comparator<Map<String, Object>>() {
                        @Override
                        public int compare(Map<String, Object> o1, Map<String, Object> o2) {
                            return (Integer) o1.get("interestCount") - (Integer) o2.get("interestCount");
                        }
                    });
                    TopNPriorityQueue<Map<String, Object>> newestInterestTopNPriorityQueue = new TopNPriorityQueue<Map<String, Object>>(newestInterestNumber, new Comparator<Map<String, Object>>() {
                        @Override
                        public int compare(Map<String, Object> o1, Map<String, Object> o2) {
                            return Integer.bitCount((Integer) o1.get("interestCount")) - Integer.bitCount((Integer) o2.get("interestCount"));
                        }
                    });
                    for (Map<String, Object> map : mapSet) {
                        mostInterestTopNPriorityQueue.push(map);
                        newestInterestTopNPriorityQueue.push(map);
                    }
                    Set<Integer> set = new HashSet<Integer>();
                    for (Map<String, Object> map : mostInterestTopNPriorityQueue.getQueue()) {
                        set.add((Integer) map.get("tagID"));
                        mapSet.remove(map);
                    }
                    for (Map<String, Object> map : newestInterestTopNPriorityQueue.getQueue()) {
                        set.add((Integer) map.get("tagID"));
                        mapSet.remove(map);
                    }
                    Random random = new Random();
                    int randomSize = mapSet.size();
                    List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>(mapSet);
                    if (randomNumber > randomSize * 3 / 4) {
                        for (Map<String, Object> map : mapList.subList(0, randomSize * 3 / 4)) {
                            set.add((Integer) map.get("tagID"));
                        }
                    } else {
                        for (int i = 0; i < randomNumber; i++) {
                            while (!set.add((Integer) mapList.get(random.nextInt(randomSize)).get("tagID"))) ;
                        }
                    }
                    result.addAll(set);
                    return result;
                }
            });
            return resultList;
        } catch (RuntimeException re) {
            throw re;
        }

    }

    protected <T> List<T> getRandomEntity(final Class<T> cls, final int totalSize, final int needSize, final String sql) {
        try {
            if (needSize <= 0) {
                return new ArrayList<T>();
            }
            List<T> resultList = getHibernateTemplate().execute(new HibernateCallback<List<T>>() {
                @Override
                public List<T> doInHibernate(Session session) throws HibernateException, SQLException {
                    List<Integer> list = session.createSQLQuery(sql).setMaxResults(totalSize).list();
                    int randomSize = list.size();
                    Random random = new Random();
                    Set<Integer> set = new HashSet<Integer>();
                    if (needSize >= randomSize ) {
                        for (Integer blogID : list.subList(0, randomSize)) {
                            set.add(blogID);
                        }
                    }else if (needSize > randomSize * 3 / 4) {
                        for (Integer blogID : list.subList(0, randomSize * 3 / 4)) {
                            set.add(blogID);
                        }
                    } else {
                        for (int i = 0; i < needSize; i++) {
                            while (!set.add(list.get(random.nextInt(randomSize)))) ;
                        }
                    }
                    List<T> entityList = new ArrayList<T>();
                    for (Integer ID : set) {
                        entityList.add((T) session.get(cls, ID));
                    }
                    return entityList;
                }
            });
            return resultList;
        } catch (RuntimeException re) {
            throw re;
        }
    }

    @Override
    public Map<String, List<BlogEntity>> getRecommendBlogs(int hottestBlogNumber, int newestBlogNumber, int hottestInterestingBlogNumber, int newestInterestingBlogNumber, List<Integer> userInterests) {
        Map<String, List<BlogEntity>> map = new HashMap<String, List<BlogEntity>>();
        map.put("hottestBlog", getHottestBlog(hottestBlogNumber));
        map.put("newestBlog", getNewestBlog(newestBlogNumber));
        map.put("hottestInterestingBlog", getHottestInterestBlog(hottestInterestingBlogNumber, userInterests));
        map.put("newestInterestingBlog", getNewestInterestBlog(newestInterestingBlogNumber, userInterests));
        return map;
    }

    protected List<BlogEntity> getHottestBlog(final int size) {
        try {
            String sql = "SELECT blogID FROM blog_recommend_value ORDER BY totalValue DESC ";
            return getRandomEntity(BlogEntity.class, 20, size, sql);
        } catch (RuntimeException re) {
            throw re;
        }
    }

    protected List<BlogEntity> getNewestBlog(final int size) {
        try {
            String sql = "SELECT blogID FROM blog_recommend_value WHERE isNEW = 1 ORDER BY todayValue DESC ";
            return getRandomEntity(BlogEntity.class, 20, size, sql);
        } catch (RuntimeException re) {
            throw re;
        }
    }

    protected List<BlogEntity> getHottestInterestBlog(final int size, List<Integer> userInterests) {
        try {
            List<BlogEntity> blogEntityList = new ArrayList<BlogEntity>();
            for (int tagID : userInterests) {
                if (tagID != 0) {
                    String sql = "SELECT recommendBlogID FROM blog_recommend WHERE tagID =" + tagID;
                    blogEntityList.addAll(getRandomEntity(BlogEntity.class, 5, size, sql));
                } else {
                    blogEntityList.addAll(getHottestBlog(size));
                }
            }
            return blogEntityList;
        } catch (RuntimeException re) {
            throw re;
        }
    }

    protected List<BlogEntity> getNewestInterestBlog(final int size, List<Integer> userInterests) {
        try {
            List<BlogEntity> blogEntityList = new ArrayList<BlogEntity>();
            for (int tagID : userInterests) {
                if (tagID != 0) {
                    String sql = "SELECT recommendBlogID FROM blog_recommend_new WHERE tagID =" + tagID;
                    blogEntityList.addAll(getRandomEntity(BlogEntity.class, 5, size, sql));
                } else {
                    blogEntityList.addAll(getNewestBlog(size));
                }
            }
            return blogEntityList;
        } catch (RuntimeException re) {
            throw re;
        }
    }

    @Override
    public Map<String, List<ForumpostEntity>> getRecommendForumposts(int hottestForumpostNumber, int newestForumpostNumber, int hottestInterestingForumpostNumber, int newestInterestingForumpostNumber, List<Integer> userInterests) {
        Map<String, List<ForumpostEntity>> map = new HashMap<String, List<ForumpostEntity>>();
        map.put("hottestForumpost", getHottestForumpost(hottestForumpostNumber));
        map.put("newestForumpost", getNewestForumpost(newestForumpostNumber));
        map.put("hottestInterestingForumpost", getHottestInterestForumpost(hottestInterestingForumpostNumber, userInterests));
        map.put("newestInterestingForumpost", getNewestInterestForumpost(newestInterestingForumpostNumber, userInterests));

        return map;
    }

    protected List<ForumpostEntity> getHottestForumpost(final int size) {
        try {
            String sql = "SELECT forumpostID FROM forumpost_recommend_value ORDER BY totalValue DESC ";
            return getRandomEntity(ForumpostEntity.class, 20, size, sql);
        } catch (RuntimeException re) {
            throw re;
        }
    }

    protected List<ForumpostEntity> getNewestForumpost(final int size) {
        try {
            String sql = "SELECT forumpostID FROM forumpost_recommend_value WHERE isNEW = 1 ORDER BY todayValue DESC ";
            return getRandomEntity(ForumpostEntity.class, 20, size, sql);
        } catch (RuntimeException re) {
            throw re;
        }
    }

    protected List<ForumpostEntity> getHottestInterestForumpost(final int size, List<Integer> userInterests) {
        try {
            List<ForumpostEntity> forumpostEntityList = new ArrayList<ForumpostEntity>();
            for (int tagID : userInterests) {
                if (tagID != 0) {
                    String sql = "SELECT recommendForumpostID FROM forumpost_recommend WHERE tagID =" + tagID;
                    forumpostEntityList.addAll(getRandomEntity(ForumpostEntity.class, 5, size, sql));
                } else {
                    forumpostEntityList.addAll(getHottestForumpost(size));
                }
            }
            return forumpostEntityList;
        } catch (RuntimeException re) {
            throw re;
        }
    }

    protected List<ForumpostEntity> getNewestInterestForumpost(final int size, List<Integer> userInterests) {
        try {
            List<ForumpostEntity> forumpostEntityList = new ArrayList<ForumpostEntity>();
            for (int tagID : userInterests) {
                if (tagID != 0) {
                    String sql = "SELECT recommendForumpostID FROM forumpost_recommend_new WHERE tagID =" + tagID;
                    forumpostEntityList.addAll(getRandomEntity(ForumpostEntity.class, 5, size, sql));
                } else {
                    forumpostEntityList.addAll(getNewestForumpost(size));
                }
            }
            return forumpostEntityList;
        } catch (RuntimeException re) {
            throw re;
        }
    }

    @Override
    public Map<String, List<ResourceEntity>> getRecommendResources(int hottestResourceNumber, int newestResourceNumber, int hottestInterestingResourceNumber, int newestInterestingResourceNumber, List<Integer> userInterests) {
        Map<String, List<ResourceEntity>> map = new HashMap<String, List<ResourceEntity>>();
        map.put("hottestResource", getHottestResource(hottestResourceNumber));
        map.put("newestResource", getNewestResource(newestResourceNumber));
        map.put("hottestInterestingResource", getHottestInterestResource(hottestInterestingResourceNumber, userInterests));
        map.put("newestInterestingResource", getNewestInterestResource(newestInterestingResourceNumber, userInterests));
        return map;
    }

    protected List<ResourceEntity> getHottestResource(final int size) {
        try {
            String sql = "SELECT resourceID FROM resource_recommend_value ORDER BY totalValue DESC ";
            return getRandomEntity(ResourceEntity.class, 20, size, sql);
        } catch (RuntimeException re) {
            throw re;
        }
    }

    protected List<ResourceEntity> getNewestResource(final int size) {
        try {
            String sql = "SELECT resourceID FROM resource_recommend_value WHERE isNEW = 1 ORDER BY todayValue DESC ";
            return getRandomEntity(ResourceEntity.class, 20, size, sql);
        } catch (RuntimeException re) {
            throw re;
        }
    }

    protected List<ResourceEntity> getHottestInterestResource(final int size, List<Integer> userInterests) {
        try {
            List<ResourceEntity> resourceEntityList = new ArrayList<ResourceEntity>();
            for (int tagID : userInterests) {
                if (tagID != 0) {
                    String sql = "SELECT recommendResourceID FROM resource_recommend WHERE tagID =" + tagID;
                    resourceEntityList.addAll(getRandomEntity(ResourceEntity.class, 5, size, sql));
                } else {
                    resourceEntityList.addAll(getHottestResource(size));
                }
            }
            return resourceEntityList;
        } catch (RuntimeException re) {
            throw re;
        }
    }

    protected List<ResourceEntity> getNewestInterestResource(final int size, List<Integer> userInterests) {
        try {
            List<ResourceEntity> resourceEntityList = new ArrayList<ResourceEntity>();
            for (int tagID : userInterests) {
                if (tagID != 0) {
                    String sql = "SELECT recommendResourceID FROM resource_recommend_new WHERE tagID =" + tagID;
                    resourceEntityList.addAll(getRandomEntity(ResourceEntity.class, 5, size, sql));
                } else {
                    resourceEntityList.addAll(getNewestResource(size));
                }
            }
            return resourceEntityList;
        } catch (RuntimeException re) {
            throw re;
        }
    }
}
