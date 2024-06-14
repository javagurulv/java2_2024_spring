package lv.javaguru.travel.insurance.core.services.writers.entity;

import lv.javaguru.travel.insurance.core.api.dto.PersonDTO;
import lv.javaguru.travel.insurance.core.domain.entities.PersonEntity;
import lv.javaguru.travel.insurance.core.repositories.entities.PersonEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
class PersonWriter {

    @Autowired
    private PersonEntityRepository repository;

    PersonEntity writePersonIfNotExists(PersonDTO person) {
        Optional<PersonEntity> personOpt = repository.findBy(
                person.personFirstName(),
                person.personLastName(),
                person.personalCode()
        );
        if (personOpt.isPresent()) {
            return personOpt.get();
        } else {
            PersonEntity personEntity = new PersonEntity();
            personEntity.setFirstName(person.personFirstName());
            personEntity.setLastName(person.personLastName());
            personEntity.setPersonalCode(person.personalCode());
            personEntity.setBirthDate(person.personBirthDate());
            return repository.save(personEntity);
        }
    }

}
