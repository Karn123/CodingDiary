package service.searchSystem.impl;

import common.searchType.SearchType;
import controller.Forumpost;
import controller.Pagination;
import controller.Search;
import cst.Constants;
import framework.exceptions.ServiceException;
import model.BlogEntity;
import model.ForumpostEntity;
import model.ResourceEntity;
import org.apache.tools.ant.types.selectors.ReadableSelector;
import org.springframework.stereotype.Service;
import service.common.impl.CommServiceImpl;
import service.searchSystem.ISearchService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Karn on 2017/3/3.
 */
@Service("ISearchService")
public class SearchServiceImpl extends CommServiceImpl implements ISearchService {
    @Override
    public List search(SearchType searchType, Pagination pagination) throws ServiceException {
        searchType.replaceTags();
        if (searchType.getTableName().equals(Constants.TableName.BLOG))
            return searchBlog(searchType, pagination);
        else if (searchType.getTableName().equals(Constants.TableName.FORUMPOST))
            return searchForumpost(searchType, pagination);
        else return searchResource(searchType, pagination);
    }

    public List<BlogEntity> searchBlog(SearchType searchType,Pagination pagination) throws ServiceException {
        List<BlogEntity> list;
        List countTotal;
        String hql;
        String getTotalPageHQL;
        String orderRule = " order by blog.blogRecommendValueByBlogId.totalValue desc";
        if (searchType.isOrderByDateDes())
            orderRule = " order by blog.publishTime desc";
        String[] startAndEndDate = null;
        if(searchType.getSpecificDate()!=null)
            startAndEndDate = getStartAndEndDateStringFormat(searchType.getSpecificDate());
        //没有标签
        if (searchType.getTags()==null||searchType.getTags().size()<=0) {
            //不是按用户ID搜索
            if (searchType.getSearchByUserId() == -1) {
                if(startAndEndDate != null)
                {
                    getTotalPageHQL = "select count(blog.blogId) from BlogEntity as blog where blog.publishTime>= '" + startAndEndDate[0] +"' and blog.publishTime<='"+startAndEndDate[1]+"' ";
                    hql = "from BlogEntity as blog where blog.publishTime>= '" + startAndEndDate[0]+"' and blog.publishTime<='"+startAndEndDate[1]+"' "+ orderRule;
                }
                else {
                    getTotalPageHQL = "select count(blog.blogId) from BlogEntity as blog";
                    hql = "from BlogEntity as blog " + orderRule;
                }
                countTotal = baseDAO.findByHQL(getTotalPageHQL);
                list = baseDAO.findByPage(hql, pagination.getOffset(), pagination.getPageSize());
            }
            //按用户ID搜索
            else {
                if(startAndEndDate != null){
                    getTotalPageHQL = "select count(blog.blogId) from BlogEntity as blog where blog.userinfoByAuthorId.userId=? and blog.publishTime>= '" + startAndEndDate[0] +"' and blog.publishTime<='"+startAndEndDate[1]+"' ";
                    hql = "from BlogEntity as blog where blog.userinfoByAuthorId.userId=? and blog.publishTime>= '" + startAndEndDate[0] +"' and blog.publishTime<='"+startAndEndDate[1]+"' " + orderRule;
                }
                else {
                    getTotalPageHQL = "select count(blog.blogId) from BlogEntity as blog where blog.userinfoByAuthorId.userId=?";
                    hql = "from BlogEntity as blog where blog.userinfoByAuthorId.userId=?" + orderRule;
                }
                countTotal = baseDAO.findByHQL(getTotalPageHQL, new Object[]{searchType.getSearchByUserId()});
                list = baseDAO.findByPage(hql, new Object[]{searchType.getSearchByUserId()}, pagination.getOffset(), pagination.getPageSize());
            }
        }
        //有标签
        else {
            if (searchType.getSearchByUserId() == -1) {
                if(startAndEndDate!=null){
                    getTotalPageHQL = "select count(distinct blog.blogId) from BlogEntity as blog join blog.blogTagsByBlogId as blogTags join blogTags.tagByTagIdNum as tag where tag.tagName in(:tags) and blog.publishTime>= '" + startAndEndDate[0] +"' and blog.publishTime<='"+startAndEndDate[1]+"' ";
                    hql = "select distinct blog from BlogEntity as blog join blog.blogTagsByBlogId as blogTags join blogTags.tagByTagIdNum as tag where tag.tagName in(:tags) and blog.publishTime>= '" + startAndEndDate[0] +"' and blog.publishTime<='"+startAndEndDate[1]+"' " + orderRule;
                }else {
                    getTotalPageHQL = "select count(distinct blog.blogId) from BlogEntity as blog join blog.blogTagsByBlogId as blogTags join blogTags.tagByTagIdNum as tag where tag.tagName in(:tags)";
                    hql = "select distinct blog from BlogEntity as blog join blog.blogTagsByBlogId as blogTags join blogTags.tagByTagIdNum as tag where tag.tagName in(:tags) " + orderRule;
                }
                countTotal = baseDAO.findByHQL(getTotalPageHQL, new Object[]{searchType.getTags()});
                list = baseDAO.findByPage(hql, new Object[]{searchType.getTags()}, pagination.getOffset(), pagination.getPageSize());
            } else {
                if(startAndEndDate != null){
                    getTotalPageHQL = "select count(distinct blog.blogId) from BlogEntity as blog join blog.blogTagsByBlogId as blogTags join blogTags.tagByTagIdNum as tag where blog.userinfoByAuthorId.userId=? and tag.tagName in(:tags) and blog.publishTime>= '" + startAndEndDate[0] +"' and blog.publishTime<='"+startAndEndDate[1]+"' " ;
                    hql = "select distinct blog from BlogEntity as blog join blog.blogTagsByBlogId as blogTags join blogTags.tagByTagIdNum as tag where blog.userinfoByAuthorId.userId=? and tag.tagName in(:tags) and blog.publishTime>= '" + startAndEndDate[0] +"' and blog.publishTime<='"+startAndEndDate[1]+"' " + orderRule;
                }
                else {
                    getTotalPageHQL = "select count(distinct blog.blogId) from BlogEntity as blog join blog.blogTagsByBlogId as blogTags join blogTags.tagByTagIdNum as tag where blog.userinfoByAuthorId.userId=? and tag.tagName in(:tags)";
                    hql = "select distinct blog from BlogEntity as blog join blog.blogTagsByBlogId as blogTags join blogTags.tagByTagIdNum as tag where blog.userinfoByAuthorId.userId=? and tag.tagName in(:tags)" + orderRule;
                }
                countTotal = baseDAO.findByHQL(getTotalPageHQL, new Object[]{searchType.getSearchByUserId(), searchType.getTags()});
                list = baseDAO.findByPage(hql, new Object[]{searchType.getSearchByUserId(), searchType.getTags()}, pagination.getOffset(), pagination.getPageSize());
            }
        }
        if (countTotal.size() > 0)
            pagination.setTotalPageNumber(Integer.parseInt(countTotal.get(0).toString()));
        else
            pagination.setTotalPageNumber(0);
        searchType.replaceBackTags();
        return list;
    }

