package cst;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 11022 on 2017/2/3.
 */
public class ReplaceMap {
    private static Map<String,String> map;
    private static Map<String,String> map1;
    static {
        map = new HashMap<String,String>();
        map.put("CPP", "C++");
        map.put("Csh", "C#");
        map.put("SQL", "数据库");

        map1 = new HashMap<String, String>();
        map1.put("C++","CPP");
        map1.put("C#","Csh");
        map1.put("数据库","SQL");
    }
    public static String replace(String original){
        if(map.containsKey(original))
            original = map.get(original);
        return original;
    }
    public static String replaceBack(String original){
        if(map1.containsKey(original))
            original = map1.get(original);
        return original;
    }
}
