package com.blas.blascommon.configurations;

import java.util.Map;
import lombok.AllArgsConstructor;
import org.slf4j.MDC;
import org.springframework.core.task.TaskDecorator;
import org.springframework.lang.NonNull;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

@AllArgsConstructor
public class WrappedMDCContextTaskDecorator implements TaskDecorator {

  private final boolean isRequestContextAvailable;

  @Override
  public @NonNull Runnable decorate(@NonNull Runnable runnable) {
    // Grab the current thread context data
    RequestAttributes requestAttributes = null;
    if (isRequestContextAvailable) {
      try {
        requestAttributes = RequestContextHolder.currentRequestAttributes();
      } catch (IllegalStateException illegalStateException) {
        // request context not available, continue the runnable
        return runnable;
      }
    }
    Map<String, String> contextMap = MDC.getCopyOfContextMap();
    RequestAttributes finalRequestAttributes = requestAttributes;
    return () -> {
      try {
        // Restore the Web thread context's data to @Async method
        RequestContextHolder.setRequestAttributes(finalRequestAttributes);
        MDC.setContextMap(contextMap);
        runnable.run();
      } finally {
        RequestContextHolder.resetRequestAttributes();
        MDC.clear();
      }
    };
  }
}
