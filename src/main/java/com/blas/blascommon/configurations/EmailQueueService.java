package com.blas.blascommon.configurations;

import static com.blas.blascommon.constants.MessageTopic.BLAS_EMAIL_QUEUE;

import com.hazelcast.collection.IQueue;
import com.hazelcast.core.HazelcastInstance;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class EmailQueueService {

  private final IQueue<String> topic;

  @Autowired
  public EmailQueueService(HazelcastInstance hazelcastInstance) {
    this.topic = hazelcastInstance.getQueue(BLAS_EMAIL_QUEUE);
  }

  public void sendMessage(String message) {
    boolean result = topic.offer(message);
    log.info("Sending result: '{}' to topic {}", result, topic);
  }
}
