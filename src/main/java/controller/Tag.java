package controller;

import cst.Constants;
import model.TagEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import service.tag.TagService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * Created by 11022 on 2017/1/31.
 */
@Controller("Tag")
public class Tag {
    @Resource
    private TagService tagService;

    @RequestMapping(value = "/jsp/tag/getNewTag", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> getNewTag(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int changeTimes = Integer.parseInt(request.getParameter("change_times"));
        Pagination pagination = new Pagination(changeTimes, Constants.tagPageSize);
        List<TagEntity> list = tagService.getNewTags(pagination);
        Map<String, Object> result = new HashMap<String, Object>();
        if (pagination.getTotalPageNumber() == changeTimes + 1) {
            result.put("retCode", 1);
        } else {
            result.put("retCode", 0);
        }
        StringBuilder tagNameStringBuilder = new StringBuilder();
        StringBuilder tagIDStringBuilder = new StringBuilder();
        for (TagEntity tagEntity : list) {
            tagNameStringBuilder.append(tagEntity.getTagName());
            tagIDStringBuilder.append(tagEntity.getTagId());
            tagIDStringBuilder.append(",");
            tagNameStringBuilder.append(",");
        }
        tagNameStringBuilder.deleteCharAt(tagNameStringBuilder.length() - 1);
        tagIDStringBuilder.deleteCharAt(tagIDStringBuilder.length() - 1);
        result.put("tagNameList", tagNameStringBuilder.toString());
        result.put("tagIDList", tagIDStringBuilder.toString());
        return result;
    }

    @RequestMapping(value = "/jsp/tag/createNewTag", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> createNewTag(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String newTag = request.getParameter("new_tag");
        int retCode = tagService.createNewTag(newTag);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("retCode", retCode);
        return result;
    }

    public static Set<Integer> getTagIDList(String tags) {
        String[] tagsArray = tags.split(",");
        Set<Integer> tagsList = new HashSet<Integer>();
        for (String tag : tagsArray) {
            tagsList.add(Integer.parseInt(tag));
        }
        return tagsList;
    }
}
