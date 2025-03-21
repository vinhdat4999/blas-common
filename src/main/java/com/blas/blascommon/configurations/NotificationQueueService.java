package com.blas.blascommon.configurations;

import static com.blas.blascommon.constants.MessageTopic.BLAS_NOTIFICATION_QUEUE;

import com.hazelcast.collection.IQueue;
import com.hazelcast.core.HazelcastInstance;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@Profile({"aks", "local"})
public class NotificationQueueService {

  private final IQueue<String> topic;

  @Autowired
  public NotificationQueueService(HazelcastInstance hazelcastInstance) {
    this.topic = hazelcastInstance.getQueue(BLAS_NOTIFICATION_QUEUE);
  }

  public void sendNotification(String message) {
    boolean result = topic.offer(message);
    log.info("Sending result: '{}' to topic {}", result, topic);
  }
}
