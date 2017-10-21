package daos;

import framework.dao.ExtHibernateDaoSupport;
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

/**
 * Created by 11022 on 2017/2/1.
 */
@Repository("praiseDAO")
@Transactional
public class PraiseDAO extends ExtHibernateDaoSupport {
    private static final Logger log = LoggerFactory.getLogger(PraiseDAO.class);

    @Autowired
    public void setMySessionFactory(SessionFactory sessionFactory) {
        super.setSessionFactory(sessionFactory);
    }

    public int praise(final int userID, final int id, final String table){
        int result = (Integer) getHibernateTemplate().execute(new HibernateCallback<Object>() {
            @Override
            public Integer doInHibernate(Session session) throws HibernateException, SQLException {
                SQLQuery query = session.createSQLQuery("SELECT * FROM praise_" + table.toLowerCase() + " WHERE praiseFromUserID=? AND praise" + table + "ID=?");
                query.setParameter(0, userID);
                query.setParameter(1, id);
                String tableName = new String(table);
                if (tableName.equals("Comment")) {
                    tableName = "User" + tableName;
                }
                if (query.list().size() == 0) {
                    SQLQuery insertQuery = session.createSQLQuery("INSERT INTO praise_" + table.toLowerCase() + " VALUES (?,?,?)");
                    insertQuery.setParameter(0, userID);
                    insertQuery.setParameter(1, id);
                    insertQuery.setParameter(2, 1);
                    insertQuery.executeUpdate();
                    session.createSQLQuery("UPDATE " + tableName.toLowerCase() + " SET praiseNum = praiseNum + 1 WHERE " + table + "ID =?").setParameter(0, id).executeUpdate();
                    return 1;
                } else {
                    SQLQuery deleteQuery = session.createSQLQuery("DELETE FROM praise_" + table.toLowerCase() + " WHERE praiseFromUserID=? AND praise" + table + "ID=?");
                    deleteQuery.setParameter(0, userID);
                    deleteQuery.setParameter(1, id);
                    deleteQuery.executeUpdate();
                    session.createSQLQuery("UPDATE " + tableName.toLowerCase() + " SET praiseNum = praiseNum - 1 WHERE " + table + "ID =?").setParameter(0, id).executeUpdate();
                    return -1;
                }
            }
        });
        return result;
    }
}
