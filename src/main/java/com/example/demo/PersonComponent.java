package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class PersonComponent {

    private static final Logger log = LoggerFactory.getLogger(PersonComponent.class);

    private PersonRepository personRepository;

    public PersonComponent(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Transactional
    public void create(final Person person) {
        this.personRepository.save(person);
    }

    public Person find(final String id) {
        return this.personRepository.findById(id).get();
    }

    @Transactional
    @Async
    public void changeName(String id, String name) {
        this.personRepository.findById(id).ifPresent(
                person -> {
                    delay(1000);
                    person.setName(name);
                    Person save = this.personRepository.save(person);
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
