package com.affaince.subscription.integration.configuation;

import com.affaince.subscription.configuration.Default;
import com.affaince.subscription.integration.command.FetchSubscriptonableItemCommand;
import com.affaince.subscription.integration.command.listener.LogProcessListener;
import com.affaince.subscription.integration.command.listener.ReadEventListener;
import com.affaince.subscription.integration.command.mapper.FetchSubscriptonableItemCommandFieldSetMapper;
import com.affaince.subscription.integration.command.processor.FetchSubscriptonableItemCommandProcessor;
import com.affaince.subscription.integration.command.writer.Writer;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

import java.io.File;
import java.io.FileFilter;

/**
 * Created by mandark on 19-07-2015.
 */
@Configuration
@EnableBatchProcessing

public class Axon extends Default {

    @Autowired
    private JobBuilderFactory jobs;

    @Autowired
    StepBuilderFactory stepBuilderFactory;

    public static final String OVERRIDEN_BY_EXPRESSION_VALUE = "overriden by expression value";

    @Bean
    Job fileReadJob() {
        return jobs.get("fileReadJob").listener(readEventListener()).start(step()).build();
    }

    @Bean
    public Step step() {
        return stepBuilderFactory.get("step").<FetchSubscriptonableItemCommand, FetchSubscriptonableItemCommand>chunk(1).reader(fileReader(OVERRIDEN_BY_EXPRESSION_VALUE)).processor(fileProcessor()).writer(fileWriter()).listener(logProcessListener()).faultTolerant().skipLimit(5).skip(Exception.class).build();
    }

    @Bean
    @StepScope
    public FlatFileItemReader<FetchSubscriptonableItemCommand> fileReader(@Value("${directoryPath}") String directoryPath) {
        FlatFileItemReader<FetchSubscriptonableItemCommand> reader = new FlatFileItemReader<FetchSubscriptonableItemCommand>();
        reader.setLinesToSkip(1);//first line is title definition
        reader.setResource(new ClassPathResource(directoryPath));
        reader.setLineMapper(lineMapper());
        return reader;
    }

/*
    private Resource getFileFromDirectory(String directoryPath) {

        File fl = new File(directoryPath);

        File[] files = fl.listFiles(new FileFilter() {
            @Override
            public boolean accept(File file) {
                return file.isFile();
            }
        });

        if (files.length != 1)
            throw new RuntimeException("There must be only one file present in the folder to be processed");

        return new FileSystemResource(files[0]);
    }
*/


    @Bean
    public DefaultLineMapper<FetchSubscriptonableItemCommand> lineMapper() {
        DefaultLineMapper<FetchSubscriptonableItemCommand> lineMapper = new DefaultLineMapper<>();

        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setDelimiter(",");
        lineTokenizer.setStrict(false);
        lineTokenizer.setNames(new String[]{"BATCH_ID", "CATEGORY_ID", "CATEGORY_NAME", "SUBCATEGORY_ID", "SUBCATEGORY_NAME", "CURRENT_MRP", "CURRENT_PURCHASE", "PRICE_PER_UNIT", "CURRENT_STOCK", "PRODUCT_ID"
        });

        BeanWrapperFieldSetMapper<FetchSubscriptonableItemCommand> fieldSetMapper = new BeanWrapperFieldSetMapper<FetchSubscriptonableItemCommand>();
        fieldSetMapper.setTargetType(FetchSubscriptonableItemCommand.class);

        lineMapper.setLineTokenizer(lineTokenizer);
        lineMapper.setFieldSetMapper(fetchSubscriptonableItemCommandFieldSetMapper());
        return lineMapper;
    }


    @Bean
    public FetchSubscriptonableItemCommandFieldSetMapper fetchSubscriptonableItemCommandFieldSetMapper() {
        return new FetchSubscriptonableItemCommandFieldSetMapper();
    }

    /**
     * configure the processor related stuff
     */
    @Bean
    public ItemProcessor<FetchSubscriptonableItemCommand, FetchSubscriptonableItemCommand> fileProcessor() {
        return new FetchSubscriptonableItemCommandProcessor();
    }

    @Bean
    public ItemWriter<FetchSubscriptonableItemCommand> fileWriter() {
        return new Writer();
    }
    // end::readerwriterprocessor[]

    @Bean
    public ReadEventListener readEventListener() {
        return new ReadEventListener();
    }

    @Bean
    public LogProcessListener logProcessListener() {
        return new LogProcessListener();
    }

}
