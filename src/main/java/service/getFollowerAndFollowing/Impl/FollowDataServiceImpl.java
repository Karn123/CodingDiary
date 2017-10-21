package service.getFollowerAndFollowing.Impl;

import controller.PersonData;
import model.UserinfoEntity;
import org.springframework.stereotype.Service;
import service.common.impl.CommServiceImpl;
import service.getFollowerAndFollowing.FollowDataService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by joy12 on 2017/3/12.
 */
@Service("FollowDataService")
public class FollowDataServiceImpl extends CommServiceImpl implements FollowDataService{
    @Override
    public ArrayList<PersonData> getFollowInfos(Integer id,String action) {
        ArrayList<PersonData> result = new ArrayList<PersonData>();

        String sql = null;
        if (action.equals("followers")){
            sql = "SELECT fanID FROM userfans WHERE userIDNum=?";
        }else if (action.equals("followings")){
            sql = "SELECT userIDNum FROM userfans WHERE fanID=?";
        }
        List<Integer> fanIDs = baseDAO.findBySQL(sql,new Object[]{id});
        for (Integer fanID : fanIDs){
            UserinfoEntity fanInfo = baseDAO.findById(fanID, UserinfoEntity.class);
            PersonData pd;
            if (fanInfo.getHeadPortrait() != null){
                pd = new PersonData(fanInfo.getUserId(),fanInfo.getNickName(),fanInfo.getHeadPortrait());
            }else{
                pd = new PersonData(fanInfo.getUserId(),fanInfo.getNickName());
            }

            result.add(pd);
        }
        return result;
    }



}
