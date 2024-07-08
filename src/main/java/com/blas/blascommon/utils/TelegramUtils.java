package com.blas.blascommon.utils;

import static com.blas.blascommon.constants.BlasConstant.TELEGRAM_BLAS_ADMIN_BOT;
import static com.blas.blascommon.constants.BlasConstant.TELEGRAM_BLAS_PAYMENT_GATEWAY_BOT;
import static com.blas.blascommon.constants.BlasConstant.TELEGRAM_BLAS_VIETNAM_BOT;
import static com.blas.blascommon.security.SecurityUtils.aesDecrypt;
import static com.blas.blascommon.utils.StringUtils.EMPTY;
import static com.blas.blascommon.utils.httprequest.HttpMethod.POST;

import com.blas.blascommon.core.service.BlasConfigService;
import com.blas.blascommon.properties.BlasPrivateKeyProperties;
import com.blas.blascommon.security.KeyService;
import com.blas.blascommon.utils.httprequest.HttpRequest;
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
import org.apache.http.client.utils.URIBuilder;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Slf4j
@Lazy
@Component
@RequiredArgsConstructor
public class TelegramUtils {

  @Lazy
  private final BlasPrivateKeyProperties blasPrivateKeyProperties;

  @Lazy
  private final BlasConfigService blasConfigService;

  @Lazy
  private final HttpRequest httpRequest;

  @Lazy
  private final KeyService keyService;

  public void sendTelegramMessageBlasVietNamBot(String text, String chatId)
      throws URISyntaxException, InvalidAlgorithmParameterException, IllegalBlockSizeException, NoSuchPaddingException, IOException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
    sendTelegramMessageWithBot(TELEGRAM_BLAS_VIETNAM_BOT, text, chatId);
  }

  public void sendTelegramMessageBlasAdminBot(String text, String chatId)
      throws URISyntaxException, InvalidAlgorithmParameterException, IllegalBlockSizeException, NoSuchPaddingException, IOException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
    sendTelegramMessageWithBot(TELEGRAM_BLAS_ADMIN_BOT, text, chatId);
  }

  public void sendTelegramMessageBlasPaymentGatewayBot(String text, String chatId)
      throws URISyntaxException, InvalidAlgorithmParameterException, IllegalBlockSizeException, NoSuchPaddingException, IOException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
    sendTelegramMessageWithBot(TELEGRAM_BLAS_PAYMENT_GATEWAY_BOT, text, chatId);
  }

  private void sendTelegramMessageWithBot(String bot, String text, String chatId)
      throws URISyntaxException, InvalidAlgorithmParameterException, IllegalBlockSizeException, NoSuchPaddingException, IOException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
    final String key = aesDecrypt(keyService.getBlasPrivateKey(),
        blasConfigService.getConfigValueFromKey(bot));
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
