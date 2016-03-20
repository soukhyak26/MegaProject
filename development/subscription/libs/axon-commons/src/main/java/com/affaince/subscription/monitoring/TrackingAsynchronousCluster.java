package com.affaince.subscription.monitoring;

import org.axonframework.domain.EventMessage;
import org.axonframework.eventhandling.EventListener;
import org.axonframework.eventhandling.MultiplexingEventProcessingMonitor;
import org.axonframework.eventhandling.async.*;
import org.axonframework.unitofwork.DefaultUnitOfWorkFactory;

import java.util.Set;
import java.util.concurrent.Executor;

/**
 * Created by rsavaliya on 20/3/16.
 */
public class TrackingAsynchronousCluster extends AsynchronousCluster {
    private Executor executor;

    public TrackingAsynchronousCluster(String name, Executor executor,
                                       SequencingPolicy<? super EventMessage<?>> sequencingPolicy) {
        super(name, executor, sequencingPolicy);
        this.executor = executor;
    }

    @Override
    protected EventProcessor newProcessingScheduler(
            EventProcessor.ShutdownCallback shutDownCallback, Set<EventListener> eventListeners,
            MultiplexingEventProcessingMonitor eventProcessingMonitor) {

        return new TrackingEventProcessor(executor,
                shutDownCallback,
                new DefaultErrorHandler(RetryPolicy.proceed()),
                new DefaultUnitOfWorkFactory(),
                eventListeners,
                eventProcessingMonitor);
    }
}
