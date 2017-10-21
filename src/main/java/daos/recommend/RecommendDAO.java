package daos.recommend;

import model.BlogEntity;
import model.ForumpostEntity;
import model.ResourceEntity;
import model.UserActionHistoryEntity;

import java.util.List;
import java.util.Map;

/**
 * Created by 11022 on 2017/3/9.
 */
public interface RecommendDAO {
    void saveAction(final UserActionHistoryEntity userActionHistoryEntity, int returnCode);

    void updateRecommend(final int recommendNumber);

    void updateNewRecommend(final int recommendNumber);

    void updateTotalValue();

    void updateUserAction();

    int updateUserParameter(final int userID, final int[] action);

    int updateUserInterest(final int userID, final int tagID);

    int preUpdateUserInterest(final int userID, final int tagNumber);

    int setNewUserInterest(final int userID, final List<Integer> tagIDList);

    List<Integer> getUserInterest(final int userID, final int mostInterestNumber, final int newestInterestNumber, final int randomNumber);

    Map<String, List<BlogEntity>> getRecommendBlogs(int hottestBlogNumber, int newestBlogNumber, int hottestInterestingBlogNumber, int newestInterestingBlogNumber, List<Integer> userInterests);

    Map<String, List<ForumpostEntity>> getRecommendForumposts(int hottestForumpostNumber, int newestForumpostNumber, int hottestInterestingForumpostNumber, int newestInterestingForumpostNumber, List<Integer> userInterests);

    Map<String, List<ResourceEntity>> getRecommendResources(int hottestResourceNumber, int newestResourceNumber, int hottestInterestingResourceNumber, int newestInterestingResourceNumber, List<Integer> userInterests);
}
