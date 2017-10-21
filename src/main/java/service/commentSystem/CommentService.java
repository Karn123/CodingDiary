package service.commentSystem;

import model.UsercommentEntity;

import java.util.List;
import java.util.Map;

/**
 * Created by 11022 on 2017/1/31.
 */
public interface CommentService {
    List<UsercommentEntity> getBlogUsercommentList(int blogId, int pageNumber) ;
    List<UsercommentEntity> getResourceUsercommentList(int resourceID, int pageNumber) ;
    int comment(int userID, String commentContent, int id, Class cls) ;
}
