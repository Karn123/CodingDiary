package service.getPortrait;

import cst.Constants;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

/**
 * Created by joy12 on 2017/2/1.
 */
@Service
public interface IGetPortraitService {
    public String getPortrait(int userID);
}
