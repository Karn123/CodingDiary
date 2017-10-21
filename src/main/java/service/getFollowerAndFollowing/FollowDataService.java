package service.getFollowerAndFollowing;

import controller.PersonData;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * Created by joy12 on 2017/3/12.
 */
@Service
public interface FollowDataService {
    public ArrayList<PersonData> getFollowInfos(Integer id,String action);
}
