package service.praiseSystem.impl;

import daos.PraiseDAO;
import org.springframework.stereotype.Service;
import service.common.impl.CommServiceImpl;
import service.praiseSystem.PraiseService;

import javax.annotation.Resource;

/**
 * Created by 11022 on 2017/2/1.
 */
@Service(value = "praiseService")
public class PraiseServiceImpl extends CommServiceImpl implements PraiseService {
    @Resource
    protected PraiseDAO praiseDAO;
    @Override
    public int praise(int userID, int id, String table) {
        try{
            return praiseDAO.praise(userID,id,table);
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }
}