    public List<ResourceEntity> searchResource(SearchType searchType,Pagination pagination)  throws ServiceException {
        List<ResourceEntity> list;
        List countTotal;
        String hql;
        String getTotalPageHQL;
        String orderRule = " order by resource.resourceRecommendValueByResourceId.totalValue desc";
        if (searchType.isOrderByDateDes())
            orderRule = " order by resource.uploadTime desc";
        String[] startAndEndDate = null;
        if(searchType.getSpecificDate()!=null)
            startAndEndDate = getStartAndEndDateStringFormat(searchType.getSpecificDate());
        //没有标签
        if (searchType.getTags()==null||searchType.getTags().size()<=0) {
            //不是按用户ID搜索
            if (searchType.getSearchByUserId() == -1) {
                if(startAndEndDate!=null){
                    getTotalPageHQL = "select count(resource.resourceId) from ResourceEntity as resource where resource.uploadTime>='"+startAndEndDate[0]+"' and resource.uploadTime<='"+startAndEndDate[1]+"'";
                    hql = "from ResourceEntity as resource where resource.uploadTime>='"+startAndEndDate[0]+"' and resource.uploadTime<='"+startAndEndDate[1]+"'" + orderRule;
                }
                else {
                    getTotalPageHQL = "select count(resource.resourceId) from ResourceEntity as resource";
                    hql = "from ResourceEntity as resource" + orderRule;
                }
                countTotal = baseDAO.findByHQL(getTotalPageHQL);
                list = baseDAO.findByPage(hql, pagination.getOffset(), pagination.getPageSize());
            }
            //按用户ID搜索
            else {
                if(startAndEndDate != null){
                    getTotalPageHQL = "select count(resource.resourceId) from ResourceEntity as resource where resource.userinfoByUploaderId.userId=? and resource.uploadTime>='"+startAndEndDate[0]+"' and resource.uploadTime<='"+startAndEndDate[1]+"'";
                    hql = "from ResourceEntity as resource where resource.userinfoByUploaderId.userId=? and resource.uploadTime>='"+startAndEndDate[0]+"' and resource.uploadTime<='"+startAndEndDate[1]+"'" + orderRule;
                }
                else{
                    getTotalPageHQL = "select count(resource.resourceId) from ResourceEntity as resource where resource.userinfoByUploaderId.userId=?";
                    hql = "from ResourceEntity as resource where resource.userinfoByUploaderId.userId=?" + orderRule;
                }
                countTotal = baseDAO.findByHQL(getTotalPageHQL, new Object[]{searchType.getSearchByUserId()});
                list = baseDAO.findByPage(hql, new Object[]{searchType.getSearchByUserId()}, pagination.getOffset(), pagination.getPageSize());
            }
        }
        //有标签
        else {
            if (searchType.getSearchByUserId() == -1) {
                if(startAndEndDate!=null){
                    getTotalPageHQL = "select count(distinct resource.resourceId) from ResourceEntity as resource join resource.resourceTagsByResourceId as resourceTags join resourceTags.tagByTagIdNum as tag where tag.tagName in(:tags) and resource.uploadTime>='"+startAndEndDate[0]+"' and resource.uploadTime<='"+startAndEndDate[1]+"'";
                    hql = "select distinct resource from ResourceEntity as resource join resource.resourceTagsByResourceId as resourceTags join resourceTags.tagByTagIdNum as tag where tag.tagName in(:tags) and resource.uploadTime>='"+startAndEndDate[0]+"' and resource.uploadTime<='"+startAndEndDate[1]+"'" + orderRule;
                }
                else{
                    getTotalPageHQL = "select count(distinct resource.resourceId) from ResourceEntity as resource join resource.resourceTagsByResourceId as resourceTags join resourceTags.tagByTagIdNum as tag where tag.tagName in(:tags)";
                    hql = "select distinct resource from ResourceEntity as resource join resource.resourceTagsByResourceId as resourceTags join resourceTags.tagByTagIdNum as tag where tag.tagName in(:tags)" + orderRule;
                }
                countTotal = baseDAO.findByHQL(getTotalPageHQL, new Object[]{searchType.getTags()});
                list = baseDAO.findByPage(hql, new Object[]{searchType.getTags()}, pagination.getOffset(), pagination.getPageSize());
            } else {
                if(startAndEndDate != null){
                    getTotalPageHQL = "select count(distinct resource.resourceId) from ResourceEntity as resource join resource.resourceTagsByResourceId as resourceTags join resourceTags.tagByTagIdNum as tag where resource.userinfoByUploaderId.userId=? and tag.tagName in(:tags) and resource.uploadTime>='"+startAndEndDate[0]+"' and resource.uploadTime<='"+startAndEndDate[1]+"'";
                    hql = "select distinct resource from ResourceEntity as resource join resource.resourceTagsByResourceId as resourceTags join resourceTags.tagByTagIdNum as tag where resource.userinfoByUploaderId.userId=? and tag.tagName in(:tags) and resource.uploadTime>='"+startAndEndDate[0]+"' and resource.uploadTime<='"+startAndEndDate[1]+"'" + orderRule;
                }
                else{
                    getTotalPageHQL = "select count(distinct resource.resourceId) from ResourceEntity as resource join resource.resourceTagsByResourceId as resourceTags join resourceTags.tagByTagIdNum as tag where resource.userinfoByUploaderId.userId=? and tag.tagName in(:tags)";
                    hql = "select distinct resource from ResourceEntity as resource join resource.resourceTagsByResourceId as resourceTags join resourceTags.tagByTagIdNum as tag where resource.userinfoByUploaderId.userId=? and tag.tagName in(:tags)" + orderRule;
                }
                countTotal = baseDAO.findByHQL(getTotalPageHQL, new Object[]{searchType.getSearchByUserId(), searchType.getTags()});
                list = baseDAO.findByPage(hql, new Object[]{searchType.getSearchByUserId(), searchType.getTags()}, pagination.getOffset(), pagination.getPageSize());
            }
        }

        if (countTotal.size() > 0)
            pagination.setTotalPageNumber(Integer.parseInt(countTotal.get(0).toString()));
        else
            pagination.setTotalPageNumber(0);
        searchType.replaceBackTags();
        return list;
    }

