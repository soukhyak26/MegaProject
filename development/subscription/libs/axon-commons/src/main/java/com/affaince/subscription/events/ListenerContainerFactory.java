package com.affaince.subscription.events;

import com.google.common.collect.Multimaps;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.env.Environment;
import org.springframework.jms.listener.DefaultMessageListenerContainer;

import javax.jms.ConnectionFactory;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.*;
import java.lang.reflect.Method;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multimaps;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.boot.autoconfigure.AutoConfigurationPackages;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.AssignableTypeFilter;
import org.springframework.core.type.filter.TypeFilter;
import org.springframework.util.ErrorHandler;
import org.springframework.util.ReflectionUtils;

import static com.google.common.base.Joiner.on;
import static com.google.common.collect.FluentIterable.from;
import static com.google.common.collect.Iterables.getFirst;
import static com.google.common.collect.Iterables.transform;
import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Sets.newHashSet;
import static java.lang.String.format;
import static javax.jms.Session.AUTO_ACKNOWLEDGE;
import static org.springframework.util.ReflectionUtils.doWithMethods;

/**
 * Created by mandark on 07-08-2015.
 */
public class ListenerContainerFactory implements FactoryBean<DefaultMessageListenerContainer>, InitializingBean, EnvironmentAware, BeanFactoryAware {

    private BeanFactory beanFactory;
    private Environment environment;

    private DefaultMessageListenerContainer container;
    private String concurrency = "2-20";
    private int sessionAcknowledgeMode = AUTO_ACKNOWLEDGE;
    private int idleConsumerLimit = 10;
    private String destinationName;
    private ErrorHandler errorHandler;
    private ConnectionFactory connectionFactory;
    private Map<String, String> consumedEventTypes;

    private Class<? extends Annotation> annotation;

