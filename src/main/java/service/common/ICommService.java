package service.common;

import framework.exceptions.ServiceException;

/**
 * 公共服务类 文件名称：ICommService
 */
public interface ICommService {
	public <T> T getEntity(String id, Class<T> cls) throws ServiceException;
}