package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class PersonStatusComponent {

    private static final Logger log = LoggerFactory.getLogger(PersonStatusComponent.class);

    private PersonStatusRepository personStatusRepository;

    public PersonStatusComponent(PersonStatusRepository personStatusRepository) {
        this.personStatusRepository = personStatusRepository;
    }

    @Transactional
    @Async
    public void changeStatus(final String id, final String status) {

        this.personStatusRepository.findById(id).ifPresent(
                personStatus -> {
                    delay(2000);
                    personStatus.setStatus(status);
                    PersonStatus save = this.personStatusRepository.save(personStatus);
                    log.info(save.toString());
                }
        );
    }

    private void delay(final long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
