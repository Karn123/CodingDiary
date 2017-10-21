package service.userLogIn;

import framework.exceptions.ServiceException;
import org.springframework.stereotype.Service;
import service.common.ICommService;

/**
 * Created by Karn on 2016/12/4.
 */
@Service
public interface IUserLogInService extends ICommService {
    int login(String name,String pwd) throws ServiceException;
    String getUserHeadPortrait(int user_id) throws ServiceException;
}
