package com.blas.blascommon.utils;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import lombok.experimental.UtilityClass;

@UtilityClass
public class AmountUtils {

  public static String convertToVietnameseCurrency(int amount) {
    Locale locale = new Locale.Builder()
        .setLanguage("vi")
        .setRegion("VN")
        .build();
    DecimalFormatSymbols symbols = DecimalFormatSymbols.getInstance(locale);
    DecimalFormat format = new DecimalFormat("#,###,###.##", symbols);
    return format.format(amount) + " VNĐ";
  }
}
