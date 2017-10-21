package service.userLogIn.impl;

/**
 * Created by Karn on 2016/12/4.
 */

import framework.exceptions.ServiceException;
import model.UserinfoEntity;
import org.springframework.stereotype.Service;
import service.common.impl.CommServiceImpl;
import service.userLogIn.IUserLogInService;

import java.util.List;

@Service("IUserLogInService")
public class IUserLogInServiceImpl extends CommServiceImpl implements IUserLogInService {
    @Override
    public int login(String name, String pwd) throws ServiceException {
        List<UserinfoEntity> emailList = baseDAO.findByProperty("email", name, UserinfoEntity.class);
        List<UserinfoEntity> phoneList = baseDAO.findByProperty("teleNum", name, UserinfoEntity.class);
        List<UserinfoEntity> nameList = baseDAO.findByProperty("nickName", name, UserinfoEntity.class);
        int emailNum = emailList.size();
        int phoneNum = phoneList.size();
        int nameNum = nameList.size();
        if (emailNum == 0 && phoneNum == 0 && nameNum == 0) {
            return -1;        //用户不存在
        }
        if (emailNum == 1) {
            if (pwd.equals(emailList.get(0).getUserPassword()))
                return emailList.get(0).getUserId();
        }
        if (phoneNum == 1) {
            if (pwd.equals(phoneList.get(0).getUserPassword()))
                return phoneList.get(0).getUserId();
        }
        if (nameNum == 1) {
            if (pwd.equals(nameList.get(0).getUserPassword()))
                return nameList.get(0).getUserId();
        }
        return 0;//账号密码错误
    }

    @Override
    public String getUserHeadPortrait(int user_id) throws ServiceException {
        UserinfoEntity userinfoEntity = baseDAO.findById(user_id,UserinfoEntity.class);
        return userinfoEntity.getHeadPortrait();
    }
}