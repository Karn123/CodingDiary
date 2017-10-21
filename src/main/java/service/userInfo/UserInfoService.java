package service.userInfo;

import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by joy12 on 2017/2/6.
 */
@Service
public interface UserInfoService {
    public Map<String,Object> getUserInfo(Integer user_id);
    public int saveUserInfo(Integer user_id, Map<String,String> info);
    void setUserCertified(Integer userId);
}
