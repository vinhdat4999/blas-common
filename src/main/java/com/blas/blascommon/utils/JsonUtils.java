package com.blas.blascommon.utils;

import static com.blas.blascommon.utils.StringUtils.ASTERISK;

import java.util.Set;
import lombok.experimental.UtilityClass;
import org.json.JSONArray;
import org.json.JSONObject;

@UtilityClass
public class JsonUtils {

  public static JSONObject maskJsonWithFields(JSONObject json, Set<String> fields) {
    maskKeys(json, fields);
    return json;
  }

  public static JSONArray maskJsonWithFields(JSONArray json, Set<String> fields) {
    maskKeys(json, fields);
    return json;
  }

  public static void maskKeys(Object jsonElement, Set<String> keys) {
    if (jsonElement instanceof JSONObject jsonObject) {
      for (String key : jsonObject.keySet()) {
        Object value = jsonObject.get(key);
        if (keys.contains(key)) {
          jsonObject.put(key, ASTERISK.repeat(8));
        } else {
          maskKeys(value, keys);
        }
      }
    } else if (jsonElement instanceof JSONArray jsonArray) {
      for (int index = 0; index < jsonArray.length(); index++) {
        maskKeys(jsonArray.get(index), keys);
      }
    }
  }
}
