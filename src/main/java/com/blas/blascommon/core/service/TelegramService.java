package com.blas.blascommon.core.service;

import static com.blas.blascommon.constants.BlasConstant.TELEGRAM_BLAS_ADMIN_BOT;
import static com.blas.blascommon.constants.BlasConstant.TELEGRAM_BLAS_PAYMENT_GATEWAY_BOT;
import static com.blas.blascommon.constants.BlasConstant.TELEGRAM_BLAS_VIETNAM_BOT;
import static com.blas.blascommon.core.service.http.HttpMethod.POST;
import static com.blas.blascommon.security.SecurityUtils.aesDecrypt;
import static com.blas.blascommon.utils.StringUtils.EMPTY;
import static org.apache.commons.lang3.StringUtils.isBlank;

import com.blas.blascommon.core.service.http.HttpRequest;
import com.blas.blascommon.security.KeyService;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URISyntaxException;
import java.net.URL;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.hc.core5.net.URIBuilder;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
@RequiredArgsConstructor
public class TelegramService {

  @Lazy
  private final BlasConfigService blasConfigService;

  @Lazy
  private final KeyService keyService;

  @Lazy
  private final HttpRequest httpRequest;

  private final RestTemplate restTemplate = new RestTemplate();

  public void sendTelegramMessageBlasVietNamBot(String text, String chatId, String imageUrl)
      throws InvalidAlgorithmParameterException, IllegalBlockSizeException, NoSuchPaddingException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException, URISyntaxException, IOException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
    sendTelegramMessageWithBot(TELEGRAM_BLAS_VIETNAM_BOT, text, chatId, imageUrl);
  }

  public void sendTelegramMessageBlasAdminBot(String text, String chatId, String imageUrl)
      throws InvalidAlgorithmParameterException, IllegalBlockSizeException, NoSuchPaddingException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException, URISyntaxException, IOException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
    sendTelegramMessageWithBot(TELEGRAM_BLAS_ADMIN_BOT, text, chatId, imageUrl);
  }

  public void sendTelegramMessageBlasPaymentGatewayBot(String text, String chatId, String imageUrl)
      throws InvalidAlgorithmParameterException, IllegalBlockSizeException, NoSuchPaddingException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException, URISyntaxException, IOException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
    sendTelegramMessageWithBot(TELEGRAM_BLAS_PAYMENT_GATEWAY_BOT, text,
        chatId, imageUrl);
  }

  public void sendTelegramMessageWithBot(String bot, String text,
      String chatId, String imageUrl)
      throws InvalidAlgorithmParameterException, IllegalBlockSizeException, NoSuchPaddingException, BadPaddingException, NoSuchAlgorithmException, InvalidKeyException, URISyntaxException, IOException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
    final String key = aesDecrypt(keyService.getBlasPrivateKey(),
        blasConfigService.getConfigValueFromKey(bot));
    if (isBlank(imageUrl)) {
      sendTelegramMessage(key, chatId, text);
    } else {
      sendTelegramMessageWithImage(key, chatId, imageUrl, text);
    }
  }

  private void sendTelegramMessageWithImage(String key, String chatId, String imageUrl,
      String text) {
    String url = "https://api.telegram.org/bot" + key + "/sendPhoto";
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.MULTIPART_FORM_DATA);
    MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
    body.add("chat_id", chatId);
    body.add("photo", imageUrl);
    body.add("caption", text);
    HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);
    restTemplate.postForEntity(url, requestEntity, String.class);
  }

  private void sendTelegramMessage(String key, String chatId, String text)
      throws URISyntaxException, IOException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
    URIBuilder uriBuilder = new URIBuilder("https://api.telegram.org");
    uriBuilder.setPath("bot" + key + "/sendMessage");
    uriBuilder.addParameter("chat_id", chatId);
    uriBuilder.addParameter("parse_mode", "MarkdownV2");
    uriBuilder.addParameter("text", standardizeMessage(text));
    URL url = uriBuilder.build().toURL();
    log.debug("Sending Telegram message");
    httpRequest.sendRequestWithStringPayload(url.toString(), POST, null, null, EMPTY);
  }

  private String standardizeMessage(String message) {
    List<Character> sensitiveChar = List.of('`', '~', '!', '!', '@', '#', '$', '%', '^', '&', '*',
        '(', ')', '-', '_', '=', '+', '[', ']', '{', '}', ';', ':', '"', '\'', ',', '<', '>', '.',
        '/', '?', '\\', '|');
    for (Character c : sensitiveChar) {
      if (message.contains(Character.toString(c))) {
        message = message.replace(Character.toString(c), "\\" + c);
      }
    }
    return message.replace("\\\\", "\\");
  }
}