    public List<ForumpostEntity> searchForumpost(SearchType searchType,Pagination pagination) throws ServiceException {
        List<ForumpostEntity> list;
        List countTotal;
        String hql;
        String getTotalPageHQL;
        String orderRule = " order by forumpost.forumpostRecommendValueByForumpostId.totalValue desc";
        if (searchType.isOrderByDateDes())
            orderRule = " order by forumpost.publishTime desc";
        String[] startAndEndDate = null;
        if(searchType.getSpecificDate()!=null)
            startAndEndDate = getStartAndEndDateStringFormat(searchType.getSpecificDate());
        //没有标签
        if (searchType.getTags()==null||searchType.getTags().size()<=0) {
            //不是按用户ID搜索
            if (searchType.getSearchByUserId() == -1) {
                if(startAndEndDate!=null){
                    getTotalPageHQL = "select count(forumpost.forumpostId) from ForumpostEntity as forumpost where forumpost.publishTime>='"+startAndEndDate[0]+"' and forumpost.publishTime<='"+startAndEndDate[1]+"'";
                    hql = "from ForumpostEntity as forumpost where forumpost.publishTime>='"+startAndEndDate[0]+"' and forumpost.publishTime<='"+startAndEndDate[1]+"'" + orderRule;
                }
                else {
                    getTotalPageHQL = "select count(forumpost.forumpostId) from ForumpostEntity as forumpost";
                    hql = "from ForumpostEntity as forumpost" + orderRule;
                }
                countTotal = baseDAO.findByHQL(getTotalPageHQL);
                list = baseDAO.findByPage(hql, pagination.getOffset(), pagination.getPageSize());
            }
            //按用户ID搜索
            else {
                if(startAndEndDate!=null){
                    getTotalPageHQL = "select count(forumpost.forumpostId) from ForumpostEntity as forumpost where forumpost.userinfoByAuthorId.userId=? and forumpost.publishTime>='"+startAndEndDate[0]+"' and forumpost.publishTime<='"+startAndEndDate[1]+"'";
                    hql = "from ForumpostEntity as forumpost where forumpost.userinfoByAuthorId.userId=? and forumpost.publishTime>='"+startAndEndDate[0]+"' and forumpost.publishTime<='"+startAndEndDate[1]+"'" + orderRule;
                }
                else {
                    getTotalPageHQL = "select count(forumpost.forumpostId) from ForumpostEntity as forumpost where forumpost.userinfoByAuthorId.userId=?";
                    hql = "from ForumpostEntity as forumpost where forumpost.userinfoByAuthorId.userId=?" + orderRule;
                }
                countTotal = baseDAO.findByHQL(getTotalPageHQL, new Object[]{searchType.getSearchByUserId()});
                list = baseDAO.findByPage(hql, new Object[]{searchType.getSearchByUserId()}, pagination.getOffset(), pagination.getPageSize());
            }
        }
        //有标签
        else {
            if (searchType.getSearchByUserId() == -1) {
                if(startAndEndDate!=null){
                    getTotalPageHQL = "select count(distinct forumpost.forumpostId) from ForumpostEntity as forumpost join forumpost.forumpostTagsByForumpostId as forumpostTags join forumpostTags.tagByTagId as tag where tag.tagName in(:tags) and forumpost.publishTime>='"+startAndEndDate[0]+"' and forumpost.publishTime<='"+startAndEndDate[1]+"'";
                    hql = "select distinct forumpost from ForumpostEntity as forumpost join forumpost.forumpostTagsByForumpostId as forumpostTags join forumpostTags.tagByTagId as tag where tag.tagName in(:tags) and forumpost.publishTime>='"+startAndEndDate[0]+"' and forumpost.publishTime<='"+startAndEndDate[1]+"'" + orderRule;
                }
                else{
                    getTotalPageHQL = "select count(distinct forumpost.forumpostId) from ForumpostEntity as forumpost join forumpost.forumpostTagsByForumpostId as forumpostTags join forumpostTags.tagByTagId as tag where tag.tagName in(:tags)";
                    hql = "select distinct forumpost from ForumpostEntity as forumpost join forumpost.forumpostTagsByForumpostId as forumpostTags join forumpostTags.tagByTagId as tag where tag.tagName in(:tags)" + orderRule;
                }
                countTotal = baseDAO.findByHQL(getTotalPageHQL, new Object[]{searchType.getTags()});
                list = baseDAO.findByPage(hql, new Object[]{searchType.getTags()}, pagination.getOffset(), pagination.getPageSize());
            } else {
                if(startAndEndDate!=null){
                    getTotalPageHQL = "select count(distinct forumpost.forumpostId) from ForumpostEntity as forumpost join forumpost.forumpostTagsByForumpostId as forumpostTags join forumpostTags.tagByTagId as tag where forumpost.userinfoByAuthorId.userId=? and tag.tagName in(:tags) and forumpost.publishTime>='"+startAndEndDate[0]+"' and forumpost.publishTime<='"+startAndEndDate[1]+"'";
                    hql = "select distinct forumpost from ForumpostEntity as forumpost join forumpost.forumpostTagsByForumpostId as forumpostTags join forumpostTags.tagByTagId as tag where forumpost.userinfoByAuthorId.userId=? and tag.tagName in(:tags) and forumpost.publishTime>='"+startAndEndDate[0]+"' and forumpost.publishTime<='"+startAndEndDate[1]+"'" + orderRule;
                }
                else{
                    getTotalPageHQL = "select count(distinct forumpost.forumpostId) from ForumpostEntity as forumpost join forumpost.forumpostTagsByForumpostId as forumpostTags join forumpostTags.tagByTagId as tag where forumpost.userinfoByAuthorId.userId=? and tag.tagName in(:tags)";
                    hql = "select distinct forumpost from ForumpostEntity as forumpost join forumpost.forumpostTagsByForumpostId as forumpostTags join forumpostTags.tagByTagId as tag where forumpost.userinfoByAuthorId.userId=? and tag.tagName in(:tags)" + orderRule;
                }
                countTotal = baseDAO.findByHQL(getTotalPageHQL, new Object[]{searchType.getSearchByUserId(), searchType.getTags()});
                list = baseDAO.findByPage(hql, new Object[]{searchType.getSearchByUserId(), searchType.getTags()}, pagination.getOffset(), pagination.getPageSize());
            }
        }
        if (countTotal.size() > 0)
            pagination.setTotalPageNumber(Integer.parseInt(countTotal.get(0).toString()));
        else
            pagination.setTotalPageNumber(0);
        searchType.replaceBackTags();
        return list;
    }

    public String[] getStartAndEndDateStringFormat(Date startDate){
        //创建基于当前时间的日历对象
        Calendar cl = Calendar.getInstance();
        cl.setTime(startDate);
        //该月内数据
        cl.add(Calendar.MONTH, 1);
        Date endDate = cl.getTime();
        SimpleDateFormat dd = new SimpleDateFormat("yyyy-MM-dd");
        //格式化开始日期和结束日期
        String start = dd.format(startDate);
        String end = dd.format(endDate);
        String[] a = new String[2];
        a[0] = start;
        a[1] = end;
        return a;
    }
}