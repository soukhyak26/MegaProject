package com.affaince.subscription.product.event.listener;

import com.affaince.subscription.common.vo.PriceTaggedWithProduct;
import com.affaince.subscription.product.event.BreakEvenPriceCalculatedEvent;
import com.affaince.subscription.product.query.repository.TaggedPriceVersionsViewRepository;
import com.affaince.subscription.product.query.view.TaggedPriceVersionsView;
import com.affaince.subscription.common.vo.ProductwiseTaggedPriceVersionId;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

/**
 * Created by mandar on 5/22/2017.
 */
@Component
public class BreakEvenPriceCalculatedEventListener {
    private TaggedPriceVersionsViewRepository taggedPriceVersionsViewRepository;
    @Autowired
    public BreakEvenPriceCalculatedEventListener(TaggedPriceVersionsViewRepository taggedPriceVersionsViewRepository) {
        this.taggedPriceVersionsViewRepository = taggedPriceVersionsViewRepository;
    }

    @EventHandler
    public void On(BreakEvenPriceCalculatedEvent event){
        Set<PriceTaggedWithProduct> taggedPriceVersions= event.getTaggedPriceVersions();
        List<TaggedPriceVersionsView> storedTaggedPriceVersions=taggedPriceVersionsViewRepository.findByProductwiseTaggedPriceVersionId_ProductId(event.getProductId());
        for(PriceTaggedWithProduct taggedPriceVersion:taggedPriceVersions){
            ProductwiseTaggedPriceVersionId id= new ProductwiseTaggedPriceVersionId(event.getProductId(),taggedPriceVersion.getTaggedPriceVersionId());
            if(storedTaggedPriceVersions.contains(id)){
                TaggedPriceVersionsView storedTaggedPriceVersion=storedTaggedPriceVersions.get(storedTaggedPriceVersions.indexOf(id));
                storedTaggedPriceVersion.setBreakEvenPrice(taggedPriceVersion.getBreakEvenPrice());
                taggedPriceVersionsViewRepository.save(storedTaggedPriceVersion);
            }
        }
    }
}
