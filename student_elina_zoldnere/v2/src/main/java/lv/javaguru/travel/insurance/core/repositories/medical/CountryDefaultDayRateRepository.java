package lv.javaguru.travel.insurance.core.repositories.medical;

import lv.javaguru.travel.insurance.core.domain.medical.CountryDefaultDayRate;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CountryDefaultDayRateRepository extends JpaRepository<CountryDefaultDayRate, Long> {

    @Cacheable("countryDefaultDayRateCache")
    Optional<CountryDefaultDayRate> findByCountryIc(String countryIc);

}
