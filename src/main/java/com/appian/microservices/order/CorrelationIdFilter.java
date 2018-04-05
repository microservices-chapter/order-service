package com.appian.microservices.order;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.ThreadContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Component
public class CorrelationIdFilter extends HandlerInterceptorAdapter {

  public static final String CORRELATION_ID = "CORRELATION-ID";

  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object)
      throws Exception {
    String correlationId = UUID.randomUUID().toString();
    if (request.getHeader(CORRELATION_ID) != null) {
      correlationId = request.getHeader(CORRELATION_ID);
      logger.debug(String.format("Found correlationId {%s} on thread {%s}", correlationId, Thread.currentThread().getId()));
    }
    ThreadContext.put(CORRELATION_ID, correlationId);
    return true;
  }

  @Override
  public void postHandle(HttpServletRequest request, HttpServletResponse response, Object object,
      ModelAndView model) throws Exception {
    ThreadContext.remove(CORRELATION_ID);
  }
}
