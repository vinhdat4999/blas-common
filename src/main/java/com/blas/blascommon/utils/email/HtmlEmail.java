package com.blas.blascommon.utils.email;

import static com.blas.blascommon.utils.email.conf.EmailConfig.getConfigInfo;

import com.blas.blascommon.utils.email.conf.EmailConfigKeys;
import java.util.Properties;
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

public class HtmlEmail extends Email implements Runnable {


    public HtmlEmail(String emailTo, String title, String htmlContent) {
        super();
        super.emailTo = emailTo;
        super.title = title;
        super.htmlContent = htmlContent;
    }

    @Override
    public void run() {
        EmailConfigKeys emailConfigKeys = getConfigInfo();
        String RECEIVE_EMAIL = this.emailTo;
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", emailConfigKeys.getPort());
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.starttls.required", "true");
        props.put("mail.smtp.ssl.protocols", "TLSv1.2");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

        Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(emailConfigKeys.getEmailSender(),
                        emailConfigKeys.getPassword());
            }
        });

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(emailConfigKeys.getEmailSender()));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(RECEIVE_EMAIL));
            message.setSubject(this.title, "utf8");

            MimeBodyPart messageBodyPartContent = new MimeBodyPart();
            messageBodyPartContent.setContent(this.htmlContent, "text/html; charset=utf-8");

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPartContent);
            message.setContent(multipart);
            Transport.send(message);
        } catch (MessagingException ex) {
            ex.printStackTrace();
        }
    }

}
