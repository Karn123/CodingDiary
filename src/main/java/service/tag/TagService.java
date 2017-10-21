package service.tag;

import controller.Pagination;
import model.TagEntity;
import service.common.ICommService;

import java.util.List;

/**
 * Created by 11022 on 2017/1/31.
 */
public interface TagService extends ICommService {
    public int createNewTag(String tagName);

    public List<TagEntity> getNewTags(Pagination pagination);
}
