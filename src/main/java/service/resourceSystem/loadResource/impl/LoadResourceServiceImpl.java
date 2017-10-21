package service.resourceSystem.loadResource.impl;

import common.util.DateUtil;
import common.util.SearchUtil;
import cst.Constants;
import daos.recommend.RecommendDAO;
import framework.exceptions.ServiceException;
import model.ResourceEntity;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.stereotype.Service;
import service.common.impl.CommServiceImpl;
import service.resourceSystem.loadResource.ILoadResourceService;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Karn on 2017/2/2.
 */
@Service(value = "LoadResourceService")
public class LoadResourceServiceImpl extends CommServiceImpl implements ILoadResourceService {

    @Resource
    protected RecommendDAO recommendDAO;

    @Override
    public List<Map<String,String>> loadResources(int user_id, int page_total, String searchContent, int offset, int page_size,String searchDate) throws ServiceException {
        //Timestamp timestamp = null;
        Date startDate = null;
        if(!searchDate.equals(""))
        {
            startDate = DateUtil.getDate(searchDate);
        }

        List<Map<String, String>> mapList = new ArrayList<Map<String, String>>();
        Map pageCountMap = new HashMap();
        pageCountMap.put("searchDate","");

        List<String> searchTagsWithoutBlankSpace = SearchUtil.removeBlankSpace(searchContent.split(" "));
        StringBuilder tags = new StringBuilder("(");
        for(String tag:searchTagsWithoutBlankSpace){
            tags.append("'").append(tag).append("',");
        }
        tags.deleteCharAt(tags.length()-1).append(")");
        String sql;
        List list = new ArrayList();
        if (!searchContent.equals("")) {
            sql="select distinct resourceID from ((select resourceID from resource where uploaderID = ?) as Temp) join resource_tag join tag where Temp.resourceID = resource_tag.resourceIDNum " +
                    "and resource_tag.tagIDNum = tag.tagID and tag.tagName in " + tags.toString();
            list = baseDAO.findBySQL(sql, new Object[]{user_id});
        } else {
            if(startDate!= null){
                sql = "select resourceID from resource where uploaderID = ? and date_format(uploadTime,'%Y-%m')=?";
                list = baseDAO.findBySQL(sql,new Object[]{user_id,searchDate.substring(0,7)});
                pageCountMap.put("searchDate",searchDate);
            }
            else{
                if(page_total == -1)
                    list = baseDAO.findBySQL("select resourceID from resource where uploaderID = ?", new Object[]{user_id});
            }
        }
        int count = list.size();
        pageCountMap.put("resourceCount", String.valueOf(count));

        mapList.add(pageCountMap);
        List<ResourceEntity> resourceEntityList;
        if(!searchContent.equals("")) {
            resourceEntityList  = baseDAO.findByPage("select distinct resource from ResourceEntity as resource join resource.resourceTagsByResourceId as resourceTags join resourceTags.tagByTagIdNum as tag where resource.userinfoByUploaderId.userId =? and tag.tagName in(:tags)", new Object[]{user_id,searchTagsWithoutBlankSpace}, offset, page_size);
        }
        else {
            if (startDate != null) {
                resourceEntityList = returnMonthlyResources(startDate, "month", user_id, page_size, offset);
            } else {
                resourceEntityList = baseDAO.findByPage("from ResourceEntity as resource where resource.userinfoByUploaderId.userId =?", new Object[]{user_id}, offset, page_size);
            }
        }
        for (ResourceEntity resourceEntity : resourceEntityList) {
            Map<String, String> map;
            int resourceID = resourceEntity.getResourceId();
            map = getResourceInfoByID(resourceID, false);
            mapList.add(map);
        }
        return mapList;
    }

    @Override
    public List<ResourceEntity> loadHottestResources() throws ServiceException {
        List<ResourceEntity> resourceEntityList = recommendDAO.getRecommendResources(8,0,0,0,new ArrayList<Integer>()).get("hottestResource");
        return resourceEntityList;
    }

