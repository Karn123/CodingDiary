package service.OtherPage.Impl;

import cst.Constants;
import model.UserinfoEntity;
import org.springframework.stereotype.Service;
import service.OtherPage.OtherPageService;
import service.common.impl.CommServiceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by joy12 on 2017/2/12.
 */
@Service("OtherPageService")
public class OtherPageServiceImpl extends CommServiceImpl implements OtherPageService{
    @Override
    public Map<String,Object> getInfo(int user_id,int this_id) {
        Map<String,Object> result = new HashMap<String,Object>();
        UserinfoEntity userInfo = baseDAO.findById(this_id, UserinfoEntity.class);
        result.put("userinfo",userInfo);
        String headPortrait = userInfo.getHeadPortrait();
        if(headPortrait == null)
            headPortrait = Constants.defaultUserHeadPortrait;
        result.put("this_portrait",headPortrait);
        String sql = "SELECT * FROM userfans WHERE userIDNum=? AND fanID= ?" ;
        List list = baseDAO.findBySQL(sql,new Object[]{this_id,user_id});
        if (list.isEmpty()){
            result.put("isFollowed","关注");
        }else{
            result.put("isFollowed","取消关注");
        }
        return result;
    }

    @Override
    public void follow(int fan_id,int this_id){
        String sql = "insert into userfans(userIDNum,fanID) VALUES(?,?)";
        baseDAO.executeSQL(sql,new Object[]{this_id,fan_id});
    }

    @Override
    public void unfollow(int fan_id,int this_id){
        String sql = "delete from userfans where userIDNum=? AND fanID=?";
        baseDAO.executeSQL(sql,new Object[]{this_id,fan_id});
    }
}
