package controller;

import common.searchType.SearchType;
import common.util.SearchUtil;
import cst.Constants;
import framework.exceptions.ServiceException;
import model.BlogEntity;
import model.ForumpostEntity;
import model.ResourceEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import service.searchSystem.ISearchService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by 86761 on 2017/2/21.
 */
@Controller("Search")
public class Search {
    @Autowired
    private HttpServletRequest request;

    @Resource
    private ISearchService searchService;

    @RequestMapping(value="/jsp/search")
    public ModelAndView toSchoolHome(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("search");
        return mv;
    }

    @RequestMapping(value = "/jsp/searchResults")
    public ModelAndView toSearchRst() throws ServiceException {
        ModelAndView mv = new ModelAndView("searchResults");
        Pagination pagination = new Pagination(request, Constants.blogListPageSize);
        SearchType searchType = new SearchType(request);
        if(searchType.getTableName().equals(Constants.TableName.BLOG)) {
            List<BlogEntity> blogEntities = searchService.search(searchType, pagination);
            mv.addObject("blogs", blogEntities);
        }
        else if(searchType.getTableName().equals(Constants.TableName.FORUMPOST)){
            List<ForumpostEntity> forumpostEntities = searchService.search(searchType, pagination);
            mv.addObject("forumposts", forumpostEntities);
        }
        else {
            List<ResourceEntity> resourceEntities = searchService.search(searchType, pagination);
            mv.addObject("resources", resourceEntities);
        }
        mv.addObject("searchType",searchType);
        mv.addObject("pagination",pagination.updateBeginAndEndNumber());
        return mv;
    }
}
