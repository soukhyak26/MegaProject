package com.affaince.subscription.monitoring;

import com.affaince.subscription.metadata.ExecutionFlowNode;
import com.affaince.subscription.query.view.CommonView;
import com.affaince.subscription.repository.CommonViewRepository;
import org.axonframework.correlation.CorrelationDataHolder;
import org.axonframework.domain.EventMessage;
import org.axonframework.domain.MetaData;
import org.axonframework.eventhandling.EventListener;
import org.axonframework.eventhandling.MultiplexingEventProcessingMonitor;
import org.axonframework.eventhandling.async.ErrorHandler;
import org.axonframework.eventhandling.async.EventProcessor;
import org.axonframework.unitofwork.UnitOfWorkFactory;

import java.util.Set;
import java.util.concurrent.Executor;

/**
 * Created by rsavaliya on 20/3/16.
 */
public class TrackingEventProcessor extends EventProcessor {
    static {
       /* try {
            ExecutionFlowConfiguration.getInstance().initConfiguration();
        } catch (Exception e) {
            //TODO: Handle error
            System.out.println(e);
            e.printStackTrace();
        }*/
    }
    //TODO: Check how to get instance of commonViewRepository
    private CommonViewRepository commonViewRepository;
    public TrackingEventProcessor(Executor executor, ShutdownCallback shutDownCallback, ErrorHandler errorHandler,
                                  UnitOfWorkFactory unitOfWorkFactory, Set<EventListener> eventListeners,
                                  MultiplexingEventProcessingMonitor eventProcessingMonitor) {
        super(executor, shutDownCallback, errorHandler, unitOfWorkFactory, eventListeners, eventProcessingMonitor);
        try {
//            ExecutionFlowConfiguration.getInstance().initConfiguration();
        } catch (Exception e) {
            //TODO: Handle error
            System.out.println(e);
            e.printStackTrace();
        }
    }

    @Override
    protected ProcessingResult doHandle(EventMessage<?> event) {
        try {
            //need to copy metadata from event to CorrelationDataHolder again here
            CorrelationDataHolder.setCorrelationData(event.getMetaData());
            //TODO: Save metadata to common read view (CommonViewRepository)
            //QUESTION: How to identify if specific UUID is abasent?
            //i.e. -> if UUID is absent or if it has not reached yet
            MetaData metadata = event.getMetaData();
            //String uuid = (String)metadata.get(MetadataFilter.FLOW_ID);
            CommonView commonViewMetadata = (CommonView)metadata.get(CommonView.METADATA);
            //TODO: fix how to read json file via sprint boot - then remove null check
            if(commonViewMetadata != null) {
                String uuid = commonViewMetadata.getUuid();
                CommonView commonView = commonViewRepository.findOne(uuid);
                if (commonView == null) {
                    commonView = commonViewMetadata;
                }
                //TODO: Ideally, input to construct ExecutionFlowNode should come from configuration (ExecutionFlowConfiguration)
                commonView.getExecutionFlow().addExecutionFlowNode(new ExecutionFlowNode("nodeName"/*, "flowName"*/));
                commonViewRepository.save(commonView);
            }
            return super.doHandle(event);
        } finally {
            //clear metadata from thread local
            CorrelationDataHolder.clear();
        }
    }
}
