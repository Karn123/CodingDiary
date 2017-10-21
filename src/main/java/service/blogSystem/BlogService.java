package service.blogSystem;

import controller.Pagination;
import cst.Authority;
import framework.exceptions.AuthorityException;
import model.BlogEntity;
import model.UserinfoEntity;
import service.common.ICommService;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by 11022 on 2017/1/31.
 */
public interface BlogService extends ICommService {
    int postBlog(int userId, String blogTitle, String blogContent, Set<Integer> tagsList);

    int editBlog(int blogID, String blogTitle, String blogContent, Set<Integer> tagsList);

    int deleteBlog(int blogID);

    BlogEntity getBlogByID(int blogID, Authority authority) throws AuthorityException;

    List<BlogEntity> getBlogList(String type, String typeValue, int userID, String order, Pagination pagination, Authority authority);

    List<BlogEntity> getPersonalBlog(int userID,Pagination pagination,Authority authority);

    UserinfoEntity getUserinfoByID(int userID);

    Map<String,Boolean> getBlogStatue(int blogID,int userID);
}
