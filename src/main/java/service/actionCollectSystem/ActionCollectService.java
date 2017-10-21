package service.actionCollectSystem;

import model.UserActionHistoryEntity;

/**
 * Created by 11022 on 2017/2/26.
 */
public interface ActionCollectService {
    void handleUserActionHistory(UserActionHistoryEntity userActionHistoryEntity,int returnCode);
}
