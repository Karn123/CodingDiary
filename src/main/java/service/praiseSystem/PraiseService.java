package service.praiseSystem;

import service.common.ICommService;

/**
 * Created by 11022 on 2017/2/1.
 */
public interface PraiseService extends ICommService {
    /**
     * 点赞
     * @param userID
     * @param id
     * @param table
     * @return 1表示点赞成功，-1表示取消赞成功，0表示请求失败
     */
    int praise(int userID,int id,String table);
}
