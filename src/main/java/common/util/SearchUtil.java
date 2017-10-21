package common.util;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Karn on 2017/3/6.
 */
public class SearchUtil {
    public static List<String> removeBlankSpace(String[] arr){
        List<String> searchTagsWithoutBlankSpace = new ArrayList<String>();
        for(String tmp:arr){
            if(!tmp.equals("")){
                searchTagsWithoutBlankSpace.add(tmp);
            }
        }
        return searchTagsWithoutBlankSpace;
    }
}
