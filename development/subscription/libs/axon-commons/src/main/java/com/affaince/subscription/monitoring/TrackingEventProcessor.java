package com.affaince.subscription.monitoring;

import org.axonframework.correlation.CorrelationDataHolder;
import org.axonframework.domain.EventMessage;
import org.axonframework.eventhandling.EventListener;
import org.axonframework.eventhandling.MultiplexingEventProcessingMonitor;
import org.axonframework.eventhandling.async.ErrorHandler;
import org.axonframework.eventhandling.async.EventProcessor;
import org.axonframework.unitofwork.UnitOfWorkFactory;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.Executor;

/**
 * Created by rsavaliya on 20/3/16.
 */
public class TrackingEventProcessor extends EventProcessor {
    public TrackingEventProcessor(Executor executor, ShutdownCallback shutDownCallback, ErrorHandler errorHandler,
                                  UnitOfWorkFactory unitOfWorkFactory, Set<EventListener> eventListeners,
                                  MultiplexingEventProcessingMonitor eventProcessingMonitor) {
        super(executor, shutDownCallback, errorHandler, unitOfWorkFactory, eventListeners, eventProcessingMonitor);
    }

    @Override
    protected ProcessingResult doHandle(EventMessage<?> event) {
        try {
            //need to copy metadata from event to CorrelationDataHolder again here
            CorrelationDataHolder.setCorrelationData(event.getMetaData());
            return super.doHandle(event);
        } finally {
            //clear metadata from thread local
            CorrelationDataHolder.clear();
        }
    }
}
