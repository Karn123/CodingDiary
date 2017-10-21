package service.PortraitUpload.impl;
import org.springframework.stereotype.Service;
import service.common.impl.CommServiceImpl;
import service.PortraitUpload.IPortraitUploadService;

/**
 * Created by joy12 on 2017/1/31.
 * 更新数据库headPortrait字段信息
 */

@Service("IPortraitUploadService")
public class IPortraitUploadServiceImpl extends CommServiceImpl implements IPortraitUploadService {
    public void savePortraitToDB(int id, String fileAddr) {
        String sql = "update userinfo SET headPortrait=? where userID=?";
        baseDAO.executeSQL(sql,new Object[]{fileAddr,id});
    }
}
