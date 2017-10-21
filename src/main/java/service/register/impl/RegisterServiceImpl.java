package service.register.impl;

import framework.exceptions.ServiceException;
import model.UserinfoEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import service.common.impl.CommServiceImpl;
import service.register.RegisterService;

import java.sql.Timestamp;
import java.util.List;
import java.util.Random;

/**
 * Created by Karn on 2016/12/4.
 */
@Service("RegisterService")
public class RegisterServiceImpl extends CommServiceImpl implements RegisterService {
    Logger logger = LoggerFactory.getLogger(service.register.impl.RegisterServiceImpl.class);

    public boolean registerByEmail(String register_account, String register_password) throws ServiceException {
        List<UserinfoEntity> list = baseDAO.findByProperty("email", register_account, UserinfoEntity.class);
        if (list.size() != 0)
            return false;
        Timestamp time = new Timestamp(System.currentTimeMillis());
        System.out.print(time);
        String nickName = "";
        while (true) {
            nickName = getRandomString(10);
            List<UserinfoEntity> list2 = baseDAO.findByProperty("nickName", nickName, UserinfoEntity.class);
            if (list2.size() == 0)
                break;
        }
        UserinfoEntity userinfoEntity=new UserinfoEntity();
        userinfoEntity.setEmail(register_account);
        userinfoEntity.setUserPassword(register_password);
        userinfoEntity.setNickName(nickName);
        userinfoEntity.setRegisterTime(time);
        userinfoEntity.setIsCertified(0);
        baseDAO.save(userinfoEntity);
        /*String sql = "insert into userinfo(email,userPassword,nickname,registerTime) VALUES('" + register_account + "','" + register_password + "','" + nickName + "','" + time + "');";
        System.out.println(sql);
        baseDAO.executeSQL(sql);*/
        return true;
    }

    public boolean registerByPhone(String register_account, String register_password) throws ServiceException {
        List<UserinfoEntity> list = baseDAO.findByProperty("teleNum", register_account, UserinfoEntity.class);
        if (list.size() != 0)
            return false;
        Timestamp time = new Timestamp(System.currentTimeMillis());
        System.out.print(time);
        String nickName = "";
        while (true) {
            nickName = getRandomString(10);
            List<UserinfoEntity> list2 = baseDAO.findByProperty("nickName", nickName, UserinfoEntity.class);
            if (list2.size() == 0)
                break;
        }
        Integer isCertified=0;
        String sql = "insert into userinfo(teleNum,userPassword,nickname,registerTime,isCertified) VALUES('" + register_account + "','" + register_password + "','" + nickName + "','" + time + "','" + isCertified +"');";
        System.out.println(sql);
        baseDAO.executeSQL(sql);
        return true;
    }

    public static String getRandomString(int length) { //length表示生成字符串的长度
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }
}