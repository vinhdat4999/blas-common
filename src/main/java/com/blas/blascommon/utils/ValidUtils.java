package com.blas.blascommon.utils;

import static com.blas.blascommon.enums.EmailTemplate.PAYMENT_RECEIPT;
import static com.blas.blascommon.utils.StringUtils.SLASH;
import static com.blas.blascommon.utils.datetimeutils.DateTimeUtils.DATE_YYYYMMDD_SLASH_FORMAT;
import static java.time.LocalTime.now;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ValidUtils {

  public static boolean isValidEmail(String email) {
    String regex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
    Pattern pattern = Pattern.compile(regex);
    Matcher matcher = pattern.matcher(email);
    return matcher.matches();
  }

  public static boolean isValidPhoneNumber(String phoneNumber) {
    String regex = "^(?:0|84)[035789]\\d{8}\\b";
    Pattern pattern = Pattern.compile(regex);
    Matcher matcher = pattern.matcher(phoneNumber);
    return matcher.matches();
  }

  public static boolean isValidDate(int year, int month, int day) {
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_YYYYMMDD_SLASH_FORMAT);
    simpleDateFormat.setLenient(false);
    try {
      simpleDateFormat.parse(year + SLASH + month + SLASH + day);
    } catch (ParseException e) {
      return false;
    }
    return true;
  }

  public static boolean isAllDigitCharacter(String input) {
    return input.chars().allMatch(Character::isDigit);
  }

  public boolean anyMatch(Pattern pattern, String... strings) {
    return Arrays.stream(strings).anyMatch(pattern.asPredicate());
  }

  public boolean allMatch(Pattern pattern, String... strings) {
    return Arrays.stream(strings).allMatch(pattern.asPredicate());
  }

  public static void main(String[] args) throws IOException {
    String content = new TemplateUtils().generateHtmlContent(PAYMENT_RECEIPT, Map.ofEntries(
        Map.entry("email", "vinhdat4999@gmail.com"),
        Map.entry("phone", "0965040999"),
        Map.entry("name", "NGUYEN VINH DAT"),
        Map.entry("transactionId", "a19ae6e8-7217-412b-bc25-43d988f28787"),
        Map.entry("transactionTime", now().toString()),
        Map.entry("cardType", "VISA"),
        Map.entry("cardNumber", "************4242"),
        Map.entry("status", "SUCCESS"),
        Map.entry("description", "test description"),
        Map.entry("amount", "100"),
        Map.entry("currency", "USD")
    ));
    System.out.println(content);
  }
}
