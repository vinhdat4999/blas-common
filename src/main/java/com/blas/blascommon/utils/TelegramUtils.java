package com.blas.blascommon.utils;

import static com.blas.blascommon.constants.BlasConstant.TELEGRAM_BLAS_VIETNAM_BOT;
import static com.blas.blascommon.security.SecurityUtils.aesDecrypt;
import static com.blas.blascommon.security.SecurityUtils.getPrivateKeyAesFromCertificate;
import static com.blas.blascommon.utils.StringUtils.EMPTY;
import static com.blas.blascommon.utils.httprequest.PostRequest.sendPostRequestWithStringPayload;

import com.blas.blascommon.core.service.BlasConfigService;
import com.blas.blascommon.properties.BlasPrivateKeyConfiguration;
import java.io.IOException;
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
import org.apache.http.client.utils.URIBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Lazy
@Component
public class TelegramUtils {

  @Lazy
  @Autowired
  private BlasPrivateKeyConfiguration blasPrivateKeyConfiguration;

  @Lazy
  @Autowired
  private BlasConfigService blasConfigService;

  @Autowired
  private String getCertPassword;

  public void sendTelegramMessage(String text, String chatId)
      throws URISyntaxException, InvalidAlgorithmParameterException, UnrecoverableKeyException, IllegalBlockSizeException, NoSuchPaddingException, CertificateException, KeyStoreException, IOException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
    final String privateKey = getPrivateKeyAesFromCertificate(
        blasPrivateKeyConfiguration.getCertificate(),
        blasPrivateKeyConfiguration.getAliasBlasPrivateKey(), getCertPassword);
    assert privateKey != null;
    final String key = aesDecrypt(privateKey,
        blasConfigService.getConfigValueFromKey(TELEGRAM_BLAS_VIETNAM_BOT));
    URIBuilder uriBuilder = new URIBuilder("https://api.telegram.org");
    uriBuilder.setPath("bot" + key + "/sendMessage");
    uriBuilder.addParameter("chat_id", chatId);
    uriBuilder.addParameter("parse_mode", "MarkdownV2");
    uriBuilder.addParameter("text", standardizeMessage(text));
    URL url = uriBuilder.build().toURL();
    sendPostRequestWithStringPayload(url.toString(), null, null, EMPTY);
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
