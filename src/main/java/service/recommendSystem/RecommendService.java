package service.recommendSystem;

import model.BlogEntity;
import model.ForumpostEntity;
import model.ResourceEntity;
import model.UserActionHistoryEntity;
import service.common.ICommService;

import java.util.List;
import java.util.Map;

/**
 * Created by 11022 on 2017/2/23.
 */
public interface RecommendService extends ICommService {
    //int updateValue(int userID, UserAction userAction, UserActionAbout userActionAbout, int actionThemeID, int returnCode);

    int updateValue(UserActionHistoryEntity userActionHistoryEntity, int returnCode);

    int updateTodayValue();

    int updateRecommend();

    void updateTotalValue();

    void updateUserAction();

    List<Integer> getUserInterest(int userID);

    Map<String, List<BlogEntity>> getRecommendBlogs(int hottestBlogNumber, int newestBlogNumber, int hottestInterestingBlogNumber, int newestInterestingBlogNumber, List<Integer> userInterests);

    Map<String, List<ForumpostEntity>> getRecommendForumposts(int hottestForumpostNumber, int newestForumpostNumber, int hottestInterestingForumpostNumber, int newestInterestingForumpostNumber, List<Integer> userInterests);

    Map<String, List<ResourceEntity>> getRecommendResources(int hottestResourceNumber, int newestResourceNumber, int hottestInterestingResourceNumber, int newestInterestingResourceNumber, List<Integer> userInterests);

}
