package com.blas.blascommon.utils.email.conf;

public class EmailConfigKeys {

    private String emailSender;
    private String password;
    private int port;

    public EmailConfigKeys() {
    }

    public EmailConfigKeys(String emailSender, String password, int port) {
        this.emailSender = emailSender;
        this.password = password;
        this.port = port;
    }

    public String getEmailSender() {
        return emailSender;
    }

    public void setEmailSender(String emailSender) {
        this.emailSender = emailSender;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    @Override
    public String toString() {
        return "EmailConfig{" +
                "emailSender='" + emailSender + '\'' +
                ", password='" + password + '\'' +
                ", port='" + port + '\'' +
                '}';
    }
}