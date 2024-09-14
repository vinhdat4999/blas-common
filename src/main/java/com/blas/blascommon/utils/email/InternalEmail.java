package com.blas.blascommon.utils.email;

import static com.blas.blascommon.constants.BlasConstant.INTERNAL_EMAIL_PASSWORD;
import static com.blas.blascommon.constants.BlasConstant.INTERNAL_EMAIL_USERNAME;
import static com.blas.blascommon.security.SecurityUtils.aesDecrypt;
import static com.blas.blascommon.security.SecurityUtils.getPrivateKeyAesFromCertificate;
import static jakarta.mail.Transport.send;
import static org.apache.commons.lang3.StringUtils.EMPTY;

import com.blas.blascommon.configurations.CertPasswordConfiguration;
import com.blas.blascommon.core.service.BlasConfigService;
import com.blas.blascommon.properties.BlasPrivateKeyProperties;
import jakarta.mail.Message.RecipientType;
import jakarta.mail.MessagingException;
import jakarta.mail.Multipart;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicInteger;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * !!Warning: This utility class used for sending internal email, not through service blas-email.
 * Only use this utility when really needed.
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class InternalEmail {

  private static final int PORT_SENDER = 465;

  @Lazy
  private final BlasConfigService blasConfigService;

  @Lazy
  private final BlasPrivateKeyProperties blasPrivateKeyProperties;

  @Lazy
  private final CertPasswordConfiguration certPasswordConfiguration;

  @Async
  public void sendEmail(String receiverEmail, String subject, String htmlContent) {
    Properties props = new Properties();
    props.put("mail.smtp.host", "smtp.gmail.com");
    props.put("mail.smtp.port", PORT_SENDER);
    props.put("mail.smtp.auth", "true");
    props.put("mail.smtp.starttls.enable", "true");
    props.put("mail.smtp.starttls.required", "true");
    props.put("mail.smtp.ssl.protocols", "TLSv1.3");
    props.put("mail.smtp.ssl.checkserveridentity", "true");
    props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
    String username = EMPTY;
    String password = EMPTY;
    try {
      final String privateKey = getPrivateKeyAesFromCertificate(
          blasPrivateKeyProperties.getCertificate(),
          blasPrivateKeyProperties.getAliasBlasPrivateKey(),
          certPasswordConfiguration.getCertPassword());
      assert privateKey != null;
      username = aesDecrypt(privateKey,
          blasConfigService.getConfigValueFromKey(INTERNAL_EMAIL_USERNAME));
      password = aesDecrypt(privateKey,
          blasConfigService.getConfigValueFromKey(INTERNAL_EMAIL_PASSWORD));
    } catch (KeyStoreException | CertificateException | IOException | NoSuchAlgorithmException |
             UnrecoverableKeyException | IllegalBlockSizeException | BadPaddingException |
             InvalidAlgorithmParameterException | InvalidKeyException |
             NoSuchPaddingException exception) {
      log.error(exception.toString());
    }
    final String finalUsername = username;
    final String finalPassword = password;
    Session session = Session.getDefaultInstance(props, new jakarta.mail.Authenticator() {
      @Override
      protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(finalUsername, finalPassword);
      }
    });
    MimeMessage message = new MimeMessage(session);
    try {
      message.setFrom(new InternetAddress(finalUsername));
    } catch (MessagingException exception) {
      log.error(exception.toString());
    }
    MimeBodyPart messageBodyPartContent = new MimeBodyPart();
    AtomicInteger sentEmailNum = new AtomicInteger();
    try {
      message.addRecipient(RecipientType.TO, new InternetAddress(receiverEmail));
      message.setSubject(subject, "utf8");
      messageBodyPartContent.setContent(htmlContent, "text/html; charset=utf-8");
      Multipart multipart = new MimeMultipart();
      multipart.addBodyPart(messageBodyPartContent);
      message.setContent(multipart);
      send(message);
      sentEmailNum.getAndIncrement();
    } catch (MessagingException exception) {
      log.error(exception.toString());
    }
  }
}
