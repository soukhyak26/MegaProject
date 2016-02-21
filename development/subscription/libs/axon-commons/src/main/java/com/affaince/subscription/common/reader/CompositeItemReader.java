package com.affaince.subscription.common.reader;

import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemStream;

/**
 * Created by mandark on 21-02-2016.
 */
public abstract class CompositeItemReader<T> implements ItemReader<T>, ItemStream {
    private ItemReader[] delegates;
    private int delegateIndex;
    private ItemReader<T> currentDelegate;
    private ExecutionContext stepExecutionContext;

    public void setDelegates(ItemReader[] delegates) {
        this.delegates = delegates;
    }

    @BeforeStep
    private void beforeStep(StepExecution stepExecution) {
        this.stepExecutionContext = stepExecution.getExecutionContext();
    }

    public T read() throws Exception {
        T item = null;
        if (null != currentDelegate) {
            item = currentDelegate.read();
            if (null == item) {
                ((ItemStream) this.currentDelegate).close();
                this.currentDelegate = null;
            }
        }
        // Move to next delegate if previous was exhausted!
        if (null == item && this.delegateIndex < this.delegates.length) {
            this.currentDelegate = this.delegates[this.delegateIndex++];
            ((ItemStream) this.currentDelegate).open(this.stepExecutionContext);
            update(this.stepExecutionContext);
            // Recurse to read() to simulate loop through delegates
            item = read();
        }
        return item;
    }

    public void open(ExecutionContext ctx) {
        // During open restore last active reader and restore its state
        if (ctx.containsKey("index")) {
            this.delegateIndex = ctx.getInt("index");
            this.currentDelegate = this.delegates[this.delegateIndex];
            ((ItemStream) this.currentDelegate).open(ctx);
        }
    }

    public void update(ExecutionContext ctx) {
        // Update current delegate index and state
        ctx.putInt("index", this.delegateIndex);
        if (null != this.currentDelegate) {
            ((ItemStream) this.currentDelegate).update(ctx);
        }
    }

    public void close(ExecutionContext ctx) {
        if (null != this.currentDelegate) {
            ((ItemStream) this.currentDelegate).close();
        }
    }

    public void close() {
        if (null != this.currentDelegate) {
            ((ItemStream) this.currentDelegate).close();
        }
    }

    public abstract T mapToMasterEntity(T item);
}