    @Override
    public Map<String,String> getResourceInfoByID(int resourceID, boolean flag) throws ServiceException{
        if(flag)
            baseDAO.executeSQL("update resource set browseNum = browseNum + 1 where resourceID=?",new Object[]{resourceID});
        ResourceEntity resourceEntity = baseDAO.findById(resourceID, ResourceEntity.class);
        Map<String, String> map = new HashedMap();
        List resourceTagList = baseDAO.findBySQL(
                        "select tag.tagName from tag join resource_tag where resource_tag.resourceIDNum=? and resource_tag.tagIDNum = tag.tagID",
                        new Object[] { resourceID });
        StringBuilder sBuilder = new StringBuilder("");
        // tag 按空格隔开
        for (int i = 0; i < resourceTagList.size(); i++) {
            sBuilder.append(resourceTagList.get(i)).append(" ");
        }
        List<Integer> praiseResourceUsers = baseDAO.findBySQL(
                        "select praiseFromUserID from praise_resource where praiseResourceID = ? and isPraised = ?",
                        new Object[] { resourceID, Constants.TFAttribute.T});
        StringBuilder usersString = new StringBuilder("");
        for (Integer string : praiseResourceUsers) {
            usersString.append(String.valueOf(string)).append(" ");
        }
        if (praiseResourceUsers.size() > 0) {
            usersString.deleteCharAt(usersString.length() - 1);
        }
        List<Integer> collectResourceUsers = baseDAO.findBySQL(
                        "select collectorID from resource_collect where collectResourceID = ? and isCollected = ?",
                        new Object[] { resourceID,Constants.TFAttribute.T});
        StringBuilder collectorsString = new StringBuilder("");
        for (Integer string : collectResourceUsers) {
            collectorsString.append(String.valueOf(string)).append(" ");
        }
        if (collectResourceUsers.size() > 0) {
            collectorsString.deleteCharAt(collectorsString.length() - 1);
        }
        map.put("praiseUsers", usersString.toString());
        map.put("collectUsers", collectorsString.toString());
        //System.out.println("Praise Users: " + usersString.toString());
        map.put("resourceID", String.valueOf(resourceID));
        map.put("resourceName", resourceEntity.getResourceName());
        map.put("resourceUploaderID", String.valueOf(resourceEntity.getUserinfoByUploaderId().getUserId()));
        map.put("resourceUploaderName", resourceEntity.getUserinfoByUploaderId().getNickName());
        map.put("uploaderImageUrl",resourceEntity.getUserinfoByUploaderId().getHeadPortrait());
        map.put("resourceTags", sBuilder.toString());
        map.put("resourceDescription", resourceEntity.getResourceDescription());
        map.put("resourcePath", resourceEntity.getResourcePath());
        map.put("praiseNum", String.valueOf(resourceEntity.getPraiseNum()));
        map.put("commentNum", String.valueOf(resourceEntity.getCommentNum()));
        map.put("downloadNum", String.valueOf(resourceEntity.getDownloadCount()));
        map.put("forwardNum", String.valueOf(resourceEntity.getForwardNum()));
        map.put("collectNum", String.valueOf(resourceEntity.getCollectNum()));
        map.put("browseNum", String.valueOf(resourceEntity.getBrowseNum()));
        map.put("resourceScore",String.valueOf(resourceEntity.getResourceScore()));
        map.put("resourceUploadTime", resourceEntity.getUploadTime().toString());
        map.put("resourceSize",resourceEntity.getResourceSize());
        return map;
    }

    //查询一周内或一个月内的数据
    public List<ResourceEntity> returnMonthlyResources(Date startDate,String flag,int user_id,int page_size,int offset){
        //创建基于当前时间的日历对象
        Calendar cl = Calendar.getInstance();
        cl.setTime(startDate);
        //该月内数据
        if(flag.equals("month")){
            cl.add(Calendar.MONTH, 1);
        }
        //该周内的数据
        if(flag.equals("week")){
            cl.add(Calendar.DATE, 7);
        }
        Date endDate = cl.getTime();
        SimpleDateFormat dd = new SimpleDateFormat("yyyy-MM-dd");
        //格式化开始日期和结束日期
        String start = dd.format(startDate);
        String end = dd.format(endDate);

        String hql = "from ResourceEntity as resource where resource.userinfoByUploaderId.userId =? and resource.uploadTime >= '" + start +"' and resource.uploadTime <= '"+end+"'";
        List<ResourceEntity> resourceEntityList = baseDAO.findByPage(hql, new Object[]{user_id}, offset, page_size);
        return resourceEntityList;
    }
}