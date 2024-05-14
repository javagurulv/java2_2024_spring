package lv.javaguru.travel.insurance.core.services.writers;

import lv.javaguru.travel.insurance.core.api.dto.PersonDTO;
import lv.javaguru.travel.insurance.core.domain.entities.PersonEntity;
import lv.javaguru.travel.insurance.core.repositories.entities.PersonEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class PersonWriter {

    @Autowired
    private PersonEntityRepository repository;

    public PersonEntity writePersonIfNotExists(PersonDTO person) {
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
