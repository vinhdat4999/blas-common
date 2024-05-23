package com.blas.blascommon.utils;

import static com.blas.blascommon.constants.BlasConstant.TELEGRAM_BLAS_VIETNAM_BOT;
import static com.blas.blascommon.security.SecurityUtils.aesDecrypt;
import static com.blas.blascommon.security.SecurityUtils.getPrivateKeyAesFromCertificate;
import static com.blas.blascommon.utils.StringUtils.EMPTY;
import static com.blas.blascommon.utils.httprequest.HttpMethod.POST;

import com.blas.blascommon.configurations.CertPasswordConfiguration;
import com.blas.blascommon.core.service.BlasConfigService;
import com.blas.blascommon.properties.BlasPrivateKeyProperties;
import com.blas.blascommon.utils.httprequest.HttpRequest;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URISyntaxException;
import java.net.URL;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
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

  private final CertPasswordConfiguration certPasswordConfiguration;

  public void sendTelegramMessage(String text, String chatId)
      throws URISyntaxException, InvalidAlgorithmParameterException, UnrecoverableKeyException, IllegalBlockSizeException, NoSuchPaddingException, CertificateException, KeyStoreException, IOException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
    final String privateKey = getPrivateKeyAesFromCertificate(
        blasPrivateKeyProperties.getCertificate(),
        blasPrivateKeyProperties.getAliasBlasPrivateKey(),
        certPasswordConfiguration.getCertPassword());
    final String key = aesDecrypt(privateKey,
        blasConfigService.getConfigValueFromKey(TELEGRAM_BLAS_VIETNAM_BOT));
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
