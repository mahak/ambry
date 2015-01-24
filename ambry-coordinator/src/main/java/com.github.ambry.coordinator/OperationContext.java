package com.github.ambry.coordinator;

import com.github.ambry.commons.ResponseHandler;
import java.util.concurrent.atomic.AtomicInteger;


/**
 * Context that, with high probability, uniquely identifies an operation.
 */
public class OperationContext {
  // currentCount wrapping around from MAX_VALUE to MIN_VALUE is OK. This should take long enough that log files
  // close together in time (days to weeks) contain only unique correlation ids.
  private static final AtomicInteger currentCount = new AtomicInteger(Integer.MIN_VALUE);
  private String clientId;
  private int correlationId;
  private int connectionPoolCheckoutTimeout;
  private boolean crossDCProxyCallEnable;
  private CoordinatorMetrics coordinatorMetrics;
  private ResponseHandler responseHandler;

  public OperationContext(String clientId, int connectionPoolCheckoutTimeout, boolean crossDCProxyCallEnable,
      CoordinatorMetrics coordinatorMetrics, ResponseHandler responseHandler) {
    this.clientId = clientId;
    this.correlationId = currentCount.incrementAndGet();
    this.connectionPoolCheckoutTimeout = connectionPoolCheckoutTimeout;
    this.coordinatorMetrics = coordinatorMetrics;
    this.crossDCProxyCallEnable = crossDCProxyCallEnable;
    this.responseHandler = responseHandler;
  }

  public String getClientId() {
    return clientId;
  }

  public int getCorrelationId() {
    return correlationId;
  }

  public int getConnectionPoolCheckoutTimeout() {
    return connectionPoolCheckoutTimeout;
  }

  public CoordinatorMetrics getCoordinatorMetrics() {
    return coordinatorMetrics;
  }

  public boolean isCrossDCProxyCallEnabled() {
    return crossDCProxyCallEnable;
  }

  public ResponseHandler getResponseHandler() {
    return responseHandler;
  }

  @Override
  public String toString() {
    return "OpContext{" + clientId + ':' + correlationId + ':' + connectionPoolCheckoutTimeout + ':'
        + crossDCProxyCallEnable + '}';
  }
}
