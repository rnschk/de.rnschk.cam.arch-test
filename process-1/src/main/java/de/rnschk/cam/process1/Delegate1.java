package de.rnschk.cam.process1;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class Delegate1 implements JavaDelegate {
    private static final Logger log = LoggerFactory.getLogger(Delegate1.class);

    @Override
    public void execute(final DelegateExecution execution) {
        log.info("Hello from {}", this.getClass().getSimpleName());
    }
}
