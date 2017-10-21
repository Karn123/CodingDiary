package service.test;

import framework.exceptions.ServiceException;
import org.springframework.stereotype.Service;
import service.common.ICommService;

/**
 * Created by Karn on 2016/12/4.
 */
public interface ITestService extends ICommService {
    void test() throws ServiceException;
}