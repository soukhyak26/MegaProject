package com.affaince.subscription.pricing.batchprocessor;

import com.affaince.subscription.pricing.query.view.ProductStatisticsView;
import com.affaince.subscription.pricing.vo.ConsolidatedProductStatistics;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemStream;
import org.springframework.batch.item.ItemStreamException;
import org.springframework.batch.item.data.MongoItemReader;
import org.springframework.batch.item.data.RepositoryItemReader;

/**
 * Created by mandark on 21-02-2016.
 */
public class ProductStatisticsItemReader implements ItemReader<ConsolidatedProductStatistics>, ItemStream {
    private RepositoryItemReader<ProductStatisticsView> delegate;
    // private List<ConsolidatedProductStatistics> consolidatedStatisticsPerProduct;


    public ProductStatisticsItemReader(RepositoryItemReader<ProductStatisticsView> delegate) {
        this.delegate = delegate;
    }

    public ConsolidatedProductStatistics read() throws Exception {
        ProductStatisticsView view = this.delegate.read();
        ConsolidatedProductStatistics tempStatistics = new ConsolidatedProductStatistics(view.getProductMonthlyVersionId().getProductId());
        tempStatistics.addToProductStatistics(view);
        for (ProductStatisticsView tempView = null; (tempView = this.delegate.read()) != null; ) {
            if (tempStatistics.getProductId().equals(view.getProductMonthlyVersionId().getProductId())) {
                tempStatistics.addToProductStatistics(view);
                return null;
            }

        }
        return tempStatistics;
    }

    @Override
    public void close() throws ItemStreamException {
        delegate.close();
    }

    @Override
    public void open(ExecutionContext ec) throws ItemStreamException {
        delegate.open(ec);
    }

    @Override
    public void update(ExecutionContext ec) throws ItemStreamException {
        delegate.update(ec);
    }

    public RepositoryItemReader<ProductStatisticsView> getDelegate() {
        return delegate;
    }

    public void setDelegate(RepositoryItemReader<ProductStatisticsView> delegate) {
        this.delegate = delegate;
    }
}
