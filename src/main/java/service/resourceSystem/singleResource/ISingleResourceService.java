package service.resourceSystem.singleResource;

import framework.exceptions.ServiceException;
import model.UsercommentEntity;
import service.common.ICommService;

import java.util.List;
import java.util.Map;


/**
 * Created by Karn on 2017/2/8.
 */
public interface ISingleResourceService extends ICommService {
    void downloadResoure(int resourceID) throws ServiceException;

    /**
     * 根据资源ID拿到资源的评论信息
     */
    List<Map<String, String>> getResourceCommentInfo(int resource_id,List<UsercommentEntity> usercommentEntities)
            throws ServiceException;
    Map<String,String> commentResource(int user_id, int resource_id, String commentContent) throws ServiceException;

    Map<String,String> praiseResource(int resourceID, int praise_user_id) throws ServiceException;

    Map<String,String> collectResource(int resourceID,int collector) throws ServiceException;

    Map<String,String> forwardResource(int resourceID,int forwarderID) throws ServiceException;

    Map<String,String> evaluateResource(int user_id,int resourceID,float score) throws ServiceException;
}
