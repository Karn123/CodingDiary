package service.resourceSystem.uploadFile;

import framework.exceptions.ServiceException;
import org.springframework.stereotype.Service;
import service.common.ICommService;

import java.sql.Timestamp;

/**
 * Created by Karn on 2017/1/31.
 */
public interface IUploadFileService extends ICommService{
        /**
         *将文件信息存入数据库
         * @param uploaderID
         * @param resourceName
         * @param resourceDescription
         * @param resourcePath
         * @param timestamp
         * @return 0 已上传过
         *         1 上传成功
         * @throws ServiceException 上传失败
         */
        int saveFileToDB(int uploaderID, String resourceName,
                               String resourceDescription,String fileSizeStr,
                               String resourcePath, Timestamp timestamp,String[] docTags) throws ServiceException;
}
