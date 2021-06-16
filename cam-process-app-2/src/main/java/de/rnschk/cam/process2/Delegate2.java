package de.rnschk.cam.process2;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class Delegate2 implements JavaDelegate {
    private static final Logger log = LoggerFactory.getLogger(Delegate2.class);

    @Override
    public void execute(final DelegateExecution execution) {
        log.info("Hello from {}", this.getClass().getSimpleName());
    }
}
