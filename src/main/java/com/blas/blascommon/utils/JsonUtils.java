package com.blas.blascommon.utils;

import static com.blas.blascommon.utils.StringUtils.ASTERISK;

import java.util.Set;
import java.util.stream.IntStream;
import lombok.experimental.UtilityClass;
import org.json.JSONArray;
import org.json.JSONObject;

@UtilityClass
public class JsonUtils {

  public static JSONObject maskJsonObjectWithFields(JSONObject jsonObject, Set<String> fields) {
    fields.stream().filter(jsonObject::has).forEach(
        field -> jsonObject.put(field, ASTERISK.repeat(jsonObject.getString(field).length())));
    return jsonObject;
  }

  public static JSONArray maskJsonArrayWithFields(JSONArray jsonArray, Set<String> fields) {
    IntStream.range(0, jsonArray.length()).mapToObj(jsonArray::getJSONObject)
        .forEach(jsonObject -> maskJsonObjectWithFields(jsonObject, fields));
    return jsonArray;
  }
}
