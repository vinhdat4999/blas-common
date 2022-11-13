package com.blas.blascommon.utils.email;

import com.blas.blascommon.payload.HtmlEmailRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicInteger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/*
  !!Warning: This utility class used for sending internal email, not through service blas-email.
  Only use this utility when really needed.
 */
public class SendEmail implements Runnable {

  private static final String EMAIL_SENDER = "blasvietnam@gmail.com";
  private static final String PASSWORD = "pdaifcxvnvsqyums";
  private static final int PORT_SENDER = 465;

  private String subject;
  private String htmlContent;
  private String receiverEmail;

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
    props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

    Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
      @Override
      protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(EMAIL_SENDER, PASSWORD);
      }
    });
    MimeMessage message = new MimeMessage(session);
    try {
      message.setFrom(new InternetAddress(EMAIL_SENDER));
    } catch (MessagingException e) {
      e.printStackTrace();
    }
    MimeBodyPart messageBodyPartContent = new MimeBodyPart();
    AtomicInteger sentEmailNum = new AtomicInteger();
    List<HtmlEmailRequest> htmlEmailRequestFailedList = new ArrayList<>();
    try {
      message.addRecipient(Message.RecipientType.TO, new InternetAddress(this.receiverEmail));
      message.setSubject(this.subject, "utf8");
      messageBodyPartContent.setContent(this.htmlContent, "text/html; charset=utf-8");
      Multipart multipart = new MimeMultipart();
      multipart.addBodyPart(messageBodyPartContent);
      message.setContent(multipart);
      Transport.send(message);
      sentEmailNum.getAndIncrement();
    } catch (MessagingException e) {
      e.printStackTrace();
    }
  }
}
