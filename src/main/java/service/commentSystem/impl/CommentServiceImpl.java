package service.commentSystem.impl;

import cst.Constants;
import daos.UsercommentDAO;
import model.UsercommentEntity;
import org.springframework.stereotype.Service;
import service.commentSystem.CommentService;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by 11022 on 2017/1/31.
 */
@Service(value = "commentService")
public class CommentServiceImpl implements CommentService {

    @Resource
    UsercommentDAO usercommentDAO;


    @Override
    public List<UsercommentEntity> getBlogUsercommentList(int blogID,int pageNumber)  {
        return usercommentDAO.findBlogCommentListByPage(blogID,pageNumber, Constants.blogCommentPageSize);
    }

    @Override
    public List<UsercommentEntity> getResourceUsercommentList(int resourceID, int pageNumber) {
        return usercommentDAO.findResourceCommentListByPage(resourceID,pageNumber,Constants.resourceCommentPageSize);
    }

    @Override
    public int comment(int userID, String commentContent, int id, Class cls)  {
        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());
        UsercommentEntity usercommentEntity = new UsercommentEntity(userID, commentContent, timestamp);
        return usercommentDAO.save(usercommentEntity, id, cls).getCommentId();
    }
}
