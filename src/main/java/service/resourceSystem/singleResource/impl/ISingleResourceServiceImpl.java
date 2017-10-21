package service.resourceSystem.singleResource.impl;

import cst.Constants;
import daos.UsercommentDAO;
import framework.exceptions.ServiceException;
import model.*;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.stereotype.Service;
import service.common.impl.CommServiceImpl;
import service.resourceSystem.singleResource.ISingleResourceService;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.*;

/**
 * Created by Karn on 2017/2/8.
 */
@Service(value = "SingleResourceService")
public class ISingleResourceServiceImpl extends CommServiceImpl implements ISingleResourceService {
    @Resource
    UsercommentDAO usercommentDAO;

    @Override
    public void downloadResoure(int resourceID) throws ServiceException {
        baseDAO.executeSQL("update resource set downloadCount=downloadCount+1 where resourceID=?",new Object[]{resourceID});
    }

    @Override
    public List<Map<String, String>> getResourceCommentInfo(int resource_id,List<UsercommentEntity> list) throws ServiceException {
        List<Map<String, String>> commentInfoList = new ArrayList<Map<String, String>>();
        int commentNum = baseDAO.findByProperty("commentResourceId",resource_id, ResourceCommentEntity.class).size();
        for (UsercommentEntity commentInfo : list) {
            Map<String, String> map = new HashedMap();
            map.put("commenterID",
                    String.valueOf(commentInfo.getUserinfoByCommenterId().getUserId()));
            map.put("commenterName", commentInfo.getUserinfoByCommenterId().getNickName());
            map.put("commentContent", commentInfo.getCommentContent());
            map.put("praiseNum", String.valueOf(commentInfo.getPraiseNum()));
            map.put("commentTime", commentInfo.getCommentTime().toString());
            map.put("commenterImageUrl",commentInfo.getUserinfoByCommenterId().getHeadPortrait());
            commentInfoList.add(map);
        }
        if(commentInfoList.size()>0)
            commentInfoList.get(0).put("commentNum",String.valueOf(commentNum));
        return commentInfoList;
    }

