package service.forumpostSystem;

import controller.Pagination;
import cst.Authority;
import framework.exceptions.AuthorityException;
import model.ForumpostEntity;
import model.ForumpostFloorEntity;
import service.common.ICommService;

import java.util.List;
import java.util.Set;

/**
 * Created by 佳乐 on 2017/2/13.
 */
public interface ForumpostService extends ICommService {
    public int postForumpost(int userId, String forumpostTitle, String forumpostContent, Set<Integer> tagsList);

    public ForumpostEntity getForumpostByID(int forumpostID, Authority authority) throws AuthorityException;

    public List<ForumpostEntity> getForumpostList(String type, String typeValue, int userID, String order, Pagination pagination, Authority authority);

    public List<ForumpostFloorEntity> getForumpostFloorList(int forumpostID) ;

    public int comment(int userId , int forumpostId ,String content);

    public int praise(int userID, int id);

    public int collect(int userID, int id);

    public List<ForumpostEntity> getBbsIndex();
}
