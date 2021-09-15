package de.rnschk.cam;

import org.camunda.bpm.client.spring.annotation.ExternalTaskSubscription;
import org.camunda.bpm.client.task.ExternalTaskHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ExternalTaskHandlerConfig {
    private static final Logger log = LoggerFactory.getLogger(ExternalTaskHandlerConfig.class);

    @Bean
    @ExternalTaskSubscription("my-topic")
    public ExternalTaskHandler myTopicWorker() {
        return (task, service) -> {
            log.info("doing some work");
            service.complete(task);
        };
    }

}
