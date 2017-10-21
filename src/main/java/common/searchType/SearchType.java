package common.searchType;

import common.util.DateUtil;
import common.util.SearchUtil;
import controller.Search;
import cst.Constants;
import cst.ReplaceMap;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by Karn on 2017/3/5.
 */
public class SearchType {
    private List<String> tags;
    private String tableName;
    private boolean orderByDateDes;
    private boolean orderByHotness;
    private int searchByUserId;

    public Date getSpecificDate() {
        return specificDate;
    }

    public void setSpecificDate(Date specificDate) {
        this.specificDate = specificDate;
    }

    private Date specificDate;

    public String getTagsString() {
        return tagsString;
    }

    public void setTagsString(String tagsString) {
        this.tagsString = tagsString;
    }

    private String tagsString;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }
    public boolean isOrderByDateDes() {
        return orderByDateDes;
    }

    public void setOrderByDateDes(boolean orderByDateDes) {
        this.orderByDateDes = orderByDateDes;
    }

    public boolean isOrderByHotness() {
        return orderByHotness;
    }

    public void setOrderByHotness(boolean orderByHotness) {
        this.orderByHotness = orderByHotness;
    }

    public int getSearchByUserId() {
        return searchByUserId;
    }

    public void setSearchByUserId(int searchByUserId) {
        this.searchByUserId = searchByUserId;
    }

    public SearchType(){
        tags = null;
        tagsString = null;
        orderByDateDes = false;
        orderByHotness = true;
        searchByUserId = -1;
        tableName = Constants.TableName.BLOG;
        specificDate = null;
    }

    public void replaceBackTags(){
        StringBuilder sb = new StringBuilder();
        if(tags!=null) {
            for (int i = 0; i < tags.size(); i++) {
                String s = tags.get(i);
                tags.set(i, ReplaceMap.replaceBack(s));
                sb.append(tags.get(i)).append(" ");
            }
            if (sb.length() > 0) {
                sb.deleteCharAt(sb.length() - 1);
                tagsString = sb.toString();
            }
        }
    }
    public void replaceTags(){
        StringBuilder sb = new StringBuilder();
        if(tags!=null) {
            for (int i = 0; i < tags.size(); i++) {
                String s = tags.get(i);
                tags.set(i, ReplaceMap.replace(s));
                sb.append(tags.get(i)).append(" ");
            }
            if (sb.length() > 0) {
                sb.deleteCharAt(sb.length() - 1);
                tagsString = sb.toString();
            }
        }
    }

    public SearchType(HttpServletRequest request) {
        String searchTags = request.getParameter("search_key");
        String searchTableName = request.getParameter("table_name");
        String isOrderByDateDes = request.getParameter("orderByDateDes");
        String isOrderByHotness = request.getParameter("orderByHotness");
        String searchUserId = request.getParameter("this_id");
        String searchSpecificDate = request.getParameter("searchBySpecificDate");
        if(searchTags!=null)
        {
            this.tags = SearchUtil.removeBlankSpace(searchTags.split(" "));
            replaceBackTags();
        }
        else
        {
            this.tags = null;
            tagsString = null;
        }

        if(searchTableName!=null)
            this.tableName = searchTableName;
        else
            this.tableName = Constants.TableName.BLOG;

        if(isOrderByDateDes!=null)
            this.orderByDateDes = Boolean.parseBoolean(isOrderByDateDes);
        else
            orderByDateDes = false;

        if(isOrderByHotness != null)
            this.orderByHotness = Boolean.parseBoolean(isOrderByHotness);
        else
            this.orderByHotness = true;

        if(searchUserId != null)
            this.searchByUserId = Integer.parseInt(searchUserId);
        else
            this.searchByUserId = -1;
        if(searchSpecificDate != null){
            this.specificDate = DateUtil.getDate(searchSpecificDate);
        }
        else
            this.specificDate = null;
    }
}