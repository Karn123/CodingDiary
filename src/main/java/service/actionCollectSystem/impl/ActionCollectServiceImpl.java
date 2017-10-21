package service.actionCollectSystem.impl;

import daos.recommend.RecommendDAO;
import model.UserActionHistoryEntity;
import org.springframework.stereotype.Service;
import service.actionCollectSystem.ActionCollectService;

import javax.annotation.Resource;

/**
 * Created by 11022 on 2017/2/26.
 */
@Service(value = "actionCollectService")
public class ActionCollectServiceImpl implements ActionCollectService {
    @Resource
    protected RecommendDAO recommendDAO;

    @Override
    public void handleUserActionHistory(UserActionHistoryEntity userActionHistoryEntity,int returnCode) {
        try {
            recommendDAO.saveAction(userActionHistoryEntity,returnCode);
        }catch (Exception e){

        }
    }
}