    public void setConnectionFactory(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    public void setContainer(DefaultMessageListenerContainer container) {
        this.container = container;
    }

    public void setConcurrency(String concurrency) {
        this.concurrency = concurrency;
    }

    public void setDestinationName(String destinationName) {
        this.destinationName = destinationName;
    }

    public void setErrorHandler(ErrorHandler errorHandler) {
        this.errorHandler = errorHandler;
    }

    public void setIdleConsumerLimit(int idleConsumerLimit) {
        this.idleConsumerLimit = idleConsumerLimit;
    }

    public void setSessionAcknowledgeMode(int sessionAcknowledgeMode) {
        this.sessionAcknowledgeMode = sessionAcknowledgeMode;
    }

    public void setConsumedEventTypes(Map<String, String> consumedEventTypes) {
        this.consumedEventTypes = consumedEventTypes;
    }


    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    @Override
    public DefaultMessageListenerContainer getObject() throws Exception {
        return container;
    }

    @Override
    public Class<?> getObjectType() {
        return DefaultMessageListenerContainer.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        container = new DefaultMessageListenerContainer();
        container.setDestinationName(destinationName);
        container.setErrorHandler(errorHandler);
        container.setConnectionFactory(connectionFactory);
        container.setConcurrency(concurrency);
        container.setIdleConsumerLimit(idleConsumerLimit);
        container.setSessionAcknowledgeMode(sessionAcknowledgeMode);
        container.setMessageSelector(selector());
    }

    private String selector() {
        Set<String> types = newHashSet(selectors());
        if (!types.isEmpty()) {
            Iterable<String> quoted = transform(types, new Format("'%s'"));
            if (types.size() == 1) {
                return format("JMSType=%s", getFirst(quoted, "''"));
            } else {
                return format("JMSType IN(%s)", on(",").join(quoted));
            }
        }
        return "JMSType=Void";
    }

    public Iterable<String> selectors() {
        final ClassPathScanningCandidateComponentProvider localTypeScanner = new ClassPathScanningCandidateComponentProvider(false, environment);
//        System.out.println("@@@Annotation@@@: " + annotation.getName());
        localTypeScanner.addIncludeFilter(new MethodAnnotationTypeFilter(annotation));
        final Multimap<String, String> reverseMap = invertMap(consumedEventTypes);
        final Iterable<String> producedEventTypes = from(packagesToScan()).transformAndConcat(toClassName(localTypeScanner)).transformAndConcat(new Function<String, Iterable<String>>() {
            public Iterable<String> apply(String input) {
                if (reverseMap.containsKey(input)) {
                    return reverseMap.get(input);
                } else {
                    return Collections.singletonList(input);
                }
            }
        }).toSet();
        return producedEventTypes;
    }

    public static <K, V> Multimap<K, V> invertMap(Map<V, K> map) {
        return Multimaps.invertFrom(Multimaps.forMap(map), ArrayListMultimap.<K, V>create());
    }

    private Iterable<String> selectors(final String basePackage, ClassPathScanningCandidateComponentProvider localTypeScanner) {
        System.out.println("@@@base package:" + basePackage);
        return from(localTypeScanner.findCandidateComponents(basePackage)).transform(beanDefinitionToClass).transformAndConcat(toEventTypes).transformAndConcat(toSubTypes(basePackage)).transform(beanDefinitionToClass).transform(toClassName).toSet();
    }

    public static final Function<Class, String> toClassName = new Function<Class, String>() {
        public String apply(Class input) {
            return input.getName();
        }
    };

    public static final Function<BeanDefinition, Class> beanDefinitionToClass = new Function<BeanDefinition, Class>() {
        public Class apply(BeanDefinition input) {
            try {
                return Class.forName(input.getBeanClassName());
            } catch (ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        }
    };
    public final Function<Class, Iterable<Class>> toEventTypes = new Function<Class, Iterable<Class>>() {
        public Iterable<Class> apply(Class input) {
            List<Class> result = new ArrayList<>();
            doWithMethods(input, new MethodParameterTypeCollector(result), new AnnotationMethodFilter(annotation));
            return result;
        }
    };

    private Function<String, Iterable<String>> toClassName(final ClassPathScanningCandidateComponentProvider localTypeScanner) {
        return new Function<String, Iterable<String>>() {
            public Iterable<String> apply(String input) {
                return selectors(input, localTypeScanner);
            }
        };
    }

    private Function<Class, Iterable<BeanDefinition>> toSubTypes(final String basePackage) {
        return new Function<Class, Iterable<BeanDefinition>>() {
            public Iterable<BeanDefinition> apply(Class input) {
                ClassPathScanningCandidateComponentProvider subTypeScanner = new ClassPathScanningCandidateComponentProvider(false, environment);
                subTypeScanner.addIncludeFilter(new AssignableTypeFilter(input));
                return subTypeScanner.findCandidateComponents(basePackage);
            }
        };
    }

    private Iterable<String> packagesToScan() {
        if (AutoConfigurationPackages.has(beanFactory)) {
            return AutoConfigurationPackages.get(beanFactory);
        }
        return new ArrayList();
    }

    public void setAnnotation(Class<? extends Annotation> annotation) {
        this.annotation = annotation;
        System.out.println("@@@Annotation@@@: " + annotation.getName());
    }

    public static class AnnotationMethodFilter implements ReflectionUtils.MethodFilter {
        private final Set<Class<? extends Annotation>> annotationClasses;

        @SafeVarargs
        public AnnotationMethodFilter(Class<? extends Annotation>... annotationClasses) {
            this.annotationClasses = newHashSet(annotationClasses);
        }

        @Override
        public boolean matches(final Method method) {
            return from(annotationClasses).anyMatch(new Predicate<Class<? extends Annotation>>() {
                @Override
                public boolean apply(Class<? extends Annotation> input) {
                    return method.isAnnotationPresent(input);
                }
            });
        }
    }

    static class MethodParameterTypeCollector implements ReflectionUtils.MethodCallback {
        private final List<Class> registry;

        public MethodParameterTypeCollector(List<Class> registry) {
            this.registry = registry;
        }

        @Override
        public void doWith(Method method) throws IllegalArgumentException, IllegalAccessException {
            registry.add(method.getParameterTypes()[0]);
        }
    }

    static class MethodAnnotationTypeFilter implements TypeFilter {
        private final Class<? extends Annotation> type;

        public MethodAnnotationTypeFilter(Class<? extends Annotation> type) {
            this.type = type;
        }

        @Override
        public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory) throws IOException {
            return metadataReader.getAnnotationMetadata().hasAnnotatedMethods(type.getName());
        }
    }

    private class Format implements Function<String, String> {
        private String format;

        public Format(String format) {
            this.format = format;
        }

        @Override
        public String apply(String input) {
            return format(format, input);
        }
    }
}
