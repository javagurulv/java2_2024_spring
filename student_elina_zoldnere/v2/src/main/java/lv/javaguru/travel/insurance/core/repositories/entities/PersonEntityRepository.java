package lv.javaguru.travel.insurance.core.repositories.entities;

import lv.javaguru.travel.insurance.core.domain.entities.PersonEntity;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;

import java.util.Optional;

public interface PersonEntityRepository extends JpaRepository<PersonEntity, Long> {
    @Cacheable(cacheNames = "personEntityCache", key = "#firstName + '_' + #lastName + '_' + #personalCode")
    @Query("SELECT pe from PersonEntity pe " +
            "where pe.firstName = :firstName " +
            "      and pe.lastName = :lastName " +
            "      and pe.personalCode = :personalCode")
    Optional<PersonEntity> findBy(
            @Param("firstName") String firstName,
            @Param("lastName") String lastName,
            @Param("personalCode") String personalCode
    );

    @Override
    @CacheEvict(cacheNames = "personEntityCache", allEntries = true)
    <S extends PersonEntity> @NonNull S save(@NonNull S entity);

    @Override
    @CacheEvict(cacheNames = "personEntityCache", allEntries = true)
    void deleteById(@NonNull Long id);

    @Override
    @CacheEvict(cacheNames = "personEntityCache", allEntries = true)
    void delete(@NonNull PersonEntity entity);

    @Override
    @CacheEvict(cacheNames = "personEntityCache", allEntries = true)
    void deleteAll(@NonNull Iterable<? extends PersonEntity> entities);

    @Override
    @CacheEvict(cacheNames = "personEntityCache", allEntries = true)
    void deleteAll();

}
