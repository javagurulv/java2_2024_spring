package lv.javaguru.travel.insurance.core.api.repositories;

import lv.javaguru.travel.insurance.core.api.domain.Classifier;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClassifierRepository
extends JpaRepository<Classifier, Long> {

    Optional<Classifier> findByTitle(String title);
}
