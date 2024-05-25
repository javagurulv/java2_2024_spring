package lv.javaguru.travel.insurance.core.sercices;

import lv.javaguru.travel.insurance.core.api.dto.PersonDTO;
import lv.javaguru.travel.insurance.core.domain.PersonEntity;
import lv.javaguru.travel.insurance.core.repositories.PersonEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
class PersonSaver {

    @Autowired
    private PersonEntityRepository repository;

    PersonEntity savePerson(PersonDTO personDTO) {
        Optional<PersonEntity> personOpt = repository.findBy(
                personDTO.personFirstName(),
                personDTO.personLastName(),
                personDTO.personCode());
        if (personOpt.isPresent()) {
            return personOpt.get();
        } else {
            PersonEntity person = new PersonEntity();
            person.setFirstName(personDTO.personFirstName());
            person.setLastName(personDTO.personLastName());
            person.setPersonCode(personDTO.personCode());
            person.setBirthDate(personDTO.personBirthDate());
            return repository.save(person);
        }
    }
}
