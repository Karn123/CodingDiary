package service.PortraitUpload;

import org.springframework.stereotype.Service;

import java.sql.Timestamp;

/**
 * Created by joy12 on 2017/1/31.
 */
@Service
public interface IPortraitUploadService {
    public void savePortraitToDB( int id,String fileAddr);
}
