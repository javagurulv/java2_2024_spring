package org.javaguru.travel.insurance.core.repositories;

import org.javaguru.travel.insurance.core.domain.CountryDefaultDayRate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CountryDefaultDayRateRepository
        extends JpaRepository<CountryDefaultDayRate, Long> {

    Optional<CountryDefaultDayRate> findByCountryIc(String countryIc);

}
