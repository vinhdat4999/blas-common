package com.blas.blascommon.configurations;

import static com.blas.blascommon.constants.MessageTopic.BLAS_EMAIL_QUEUE;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.hazelcast.collection.IQueue;
import com.hazelcast.core.HazelcastInstance;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class EmailQueueServiceTest {

  @Mock
  private HazelcastInstance mockHazelcastInstance;

  @Mock
  private IQueue topic;

  private EmailQueueService emailQueueService;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    when(mockHazelcastInstance.getQueue(BLAS_EMAIL_QUEUE)).thenReturn(topic);
    emailQueueService = new EmailQueueService(mockHazelcastInstance);
  }

  @Test
  void testSendMessage() {
    String message = "Test message";
    boolean expectedResult = true;
    when(topic.offer(message)).thenReturn(expectedResult);
    emailQueueService.sendMessage(message);
    verify(topic).offer(message);
  }
}
