package lv.javaguru.travel.insurance.core.services;

import lv.javaguru.travel.insurance.core.api.dto.PersonDTO;
import lv.javaguru.travel.insurance.core.domain.PersonEntity;
import lv.javaguru.travel.insurance.core.repositories.PersonEntityRepository;
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
            PersonEntity newPersonEntity = new PersonEntity();
            newPersonEntity.setFirstName(person.personFirstName());
            newPersonEntity.setLastName(person.personLastName());
            newPersonEntity.setPersonalCode(person.personalCode());
            newPersonEntity.setBirthDate(person.personBirthDate());
            return repository.save(newPersonEntity);
        }
    }

}
