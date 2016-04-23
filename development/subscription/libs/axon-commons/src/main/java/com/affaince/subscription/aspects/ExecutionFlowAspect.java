package com.affaince.subscription.aspects;

import com.affaince.subscription.metadata.ExecutionFlow;
import com.affaince.subscription.metadata.ExecutionFlowNode;
import com.affaince.subscription.query.view.CommonView;
import com.affaince.subscription.repository.CommonViewRepository;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Pointcut;
import org.axonframework.domain.MetaData;

import java.util.HashMap;
import java.util.UUID;

/**
 * Created by anayonkar on 10/4/16.
 */
public class ExecutionFlowAspect {
    CommonViewRepository commonViewRepository;

    @Pointcut("call(** org.axonframework.eventsourcing.AbstractEventSourcedAggregateRoot.apply(..))")
    public void executionFlowPointCut() {

    }

    @Around("executionFlowPointCut()")
    public void attachMetadata(ProceedingJoinPoint pjp) throws Throwable {
        Object[] args = pjp.getArgs();
        MetaData metadata = args.length == 2 && MetaData.class.isAssignableFrom(args[1].getClass())
                ? (MetaData) args[1]
                : new MetaData(new HashMap<String, Object>());
        CommonView commonViewMetadata = null;
        CommonView commonView = null;
        if(args.length == 2) {
            commonViewMetadata = (CommonView)metadata.get(CommonView.METADATA);
            String uuid = commonViewMetadata.getUuid();
            commonView = commonViewRepository.findOne(uuid);
            if(commonView != null) {
                commonView = commonViewMetadata;
            }
        }
        //TODO : How to assign flow name here?
        String flowName = pjp.getTarget().getClass().getName();
        if(commonView == null) {
            ExecutionFlow executionFlow = new ExecutionFlow(flowName);
            executionFlow.addExecutionFlowNode(new ExecutionFlowNode(flowName, flowName));
            commonView = new CommonView(UUID.randomUUID().toString(), flowName);
        } else {
            commonView.getExecutionFlow().addExecutionFlowNode(new ExecutionFlowNode(flowName, flowName));
        }
        commonViewRepository.save(commonView);
        metadata.put(CommonView.METADATA, commonView);
        pjp.proceed(new Object[]{args[0], metadata});
    }
}
