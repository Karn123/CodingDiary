package service.register;

import framework.exceptions.ServiceException;
import org.springframework.stereotype.Service;
import service.common.ICommService;

/**
 * Created by Karn on 2016/11/29.
 */
@Service
public interface RegisterService extends ICommService {
   boolean registerByEmail(String register_account,String register_password) throws ServiceException;
   boolean registerByPhone(String register_account,String register_password) throws ServiceException;
}