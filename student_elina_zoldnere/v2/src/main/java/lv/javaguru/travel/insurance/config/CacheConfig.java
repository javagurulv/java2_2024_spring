package lv.javaguru.travel.insurance.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
@EnableCaching
public class CacheConfig {

    @Bean
    public CacheManager cacheManager() {
        CaffeineCacheManager cacheManager = new CaffeineCacheManager();

        cacheManager.registerCustomCache("classifierCache", Caffeine.newBuilder()
                .initialCapacity(20)
                .maximumSize(100)
                .expireAfterAccess(30, TimeUnit.MINUTES)
                .build());

        cacheManager.registerCustomCache("classifierValueCache", Caffeine.newBuilder()
                .initialCapacity(40)
                .maximumSize(200)
                .expireAfterWrite(30, TimeUnit.MINUTES)
                .build());

        cacheManager.registerCustomCache("tcTravelCostCoefficientCache", Caffeine.newBuilder()
                .initialCapacity(40)
                .maximumSize(200)
                .expireAfterWrite(30, TimeUnit.MINUTES)
                .build());

        cacheManager.registerCustomCache("tcCountrySafetyRatingCoefficientCache", Caffeine.newBuilder()
                .initialCapacity(40)
                .maximumSize(200)
                .expireAfterWrite(30, TimeUnit.MINUTES)
                .build());

        cacheManager.registerCustomCache("tcAgeCoefficientCache", Caffeine.newBuilder()
                .initialCapacity(40)
                .maximumSize(200)
                .expireAfterWrite(30, TimeUnit.MINUTES)
                .build());

        cacheManager.registerCustomCache("tcAgeCoefficientCache", Caffeine.newBuilder()
                .initialCapacity(40)
                .maximumSize(200)
                .expireAfterWrite(30, TimeUnit.MINUTES)
                .build());

        cacheManager.registerCustomCache("countryDefaultDayRateCache", Caffeine.newBuilder()
                .initialCapacity(40)
                .maximumSize(200)
                .expireAfterWrite(30, TimeUnit.MINUTES)
                .build());

        cacheManager.registerCustomCache("medicalRiskLimitLevelCache", Caffeine.newBuilder()
                .initialCapacity(40)
                .maximumSize(200)
                .expireAfterWrite(30, TimeUnit.MINUTES)
                .build());

        cacheManager.registerCustomCache("tmAgeCoefficientCache", Caffeine.newBuilder()
                .initialCapacity(40)
                .maximumSize(200)
                .expireAfterWrite(30, TimeUnit.MINUTES)
                .build());

        cacheManager.registerCustomCache("personEntityCache", Caffeine.newBuilder()
                .initialCapacity(100)
                .maximumSize(1000)
                .expireAfterAccess(30, TimeUnit.MINUTES)
                .build());

        return cacheManager;
    }

}