    @Override
    public Map<String, String> commentResource(int user_id, int resource_id, String commentContent) throws ServiceException {
        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());
        UsercommentEntity usercommentEntity = new UsercommentEntity(user_id, commentContent, timestamp);
        usercommentDAO.save(usercommentEntity, resource_id, ResourceEntity.class).getCommentId();
        UserinfoEntity userinfoEntity = usercommentEntity.getUserinfoByCommenterId();
        baseDAO.executeSQL(
                "update resource set commentNum = commentNum+1 where resourceID = ?",
                new Object[] { resource_id });
        Map<String,String> map = new HashMap<String, String>();
        List commentNum = baseDAO.findBySQL("select commentNum from resource where resourceID=?", new Object[]{resource_id});
        if(commentNum.size()>0)
        {
            map.put("commentNum",commentNum.get(0).toString());
        }else{
            map.put("commentNum","-1");
        }
        map.put("commenterID",String.valueOf(userinfoEntity.getUserId()));
        map.put("commenterName",userinfoEntity.getNickName());
        map.put("commentTime",timestamp.toString());
        map.put("commenterImage",userinfoEntity.getHeadPortrait());
        map.put("commentContent",commentContent);
        return map;
    }

    @Override
    public Map<String,String> praiseResource(int resourceID, int praise_user_id) throws ServiceException {
        Map<String, String> map = new HashedMap();
        PraiseResourceEntityPK praiseResourceEntityPK = new PraiseResourceEntityPK(praise_user_id, resourceID);
        PraiseResourceEntity praiseResourceEntity = baseDAO.findById(praiseResourceEntityPK, PraiseResourceEntity.class);
        if (praiseResourceEntity == null) {
            baseDAO.executeSQL("insert into praise_resource values(?,?,?)",new Object[]{praise_user_id,resourceID,Constants.TFAttribute.T});
            baseDAO.executeSQL("update resource set praiseNum = praiseNum + 1 where resourceID=?",new Object[]{resourceID});
            map.put("is_have_praised", "false");
        } else {
            if (praiseResourceEntity.getTfByIsPraised().getTfid() == Constants.TFAttribute.T) {
                baseDAO.executeSQL("update praise_resource set isPraised = ? " +
                        "where praiseFromUserID=? and praiseResourceID=?",
                        new Object[]{Constants.TFAttribute.F,praise_user_id,resourceID});
                baseDAO.executeSQL("update resource set praiseNum = praiseNum - 1 where resourceID=?",new Object[]{resourceID});
                map.put("is_have_praised", "true");
            } else {
                baseDAO.executeSQL("update praise_resource set isPraised = ? " +
                                "where praiseFromUserID=? and praiseResourceID=?",
                        new Object[]{Constants.TFAttribute.T,praise_user_id,resourceID});
                baseDAO.executeSQL("update resource set praiseNum = praiseNum + 1 where resourceID=?",new Object[]{resourceID});
                map.put("is_have_praised", "false");
            }
        }
        ResourceEntity resourceEntity = baseDAO.findById(resourceID, ResourceEntity.class);
        String praiseNum = String.valueOf(resourceEntity.getPraiseNum());
        map.put("praise_num", praiseNum);
        return map;
    }

    @Override
    public Map<String, String> collectResource(int resourceID, int collector) throws ServiceException {
        Map<String, String> map = new HashedMap();
        ResourceCollectEntityPK resourceCollectEntityPK = new ResourceCollectEntityPK(collector, resourceID);
        ResourceCollectEntity resourceCollectEntity = baseDAO.findById(resourceCollectEntityPK, ResourceCollectEntity.class);
        if (resourceCollectEntity == null) {
            baseDAO.executeSQL("insert into resource_collect values(?,?,?)",new Object[]{resourceID,collector,Constants.TFAttribute.T});
            baseDAO.executeSQL("update resource set collectNum = collectNum + 1 where resourceID=?",new Object[]{resourceID});
            map.put("is_have_collected", "false");
        } else {
            if (resourceCollectEntity.getTfByIsCollected().getTfid() == Constants.TFAttribute.T) {
                baseDAO.executeSQL("update resource_collect set isCollected = ? " +
                                "where collectorID = ? and collectResourceID = ?",
                        new Object[]{Constants.TFAttribute.F,collector,resourceID});
                baseDAO.executeSQL("update resource set collectNum = collectNum - 1 where resourceID = ?",
                        new Object[]{resourceID});
                map.put("is_have_collected", "true");
            } else {
                baseDAO.executeSQL("update resource_collect set isCollected = ? " +
                                "where collectorID = ? and collectResourceID = ?",
                        new Object[]{Constants.TFAttribute.T,collector,resourceID});
                baseDAO.executeSQL("update resource set collectNum = collectNum + 1 where resourceID=?",new Object[]{resourceID});
                map.put("is_have_collected", "false");
            }
        }
        ResourceEntity resourceEntity = baseDAO.findById(resourceID, ResourceEntity.class);
        String collectNum = String.valueOf(resourceEntity.getCollectNum());
        map.put("collect_num", collectNum);
        return map;
    }

    @Override
    public Map<String, String> forwardResource(int resourceID, int forwarderID) throws ServiceException {
        Map map = new HashMap();
        return map;
    }

    @Override
    public Map<String, String> evaluateResource(int user_id, int resourceID, float score) throws ServiceException {
        Map map = new HashMap();
        ResourceScoreEntityPK resourceScoreEntityPK = new ResourceScoreEntityPK(user_id,resourceID);
        ResourceScoreEntity resourceScoreEntity = baseDAO.findById(resourceScoreEntityPK,ResourceScoreEntity.class);
        if(resourceScoreEntity == null){
            baseDAO.executeSQL("insert into resource_score values(?,?,?)",new Object[]{user_id,resourceID,score});
        }
        else{
            resourceScoreEntity.setScore(score);
            baseDAO.update(resourceScoreEntity);
            //baseDAO.executeSQL("update resource_score set score=? where user_id=? and resource_id=? ",new Object[]{score,user_id,resourceID});
        }
        List<ResourceScoreEntity> resourceScoreEntities = baseDAO.findByProperty("resourceId",resourceID,ResourceScoreEntity.class);
        int size = resourceScoreEntities.size();
        Double resource_score = 0D;
        for(ResourceScoreEntity resourceScoreEntity1 : resourceScoreEntities){
            resource_score += round(resourceScoreEntity1.getScore(),1);
        }
        if(size != 0){
            resource_score /= size;
        }
        resource_score = round(resource_score,1);
        baseDAO.executeSQL("update resource set resourceScore=? where resourceID=?",new Object[]{resource_score,resourceID});
        map.put("resource_score",String.valueOf(resource_score));
        return map;
    }

    public double round(double v,int scale){
        if(scale<0){
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        }
        BigDecimal b = new BigDecimal(Double.toString(v));
        BigDecimal one =  new BigDecimal("1");
        return b.divide(one,scale,BigDecimal.ROUND_HALF_UP).doubleValue();
    }
}