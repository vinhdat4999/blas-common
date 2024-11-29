package com.blas.blascommon.configurations;

import static com.blas.blascommon.constants.MdcConstants.GLOBAL_ID;
import static com.blas.blascommon.constants.MessageTopic.BLAS_EMAIL_QUEUE;

import com.blas.blascommon.dto.IdContextHazelcastWrapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hazelcast.collection.IQueue;
import com.hazelcast.core.HazelcastInstance;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@Profile({"aks", "local"})
public class EmailQueueService {

  @Value("${blas.service.serviceName}")
  private String serviceName;

  private final IQueue<String> topic;
  private final ObjectMapper objectMapper;

  @Autowired
  public EmailQueueService(HazelcastInstance hazelcastInstance, ObjectMapper objectMapper) {
    this.topic = hazelcastInstance.getQueue(BLAS_EMAIL_QUEUE);
    this.objectMapper = objectMapper;
  }

  public void sendMessage(String message) {
    IdContextHazelcastWrapper hazelcastWrapper = IdContextHazelcastWrapper.builder()
        .message(message)
        .globalId(MDC.get(GLOBAL_ID))
        .callerId(serviceName)
        .callerServiceId(serviceName)
        .build();
    try {
      boolean result = topic.offer(objectMapper.writeValueAsString(hazelcastWrapper));
      log.info("Sending result: '{}' to topic {}", result, topic);
    } catch (JsonProcessingException exception) {
      log.error("Error while sending message", exception);
    }
  }
}
