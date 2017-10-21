package service.recommendSystem.impl;

import cst.UserAction;
import cst.UserActionAbout;
import daos.recommend.RecommendDAO;
import model.BlogEntity;
import model.ForumpostEntity;
import model.ResourceEntity;
import model.UserActionHistoryEntity;
import org.springframework.stereotype.Service;
import service.common.impl.CommServiceImpl;
import service.recommendSystem.RecommendService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by 11022 on 2017/2/23.
 */
@Service(value = "recommendService")
public class RecommendServiceImpl extends CommServiceImpl implements RecommendService {

    @Resource
    protected RecommendDAO recommendDAO;

    public int updateValue(int userID, UserAction userAction, UserActionAbout userActionAbout, int actionThemeID, int returnCode) {
        double parameter = Double.parseDouble(baseDAO.findBySQL("select " + userAction.name().toLowerCase() + "Parameter from user_parameter where userID=?", new Object[]{userID}).get(0).toString());
        baseDAO.executeSQL("update " + userActionAbout.name().toLowerCase() + "_recommend_value set " + userAction.name().toLowerCase() + "Value = " + userAction.name().toLowerCase() + "Value + ? where " + userActionAbout.name().toLowerCase() + "ID=?", new Object[]{returnCode * parameter, actionThemeID});
        return 0;
    }

    @Override
    public int updateValue(UserActionHistoryEntity userActionHistoryEntity, int returnCode) {
        return updateValue(userActionHistoryEntity.getUserId(), UserAction.values()[userActionHistoryEntity.getActionId()], UserActionAbout.values()[userActionHistoryEntity.getActionAboutId()], userActionHistoryEntity.getAboutThemeId(), returnCode);
    }

    @Override
    public int updateTodayValue() {
        updateTodayValue(new Double[]{0.1, 0.3, 0.3, 0.3}, false);
        return 0;
    }

    protected int updateTodayValue(Double[] parameter, boolean refreshTable) {
        baseDAO.executeSQL("update blog_recommend_value set todayValue=browseValue*?+collectValue*?+praiseValue*?+forwardValue+?", parameter);
        baseDAO.executeSQL("update forumpost_recommend_value set todayValue=browseValue*?+collectValue*?+praiseValue*?+forwardValue+?", parameter);
        baseDAO.executeSQL("update resource_recommend_value set todayValue=browseValue*?+collectValue*?+praiseValue*?+forwardValue+?", parameter);
        if (refreshTable) {
            baseDAO.executeSQL("update blog_recommend_value set browseValue=0,collectValue=0,praiseValue=0,forwardValue=0");
            baseDAO.executeSQL("update forumpost_recommend_value set browseValue=0,collectValue=0,praiseValue=0,forwardValue=0");
            baseDAO.executeSQL("update resource_recommend_value set browseValue=0,collectValue=0,praiseValue=0,forwardValue=0");
        }
        return 0;
    }

    @Override
    public int updateRecommend() {
        recommendDAO.updateRecommend(5);
        return 0;
    }

    @Override
    public void updateTotalValue() {
        recommendDAO.updateTotalValue();
    }

    @Override
    public void updateUserAction() {
        recommendDAO.updateUserAction();
    }

    @Override
    public List<Integer> getUserInterest(int userID) {
        return recommendDAO.getUserInterest(userID, 1, 1, 1);
    }

    @Override
    public Map<String, List<BlogEntity>> getRecommendBlogs(int hottestBlogNumber, int newestBlogNumber, int hottestInterestingBlogNumber, int newestInterestingBlogNumber, List<Integer> userInterests) {
        if (userInterests == null) {
            userInterests = new ArrayList<Integer>();
            userInterests.add(0);
        }
        return recommendDAO.getRecommendBlogs(hottestBlogNumber, newestBlogNumber, hottestInterestingBlogNumber, newestInterestingBlogNumber, userInterests);
    }

    @Override
    public Map<String, List<ForumpostEntity>> getRecommendForumposts(int hottestForumpostNumber, int newestForumpostNumber, int hottestInterestingForumpostNumber, int newestInterestingForumpostNumber, List<Integer> userInterests) {
        if (userInterests == null) {
            userInterests = new ArrayList<Integer>();
            userInterests.add(0);
        }
        return recommendDAO.getRecommendForumposts(hottestForumpostNumber, newestForumpostNumber, hottestInterestingForumpostNumber, newestInterestingForumpostNumber, userInterests);
    }

    @Override
    public Map<String, List<ResourceEntity>> getRecommendResources(int hottestResourceNumber, int newestResourceNumber, int hottestInterestingResourceNumber, int newestInterestingResourceNumber, List<Integer> userInterests) {
        if (userInterests == null) {
            userInterests = new ArrayList<Integer>();
            userInterests.add(0);
        }
        return recommendDAO.getRecommendResources(hottestResourceNumber, newestResourceNumber, hottestInterestingResourceNumber, newestInterestingResourceNumber, userInterests);
    }
}
