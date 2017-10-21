package service.blogSystem.impl;

import controller.Pagination;
import cst.Authority;
import daos.BlogDAO;
import daos.TagDAO;
import daos.UserinfoDAO;
import framework.exceptions.AuthorityException;
import model.BlogContentEntity;
import model.BlogEntity;
import model.UserinfoEntity;
import org.springframework.stereotype.Service;
import service.blogSystem.BlogService;
import service.common.impl.CommServiceImpl;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.*;

/**
 * Created by 11022 on 2017/1/31.
 */
@Service(value = "blogService")
public class BlogServiceImpl extends CommServiceImpl implements BlogService {
    @Resource
    protected BlogDAO blogDAO;
    @Resource
    protected TagDAO tagDAO;
    @Resource
    protected UserinfoDAO userinfoDAO;

    @Override
    public int postBlog(int userId, String blogTitle, String blogContent, Set<Integer> tagsList) {
        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());
        BlogEntity blogEntity = new BlogEntity(blogTitle, timestamp);
        BlogContentEntity blogContentEntity = new BlogContentEntity(blogContent);
        return blogDAO.save(userId, blogEntity, blogContentEntity, tagsList).getBlogId();
    }

    @Override
    public int editBlog(int blogID, String blogTitle, String blogContent, Set<Integer> tagsList) {
        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());
        BlogEntity blogEntity = new BlogEntity(blogTitle, timestamp);
        BlogContentEntity blogContentEntity = new BlogContentEntity(blogContent);
        return blogDAO.update(blogID, blogEntity, blogContentEntity, tagsList).getBlogId();
    }

    @Override
    public int deleteBlog(int blogID) {
        if(baseDAO.findBySQL("select * from blog where blogID=? and isLegal=1",new Object[]{blogID}).size()!=0){
            baseDAO.executeSQL("update blog set isLegal = 3 where blogID=?",new Object[]{blogID});
            return 1;
        }
        if(baseDAO.findBySQL("select * from blog where blogID=? and isLegal=3",new Object[]{blogID}).size()!=0){
            baseDAO.executeSQL("update blog set isLegal = 1 where blogID=?",new Object[]{blogID});
            return -1;
        }
        return 0;
    }

    @Override
    public BlogEntity getBlogByID(int blogID, Authority authority) throws AuthorityException {
        BlogEntity blogEntity = blogDAO.findById(blogID);
        switch (authority) {
            case ADMIN:
                return blogEntity;
            default:
                if (blogEntity.getTfByIsLegal().getTfOption().equals("F")) {
                    throw new AuthorityException("该篇博客已被封禁");
                }
                return blogEntity;
        }
    }

    @Override
    public List<BlogEntity> getBlogList(String type, String typeValue, int userID, String order, Pagination pagination, Authority authority) {
        List<BlogEntity> original = blogDAO.getListByPage(type, typeValue, userID, pagination);
        return getAbbreviatedList(original, authority);
    }

    @Override
    public List<BlogEntity> getPersonalBlog(int userID, Pagination pagination, Authority authority) {
        List<BlogEntity> original = blogDAO.findByProperty("userinfoByAuthorId.userId", userID);
        List<BlogEntity> result = getAbbreviatedList(original, authority);
        pagination.setTotalPageNumber(result.size());
        return result;
    }

    private List<BlogEntity> getAbbreviatedList(Collection<BlogEntity> collection, Authority authority) {
        List<BlogEntity> result = new ArrayList<BlogEntity>();
        switch (authority) {
            case ADMIN:
                for (BlogEntity blogEntity : collection) {
                    String tfOption = blogEntity.getTfByIsLegal().getTfOption();
                    if (!tfOption.equals("T")) {
                        //blogEntity.setBlogTitle(blogEntity.getBlogTitle() + "（" + tfOption + "）");
                    }
                    result.add(blogEntity);
                }
                return result;
            default:
                for (BlogEntity blogEntity : collection) {
                    if (blogEntity.getTfByIsLegal().getTfOption().equals("T")) {
                        result.add(blogEntity);
                    }
                }
                return result;
        }
    }

    @Override
    public UserinfoEntity getUserinfoByID(int userID) {
        return userinfoDAO.findById(userID);
    }

    @Override
    public Map<String, Boolean> getBlogStatue(int blogID, int userID) {
        Map<String, Boolean> map = new HashMap<String, Boolean>();
        if (userID == 0) {
            map.put("isCollected", false);
            map.put("isPraised", false);
        } else {
            map.put("isCollected", baseDAO.findBySQL("select * from blog_collect where collectBlogID=? and collectorID=?", new Object[]{blogID, userID}).size() != 0);
            map.put("isPraised", baseDAO.findBySQL("select * from praise_blog where praiseBlogID=? and praiseFromUserID=?", new Object[]{blogID, userID}).size() != 0);
        }
        return map;
    }
}
