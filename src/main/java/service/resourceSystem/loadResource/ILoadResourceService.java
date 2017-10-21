package service.resourceSystem.loadResource;

import framework.exceptions.ServiceException;
import model.ResourceEntity;
import service.common.ICommService;

import java.util.List;
import java.util.Map;

/**
 * Created by Karn on 2017/2/2.
 */
public interface ILoadResourceService extends ICommService {
    List<Map<String,String>> loadResources(int user_id, int page_total, String searchContent, int offset, int page_size,String searchDate) throws ServiceException;

    List<ResourceEntity> loadHottestResources() throws ServiceException;
    /**
     * 获取/加载单个资源
     * @param resourceID
     * @param flag  false 代表我的资源首页加载资源列表（浏览数无需+1）
     *              true  代表用户点击资源（资源浏览数+1）
     * @return
     * @throws ServiceException
     */
    Map<String,String> getResourceInfoByID(int resourceID, boolean flag) throws ServiceException;
}
