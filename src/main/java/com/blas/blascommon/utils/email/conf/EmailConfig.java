package com.blas.blascommon.utils.email.conf;

import static com.blas.blascommon.constants.Email.EMAIL_SENDER_PREFIX;
import static com.blas.blascommon.constants.Email.PASSWORD_PREFIX;
import static com.blas.blascommon.constants.Email.PORT_PREFIX;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmailConfig {

    private static final String EMAIL_CONFIG_PATH = "/src/main/resources/email.conf";

    public static EmailConfigKeys getConfigInfor() {
        List<String> rawInfoList = readConfig(
                new File("").getAbsoluteFile() + EMAIL_CONFIG_PATH);
        Map<String, String> infoMap = new HashMap<>();
        for (String infoLine : rawInfoList) {
            if (infoLine.trim().indexOf("#") != 0 && infoLine.trim().indexOf("//") != 0) {
                String key = infoLine.split(":")[0].trim();
                String value = infoLine.split(":")[1].trim();
                infoMap.put(key, value);
            }
        }
        String emailSender = infoMap.get(EMAIL_SENDER_PREFIX);
        String password = infoMap.get(PASSWORD_PREFIX);
        int port = Integer.parseInt(infoMap.get(PORT_PREFIX));
        return new EmailConfigKeys(emailSender, password, port);
    }

    public static List<String> readConfig(String path) {
        List<String> stringList = new ArrayList<>();
        BufferedReader objReader = null;
        try {
            String tempStr = "";
            objReader = new BufferedReader(new FileReader(path));
            while ((tempStr = objReader.readLine()) != null) {
                if (!tempStr.trim().equals("") && tempStr.indexOf(":") != -1) {
                    stringList.add(tempStr.trim());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (objReader != null) {
                    objReader.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return stringList;
    }

}
