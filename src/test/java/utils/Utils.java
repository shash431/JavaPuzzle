package utils;

import java.util.Map;

public class Utils {

    public static String getKey(Map<Object, Object> map, String value) {
        for (Map.Entry<Object, Object> entry : map.entrySet()) {
            if (entry.getValue().equals(value)) {
                return entry.getKey().toString();
            }
        }
        return null;
    }

}
