package service.collectSystem;

import controller.Pagination;
import cst.Authority;
import cst.UserActionAbout;
import model.BlogEntity;
import model.ForumpostEntity;
import model.ResourceEntity;

import java.util.List;

/**
 * Created by 11022 on 2017/2/1.
 */
public interface CollectService {
    /**
     * 收藏
     * @param userID
     * @param id
     * @param userActionAbout
     * @return 1表示收藏成功，-1表示取消收藏成功，0表示请求失败
     */
    public int collect(int userID, int id, UserActionAbout userActionAbout);

    public <T> List<T> getCollectList(int userID,UserActionAbout userActionAbout,Class<T> cls);

    public List<ForumpostEntity> getForumpostCollectList(String type, String typeValue, int userID, String order, Pagination pagination) ;

    public List<BlogEntity> getBlogCollectList(String type, String typeValue, int userID, String order, Pagination pagination);
    public List<ResourceEntity> getResourceCollectList(String type, String typeValue, int userID, String order, Pagination pagination);

    }
