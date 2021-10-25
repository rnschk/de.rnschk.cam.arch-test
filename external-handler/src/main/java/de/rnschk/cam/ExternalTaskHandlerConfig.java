package de.rnschk.cam;

import de.viadee.bpm.camunda.externaltask.retry.aspect.error.ExternalTaskBusinessError;
import de.viadee.bpm.camunda.externaltask.retry.aspect.error.InstantIncidentException;
import org.camunda.bpm.client.spring.annotation.ExternalTaskSubscription;
import org.camunda.bpm.client.task.ExternalTaskHandler;
import org.slf4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.slf4j.LoggerFactory.getLogger;

@Configuration
public class ExternalTaskHandlerConfig {
    private static final Logger with_retries_log = getLogger("with-retries-handler");
    private static final Logger business_error_log = getLogger("bpmn-error-handler");
    private static final Logger instant_incident_log = getLogger("instant-incident-handler");



    /** + + + + + + + + + + + + + + + + + + + + + + + + + + + + + + + + + + + + + + + */


    @Bean
    @ExternalTaskSubscription("instant-incident")
    public ExternalTaskHandler withInstantIncident() {
        return (task, service) -> {

            instant_incident_log.info("do business logic");
            instantIncident(); // avoid 'unreachable statement' in IDE

            instant_incident_log.info("complete external-task, never reached");
            service.complete(task);
        };
    }

    private static void instantIncident() {
        throw new InstantIncidentException("Some error occurred", new RuntimeException("Resistance is futile!"));
    }


    /** + + + + + + + + + + + + + + + + + + + + + + + + + + + + + + + + + + + + + + + */


    @Bean
    @ExternalTaskSubscription("topic-with-retries")
    public ExternalTaskHandler withRetries() {
        return (task, service) -> {

            with_retries_log.info("do business logic");
            runtimeException(); // unhandled technical error

            with_retries_log.info("complete external-task, never reached");
            service.complete(task);
        };
    }

    private static void runtimeException() {
        throw new RuntimeException("Some error occurred, but a retry might help.");
    }


    /** + + + + + + + + + + + + + + + + + + + + + + + + + + + + + + + + + + + + + + + */


    @Bean
    @ExternalTaskSubscription("topic-with-business-error")
    public ExternalTaskHandler withBusinessErrorRetries() {
        return (task, service) -> {

            business_error_log.info("do business logic");
            businessException(); // maps to bpmn-error

            business_error_log.info("complete external-task, never reached");
            service.complete(task);
        };
    }

    private static void businessException() {
        throw new ExternalTaskBusinessError("4711", "business-error");
    }

}
