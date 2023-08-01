package com.blas.blascommon.utils;

import static com.blas.blascommon.utils.StringUtils.EMPTY;
import static com.blas.blascommon.utils.StringUtils.HYPHEN;
import static com.blas.blascommon.utils.StringUtils.SPACE;

import java.text.Normalizer;
import java.util.regex.Pattern;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UrlUtils {

  public static String toUrlStandard(String text) {
    String normalizedString = Normalizer.normalize(text, Normalizer.Form.NFD);
    Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
    String noDiacriticalMarks = pattern.matcher(normalizedString).replaceAll(EMPTY);
    return noDiacriticalMarks.replaceAll(SPACE, HYPHEN).toLowerCase();
  }
}
