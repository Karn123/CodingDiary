package service.resourceSystem.uploadFile.impl;

import framework.exceptions.ServiceException;
import model.*;
import org.springframework.stereotype.Service;
import service.common.impl.CommServiceImpl;
import service.resourceSystem.uploadFile.IUploadFileService;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by Karn on 2017/1/31.
 */

@Service(value = "UploadFileService")
public class UploadFileServiceImpl extends CommServiceImpl implements IUploadFileService {

    @Override
    public int saveFileToDB(int uploaderID, String resourceName, String resourceDescription,String fileSizeStr, String resourcePath,
                               Timestamp timestamp,String[] docTags) throws ServiceException {
        UserinfoEntity userinfoEntity = baseDAO.findById(uploaderID, UserinfoEntity.class);
        ResourceEntity resourceEntity = new ResourceEntity(userinfoEntity, resourceName,
                resourceDescription,fileSizeStr,resourcePath, timestamp);
        List<ResourceEntity> resourceEntities = baseDAO.findBySQL("select resource.resourceID from resource " +
                "where uploaderID=? and resourceName=?", new Object[]{uploaderID, resourceName});
        //检查文件是否已存在
        if(resourceEntities.size()>0)
            return 0;
        //未上传过，则保存
        baseDAO.save(resourceEntity);
        //插入标签
        for(String tag_id : docTags){
//            int tagID = -1;
//            TagEntity newTag = new TagEntity(tagName);
//            List<TagEntity> tags = baseDAO.findByProperty("tagName", tagName, TagEntity.class);
//            if (tags.isEmpty()) {
//                baseDAO.save(newTag);
//                tagID = newTag.getTagId();
//            }
//            else {
//                tagID = tags.get(0).getTagId();
//            }
            int tagID = Integer.parseInt(tag_id);
            baseDAO.executeSQL("insert into resource_tag values(?,?)",new Object[]{resourceEntity.getResourceId(),tagID});
        }
        return 1;
    }
}