package service.collectSystem.impl;

import controller.Pagination;
import cst.Authority;
import cst.UserActionAbout;
import daos.CollectDAO;
import model.BlogEntity;
import model.ForumpostEntity;
import model.ResourceEntity;
import org.springframework.stereotype.Service;
import service.collectSystem.CollectService;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by 11022 on 2017/2/1.
 */
@Service(value = "collectService")
public class CollectServiceImpl implements CollectService {
    @Resource
    protected CollectDAO collectDAO;

    @Override
    public int collect(int userID, int id, UserActionAbout userActionAbout) {
        try {
            return collectDAO.collect(userID, id, userActionAbout.getName());
        } catch (Exception re) {
            return 0;
        }
    }

    @Override
    public <T> List<T> getCollectList(int userID, UserActionAbout userActionAbout, Class<T> cls) {
        return null;
    }

    public List<ForumpostEntity> getForumpostCollectList(String type, String typeValue, int userID, String order, Pagination pagination) {
        List<ForumpostEntity> list = collectDAO.getForumpostCollectListByPage(type, typeValue, userID, pagination);
        pagination.updateBeginAndEndNumber();
        System.out.print(pagination.getBeginNumber()+"    "+pagination.getEndNumber());
        return  list;
    }
    public List<BlogEntity> getBlogCollectList(String type, String typeValue, int userID, String order, Pagination pagination) {
        List<BlogEntity> list = collectDAO.getBlogCollectListByPage(type, typeValue, userID, pagination);
        pagination.updateBeginAndEndNumber();
        System.out.print("blogCollectList"+pagination.getBeginNumber()+"    "+pagination.getEndNumber());
        return  list;
    }
    public List<ResourceEntity> getResourceCollectList(String type, String typeValue, int userID, String order, Pagination pagination) {
        List<ResourceEntity> list = collectDAO.getResourceCollectListByPage(type, typeValue, userID, pagination);
        pagination.updateBeginAndEndNumber();
        System.out.print("ResourceCollectList"+pagination.getBeginNumber()+"    "+pagination.getEndNumber());
        return  list;
    }
}
