package service.forumpostSystem.impl;

import controller.Pagination;
import cst.Authority;
import daos.ForumpostDAO;
import daos.TagDAO;
import daos.recommend.RecommendDAO;
import framework.exceptions.AuthorityException;
import model.ForumpostEntity;
import model.ForumpostFloorEntity;
import model.UserinfoEntity;
import org.springframework.stereotype.Service;
import service.common.impl.CommServiceImpl;
import service.forumpostSystem.ForumpostService;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by 佳乐 on 2017/2/13.
 */
@Service(value = "forumpostService")
public class ForumpostServiceImpl extends CommServiceImpl implements ForumpostService {
    @Resource
    protected TagDAO tagDAO;
    @Resource
    protected ForumpostDAO forumpostDAO;
    @Resource
    protected RecommendDAO recommendDAO;

    @Override
    public int postForumpost(int userId, String forumpostTitle, String forumpostContent, Set<Integer> tagsList) {
        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());
        ForumpostFloorEntity forumpostFloorEntity = new ForumpostFloorEntity();
        forumpostFloorEntity.setPublishTime(timestamp);
        forumpostFloorEntity.setFloorContent(forumpostContent);
        forumpostFloorEntity.setUserinfoByAuthorId((UserinfoEntity) (baseDAO.findByProperty("userId", userId, UserinfoEntity.class).get(0)));
        // baseDAO.executeSQL("insert into forumpost_floor(nextFloorID,authorID,floorContent,publishTime) values(?,?,?,?)",new Object[]{1,userId,});
        baseDAO.save(forumpostFloorEntity);
        ForumpostEntity forumpostEntity = new ForumpostEntity();
        forumpostEntity.setPublishTime(timestamp);
        forumpostEntity.setForumpostFloorByFirstFloorId(forumpostFloorEntity);
        forumpostEntity.setForumpostTitle(forumpostTitle);
        forumpostEntity.setUserinfoByAuthorId((UserinfoEntity)(baseDAO.findByProperty("userId", userId, UserinfoEntity.class).get(0)));
        baseDAO.save(forumpostEntity);
        for (int tagID : tagsList) {
            baseDAO.executeSQL("insert into forumpost_tag values(?,?)", new Object[]{forumpostEntity.getForumpostId(), tagID});
        }
        return forumpostEntity.getForumpostId();
    }

    @Override
    public ForumpostEntity getForumpostByID(int forumpostID, Authority authority) throws AuthorityException {
        ForumpostEntity forumpostEntity = baseDAO.findById(forumpostID, ForumpostEntity.class);
        switch (authority) {
            case ADMIN:
                return forumpostEntity;
            default:
                if (forumpostEntity.getForumpostFloorByFirstFloorId().getTfByIsLegal().getTfOption().equals("F")) {
                    throw new AuthorityException("该篇帖子已被封禁");
                }
                return forumpostEntity;
        }

    }

    @Override
    public List<ForumpostEntity> getForumpostList(String type, String typeValue, int userID, String order, Pagination pagination, Authority authority) {
        List<ForumpostEntity> original = forumpostDAO.getListByPage(type, typeValue, userID, pagination);
        pagination.updateBeginAndEndNumber();
        System.out.print(pagination.getBeginNumber() + "    " + pagination.getEndNumber());
        return original;
    }

       /* List<ForumpostEntity> original = forumpostDAO.getListByPage(type, typeValue, userID, pagination);
        List<ForumpostEntity> result = new ArrayList<ForumpostEntity>();
        switch (authority) {
            case ADMIN:
                for (ForumpostEntity forumpostEntity : original) {
                    try {
                        forumpostEntity.setPostContent(handlerHTML(forumpostEntity.getPostContent()).substring(0, Constants.abbreviatedWordsNumber) + "......");
                    } catch (Exception e) {
                        forumpostEntity.setPostContent("无法显示，请查看全文");
                    }
                    String tfOption = forumpostEntity.getTfByIsLegal().getTfOption();
                    if (!tfOption.equals("T")) {
                        forumpostEntity.setPostTitle(forumpostEntity.getPostTitle() + "（" + tfOption + "）");
                    }
                    result.add(forumpostEntity);
                }
                return result;
            default:
                for (ForumpostEntity forumpostEntity : original) {
                    if (forumpostEntity.getTfByIsLegal().getTfOption().equals("T")) {
                        try {
                            forumpostEntity.setPostContent(handlerHTML(forumpostEntity.getPostContent()).substring(0, Constants.abbreviatedWordsNumber) + "......");
                        } catch (Exception e) {
                            forumpostEntity.setPostContent("无法显示，请查看全文");
                        }
                        result.add(forumpostEntity);
                    }
                }
                return result;
        }*/

    @Override
    public List<ForumpostFloorEntity> getForumpostFloorList(int forumpostID) {
        List<ForumpostFloorEntity> forumpostFloorEntityList = new ArrayList();
        ForumpostEntity forumpostEntity = baseDAO.findById(forumpostID, ForumpostEntity.class);
        ForumpostFloorEntity forumpostFloorEntity = forumpostEntity.getForumpostFloorByFirstFloorId();
        if (forumpostFloorEntity.getNextFloorByFloorID().getFloorId() == 1)
            return forumpostFloorEntityList;
        else forumpostFloorEntity = forumpostFloorEntity.getNextFloorByFloorID();
        while (true) {
            forumpostFloorEntityList.add(forumpostFloorEntity);
            if (forumpostFloorEntity.getNextFloorByFloorID().getFloorId() == 1)
                break;
            else forumpostFloorEntity = forumpostFloorEntity.getNextFloorByFloorID();
        }
        return forumpostFloorEntityList;
    }

    @Override
    public int comment(int userId, int forumpostId, String content) {
        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());
        ForumpostFloorEntity forumpostFloorEntity = new ForumpostFloorEntity();
        forumpostFloorEntity.setPublishTime(timestamp);
        forumpostFloorEntity.setFloorContent(content);
        forumpostFloorEntity.setUserinfoByAuthorId(baseDAO.findById(userId, UserinfoEntity.class));
        baseDAO.save(forumpostFloorEntity);

        ForumpostEntity forumpostEntity = baseDAO.findById(forumpostId, ForumpostEntity.class);
        ForumpostFloorEntity forumpostFloorEntity1 = forumpostEntity.getForumpostFloorByFirstFloorId();
        while (true) {
            if (forumpostFloorEntity1.getNextFloorByFloorID().getFloorId() == 1)
                break;
            else forumpostFloorEntity1 = forumpostFloorEntity1.getNextFloorByFloorID();
        }
        baseDAO.executeSQL("update forumpost_floor set nextFloorID=? where floorID=?", new Object[]{forumpostFloorEntity.getFloorId(), forumpostFloorEntity1.getFloorId()});
        return 1;
    }

    @Override
    public int praise(int userID, int id) {
        List<ForumpostFloorEntity> list = baseDAO.findBySQL("select * from praise_forumpost_floor where praiseFromUserId= ? and praiseForumpostFloorId = ?", new Object[]{userID, id});
        boolean flag = (list.size() == 1);
        if (flag) {
            baseDAO.executeSQL("delete from praise_forumpost_floor where praiseFromUserID=? and praiseForumpostFloorID=?", new Object[]{userID, id});
            baseDAO.executeSQL("update forumpost_floor set praiseNum=praiseNum-1 where floorID=?", new Object[]{id});
            return 0;
        } else {
            baseDAO.executeSQL("insert into praise_forumpost_floor values(?,?,?)", new Object[]{userID, id, 0});
            baseDAO.executeSQL("update forumpost_floor set praiseNum=praiseNum+1 where floorID=?", new Object[]{id});
            return 1;
        }
    }

    @Override
    public int collect(int userID, int id) {
        List<ForumpostFloorEntity> list = baseDAO.findBySQL("select * from forumpost_collect where collectorId= ? and forumpostId = ?", new Object[]{userID, id});
        boolean flag = (list.size() == 1);
        if (flag) {
            baseDAO.executeSQL("delete from forumpost_collect where collectorId=? and forumpostId=?", new Object[]{userID, id});
            baseDAO.executeSQL("update forumpost set collectNum=collectNum-1 where forumpostId=?", new Object[]{id});
            return 0;
        } else {
            baseDAO.executeSQL("insert into forumpost_collect values(?,?,?)", new Object[]{id, userID, 0});
            baseDAO.executeSQL("update forumpost set collectNum=collectNum+1 where forumpostId=?", new Object[]{id});
            return 1;
        }
    }

    @Override
    public List<ForumpostEntity> getBbsIndex() {
        List<ForumpostEntity> list = recommendDAO.getRecommendForumposts(9, 0, 0, 0, new ArrayList<Integer>()).get("hottestForumpost");
        return list;
    }


}
