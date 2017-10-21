package service.userInfo.impl;

import model.GenderEntity;
import model.UserinfoEntity;
import org.springframework.stereotype.Service;
import service.common.impl.CommServiceImpl;
import service.userInfo.UserInfoService;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by joy12 on 2017/2/6.
 * 获取和修改用户信息
 */
@Service("UserInfoService")
public class UserInfoServiceImpl extends CommServiceImpl implements UserInfoService {
    @Override
    public Map<String,Object> getUserInfo(Integer user_id){
        Map result = null;
        UserinfoEntity userInfo = baseDAO.findById(user_id, UserinfoEntity.class);
        if(userInfo != null){
            result = new HashMap();
//            result.put("nickName",userInfo.getNickName());
//            result.put("age",userInfo.getAge());
//            result.put("sex",userInfo.getGenderBySex().getGenderName());
//            result.put("universityName",userInfo.getUniversityName());
//            result.put("instituteName",userInfo.getInstituteName());
//            result.put("entranceYear",userInfo.getEntranceYear());
//            result.put("email",userInfo.getEmail());
//            result.put("teleNum",userInfo.getTeleNum());
            result.put("myinfo",userInfo);
        }
        return result;
    }

    @Override
    public int saveUserInfo(Integer user_id, Map<String,String> info) {
        if (info != null && info.size() > 0){
//            String sqlModel = "update userinfo SET info_key='info_value' where userID=?" ;
//            Iterator iter = info.entrySet().iterator();
//            while(iter.hasNext()){
//                Map.Entry entry = (Map.Entry)iter.next();
//                if (entry.getValue() != null && !entry.getValue().toString().isEmpty()){
//                    if (entry.getKey().toString().equals("sex")){
//
//                    }
//                    String sql = sqlModel.replace("info_key",entry.getKey().toString());
//                    sql = sql.replace("info_value",entry.getValue().toString());
//                    System.out.println("修改数据库用户信息：" + sql);
//                    baseDAO.executeSQL(sql,new Object[]{user_id});
//                }
//            }
            UserinfoEntity userinfoEntity = baseDAO.findById(user_id,UserinfoEntity.class);
            Iterator iter = info.entrySet().iterator();
            while(iter.hasNext()){
                Map.Entry entry = (Map.Entry)iter.next();
                String key = entry.getKey().toString();
                String value = entry.getValue().toString();
                if (value != null && !value.isEmpty()){
                    if (key.equals("sex")){
                        if (value.equals("男")){
                            GenderEntity gender = baseDAO.findById(0,GenderEntity.class);
                            userinfoEntity.setGenderBySex(gender);
                        } else if (value.equals("女")){
                            GenderEntity gender = baseDAO.findById(1,GenderEntity.class);
                            userinfoEntity.setGenderBySex(gender);
                        }
                    } else if (key.equals("nickname")){
                        userinfoEntity.setNickName(value);
                    } else if (key.equals("age")){
                        userinfoEntity.setAge(Integer.parseInt(value));
                    } else if (key.equals("universityName")){
                        userinfoEntity.setUniversityName(value);
                    } else if (key.equals("entranceYear")){
                        userinfoEntity.setEntranceYear(Integer.parseInt(value));
                    } else if (key.equals("instituteName")){
                        userinfoEntity.setInstituteName(value);
                    } else if (key.equals("email")){
                        userinfoEntity.setEmail(value);
                    } else if (key.equals("teleNum")){
                        userinfoEntity.setTeleNum(value);
                    }
                }
            }
            baseDAO.update(userinfoEntity);
            return 0;
        }else {
            return 1;
        }
    }

    @Override
    public void setUserCertified(Integer userId){
        UserinfoEntity userinfoEntity = baseDAO.findById(userId,UserinfoEntity.class);
        userinfoEntity.setIsCertified(1);
        baseDAO.save(userinfoEntity);
    }
}