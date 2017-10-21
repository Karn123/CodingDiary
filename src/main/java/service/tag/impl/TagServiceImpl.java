package service.tag.impl;

import controller.Pagination;
import cst.Constants;
import daos.TagDAO;
import model.TagEntity;
import org.springframework.stereotype.Service;
import service.common.impl.CommServiceImpl;
import service.tag.TagService;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by 11022 on 2017/1/31.
 */
@Service(value = "tagService")
public class TagServiceImpl extends CommServiceImpl implements TagService {
    @Resource
    TagDAO tagDAO;

    @Override
    public int createNewTag(String tagName) {
        List<TagEntity> list = tagDAO.findByTagName(tagName);
        if (list.size() != 0) {
            return list.get(0).getTagId();
        }
        TagEntity tagEntity = new TagEntity(tagName);
        tagDAO.save(tagEntity);
        return tagEntity.getTagId();
    }

    @Override
    public List<TagEntity> getNewTags(Pagination pagination) {
        return tagDAO.findByPage(pagination);
    }
}
