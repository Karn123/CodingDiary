package service.searchSystem;

import common.searchType.SearchType;
import controller.Pagination;
import cst.Constants;
import framework.exceptions.ServiceException;
import org.springframework.stereotype.Service;
import service.common.ICommService;

import java.util.Date;
import java.util.List;

/**
 * Created by Karn on 2017/3/3.
 */
@Service
public interface ISearchService extends ICommService {
    /**
     * 用于分区按条件搜索
     * @param searchType 搜索类型
     * @param pagination 分页
     * @return resultList
     * @throws ServiceException
     */
    List search(SearchType searchType, Pagination pagination) throws ServiceException;
}
