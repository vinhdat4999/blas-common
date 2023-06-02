package com.blas.blascommon.utils.email;

import static jakarta.mail.Transport.send;

import jakarta.mail.Message.RecipientType;
import jakarta.mail.MessagingException;
import jakarta.mail.Multipart;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicInteger;
import lombok.extern.slf4j.Slf4j;

/*
  !!Warning: This utility class used for sending internal email, not through service blas-email.
  Only use this utility when really needed.
 */
@Slf4j
public class SendEmail implements Runnable {

  private static final String EMAIL_SENDER = "blasvietnam@gmail.com";
  private static final String PASSWORD = "pdaifcxvnvsqyums";
  private static final int PORT_SENDER = 465;

  private final String subject;
  private final String htmlContent;
  private final String receiverEmail;

  public SendEmail(String subject, String htmlContent, String receiverEmail) {
    this.subject = subject;
    this.htmlContent = htmlContent;
    this.receiverEmail = receiverEmail;
  }

  @Override
  public void run() {
    Properties props = new Properties();
    props.put("mail.smtp.host", "smtp.gmail.com");
    props.put("mail.smtp.port", PORT_SENDER);
    props.put("mail.smtp.auth", "true");
    props.put("mail.smtp.starttls.enable", "true");
    props.put("mail.smtp.starttls.required", "true");
    props.put("mail.smtp.ssl.protocols", "TLSv1.2");
    props.put("mail.smtp.ssl.checkserveridentity", "true");
    props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

    Session session = Session.getDefaultInstance(props, new jakarta.mail.Authenticator() {
      @Override
      protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(EMAIL_SENDER, PASSWORD);
      }
    });
    MimeMessage message = new MimeMessage(session);
    try {
      message.setFrom(new InternetAddress(EMAIL_SENDER));
    } catch (MessagingException e) {
      log.error(e.toString());
    }
    MimeBodyPart messageBodyPartContent = new MimeBodyPart();
    AtomicInteger sentEmailNum = new AtomicInteger();
    try {
      message.addRecipient(RecipientType.TO, new InternetAddress(this.receiverEmail));
      message.setSubject(this.subject, "utf8");
      messageBodyPartContent.setContent(this.htmlContent, "text/html; charset=utf-8");
      Multipart multipart = new MimeMultipart();
      multipart.addBodyPart(messageBodyPartContent);
      message.setContent(multipart);
      send(message);
      sentEmailNum.getAndIncrement();
    } catch (MessagingException e) {
      log.error(e.toString());
    }
  }
}
