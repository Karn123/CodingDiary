package service.getPortrait.impl;

import model.UserinfoEntity;
import org.springframework.stereotype.Service;
import service.common.impl.CommServiceImpl;
import service.getPortrait.IGetPortraitService;

import java.util.List;

/**
 * Created by joy12 on 2017/2/1.
 * 从数据库获取头像url
 */
@Service("IGetPortraitService")
public class IGetPortraitServiceImpl extends CommServiceImpl implements IGetPortraitService {
    @Override
    public String getPortrait(int userID){
    //    System.out.println("目标ID" + userID);
        List<String> urlList = baseDAO.findBySQL("select headPortrait from userinfo where userID=?",new Object[]{userID});
        if (urlList.isEmpty()){
            System.out.println("头像列表为空");
            return null;
        }else{
            System.out.println(urlList.get(0));
            return urlList.get(0);
        }
    }
}
