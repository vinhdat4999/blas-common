package com.blas.blascommon.configurations;

import static com.blas.blascommon.constants.BlasConstant.MONGO_CONNECTION_STRING;
import static com.blas.blascommon.security.SecurityUtils.aesDecrypt;
import static org.apache.commons.lang3.StringUtils.isBlank;

import com.blas.blascommon.core.service.BlasConfigService;
import com.blas.blascommon.security.KeyService;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class MongoDbConfiguration {

  @Lazy
  private final MongoProperties mongoProperties;

  @Lazy
  private final KeyService keyService;

  @Lazy
  private final BlasConfigService blasConfigService;

  @Bean
  public MongoClient mongoClient() {
    String uri = mongoProperties.getUri();
    if (isBlank(uri)) {
      return null;
    }

    String connectionString = getMongoDbConnectionString(uri);
    MongoClientSettings settings = MongoClientSettings.builder()
        .applyConnectionString(new ConnectionString(connectionString))
        .build();
    return MongoClients.create(settings);
  }

  private String getMongoDbConnectionString(String defaultConnectionString) {
    try {
      String connectionString = aesDecrypt(keyService.getBlasPrivateKey(),
          blasConfigService.getConfigValueFromKey(MONGO_CONNECTION_STRING));
      log.info("Retrieved the MONGO_CONNECTION_STRING value configured in the database");
      return connectionString;
    } catch (Exception exception) {
      log.warn(
          "Can not get MONGO_CONNECTION_STRING value in database. Using default connection string..");
      return defaultConnectionString;
    }
  }
}
