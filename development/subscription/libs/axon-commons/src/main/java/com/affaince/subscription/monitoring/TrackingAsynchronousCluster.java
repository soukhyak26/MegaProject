package com.affaince.subscription.monitoring;

import org.axonframework.domain.EventMessage;
import org.axonframework.eventhandling.EventListener;
import org.axonframework.eventhandling.MultiplexingEventProcessingMonitor;
import org.axonframework.eventhandling.async.AsynchronousCluster;
import org.axonframework.eventhandling.async.ErrorHandler;
import org.axonframework.eventhandling.async.EventProcessor;
import org.axonframework.eventhandling.async.SequencingPolicy;
import org.axonframework.unitofwork.DefaultUnitOfWorkFactory;
import org.axonframework.unitofwork.UnitOfWorkFactory;

import java.util.Set;
import java.util.concurrent.Executor;

/**
 * Created by rsavaliya on 20/3/16.
 */
public class TrackingAsynchronousCluster extends AsynchronousCluster {
    private Executor executor;
    private ErrorHandler errorHandler;

    public TrackingAsynchronousCluster(String name, Executor executor,
                                       SequencingPolicy<? super EventMessage<?>> sequencingPolicy) {
        super(name, executor, sequencingPolicy);
        this.executor = executor;
        this.errorHandler = errorHandler;
    }

    @Override
    protected EventProcessor newProcessingScheduler(
            EventProcessor.ShutdownCallback shutDownCallback, Set<EventListener> eventListeners,
            MultiplexingEventProcessingMonitor eventProcessingMonitor) {

        return new TrackingEventProcessor(executor,
                shutDownCallback,
                errorHandler,
                new DefaultUnitOfWorkFactory(),
                eventListeners,
                eventProcessingMonitor);
    }
}
