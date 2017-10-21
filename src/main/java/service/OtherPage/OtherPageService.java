package service.OtherPage;

import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by joy12 on 2017/2/12.
 */
@Service
public interface OtherPageService {
    public Map getInfo(int user_id,int this_id);
    public void follow(int fan_id,int this_id);
    public void unfollow(int fan_id,int this_id);
}
