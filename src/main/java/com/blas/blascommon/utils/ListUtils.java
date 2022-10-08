package com.blas.blascommon.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class ListUtils {

  private ListUtils() {
  }

  public static <T> List<T> removeDuplicate(List<T> list) {
    return new ArrayList<>(new HashSet<>(list));
  }

  public static <T> Map<T, Integer> statsDuplicate(List<T> list) {
    Map<T, Integer> map = new HashMap<>();
    list.forEach(element -> {
      if (!map.containsKey(element)) {
        int tempTotal = (int) list.stream()
            .filter(elementForCount -> element.toString()
                .equals(elementForCount.toString()))
            .count();
        map.put(element, tempTotal);
      }
    });
    return map;
  }
}